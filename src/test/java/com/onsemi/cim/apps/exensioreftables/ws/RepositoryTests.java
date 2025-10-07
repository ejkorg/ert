package com.onsemi.cim.apps.exensioreftables.ws;

import com.onsemi.cim.apps.exensioreftables.ws.entity.application.*;
import com.onsemi.cim.apps.exensioreftables.ws.repository.application.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

// These tests are not working for now
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@ActiveProfiles("test")
public class RepositoryTests {

    @Autowired
    private OnFabConfRepository onFabConfRepository;

    @Autowired
    private OnLotRepository onLotRepository;

    @Autowired
    private OnProdRepository onProdRepository;

    @Autowired
    private OnScribeRepository onScribeRepository;

    @Autowired
    private OnWmapRepository onWmapRepository;

    private OnFabConf onFabConf;
    private OnLot onLot;
    private OnProd onProd;
    private OnScribe onScribe;
    private OnWmap onWmap;
    private OnSlice onSlice;

    @Before
    public void initializeDataObjects(){
        onFabConf = new OnFabConf();
        onFabConf.setDataType("DataType");
        onFabConf.setFoundryFab("FoundryFab");
        onFabConf.setId(1L);
        onFabConf.setLtmUrl("LtmUrl");
        onFabConf.setWmcUrl("WmcUrl");
        onFabConf.setVid2ScribeUrl("Vid2ScribeUrl");
        onFabConf.setScribe2VidUrl("Scribe2VidUrl");

        onLot = new OnLot();
        onLot.setAlternateLot("AlternateLot");
        onLot.setAlternateProduct("AlternateProduct");
        onLot.setFab("Fab");
        onLot.setInsertTime(new Date(new java.util.Date().getTime()));
        onLot.setLot("Lot");
        onLot.setLotClass("LotClass");
        onLot.setLotOwner("LotOwner");
        onLot.setParentLot("ParentLot");
        onLot.setParentProduct("ParentProduct");
        onLot.setProduct("Product");
        onLot.setProductCode("ProductCode");
        onLot.setStatus(Status.FOUND);
        onLot.setSubconLot("SunconLot");
        onLot.setSubconProduct("SubconProduct");

        onProd = new OnProd();
        onProd.setAfm("Afm");
        onProd.setDieHeight(1.5);
        onProd.setDieUnits("DieUnits");
        onProd.setDieWidth(2.5);
        onProd.setFab("Fab");
        onProd.setFabDesc("FabDesc");
        onProd.setFamily("Family");
        onProd.setGdpw(3L);
        onProd.setId(1L);
        onProd.setInsertTime(new Date(new java.util.Date().getTime()));
        onProd.setItemType("ItemType");
        onProd.setMaskSet("MaskSet");
        onProd.setPackage("Package");
        onProd.setProcess("Process");
        onProd.setProduct("Product");
        onProd.setPti4("Pti4");
        onProd.setStatus(Status.FOUND);
        onProd.setTechnology("Technology");
        onProd.setWfSize(4.5);
        onProd.setWfUnits("WfUnits");

        onScribe = new OnScribe();
        onScribe.setId(1L);
        onScribe.setInsertTime(new Date(new java.util.Date().getTime()));
        onScribe.setLot("Lot");
        onScribe.setScribeId("ScribeId");
        onScribe.setStatus(Status.FOUND);
        onScribe.setWaferId("WaferId");
        onScribe.setWaferNum(1);

        onWmap = new OnWmap();
        onWmap.setCenterX(1.5);
        onWmap.setCenterY(2.5);
        onWmap.setCfgId(3L);
        onWmap.setComments("Comments");
        onWmap.setConfirmed("Confirmed");
        onWmap.setConfirmTime(new Date(new java.util.Date().getTime()));
        onWmap.setDeviceCount(4L);
        onWmap.setDieHeight(5.5);
        onWmap.setDieWidth(6.5);
        onWmap.setFlat("Flat");
        onWmap.setFlatType("FlatType");
        onWmap.setId(1L);
        onWmap.setInputFile("InputFile");
        onWmap.setInsertTime(new Date(new java.util.Date().getTime()));
        onWmap.setLocation("Location");
        onWmap.setPositiveX("PositiveX");
        onWmap.setPositiveY("PositiveY");
        onWmap.setProduct("Product");
        onWmap.setRefDieInitDt(new Date(new java.util.Date().getTime()));
        onWmap.setRefDieX(7);
        onWmap.setRefDieY(8);
        onWmap.setReticleColOffset(9L);
        onWmap.setReticleCols(10L);
        onWmap.setReticleRowOffset(11L);
        onWmap.setReticleRows(12L);
        onWmap.setStatus(Status.FOUND);
        onWmap.setWfSize(13.5);
        onWmap.setWfUnits("WfUnits");
        onWmap.setWmcDevice("WmcDevice");


        onSlice = new OnSlice();
        onSlice.setSlice("Slice");
        onSlice.setPuckId("PuckId");
        onSlice.setRunId("RunId");
        onSlice.setSliceSourceLot("SliceSourceLot");
        onSlice.setStartLot("StartLot");
        onSlice.setFabSourceLot("FabSourceLot");
        onSlice.setSliceStartTime(new Date(new java.util.Date().getTime()));
        onSlice.setSlicePartname("SlicePartname");
        onSlice.setSliceLottype("SliceLottype");
        onSlice.setSliceSuplierid("SliceSuplierId");
        onSlice.setPuckHeight(1L);
        onSlice.setInsertTime(new Date(new java.util.Date().getTime()));


    }

    @Test
    public void shouldSaveOnFabConfToMemberDB() {
        OnFabConf savedOnFabConf = onFabConfRepository.save(onFabConf);
        Optional<OnFabConf> onFabConfFromDb = onFabConfRepository.findById(savedOnFabConf.getId());
        assertTrue(onFabConfFromDb.isPresent());
    }

    @Test
    public void shouldSaveOnLotToMemberDB() {
        OnLot savedOnLot = onLotRepository.save(onLot);
        Optional<OnLot> onLotFromDb = onLotRepository.findById(savedOnLot.getId());
        assertTrue(onLotFromDb.isPresent());
    }

    @Test
    public void shouldSaveOnProdToMemberDB() {
        OnProd savedOnProd = onProdRepository.save(onProd);
        Optional<OnProd> onProdFromDb = onProdRepository.findById(savedOnProd.getId());
        assertTrue(onProdFromDb.isPresent());
    }

    @Test
    public void shouldSaveOnScribeToMemberDB() {
        OnScribe savedOnScribe = onScribeRepository.save(onScribe);
        Optional<OnScribe> onScribeFromDb = onScribeRepository.findById(savedOnScribe.getId());
        assertTrue(onScribeFromDb.isPresent());
    }

    @Test
    public void shouldSaveOnWmapToMemberDB() {
        OnWmap savedOnWmap = onWmapRepository.save(onWmap);
        Optional<OnWmap> onWmapFromDb = onWmapRepository.findById(savedOnWmap.getId());
        assertTrue(onWmapFromDb.isPresent());
    }

}