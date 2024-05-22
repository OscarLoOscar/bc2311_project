import { useEffect, useState } from 'react';
import ListItem from '@mui/material/ListItem';
import ListItemButton from '@mui/material/ListItemButton';
import Box from '@mui/material/Box';
import { FixedSizeList } from 'react-window';
import * as ListApi from "../../../api/ListApi";

export default function CombinedList({ onItemSelect }) {
  const [stockList, setStockList] = useState<string[]>([]);
  const [coinList, setCoinList] = useState<string[]>([]);
  const [searchTerm, setSearchTerm] = useState<string>('');
  const [searchResults, setSearchResults] = useState<string[]>([]);

  useEffect(() => {
    const fetchInitialData = async () => {
      try {
        const stockResponse = await ListApi.getStockListApi();
        const coinResponse = await ListApi.getCoinListApi();

        localStorage.setItem('stockList', JSON.stringify(stockResponse.stockList));
        localStorage.setItem('coinList', JSON.stringify(coinResponse.coinList));

        setStockList(stockResponse.stockList);
        setCoinList(coinResponse.coinList.slice(0, 10));
      } catch (error) {
        console.error(error);
      }
    };

    fetchInitialData();
  }, []);

  useEffect(() => {
    if (searchTerm) {
      const storedStockList = JSON.parse(localStorage.getItem('stockList') || '[]');
      const storedCoinList = JSON.parse(localStorage.getItem('coinList') || '[]');

      const filteredStocks = storedStockList.filter((item: string) =>
        item.toUpperCase().includes(searchTerm.toUpperCase())
      );
      const filteredCoins = storedCoinList.filter((item: string) =>
        item.toUpperCase().includes(searchTerm.toUpperCase())
      );

      setSearchResults([...filteredStocks, ...filteredCoins]);
    } else {
      setSearchResults([]);
    }
  }, [searchTerm]);

  const handleClick = (item: string) => {
    const isStock = stockList.some(stock => stock.toUpperCase() === item.toUpperCase());
    onItemSelect(item, isStock ? "Stock" : "Coin");
  };

  const renderResults = () => (
    searchResults.map(item => {
      const isStock = stockList.some(stock => stock.toUpperCase() === item.toUpperCase());
      const itemType = isStock ? "Stock" : "Coin";
      return (
        <ListItem key={item} component="div" disablePadding>
          <ListItemButton onClick={() => handleClick(item)}>
            <div style={{ display: 'flex', justifyContent: 'space-between', width: '100%' }}>
              <span>{item}</span>
              <span style={{ color: 'blue', marginLeft: '10px' }}>{itemType}</span>
            </div>
          </ListItemButton>
        </ListItem>
      );
    })
  );

  const renderRow = ({ index, style }: { index: number; style: React.CSSProperties }) => {
    const item = index < stockList.length ? stockList[index] : coinList[index - stockList.length];
    const itemType = index < stockList.length ? "Stock" : "Coin";
    return (
      <ListItem style={style} key={item} component="div" disablePadding>
        <ListItemButton onClick={() => handleClick(item)}>
          <div style={{ display: 'flex', justifyContent: 'space-between', width: '100%' }}>
            <span>{item}</span>
            <span style={{ color: 'blue', marginLeft: '10px' }}>{itemType}</span>
          </div>
        </ListItemButton>
      </ListItem>
    );
  };

  const totalItemCount = stockList.length + coinList.length;

  return (
    <Box sx={{ width: '20%', float: 'left', bgcolor: 'background.paper' }}>
      <input
        type="text"
        value={searchTerm}
        onChange={(e) => setSearchTerm(e.target.value)}
        placeholder="Search"
      />
      {searchTerm ? (
        <Box>
          {renderResults()}
        </Box>
      ) : (
        <Box></Box>
        // <FixedSizeList
        //   height={1000}
        //   width={215}
        //   itemSize={46}
        //   itemCount={totalItemCount}
        //   overscanCount={5}
        // >
        //   {renderRow}
        // </FixedSizeList>
      )}
    </Box>
  );
}
