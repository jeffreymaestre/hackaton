package com.hackaton.jeffrey.service;

import com.hackaton.jeffrey.entity.Estadistica;
import com.hackaton.jeffrey.repository.IEstadisticaDao;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

class AdnServiceTest {

    @Mock
    private IEstadisticaDao estadisticaDao;

    @InjectMocks
    private AdnService estadisticaService;

    @Test
    public void testIsMutantWithValidDNA() {
        AdnService detector = new AdnService();
        String[] dna = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
        boolean result = detector.isMutant(dna);
        Assert.assertTrue(result);
    }

    @Test
    public void testIsMutantWithInvalidDNA() {
        AdnService detector = new AdnService();
        String[] dna = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTX"};
        boolean result = detector.isMutant(dna);
        Assert.assertFalse(result);
    }
}