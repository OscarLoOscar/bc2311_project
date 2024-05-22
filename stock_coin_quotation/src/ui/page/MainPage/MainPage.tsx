import { useState } from 'react';
import Container from "@mui/material/Container";
import BottomWrapper from "../../component/BottomWrapper/BottomWrapper";
import ItemTab from "../../component/ItemTab/ItemTab";
import NavBar from "../../component/NavBar/NavBar";
import TopContainer from "../../component/TopContainer/TopContainer";
import MainWrapper from "../../component/adv/MainWrapper";
import Footer from "../../component/Footer/Footer";
import LogoImage from "../../component/LogoImage/LogoImage";
import ComplexButton from "../../component/ComplexButton/ComplexButton";
import CombinedList from "../../component/CombinedList/CombinedList";
import CombinedData from "../../component/CombinedData/CombinedData";

export default function MainPage() {
  const [selectedItem, setSelectedItem] = useState<string | null>(null);
  const [itemType, setItemType] = useState<string | null>(null);

  const handleItemSelect = (item: string, itemType: string) => {
    setSelectedItem(item);
    setItemType(itemType);
  };

  return (
    <>
      {/* <title>Main Page</title>
      <TopContainer />
      <LogoImage />
      <ItemTab />
      <NavBar />
      <BottomWrapper />
      <Container >
        <MainWrapper imgs={photos} />
      </Container>
      <Container
        sx={{ display: 'flex', justifyContent: 'center', marginBottom: '5%' }}
      >
        {/* <Parallax /> */}
        {/* <ComplexButton />
      </Container>
      <Footer /> */}
      <CombinedList onItemSelect={handleItemSelect} />
      {selectedItem && itemType && (
        <CombinedData item={selectedItem} itemType={itemType} />
      )}
    </>
  );
}
