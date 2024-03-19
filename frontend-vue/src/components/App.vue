<template>
  <div>
    <CoinDisplay :tenCoins="tenCoins" />
    <StockDisplay :tenStocks="tenStocks" />
  </div>
</template>

<script>
import { ref } from 'vue';
import { tenCoins } from '../components/coin/Coin.js';
import { tenStocks } from '../components/stock/Stock.js';
import CoinDisplay from '../components/coin/CoinDisplay.vue';
import StockDisplay from '../components/stock/StockDisplay.vue';
import '../components/coin/Coin.css';
import { fa } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon as FaIcon } from '@fortawesome/vue-fontawesome';

export default {
  setup() {
    const searchQuery = ref('');

    return { tenCoins,tenStocks, searchQuery };
  },
  components: {
    CoinDisplay,
    StockDisplay,
    fa,
    FaIcon
  }
};
</script>
