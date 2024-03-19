import { computed, ref } from "vue";
import axios from 'axios'

const coins = ref([]);
const search = ref('');

const retrieveCoins = async () => {
  try {
    // const response = await axios.get("http://localhost:8082/crypto/coingecko/api/v1/market");
    const response = await axios.get("http://localhost:8084/api/v1/market");

    coins.value = response.data;
    console.log(coin.value[0].image);
  } catch (err) {
    console.log(err);
  }
};

setInterval(() => {
  retrieveCoins();
}, 100000);

const tenCoins = computed(() => {
  return coins.value.slice(0, 10);
});

const matchedNames = computed(() => {
  return coins.value.filter((coin) => coin.id.includes(search.value));
});

retrieveCoins();

export { tenCoins, matchedNames, search };
