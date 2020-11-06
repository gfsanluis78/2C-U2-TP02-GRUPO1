/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aivon.main;

import aivon.entidades.*;
import aivon.modelos.*;
import java.util.List;

/**
 *
 * @author Ezequiel
 */
public class aivon {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Conexion con = new Conexion();
        CampañaData cd = new CampañaData(con);
        ProductoData prd = new ProductoData(con);
        DetallePedidoData dp = new DetallePedidoData(con);
        PedidoData ped = new PedidoData(con);
        RevendedorData rd = new RevendedorData(con);
        //HistoricoData hd = new HistoricoData(con);
        
//##################### REGISTRO DE MÉTODOS ####################################

    // REVENDEDOR
    // guardarRevendedor() - OK
    // borrarRevendedor() - OK
    // modificarRevendedor() - OK
    // buscarRevendedor() - OK
    
    
        
//##############################################################################        
//################ ALTA REVENDEDOR #############################################

    //String nombre, String apellido, String dni, String tel, String email
//    Revendedor eze = new Revendedor("Ezequiel", "Albornoz", "36227970", "1123917575", "franco.ezequielq@outlook.com");
//    Revendedor mario = new Revendedor("Mario", "Avaca", "30377673", "2664222979", "marioraulavaca@gmail.com");
//    Revendedor genaro = new Revendedor("Genaro", "Farias", "26525567", "2664692950", "gfsanluis78@gmail.com");
    
//    rd.guardarRevendedor(eze);
//    rd.guardarRevendedor(mario);
//    rd.guardarRevendedor(genaro);
//    
//    System.out.println(eze.toString());
//    
//##############################################################################        
//################ BORRAR REVENDEDOR ###########################################    
    
//    rd.borrarRevendedor("36227970");// Ezequiel Albornoz (eze)
//    rd.borrarRevendedor(10); // Genaro Farias
    
//##############################################################################        
//################ ACTUALIZAR REVENDEDOR #######################################
    
//    Revendedor genaro = new Revendedor("Genaro", "Farias", "26525567", "2664692950", "gfsanluis78@gmail.com");
//    rd.guardarRevendedor(genaro);
//    
//    genaro.setActivo(false);
//    
//    rd.modificarRevendedor(genaro);
   
//##############################################################################        
//#################### BUSCAR REVENDEDOR #######################################
    
//        Revendedor alguien = rd.buscarRevendedor("26525567");
//        System.out.println(alguien.toString());
//        Revendedor alguien2 = rd.buscarRevendedor(alguien.getId_revendedor());
//        System.out.println(alguien2.toString());

//##############################################################################        
//#################### LISTA REVENDEDORES ######################################

//    List<Revendedor> revendedores = rd.buscarRevendedores();
//    revendedores.forEach((it) -> {
//        System.out.println(it.toString());
//        });    

//##############################################################################        
//#################### LISTA REVENDEDORES ######################################

//    List<Revendedor> revendedores = rd.buscarRevendedoresActivos();
//    revendedores.forEach((it) -> {
//        System.out.println(it.toString());
//        });
//##############################################################################        
//#################### LISTA REVENDEDORES X NIVEL ##############################

//    List<Revendedor> revendedores = rd.buscarRevendedoresXNivel(4);
//    revendedores.forEach((it) -> {
//        System.out.println(it.toString());
//        });
    }
      
}
