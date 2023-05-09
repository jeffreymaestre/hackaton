package com.hackaton.jeffrey.service;

import com.hackaton.jeffrey.entity.Estadistica;
import com.hackaton.jeffrey.repository.IAdnDao;
import com.hackaton.jeffrey.repository.IEstadisticaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdnService {

    @Autowired
    private IAdnDao adnDao;

    @Autowired
    private IEstadisticaDao estadisticaDao;

    private Long contadorMutante = Long.valueOf(0), contadorHumano = Long.valueOf(0);

        public boolean isMutant(String[] dna) {
            // Validar que solo se reciban los caracteres permitidos
            String regex = "^[ATGC]+$";
            for (String cadena : dna) {
                if (!cadena.matches(regex)) {
                    return false;
                }
            }

            int n = dna.length;
            char[][] valores = new char[n][n];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    valores[i][j] = dna[i].charAt(j);
                }
            }

            int contador = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    // busca patrones horizontales
                    if (j < n - 3 && valores[i][j] == valores[i][j+1] && valores[i][j] == valores[i][j+2] && valores[i][j] == valores[i][j+3]) {
                        contador++;
                    }
                    // busca patrones verticales
                    if (i < n - 3 && valores[i][j] == valores[i+1][j] && valores[i][j] == valores[i+2][j] && valores[i][j] == valores[i+3][j]) {
                        contador++;
                    }
                    // busca patrones diagonales hacia la derecha
                    if (i < n - 3 && j < n - 3 && valores[i][j] == valores[i+1][j+1] && valores[i][j] == valores[i+2][j+2] && valores[i][j] == valores[i+3][j+3]) {
                        contador++;
                    }
                    // busca patrones diagonales hacia la izquierda
                    if (i < n - 3 && j >= 3 && valores[i][j] == valores[i+1][j-1] && valores[i][j] == valores[i+2][j-2] && valores[i][j] == valores[i+3][j-3]) {
                        contador++;
                    }
                }
            }

            if(contador > 1){
                contadorMutante++;
            }else{
                contadorHumano++;
            }

            return contador > 1;
        }
    public void guardarEstadistica(){
        Estadistica nuevaEstadistica = estadisticaDao.findById(1L).orElse(null);
        if(nuevaEstadistica != null){
            nuevaEstadistica.setHumano(contadorHumano);
            nuevaEstadistica.setMutante(contadorMutante);
            try {
                nuevaEstadistica.setRatio((double) (nuevaEstadistica.getMutante() / nuevaEstadistica.getHumano()));
            } catch (ArithmeticException e) {
                nuevaEstadistica.setRatio(0.0); // asignar valor predeterminado en caso de que ocurra una excepción
            }
            estadisticaDao.save(nuevaEstadistica);
        } else {
            try {
                if (contadorHumano == 0) {
                    Estadistica estadistica = new Estadistica(contadorMutante, contadorHumano, 0.0); // asignar valor predeterminado en caso de que el denominador sea cero
                    estadisticaDao.save(estadistica);
                } else {
                    Double ratio = Double.valueOf(contadorMutante) / (double) (contadorHumano);
                    Estadistica estadistica = new Estadistica(contadorMutante, contadorHumano, ratio);
                    estadisticaDao.save(estadistica);
                }
            } catch (ArithmeticException e) {
                Estadistica estadistica = new Estadistica(contadorMutante, contadorHumano, 0.0); // asignar valor predeterminado en caso de que ocurra una excepción
                estadisticaDao.save(estadistica);
            }
        }
    }




    @Transactional(readOnly = true)
    public List<Estadistica> findAll() {
        return (List<Estadistica>) estadisticaDao.findAll();
    }

}
