import { computed, ref } from "vue";
import axios from 'axios'

const stocks = ref([]);
const search = ref('');

const retrieveStocks = async () => {
  try {
    // const response = await axios.get("http://localhost:8082/crypto/coingecko/api/v1/market");
    const response = await axios.get("http://localhost:8084/api/v1/market");

    stocks.value = response.data;
    console.log(stocks.value[0].image);
  } catch (err) {
    console.log(err);
  }
};

setInterval(() => {
  retrieveStocks();
}, 100000);

const tenStocks = computed(() => {
  return stocks.value.slice(0, 10);
});

// const matchedNames = computed(() => {
//   return stocks.value.filter((stock) => stock.id.includes(search.value));
// });

retrieveStocks();

// export { stocks, matchedNames, search };
export { stocks, search };
