package com.vtxlab.project.bc_crypto_coingecko.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RedisHelper<T> {

  @Autowired
  private RedisTemplate<String, T> redisTemplate;

  @Autowired
  private ObjectMapper objectMapper;

  public RedisHelper(RedisTemplate<String, T> redisTemplate) {
    this.redisTemplate = redisTemplate;
  }

  public RedisHelper(RedisConnectionFactory factory) {
    RedisTemplate<String, T> template = new RedisTemplate<>();
    template.setConnectionFactory(factory);

    Jackson2JsonRedisSerializer<T> jackson2JsonRedisSerializer =
        new Jackson2JsonRedisSerializer<>((Class<T>) Object.class);
    jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

    StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

    template.setKeySerializer(stringRedisSerializer);
    template.setValueSerializer(jackson2JsonRedisSerializer);
    template.setHashKeySerializer(stringRedisSerializer);
    template.setHashValueSerializer(jackson2JsonRedisSerializer);
    template.afterPropertiesSet();

    this.redisTemplate = template;
  }

  public RedisHelper(RedisConnectionFactory factory,
      ObjectMapper redisObjectMapper) {
    this.redisTemplate = template(factory, redisObjectMapper);
  }

  public static <T> RedisTemplate<String, T> template(
      RedisConnectionFactory factory, ObjectMapper redisObjectMapper) {
    RedisTemplate<String, T> redisTemplate = new RedisTemplate<>();
    redisTemplate.setConnectionFactory(factory);
    redisTemplate.setKeySerializer(RedisSerializer.string());
    redisTemplate.setValueSerializer(RedisSerializer.json());
    redisTemplate.afterPropertiesSet();

    Jackson2JsonRedisSerializer<T> serializer =
        new Jackson2JsonRedisSerializer<>(redisObjectMapper, (Class<T>) Object.class);
    redisTemplate.setValueSerializer(serializer);

    return redisTemplate;
  }

  public boolean expire(String key, long time) {
    try {
      if (time > 0) {
        redisTemplate.expire(key, time, TimeUnit.SECONDS);
      }
      return true;
    } catch (Exception e) {
      log.error("Error setting expire for key: {}", key, e);
      return false;
    }
  }

  public long getExpire(String key) {
    return redisTemplate.getExpire(key, TimeUnit.SECONDS);
  }

  public boolean hasKey(String key) {
    try {
      return redisTemplate.hasKey(key);
    } catch (Exception e) {
      log.error("Error checking key existence for key: {}", key, e);
      return false;
    }
  }

  @SuppressWarnings("unchecked")
  public void del(String... key) {
    if (key != null && key.length > 0) {
      if (key.length == 1) {
        redisTemplate.delete(key[0]);
      } else {
        redisTemplate.delete(Arrays.asList(key));
      }
    }
  }

  public T get(String key) {
    return key == null ? null : redisTemplate.opsForValue().get(key);
  }

  public T get(String key, Class<T> clazz) throws JsonProcessingException {
    String value = (String) this.redisTemplate.opsForValue().get(key);
    return objectMapper.readValue(value, clazz);
  }

  public boolean set(String key, T value) {
    try {
      String json = objectMapper.writeValueAsString(value);
      redisTemplate.opsForValue().set(key, value);
      return true;
    } catch (Exception e) {
      log.error("Error setting value for key: {}", key, e);
      return false;
    }
  }

  public boolean set(String key, T value, long time) {
    try {
      if (time > 0) {
        redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
      } else {
        set(key, value);
      }
      return true;
    } catch (Exception e) {
      log.error("Error setting value with expiration for key: {}", key, e);
      return false;
    }
  }

  public long incr(String key, long delta) {
    if (delta < 0) {
      throw new RuntimeException("Increment factor must be greater than 0");
    }
    return redisTemplate.opsForValue().increment(key, delta);
  }

  public long decr(String key, long delta) {
    if (delta < 0) {
      throw new RuntimeException("Decrement factor must be greater than 0");
    }
    return redisTemplate.opsForValue().increment(key, -delta);
  }

  // Other methods...

  public <T> Map<String, T> hgetall(String key, Class<T> clazz) {
    try {
      Map<Object, Object> hashEntries = redisTemplate.opsForHash().entries(key);
      Map<String, T> resultMap = new LinkedHashMap<>();
      for (Map.Entry<Object, Object> entry : hashEntries.entrySet()) {
        String hashKey = (String) entry.getKey();
        String jsonValue = objectMapper.writeValueAsString(entry.getValue());
        T value = objectMapper.readValue(jsonValue, clazz);
        resultMap.put(hashKey, value);
      }
      return resultMap;
    } catch (Exception e) {
      log.error("Error getting all values from Redis for key: {}", key, e);
      return Collections.emptyMap();
    }
  }

  // Other methods...

  public <T> Set<T> sGet(String key, Class<T> clazz) {
    try {
      Set<T> members = (Set<T>) redisTemplate.opsForSet().members(key);
      Set<T> resultSet = new HashSet<>();
      for (T member : members) {
        String jsonValue = objectMapper.writeValueAsString(member);
        T value = objectMapper.readValue(jsonValue, clazz);
        resultSet.add(value);
      }
      return resultSet;
    } catch (Exception e) {
      log.error("Error getting set values from Redis for key: {}", key, e);
      return Collections.emptySet();
    }
  }

  // Other methods...

  public boolean sSet(String key, T... values) {
    try {
      return redisTemplate.opsForSet().add(key, values) > 0;
    } catch (Exception e) {
      log.error("Error setting set values in Redis for key: {}", key, e);
      return false;
    }
  }

  // Other methods...

  public <T> List<T> lGet(String key, long start, long end, Class<T> clazz) {
    try {
      List<T> range =
          (List<T>) redisTemplate.opsForList().range(key, start, end);
      return range.stream().<T>map(obj -> deserializeObject(obj, clazz))
          .collect(Collectors.toList());
    } catch (Exception e) {
      log.error("Error getting list values from Redis for key: {}", key, e);
      return Collections.emptyList();
    }
  }

  private <S> S deserializeObject(Object obj, Class<S> clazz) {
    try {
      String jsonValue = objectMapper.writeValueAsString(obj);
      return objectMapper.readValue(jsonValue, clazz);
    } catch (Exception e) {
      log.error("Error deserializing object from Redis: {}", obj, e);
      return null;
    }
  }

  // Other methods...

  public boolean lSet(String key, T value) {
    try {
      redisTemplate.opsForList().rightPush(key, value);
      return true;
    } catch (Exception e) {
      log.error("Error setting list value for key: {}", key, e);
      return false;
    }
  }

  public Long lSet2(String key, T value) {
    return redisTemplate.opsForList().leftPush(key, value);
  }

  // Other methods...

  public boolean lSet(String key, List<T> value) {
    try {
      redisTemplate.opsForList().rightPushAll(key, value);
      return true;
    } catch (Exception e) {
      log.error("Error setting list values for key: {}", key, e);
      return false;
    }
  }

  // Other methods...

  public boolean lUpdateIndex(String key, long index, T value) {
    try {
      redisTemplate.opsForList().set(key, index, value);
      return true;
    } catch (Exception e) {
      log.error("Error updating list value for key: {}", key, e);
      return false;
    }
  }

  // Other methods...

  public long lRemove(String key, long count, T value) {
    try {
      Long remove = redisTemplate.opsForList().remove(key, count, value);
      return remove;
    } catch (Exception e) {
      log.error("Error removing list value for key: {}", key, e);
      return 0;
    }
  }
}
