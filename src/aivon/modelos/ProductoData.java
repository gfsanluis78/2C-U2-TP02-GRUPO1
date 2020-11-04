/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aivon.modelos;

import java.sql.Connection;

/**
 *
 * @author Ezequiel
 */
public class ProductoData {
    private Connection con;

    public ProductoData(Conexion conexion) {
        con = conexion.getConnection();
    }
    //--------------------------ALTA----------------------------------------------//////
    
}
