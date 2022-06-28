/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.util.colecciones_seed;

/**
 *
 * @author madar
 */
public class NodoHuffman {
    
    private char letra;
    private int repeticion;
    NodoHuffman izquierdo;
    NodoHuffman derecho;

    public NodoHuffman(char letra, int repeticion, NodoHuffman izquierdo, NodoHuffman derecho) {
        this.letra = letra;
        this.repeticion = repeticion;
        this.izquierdo = izquierdo;
        this.derecho = derecho;
    }

    
    
    
    public char getLetra() {
        return letra;
    }

    public void setLetra(char letra) {
        this.letra = letra;
    }

    public int getRepeticion() {
        return repeticion;
    }

    public void setRepeticion(int repeticion) {
        this.repeticion = repeticion;
    }

    public NodoHuffman getIzquierdo() {
        return izquierdo;
    }

    public void setIzquierdo(NodoHuffman izquierdo) {
        this.izquierdo = izquierdo;
    }

    public NodoHuffman getDerecho() {
        return derecho;
    }

    public void setDerecho(NodoHuffman derecho) {
        this.derecho = derecho;
    }
    
   
    @Override
    public String toString() {
         if (izquierdo == null && derecho == null) {
            
             return "(" + letra + "," + repeticion + ")";
             
        } else{
            if (izquierdo == null) {
                 return "(" + letra + "," + repeticion + "),(" + "->" + derecho + ")";
            }
            if (derecho == null) {
                 return "(" + letra + "," + repeticion + "),(" + izquierdo  + "->"  + ")";
            }
        }
        
        return "(" + letra + "," + repeticion + "),(" + izquierdo  + "->" + derecho + ")";
        
    }

}
