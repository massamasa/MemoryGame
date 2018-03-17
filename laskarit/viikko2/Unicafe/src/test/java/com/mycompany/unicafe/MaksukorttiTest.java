package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void luodullaKortillaOikeaSaldo() {
        assertEquals(10, kortti.saldo());
    }
    
    @Test
    public void rahanLataaminenKasvattaaSaldoaOikein() {
        kortti.lataaRahaa(5);
        assertEquals(15, kortti.saldo());
    }
    
    @Test
    public void saldoVaheneeOikeinJosRahaaTarpeeksi() {
        kortti.otaRahaa(10);
        assertEquals(0, kortti.saldo());
    }
    
    @Test
    public void palauttaaTrueJosRahatRiittaa() {
        Boolean riittaako = kortti.otaRahaa(10);
        assertEquals(true, riittaako);
    }
    
    @Test
    public void palauttaaFalseJosRahatEivatRiita() {
        Boolean riittaako = kortti.otaRahaa(11);
        assertEquals(false, riittaako);
    }
    
    @Test
    public void onkoToStringMuotoiltuOikein() {
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
}
