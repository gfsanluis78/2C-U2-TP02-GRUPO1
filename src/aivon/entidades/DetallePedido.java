/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aivon.entidades;

/**
 *
 * @author Ezequiel
 */
public class DetallePedido {
    private int id_caja;
    private int id_pedido;
    private int id_producto;
    private int cantidad_producto;
    private double costo_caja;
    private double costo_caja_publico;
    private int estrellas_caja;

    public DetallePedido() {
    }

    public DetallePedido(int id_pedido, int id_producto, int cantidad_producto, double costo_caja, double costo_caja_publico, int estrellas_caja) {
        this.id_pedido = id_pedido;
        this.id_producto = id_producto;
        this.cantidad_producto = cantidad_producto;
        this.costo_caja = costo_caja;
        this.costo_caja_publico = costo_caja_publico;
        this.estrellas_caja = estrellas_caja;
    }

    public DetallePedido(int id_caja, int id_pedido, int id_producto, int cantidad_producto, double costo_caja, double costo_caja_publico, int estrellas_caja) {
        this.id_caja = id_caja;
        this.id_pedido = id_pedido;
        this.id_producto = id_producto;
        this.cantidad_producto = cantidad_producto;
        this.costo_caja = costo_caja;
        this.costo_caja_publico = costo_caja_publico;
        this.estrellas_caja = estrellas_caja;
    }

    public int getId_caja() {
        return id_caja;
    }

    public void setId_caja(int id_caja) {
        this.id_caja = id_caja;
    }

    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public int getCantidad_producto() {
        return cantidad_producto;
    }

    public void setCantidad_producto(int cantidad_producto) {
        this.cantidad_producto = cantidad_producto;
    }

    public double getCosto_caja() {
        return costo_caja;
    }

    public void setCosto_caja(double costo_caja) {
        this.costo_caja = costo_caja;
    }

    public double getCosto_caja_publico() {
        return costo_caja_publico;
    }

    public void setCosto_caja_publico(double costo_caja_publico) {
        this.costo_caja_publico = costo_caja_publico;
    }

    public int getEstrellas_caja() {
        return estrellas_caja;
    }

    public void setEstrellas_caja(int estrellas_caja) {
        this.estrellas_caja = estrellas_caja;
    }

    @Override
    public String toString() {
        return "DetallePedido{" + "id_caja=" + id_caja + ", id_pedido=" + id_pedido + ", id_producto=" + id_producto + ", cantidad_producto=" + cantidad_producto + ", costo_caja=" + costo_caja + ", costo_caja_publico=" + costo_caja_publico + ", estrellas_caja=" + estrellas_caja + '}';
    }
    
    
    
}
