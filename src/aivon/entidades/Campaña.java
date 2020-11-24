
package aivon.entidades;

import java.time.LocalDate;

public class Campaña {
    
   private int id_campaña;
   private String nombre;
   private LocalDate fecha_inicio;
   private LocalDate fecha_fin;
   private double monto_min;
   private double monto_max;
   private boolean activa;
   

    public Campaña() {
    }
    
     public Campaña(String nombre, LocalDate fecha_inicio, double monto_min, double monto_max, boolean activa) {
        this.nombre = nombre;
        this.fecha_inicio = fecha_inicio;
        this.monto_min = monto_min;
        this.monto_max = monto_max;
        this.activa = activa;
    }
   
    public Campaña(String nombre, LocalDate fecha_inicio, LocalDate fecha_fin, double monto_min, double monto_max, boolean activa) {
        this.nombre = nombre;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.monto_min = monto_min;
        this.monto_max = monto_max;
        this.activa = activa;
    }
   
    public Campaña(int id_campaña, String nombre, LocalDate fecha_inicio, LocalDate fecha_fin, double monto_min, double monto_max, boolean activa) {
        this.id_campaña = id_campaña;
        this.nombre = nombre;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.monto_min = monto_min;
        this.monto_max = monto_max;
        this.activa = activa;
    }

    public int getId_campaña() {
        return id_campaña;
    }

    public void setId_campaña(int id_campaña) {
        this.id_campaña = id_campaña;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    public LocalDate getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(LocalDate fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public LocalDate getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(LocalDate fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public double getMonto_min() {
        return monto_min;
    }

    public void setMonto_min(double monto_min) {
        this.monto_min = monto_min;
    }

    public double getMonto_max() {
        return monto_max;
    }

    public void setMonto_max(double monto_max) {
        this.monto_max = monto_max;
    }

    
    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

//    @Override
//    public String toString() {
//        return "Campa\u00f1a{" + "id_campa\u00f1a=" + id_campaña + ", nombre=" + nombre + ", fecha_inicio=" + fecha_inicio + ", fecha_fin=" + fecha_fin + ", monto_min=" + monto_min + ", monto_max=" + monto_max + ", activa=" + activa + '}';
//    }

    @Override
    public String toString() {
        return id_campaña + " - " + nombre;
    }

        
        
    
}

