
<template>
  <div>
    <!-- Marquee Section -->
    <div class="marquee w-full h-12 overflow-hidden position-relative">
      <ul class="marquee-content h-full flex" ref="mq">
        <li v-for="stock in tenStocks" :key="stock.name" class="flex justify-center items-center flex-shrink-0 text-white transform scale-75 lg:scale-100">
          <div class="flex justify-between w-3/4">
            <div class="flex items-center">
              <img :src="stock.image" alt="stock logo" class="w-4 h-4 lg:w-6 lg:h-6 rounded-full mr-4 object-cover" />
              <div class="hidden lg:block">
                <p class="font-bold">{{ stock.name }}</p>
                <p class="text-xs uppercase tracking-widest">{{ stock.symbol }}</p>
              </div>
            </div>
            <div>
              <p class="font-bold text-xs lg:text-base flex justify-end ">{{ commaSeparator(stock.current_price) }}</p>
              <p v-if="priceNegative(stock.price_change_percentage_24h)" class="font-bold text-xs text-red-400 flex justify-end items-center ">
                <fa icon="caret-down" class="mr-1" />
                {{ stringTrunc(stock.price_change_percentage_24h, 5) }}%
              </p>
              <p v-else class="font-bold text-xs text-green-400 flex justify-end items-center ">
                <fa icon="caret-up" class="mr-1" />
                {{ stringTrunc(stock.price_change_percentage_24h, 5) }}%
              </p>
            </div>
          </div>
        </li>
      </ul>
    </div>

    <!-- Table Section -->
    <div class="container mx-auto pt-18 px-2">
      <table class="table-fixed cursor-pointer">
        <caption class="table-title font-bold text-gray-700  pb-2">Stock  - Top 10 (Market Cap)</caption>
        <thead>
          <tr class="relative text-left text-gray-600 text-sm">
            <td class="p-2">
              Search:
              <input type="text" placeholder="stock Name ..." class="border border-gray-500 rounded p-2" v-model="search" />
            </td>
          </tr>
          <tr class="text-left bg-gray-100 text-gray-600 text-sm">
            <th class="w-1/4 p-4">Stock Name</th>
            <th class="w-1/4">Market Price(Real Time)</th>
            <th class="w-1/4">Change%(24 Hours)</th>
            <th class="w-1/4 hidden sm:table-cell">Trading Volume</th>
            <th class="w-1/4 hidden sm:table-cell">Market Capitalization</th>
          </tr>
        </thead>
        <tbody class="divide-y">
          <tr v-for="stock in matchedCoins" :key="stock.name" class="text-sm hover:bg-gray-100 transition duration-300">
            <td class="p-4 flex items-center">
              <p class="mr-2">{{ stock.market_cap_rank }}</p>
              <img :src="stock.image" alt="stock logo" class="w-7 h-7 rounded-full mr-1" />
              <p class="font-bold p-1 mr-1">{{ stock.name }}</p>
              <p class="uppercase text-gray-500 hidden sm:table-cell">{{ stock.symbol }}</p>
            </td>
            <td class="font-bold text-gray-600">
              ${{ commaSeparator(stock.current_price) }}
            </td>
            <td class=" font-bold">
              <div v-if="priceNegative(stock.price_change_percentage_24h)" class="text-red-500">
                <fa icon="caret-down" class="mr-1" />{{ stringTrunc(stock.price_change_percentage_24h, 5) }}%
              </div>
              <div v-else class="text-green-500">
                <fa icon="caret-up" class="mr-1" />{{ stringTrunc(stock.price_change_percentage_24h, 5) }}%
              </div>
            </td>
            <td class="hidden sm:table-cell">
              <p style="color:rgb(26, 137, 165)">{{ commaSeparator(stock.total_volume) }} </p>
            </td>
            <td class="pr-10 hidden sm:table-cell">
              <p style="color:rgb(26, 137, 165)">${{ commaSeparator(stock.market_cap) }} </p>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import { ref, computed } from 'vue';

export default {
  props: ['tenStocks'],
  setup(props) {
    const search = ref('');

    // const matchedCoins = computed(() => {
    //   return props.tenStocks.filter((stock) => stock.name.toLowerCase().includes(search.value.toLowerCase()));
    // });

    const commaSeparator = (value) => {
      // Your comma separator logic here
      return value;
    };

    const priceNegative = (value) => {
      // Your price negative logic here
      return value < 0;
    };

    const stringTrunc = (value, length) => {
      if (typeof value !== 'string') {
        return value; // Return value as is if it's not a string
      }
      return value.slice(0, length);
    };

    // return { search, matchedCoins, commaSeparator, priceNegative, stringTrunc };
    return { search, commaSeparator, priceNegative, stringTrunc };

  }
};
</script>
