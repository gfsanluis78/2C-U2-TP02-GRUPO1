/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aivon.entidades;

/**
 *
 * @author Mario
 */
public class Historico {
    private int id_historico;
    private Revendedor revendedor;
    private Campaña campaña;
    private double monto_min;
    private double monto_max;
    private int nivel=1;
    private double ganancia;
    private int estrellas_campaña_revendedor;
    //private boolean estado_campaña_revendedor;

    public Historico() {
    }

    public Historico(Revendedor revendedor, Campaña campaña, double monto_min, double monto_max) {
        this.revendedor = revendedor;
        this.campaña = campaña;
        this.monto_min = monto_min;
        this.monto_max = monto_max;
        //this.nivel = nivel;
    }

    public Historico(Revendedor revendedor, Campaña campaña) {
        this.revendedor = revendedor;
        this.campaña = campaña;
    }
          
    public Historico(Revendedor revendedor, Campaña campaña, double monto_min, double monto_max,   double ganancia, int estrellas_campaña_revendedor) {
        this.revendedor = revendedor;
        this.campaña = campaña;
        this.monto_min = monto_min;
        this.monto_max = monto_max;
        //this.nivel = nivel;
        this.ganancia = ganancia;
        this.estrellas_campaña_revendedor = estrellas_campaña_revendedor;
    }
    
    public Historico(int id_historico, Revendedor revendedor, Campaña campaña, double monto_min, double monto_max,    double ganancia, int estrellas_campaña_revendedor) {
        this.id_historico = id_historico;
        this.revendedor = revendedor;
        this.campaña = campaña;
        this.monto_min = monto_min;
        this.monto_max = monto_max;
        //this.nivel = nivel;
        this.ganancia = ganancia;
        this.estrellas_campaña_revendedor = estrellas_campaña_revendedor;
    }

    

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public double getGanancia() {
        return ganancia;
    }

    public void setGanancia(double ganancia) {
        this.ganancia = ganancia;
    }

    public int getId_historico() {
        return id_historico;
    }

    public void setId_historico(int id_historico) {
        this.id_historico = id_historico;
    }

    public Revendedor getRevendedor() {
        return revendedor;
    }

    public void setRevendedor(Revendedor revendedor) {
        this.revendedor = revendedor;
    }

    public Campaña getCampaña() {
        return campaña;
    }

    public void setCampaña(Campaña campaña) {
        this.campaña = campaña;
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

    public int getEstrellas_campaña_revendedor() {
        return estrellas_campaña_revendedor;
    }

    public void setEstrellas_campaña_revendedor(int estrellas_campaña_revendedor) {
        this.estrellas_campaña_revendedor = estrellas_campaña_revendedor;
    }

//    public boolean isEstado_campaña_revendedor() {
//        return estado_campaña_revendedor;
//    }

//    public void setEstado_campaña_revendedor(boolean estado_campaña_revendedor) {
//        this.estado_campaña_revendedor = estado_campaña_revendedor;
//    }

//    @Override
//    public String toString() {
//        return "Historico{" + "id_historico=" + id_historico + ", revendedor=" + revendedor + ", campa\u00f1a=" + campaña + ", monto_min=" + monto_min + ", monto_max=" + monto_max + ", estrellas_campa\u00f1a_revendedor=" + estrellas_campaña_revendedor + ", estado_campa\u00f1a_revendedor=" + estado_campaña_revendedor + '}';
//    }

    @Override
    public String toString() {
        return "\nHistorico{" + "id_historico=" + id_historico + ", revendedor=" + revendedor + ", campa\u00f1a=" + campaña + ", monto_min=" + monto_min + ", monto_max=" + monto_max + ", nivel=" + nivel + ", ganancia=" + ganancia + ", estrellas_campa\u00f1a_revendedor=" + estrellas_campaña_revendedor + '}';
    }
    
    
}
