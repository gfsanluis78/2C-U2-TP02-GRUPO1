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
public class Historico {
    private int id_historico;
    private int id_revendedor;
    private int id_campaña;
    private double monto_min;
    private double monto_max;
    private int estrellas_campaña_revendedor;
    private boolean estado_campaña_revendedor;

    public Historico() {
    }

    public Historico(int id_revendedor, int id_campaña, double monto_min, double monto_max, int estrellas_campaña_revendedor, boolean estado_campaña_revendedor) {
        this.id_revendedor = id_revendedor;
        this.id_campaña = id_campaña;
        this.monto_min = monto_min;
        this.monto_max = monto_max;
        this.estrellas_campaña_revendedor = estrellas_campaña_revendedor;
        this.estado_campaña_revendedor = estado_campaña_revendedor;
    }

    public Historico(int id_historico, int id_revendedor, int id_campaña, double monto_min, double monto_max, int estrellas_campaña_revendedor, boolean estado_campaña_revendedor) {
        this.id_historico = id_historico;
        this.id_revendedor = id_revendedor;
        this.id_campaña = id_campaña;
        this.monto_min = monto_min;
        this.monto_max = monto_max;
        this.estrellas_campaña_revendedor = estrellas_campaña_revendedor;
        this.estado_campaña_revendedor = estado_campaña_revendedor;
    }

    public int getId_historico() {
        return id_historico;
    }

    public void setId_historico(int id_historico) {
        this.id_historico = id_historico;
    }

    public int getId_revendedor() {
        return id_revendedor;
    }

    public void setId_revendedor(int id_revendedor) {
        this.id_revendedor = id_revendedor;
    }

    public int getId_campaña() {
        return id_campaña;
    }

    public void setId_campaña(int id_campaña) {
        this.id_campaña = id_campaña;
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

    public boolean isEstado_campaña_revendedor() {
        return estado_campaña_revendedor;
    }

    public void setEstado_campaña_revendedor(boolean estado_campaña_revendedor) {
        this.estado_campaña_revendedor = estado_campaña_revendedor;
    }
    
    
}
