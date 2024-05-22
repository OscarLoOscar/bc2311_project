import axios from "axios";
import getDevConfig from "../Config/DevConfig";
import { CoinDataDto } from "../data/Coin/CoinDataDTO";
import { StockDataDto } from "../data/Coin/StockDataDTO";
const baseUrl = getDevConfig.baseUrl;

export const getCoinDataApi = async () => {
  try {
    const response = await axios.get<CoinDataDto[]>(`${baseUrl}/api/v1/market`);
    return response.data;
  } catch (error) {
    console.error(error);
    throw error;
  }
}
export const getCoinDataByIDApi = async (symbol: string) => {
  try {
    const response = await axios.get<CoinDataDto[]>(`${baseUrl}/api/v1/coinData?symbol=${symbol}`);
    return response.data;
  } catch (error) {
    console.error(error);
    throw error;
  }
}
export const getStockDataByIDApi = async (symbol: string) => {
  try {
    const response = await axios.get<StockDataDto[]>(`${baseUrl}/api/v1/stock?symbol=${symbol}`);
    return response.data;
  } catch (error) {
    console.error(error);
    throw error;
  }
}