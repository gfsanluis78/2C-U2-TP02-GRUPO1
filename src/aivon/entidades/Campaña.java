
package aivon.entidades;

import java.time.LocalDate;

public class Campaña {
    
   private int id_campaña;
   private LocalDate fecha_inicio;
   private LocalDate fecha_fin;
   private boolean activa;

    public Campaña() {
    }
   
    public Campaña(LocalDate fecha_inicio, LocalDate fecha_fin, boolean activa) {
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.activa = activa;
    }
   
    public Campaña(int id_campaña, LocalDate fecha_inicio, LocalDate fecha_fin, boolean activa) {
        this.id_campaña = id_campaña;
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
   
    
}

