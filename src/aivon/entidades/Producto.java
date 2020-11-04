/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aivon.entidades;

/**
 *
 * @author Mario
 id_producto
nombre
uso
tamaño
costo_publico
costo
estrellas
activo
 */

public class Producto {
    //Atributos
    private int id_producto;
    private String nombre;
    private String uso;
    private String tamaño;
    private Double costo_publico;
    private Double costo;
    private int estrellas;
    private boolean activo;
    
    //Metodos

    public Producto() {
    }

    public Producto(String nombre, String uso, String tamaño, Double costo_publico, Double costo, int estrellas, boolean activo) {
        this.nombre = nombre;
        this.uso = uso;
        this.tamaño = tamaño;
        this.costo_publico = costo_publico;
        this.costo = costo;
        this.estrellas = estrellas;
        this.activo = activo;
    }

    public Producto(int id_producto, String nombre, String uso, String tamaño, Double costo_publico, Double costo, int estrellas, boolean activo) {
        this.id_producto = id_producto;
        this.nombre = nombre;
        this.uso = uso;
        this.tamaño = tamaño;
        this.costo_publico = costo_publico;
        this.costo = costo;
        this.estrellas = estrellas;
        this.activo = activo;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUso() {
        return uso;
    }

    public void setUso(String uso) {
        this.uso = uso;
    }

    public String getTamaño() {
        return tamaño;
    }

    public void setTamaño(String tamaño) {
        this.tamaño = tamaño;
    }

    public Double getCosto_publico() {
        return costo_publico;
    }

    public void setCosto_publico(Double costo_publico) {
        this.costo_publico = costo_publico;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public int getEstrellas() {
        return estrellas;
    }

    public void setEstrellas(int estrellas) {
        this.estrellas = estrellas;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "Producto{" + "id_producto=" + id_producto + ", nombre=" + nombre + ", uso=" + uso + ", tama\u00f1o=" + tamaño + ", costo_publico=" + costo_publico + ", costo=" + costo + ", estrellas=" + estrellas + ", activo=" + activo + '}';
    }
    
}
