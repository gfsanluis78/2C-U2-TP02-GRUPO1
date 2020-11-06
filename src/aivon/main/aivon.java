/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aivon.main;

import aivon.entidades.Producto;
import aivon.modelos.CampañaData;
import aivon.modelos.Conexion;
import aivon.modelos.DetallePedidoData;
import aivon.modelos.PedidoData;
import aivon.modelos.ProductoData;
import aivon.modelos.RevendedorData;
import aivon.entidades.*;
import aivon.modelos.*;
import java.util.List;
import aivon.entidades.Campaña;
import aivon.modelos.CampañaData;
import aivon.modelos.Conexion;
import aivon.modelos.DetallePedidoData;
import aivon.modelos.PedidoData;
import aivon.modelos.ProductoData;
import aivon.modelos.RevendedorData;
import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author Ezequiel
 */
public class aivon {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Conexion con = new Conexion();
        CampañaData cd = new CampañaData(con);
        ProductoData prd = new ProductoData(con);
        DetallePedidoData dp = new DetallePedidoData(con);
        PedidoData ped = new PedidoData(con);
        RevendedorData rd = new RevendedorData(con);
        
//##############################################################################
//#################   ALTA PRODUCTO     ########################################
    //Se debe controlar en vistas que los precios no pueden ser nulos/vacios
    //Controlar el largo de los nombres (varchar30)

//    Producto aux=new Producto("Jabon liquido miel","Cabello","120", 250.0, 300.0, 1, true);
//  
//    prd.altaProducto(aux);

//##############################################################################
//#################   BAJA PRODUCTO     ########################################
    //1 Crema --  2 Desodorante -- 3 esmalte -- 4 esponja vegetal -- 5 shampoo
    
    // Baja virtual por iD - POr si no hay stock
//    prd.bajaProducto(5);
    

//##############################################################################
//#################   MODIFICAR PRODUCTO     ################################### 
    //1 Crema --  2 Desodorante -- 3 esmalte -- 4 esponja vegetal -- 5 shampoo
    
//    Producto aux= prd.buscarProducto(5);
//    aux.setEstrellas(5);
//    prd.modificarProducto(aux);


//##############################################################################
//#################   BUSCAR PRODUCTO     ################################### 
    //1 Crema --  2 Desodorante -- 3 esmalte -- 4 esponja vegetal -- 5 shampoo

    //BUSCAR PRODUCTOS POR ID
    
//    Producto aux=prd.buscarProducto(5);
//    System.out.println(aux.toString());
    
    
    //BUSCAR TODOS LOS PRODUCTOS
    
//   for(Producto iterador: prd.buscarProductos()){
//       System.out.println(iterador.toString());
//    }
    
    
    //BUSCAR PRODUCTOS ACTIVOS
    
//    for(Producto iterador: prd.buscarProductosActivos()){
//       System.out.println(iterador.toString());
//    }
    
    //BUSCAR PRODUCTOS INACTIVOS
    
//    for(Producto iterador: prd.buscarProductosInactivos()){
//       System.out.println(iterador.toString());
//    }
    
    //BUSCAR PRODUCTOS POR NOMBRE
    
//    String nombre="CREMA";
//    for(Producto iterador: prd.buscarProductosPorNombre(nombre)){
//       System.out.println(iterador.toString());
//    }
    
    
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
    
      
    
//############################################################################## 
//######################## TODO CAMPAÑA DATA ####################################

// GUARDAR CAMPAÑA - ok
// BORRAR CAMPAÑA X ID - ok
// BORRAR CAMPAÑA X NOMBRE - ok
// MODIFICAR CAMPAÑA - ok
// BUSCAR CAMPAÑA X ID - ok
// BUSCAR CAMPAÑA X NOMBRE - ok
// BUSCAR CAMPAÑA X FECHA DE INICIO - ok
// BUSCAR CAMPAÑA ACTIVA - ok
// BUSCAR LISTA CAMPAÑAS - ok
// BUSCAR LISTA CAMPAÑAS INACTIVAS - ok
    
//############################################################################## 
//########################## GUARDAR CAMPAÑA ####################################    

     /*
       Campaña nov = new Campaña("Febrero", LocalDate.of(2020, 02, 01), 1500, 2000, true );
       cd.guardarCampaña(nov);
       System.out.println(nov);
    
      //Campaña oct = new Campaña("Octubre", LocalDate.of(2020, 10, 01), LocalDate.of(2020, 10, 26), 1500, 2000, false );
      //cd.guardarCampaña(oct);
    */
      
//##############################################################################
//########################## BORRAR CAMPAÑA ####################################    
    
        //BORRAR X ID
        //cd.borrarCampaña(2);
        
        //BORRAR X NOMBRE
        //cd.borrarCampaña("Noviembre");
        
                
//##############################################################################
//########################## MODIFICAR CAMPAÑA #################################           
    
    /*
        //MODIFICAR CAMPAÑA
        Campaña nov = cd.buscarCampaña(8);
        System.out.println(nov);
        nov.setNombre("Mayo");
        System.out.println(nov);
        cd.modificarCampaña(nov);
        System.out.println(nov);
    */ 
    
    /*
        //CERRAR CAMPAÑA
        Campaña nov = cd.buscarCampaña(12);
        System.out.println(nov);
        cd.cerrarCampaña(nov);
        System.out.println(nov);
        
    */
//##############################################################################
//########################### BUSCAR CAMPAÑA ###################################          
   
        //BUSCAR CAMPAÑA X ID
        //System.out.println(cd.buscarCampaña(3));
         
        //BUSCAR CAMPAÑA X NOMBRE
        //System.out.println(cd.buscarCampaña("marzo"));
    
        //BUSCAR CAMPAÑA X FECHA DE INICIO
        //System.out.println(cd.buscarCampañaXFechaInicio(LocalDate.of(2020,11,6)));
        
        //BUSCAR CAMPAÑA ACTIVA
        //System.out.println(cd.buscarCampañaActiva());
    
//##############################################################################
//####################### BUSCAR CAMPAÑAS - LISTAS #############################

        /* VER TODOS LAS CAMPAÑAS
            for (Campaña it: cd.buscarCampañas()) {
                System.out.println(it);
            }
        */    
            
                    
        /* VER TODOS LAS CAMPAÑAS INACTIVAS
            for (Campaña it: cd.buscarCampañasInActivas()) {
                System.out.println(it);
            }    
        */
        
        
        
    }
}
