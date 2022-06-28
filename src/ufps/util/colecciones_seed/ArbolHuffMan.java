/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.util.colecciones_seed;

/**
 *
 * @author madarme
 */
public class ArbolHuffMan {

    private NodoHuffman raiz;
    private String cadena;

    public ArbolHuffMan() {
    }

    public ArbolHuffMan(String cadena) {
        this.cadena = cadena;
    }

    /**
     * Dada una cadena que se paso como atributo de la clase, se crea el árbol y
     * retorna la secuencia de pasos. Ejemplo: cadena= SISTEMAS Retornaría: Paso
     * Frecuencia: (S,3)->(I,1)->(T,1)->(E,1)->(M,1)->(A,1)->null Ordenar
     * Frecuencia: (I,1)->(T,1)->(E,1)->(M,1)->(A,1)->(S,3)->null Paso 1:
     * (E,1)->(M,1)->(A,1)->((null,2),((I,1)->(T,1)))->(S,3)->null Paso 2:
     * (A,1)->(( null,2)->((I,1)->(T,1))->((2, null), (E,1)->(M,1))->(S,3)->
     * null Paso 3: ((null, 2), (E,1)->(M,1))->(S,3)->((null,3),(A,1)->((
     * null,2)->((I,1)->(T,1))-> null Paso 4: ((null,3),(A,1)->((
     * null,2)->((I,1)->(T,1))-> ((null,5),((null, 2),
     * ((E,1)->(M,1))->(S,3))-->null Paso 5: (null, 8),((null,3),(A,1)->((
     * null,2)->((I,1)->(T,1))-> ((null,5),((null, 2),
     * ((E,1)->(M,1))->(S,3))-->null
     *
     * @return un String con la secuencia de pasos.
     */
    public String crear() {

        ListaS<NodoHuffman> lista = this.combina();

        String cad = "Paso Frecuencia:" + lista + "\nOrdenar Frecuencia: ";
        ListaS<NodoHuffman> ordenados = this.ordena(lista);
        cad += ordenados + "\n";
        cad += this.arbolx(ordenados, "", 1);

        return this.decorator(cad);

    }

    public String decorator(String cad) {
        String cadaux = "";
        for (int i = 0; i < cad.length(); i++) {

            if (cad.charAt(i) == (char) 12) {
                cadaux += "null";

            } else {
                cadaux += cad.charAt(i);
            }
        }

        return cadaux;
    }

    public void imprimirx(ListaS<NodoHuffman> xx) {

        for (NodoHuffman nodoHuffman : xx) {

            System.out.println("" + nodoHuffman.getLetra() + "," + nodoHuffman.getRepeticion());
        }

    }

    public ListaS<NodoHuffman> combina() {

        ListaS<Character> xletras = this.letrasinrepetir();
        ListaS<Integer> xcantidad = this.cantidadrepetir(xletras);
//        System.out.println("sdletras"+xletras.toString());
//        System.out.println("sdcantidades"+xcantidad.toString());
        ListaS<NodoHuffman> lista = new ListaS<>();

        for (int i = 0; i < xletras.getTamanio(); i++) {

            NodoHuffman a = new NodoHuffman(xletras.get(i), xcantidad.get(i), null, null);

            lista.insertarAlFinal(a);

        }

        return lista;
    }

    public String arbolx(ListaS<NodoHuffman> listaNodos, String pasos, int contpaso) {
        /*Si es Menor encontro la raiz*/
        if (listaNodos.getTamanio() < 2) {
            this.raiz = listaNodos.get(0);
            return pasos;
        }
        ListaS<NodoHuffman> aux = new ListaS();
        int zero = 0;
        int one = 1;
        int suma;
        NodoHuffman nodoD = listaNodos.get(one);
        NodoHuffman nodoI = listaNodos.get(zero);
        suma = nodoD.getRepeticion();
        suma = suma + nodoI.getRepeticion();
        NodoHuffman nodoR = new NodoHuffman((char) 12, suma, nodoI, nodoD);

        for (int i = 2; i < listaNodos.getTamanio(); i++) {
            aux.insertarAlFinal(listaNodos.get(i));
        }

        aux.insertarAlFinal(nodoR);
        aux = this.ordena(aux);
        pasos += "Paso " + contpaso + ": " + aux + "\n";

        return arbolx(aux, pasos, contpaso + 1);
    }

    public ListaS ordena(ListaS<NodoHuffman> lec) {

        NodoHuffman aux;
        int i, j;
        for (i = 0; i < lec.getTamanio() - 1; i++) {
            for (j = 0; j < lec.getTamanio() - i - 1; j++) {
                if (lec.get(j + 1).getRepeticion() < lec.get(j).getRepeticion()) {
                    aux = lec.get(j + 1);
                    lec.set(j + 1, lec.get(j));
                    lec.set(j, aux);
                }
            }
        }

        return lec;
    }

//    public TablaHash crepite(){
//        TablaHash<Character,Integer> c =  new TablaHash<>();
//      
//        
//        for (int i = 0; i < cadena.length(); i++) {
//        
//            if(c.esta(cadena.charAt(i))){
//               
//                
//               int a=(int) c.getObjeto(cadena.charAt(i));
//                
//                c.insertar(cadena.charAt(i),2);
//            
//                
//               
//            }else{
//                
//                c.insertar(cadena.charAt(i), 1);
//            }
//        }
//        
//        
//        
//        return c;
//    }
    public ListaS letrasinrepetir() {
        ListaS<Character> c = new ListaS<>();

        for (int i = 0; i < cadena.length(); i++) {

            if (!c.esta(cadena.charAt(i))) {
                Character x = cadena.charAt(i);
                c.insertarAlFinal(cadena.charAt(i));

            }
        }
        return c;
    }

