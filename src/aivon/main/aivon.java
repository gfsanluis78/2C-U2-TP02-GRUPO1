/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aivon.main;

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
        // TODO code application logic here
        
        Conexion con = new Conexion();
        CampañaData cd = new CampañaData(con);
        ProductoData prd = new ProductoData(con);
        DetallePedidoData dp = new DetallePedidoData(con);
        PedidoData ped = new PedidoData(con);
        RevendedorData rd = new RevendedorData(con);

    
    
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
