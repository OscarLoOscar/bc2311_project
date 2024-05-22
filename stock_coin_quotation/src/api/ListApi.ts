import axios from "axios";
import getDevConfig from "../Config/DevConfig";
const baseUrl = getDevConfig.baseUrl;

export const getStockListApi = async () => {
  try {
    const response = await axios.get(`${baseUrl}/admin/stock`);
    return response.data;
  } catch (error) {
    console.error(error);
    throw error;
  }
}
export const getCoinListApi = async () => {
  try {
    const response = await axios.get(`${baseUrl}/admin/coin`);
    return response.data;
  } catch (error) {
    console.error(error);
    throw error;
  }
}