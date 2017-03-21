/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Excepciones.ListaVaciaException;
import Modelo.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author j4v13
 */
public class Mapa {
    
    int[][] mapa;
    
    public void abrirMapa(File file) throws FileNotFoundException, IOException {
        FileInputStream stream = new FileInputStream(file);
        int f = Integer.parseInt(""+(char)stream.read());
        stream.read();
        int c = Integer.parseInt(""+(char)stream.read());
        mapa = new int [f][c];
        int i = 0, j = 0;        
        int aux = stream.read();
        while(aux!=-1) {            
            if(aux == 48 || aux == 49){
                mapa[i][j] = aux-48;
                if (j < c-1) {
                    j++;
                } else {
                    j = 0;
                    i++;
                }
            }
            aux = stream.read();
        }
        stream.close();
    }    
    
    private void buscarBFS(int f,int c) throws ListaVaciaException {
        Cola<Coordenada> q = new Cola();
        q.pushBack(new Coordenada(f,c));
        while(!q.isEmpty()) {
            Coordenada coor = q.front();
            q.pop();
            mapa[coor.getF()][coor.getC()] = -1;
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if(coor.getF()+i >= 0 && coor.getC()+j >= 0 &&
                            coor.getF()+i < mapa.length && coor.getC()+j < mapa[0].length &&
                            mapa[coor.getF()+i][coor.getC()+j] == 1) {
                        q.pushBack(new Coordenada(coor.getF()+i,coor.getC()+j));
                    }
                }
            }
        }
    }
    
    private void buscarDFS(int f,int c) {
        if(f >= 0 && c >= 0 && f < mapa.length && c < mapa[0].length && mapa[f][c] == 1) {
            mapa[f][c] = -1;
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    buscarDFS(f+i,c+j);
                }
            }
        }
    }
    
    public int numeroIslas() {
        int numero = 0;
        for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j < mapa[0].length; j++) {
                if (mapa[i][j] == 1) {
                    numero++;
                    buscarDFS(i,j);
                }
            }
        }
        return numero;
    }
    
    public void imprimir() {
        for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j < mapa[0].length; j++) {
                System.out.printf("%d ",mapa[i][j]);
            }
            System.out.println();
        }
    }
    
    public int get(int i,int j) {
        return mapa[i][j];
    }

    public int[][] getMapa() {
        return mapa;
    }

    public void setMapa(int[][] mapa) {
        this.mapa = mapa;
    }        
    
}
