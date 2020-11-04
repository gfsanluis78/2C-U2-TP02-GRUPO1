
package aivon.entidades;

import java.time.LocalDate;

public class Campaña {
    
   private int id_campaña;
   private String nombre;
   private LocalDate fecha_inicio;
   private LocalDate fecha_fin;
   private boolean activa;

    public Campaña() {
    }
   
    public Campaña(String nombre, LocalDate fecha_inicio, LocalDate fecha_fin, boolean activa) {
        this.nombre = nombre;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.activa = activa;
    }
   
    public Campaña(int id_campaña, String nombre, LocalDate fecha_inicio, LocalDate fecha_fin, boolean activa) {
        this.id_campaña = id_campaña;
        this.nombre = nombre;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
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

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    @Override
    public String toString() {
        return "Campa\u00f1a{" + "id_campa\u00f1a=" + id_campaña + ", nombre=" + nombre + ", fecha_inicio=" + fecha_inicio + ", fecha_fin=" + fecha_fin + ", activa=" + activa + '}';
    }
    
        
    
}

