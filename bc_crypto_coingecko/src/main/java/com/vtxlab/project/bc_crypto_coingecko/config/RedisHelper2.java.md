package com.vtxlab.project.bc_crypto_coingecko.config;

import java.util.concurrent.TimeUnit;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.client.ResourceAccessException;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;

public class RedisHelper2<T> {

  private RedisTemplate<String, T> redisTemplate;

  public RedisHelper2(RedisTemplate<String, T> redisTemplate) {
    this.redisTemplate = redisTemplate;
  }

  public RedisHelper2(RedisConnectionFactory factory, Class<T> clazz) {
    this.redisTemplate = template(factory, clazz);
  }

  public static <T> RedisTemplate<String, T> template(
      RedisConnectionFactory factory, Class<T> clazz) {
    RedisTemplate<String, T> redisTemplate = new RedisTemplate<>();
    redisTemplate.setConnectionFactory(factory);
    redisTemplate.setKeySerializer(RedisSerializer.string());
    Jackson2JsonRedisSerializer<T> serializer =
        new Jackson2JsonRedisSerializer<>(clazz);
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.setVisibility(PropertyAccessor.ALL,
        JsonAutoDetect.Visibility.ANY);
    serializer.setObjectMapper(objectMapper);
    redisTemplate.setValueSerializer(serializer);
    redisTemplate.afterPropertiesSet();
    return redisTemplate;
  }

  public boolean set(String key, T value) {
    try {
      redisTemplate.opsForValue().set(key, value);
      return true;
    } catch (Exception e) {
      throw new ResourceAccessException("Redis unavailable");
    }
  }

  public boolean set(String key, T value, long time) {
    try {
      redisTemplate.opsForValue().set(key, value, time, TimeUnit.MILLISECONDS);
      return true;
    } catch (Exception e) {
      throw new ResourceAccessException(
          "Redis unavailable (set method) msg = " + e.getMessage());
    }
  }

  public T get(String key) {
    try {
      return redisTemplate.opsForValue().get(key);
    } catch (Exception e) {
      throw new ResourceAccessException(
          "Redis unavailable (set method) msg = " + e.getMessage());
    }
  }

  public boolean expire(String key, long time) {
    try {
      if (time > 0) {
        redisTemplate.expire(key, time, TimeUnit.MILLISECONDS);
        return true;
      }
      return false;
    } catch (Exception e) {
      throw new ResourceAccessException("Redis unavailable.");
    }
  }

  public boolean delete(String key) {
    try {
      redisTemplate.delete(key);
      return true;
    } catch (Exception e) {
      throw new ResourceAccessException("Redis unavailable.");
    }
  }

  public static String formatKey(String head, String source) {
    return head.concat(":").concat(source);
  }
}
