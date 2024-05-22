import { useEffect, useState } from 'react';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import { StockDataDto } from '../../../data/Coin/StockDataDTO';
import { CoinDataDto } from '../../../data/Coin/CoinDataDTO';
import * as CombinedDataApi from "../../../api/CombinedDataApi";

interface CombinedDataProps {
  item: string;
  itemType: string;
}

export default function CombinedData({ item, itemType }: CombinedDataProps) {
  const [data, setData] = useState<StockDataDto | CoinDataDto>();

  useEffect(() => {
    const fetchData = async () => {
      try {
        let response;
        if (itemType === 'Stock') {
          response = await CombinedDataApi.getStockDataByIDApi(item);
        } else {
          response = await CombinedDataApi.getCoinDataByIDApi(item);
        }
        setData(response);
      } catch (error) {
        console.error(error);
      }
    };

    fetchData();
  }, [item, itemType]);

  const renderStockData = () => (
    <TableContainer component={Paper}>
      <Table sx={{ minWidth: 650 }} aria-label="simple table">
        <TableHead>
          <TableRow>
            <TableCell>Name</TableCell>
            <TableCell align="right">Ticker</TableCell>
            <TableCell align="right">Current Price</TableCell>
            <TableCell align="right">Change</TableCell>
            <TableCell align="right">Percent Change</TableCell>
            <TableCell align="right">High Price of the Day</TableCell>
            <TableCell align="right">Low Price of the Day</TableCell>
            <TableCell align="right">Open Price of the Day</TableCell>
            <TableCell align="right">Previous Close Price</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          <TableRow>
            <TableCell component="th" scope="row">{data.name}</TableCell>
            <TableCell align="right">{data.ticker}</TableCell>
            <TableCell align="right">{data.Current_Price}</TableCell>
            <TableCell align="right">{data.Change}</TableCell>
            <TableCell align="right">{data.Percent_Change}</TableCell>
            <TableCell align="right">{data.High_Price_Of_The_Day}</TableCell>
            <TableCell align="right">{data.Low_Price_Of_The_Day}</TableCell>
            <TableCell align="right">{data.Open_Price_Of_The_Day}</TableCell>
            <TableCell align="right">{data.Previous_Close_Price}</TableCell>
          </TableRow>
        </TableBody>
      </Table>
    </TableContainer>
  );

  const renderCoinData = () => (
    <TableContainer component={Paper}>
      <Table sx={{ minWidth: 650 }} aria-label="simple table">
        <TableHead>
          <TableRow>
            <TableCell>ID</TableCell>
            <TableCell align="right">Symbol</TableCell>
            <TableCell align="right">Name</TableCell>
            <TableCell align="right">Total Volume</TableCell>
            <TableCell align="right">Current Price</TableCell>
            <TableCell align="right">Market Cap</TableCell>
            <TableCell align="right">Market Cap Rank</TableCell>
            <TableCell align="right">Price Change Percentage 24h</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          <TableRow>
            <TableCell component="th" scope="row">{data.id}</TableCell>
            <TableCell align="right">{data.symbol}</TableCell>
            <TableCell align="right">{data.name}</TableCell>
            <TableCell align="right">{data.total_volume}</TableCell>
            <TableCell align="right">{data.current_price}</TableCell>
            <TableCell align="right">{data.market_cap}</TableCell>
            <TableCell align="right">{data.market_cap_rank}</TableCell>
            <TableCell align="right">{data.price_change_percentage_24h}</TableCell>
          </TableRow>
        </TableBody>
      </Table>
    </TableContainer>
  );

  return (
    <div>
      {data && (itemType === "Stock" ? renderStockData() : renderCoinData())}
    </div>
  );
}