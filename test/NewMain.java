
import ufps.util.colecciones_seed.ArbolHuffMan;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author Masamorro
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArbolHuffMan arbolh = new ArbolHuffMan("SISTEMAS");
        System.out.println(arbolh.crear());
        System.out.println(arbolh.getRamas());
        System.out.println(arbolh.getCodificación());
        
    }
    
}
