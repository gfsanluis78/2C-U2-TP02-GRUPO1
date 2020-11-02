

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aivon.modelos;

/**
 *
 * @author Ezequiel
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexion {

    private final String base = "aivon";
    private final String url = "jdbc:mysql://localhost:3306/" + base;
    private final String user = "root";
    private final String pass = "";
    private Connection conexion;
 
    public Connection getConnection() {
        if (conexion == null) {
            try {
                Class.forName("org.mariadb.jdbc.Driver");

                conexion = DriverManager.getConnection(url, user, pass);
                JOptionPane.showMessageDialog(null, "Conexion Exitosa");
            } catch (SQLException | ClassNotFoundException e) {
                JOptionPane.showMessageDialog(null, "Error al conectarse");
            }
        }
        return conexion;
    }

}