    public ListaS cantidadrepetir(ListaS x) {
        int numero = 0;
        ListaS<Integer> c = new ListaS<>();
        for (int i = 0; i < x.getTamanio(); i++) {
            numero = numerovec((Character) x.get(i));
            c.insertarAlFinal(numero);
        }
        return c;
    }

    public int numerovec(Character x) {
        int cont = 0;
        for (int i = 0; i < cadena.length(); i++) {
            if (x == cadena.charAt(i)) {
                cont++;
            }
        }
        return cont;
    }

    /**
     * Obtiene en una cadena todas las ramas del árbol. Ejemplo: Si cadena=
     * "SISTEMAS". El String generado sería: Rama 1: (null, 8)- (null,3) -(A,1)
     * Rama 2: (null, 8)-(null,3)-(null,2)-(I,1) Rama 3: (null,
     * 8)-(null,3)-(null,2)-(T,1) Rama 4: (null, 8)-(null,5)-(null,2)-(E,1) Rama
     * 5: (null, 8)-(null,5)-(null,2)-(M,1) Rama 6: (null, 8)-(null,5)-(S,3)
     *
     * @return un String con las ramas del árbol generado
     */
    public String getRamas() {
        return this.decorator(decoratorRamas(raiz));
    }

    public String decoratorRamas(NodoHuffman nodo) {
        String cad = "";
        ListaS listalr = letrasinrepetir();

        for (int i = 0; i < listalr.getTamanio(); i++) {
            cad += "Rama " + (i + 1) + ": ";
            char letra = (char) listalr.get(i);
            IteratorLS camino = (IteratorLS) getAllRamas(raiz, letra).iterator();
            while (camino.hasNext()) {
                NodoHuffman nodoh = (NodoHuffman) camino.next();
                cad += "(" + nodoh.getLetra() + "," + nodoh.getRepeticion() + ")" + "->";

            }
            cad += "\n";
        }
        return cad;
    }

    public ListaS getAllRamas(NodoHuffman nodoh, char data) {
        if (nodoh == null) {
            return new ListaS();
        }
        if (nodoh.getLetra() == data) {
            ListaS list = new ListaS();
            list.insertarAlInicio(nodoh);
            return list;
        }

        ListaS lista = getAllRamas(nodoh.izquierdo, data);
        if (lista.getTamanio() > 0) {
            lista.insertarAlInicio(nodoh);
            return lista;
        }

        ListaS xlist = getAllRamas(nodoh.derecho, data);
        if (xlist.getTamanio() > 0) {
            xlist.insertarAlInicio(nodoh);
            return xlist;
        }
        return new ListaS();

    }

    /**
     * Obtiene la codificaciòn representada en el árbol con la cadena que se
     * pasa como argumento Ejemplo: Si cadena="SISTEMAS" El String generado
     * sería: S : 11 I : 010 T : 011 E : 100 M : 101 A : 00 SISTEMAS:
     * 11010110111010011
     *
     * @return un String con la codificación de la palabra
     */
    public String getCodificación() {
        String cad = "";
        cad += "***************************************************************** \n";
        for (int i = 0; i < this.letrasinrepetir().getTamanio(); i++) {

            cad += letrasinrepetir().get(i) + ":" + codigoletra((char) letrasinrepetir().get(i)) + "\n";
        }
        cad += "***************************************************************** \n";
        cad += "\nCadena " + cadena + " : ";

        for (int i = 0; i < cadena.length(); i++) {
            cad += codigoletra(cadena.charAt(i));
        }
        cad += "\n***************************************************************** \n";
        return cad;
    }

    public String codigoletra(char letra) {
        ListaS binario = this.obtenerB(raiz, letra);
        String cad = "";
        for (int i = 0; i < binario.getTamanio() - 1; i++) {
            cad += binario.get(i);
        }
        return cad;
    }

    public ListaS obtenerB(NodoHuffman nodo, char c) {
        if (nodo == null) {
            return new ListaS();
        }
        if (nodo.getLetra() == c) {
            ListaS listaencontro = new ListaS();
            listaencontro.insertarAlFinal(nodo.getLetra());
            return listaencontro;
        }

        ListaS listaIzquierda = obtenerB(nodo.izquierdo, c);
        if (listaIzquierda.getTamanio() > 0) {
            listaIzquierda.insertarAlInicio(0);
            return listaIzquierda;
        }

        ListaS listaDerecha = obtenerB(nodo.derecho, c);
        if (listaDerecha.getTamanio() > 0) {
            listaDerecha.insertarAlInicio(1);
            return listaDerecha;
        }

        return new ListaS();
    }
}
