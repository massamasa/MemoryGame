package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class KassapaateTest {

    Kassapaate paate;
    int aluksiKassassa;

    public KassapaateTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        paate = new Kassapaate();
        aluksiKassassa = paate.kassassaRahaa();
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void aluksiRahaaTuhatEuroa() {
        assertEquals(100000, paate.kassassaRahaa());
    }

    @Test
    public void aluksiMyytyjaEdullisiaLounaitaOnNolla() {
        assertEquals(0, paate.edullisiaLounaitaMyyty());
    }

    @Test
    public void aluksiMyytyjaMaukkaitaLounaitaOnNolla() {
        assertEquals(0, paate.maukkaitaLounaitaMyyty());
    }

    @Test
    public void rahaVaihtuuOikeinEdullinen() {
        int vaihtoraha = paate.syoEdullisesti(250);
        assertEquals(10, vaihtoraha);
        assertEquals(aluksiKassassa + 240, paate.kassassaRahaa());
        assertEquals(paate.edullisiaLounaitaMyyty(), 1);
    }

    @Test
    public void rahaVaihtuuOikeinMaukas() {
        int vaihtoraha = paate.syoMaukkaasti(410);
        assertEquals(10, vaihtoraha);
        assertEquals(aluksiKassassa + 400, paate.kassassaRahaa());
        assertEquals(paate.maukkaitaLounaitaMyyty(), 1);
    }

    @Test
    public void liianPieniMaksuEiMeneLapiJaRahatPalautetaanEdullinen() {
        int vaihtoraha = paate.syoEdullisesti(239);
        assertEquals(239, vaihtoraha);
        assertEquals(aluksiKassassa, paate.kassassaRahaa());
        assertEquals(paate.edullisiaLounaitaMyyty(), 0);
    }

    @Test
    public void liianPieniMaksuEiMeneLapiJaRahatPalautetaanMaukas() {
        int vaihtoraha = paate.syoMaukkaasti(399);
        assertEquals(399, vaihtoraha);
        assertEquals(aluksiKassassa, paate.kassassaRahaa());
        assertEquals(paate.maukkaitaLounaitaMyyty(), 0);
    }

    @Test
    public void josRahatRiittaaKortillaEdulliseen() {
        Maksukortti kortti = new Maksukortti(240);
        Boolean riittiko = paate.syoEdullisesti(kortti);
        assertEquals(true, riittiko);
        assertEquals(0, kortti.saldo());
        assertEquals(paate.edullisiaLounaitaMyyty(), 1);
    }

    @Test
    public void josRahatRiittaaKortillaMaukkaaseen() {
        Maksukortti kortti = new Maksukortti(400);
        Boolean riittiko = paate.syoMaukkaasti(kortti);
        assertEquals(true, riittiko);
        assertEquals(0, kortti.saldo());
        assertEquals(paate.maukkaitaLounaitaMyyty(), 1);
    }

    @Test
    public void josRahatEiRiitaKortillaEdulliseen() {
        Maksukortti kortti = new Maksukortti(239);
        Boolean riittiko = paate.syoEdullisesti(kortti);
        assertEquals(false, riittiko);
        assertEquals(239, kortti.saldo());
        assertEquals(paate.edullisiaLounaitaMyyty(), 0);
    }

    @Test
    public void josRahatEiRiitaKortillaMaukkaaseen() {
        Maksukortti kortti = new Maksukortti(399);
        Boolean riittiko = paate.syoMaukkaasti(kortti);
        assertEquals(false, riittiko);
        assertEquals(399, kortti.saldo());
        assertEquals(paate.maukkaitaLounaitaMyyty(), 0);
    }

    @Test
    public void kassanRahamaaraEiMuutuKorttimaksussaEdullinen() {
        Maksukortti kortti = new Maksukortti(9001);
        paate.syoEdullisesti(kortti);
        assertEquals(aluksiKassassa, paate.kassassaRahaa());
    }

    @Test
    public void kassanRahamaaraEiMuutuKorttimaksussaMaukas() {
        Maksukortti kortti = new Maksukortti(9001);
        paate.syoMaukkaasti(kortti);
        assertEquals(aluksiKassassa, paate.kassassaRahaa());
    }

    @Test
    public void paatteeltaVoiLadataSaldoaKortilleJokaKasvattaaKassaa() {
        Maksukortti kortti = new Maksukortti(0);
        paate.lataaRahaaKortille(kortti, 1);
        assertEquals(1, kortti.saldo());
        assertEquals(aluksiKassassa + 1, paate.kassassaRahaa());
    }
    
    @Test
    public void paatteeltaEiVoiLadataNegatiivistaSaldoaKortille() { // tässä kohtaa väittää ettei ole täyttä testikattavuutta vaikka käytännössä on
        Maksukortti kortti = new Maksukortti(1);
        paate.lataaRahaaKortille(kortti, -10);
        assertEquals(1, kortti.saldo());
        assertEquals(aluksiKassassa, paate.kassassaRahaa());
    }

}
