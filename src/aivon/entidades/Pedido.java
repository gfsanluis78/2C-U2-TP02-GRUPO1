package aivon.entidades;

import java.time.LocalDate;

public class Pedido {
    
    private int id_pedido;
    private int id_revendedor;
    private LocalDate fecha_ingreso;
    private LocalDate fecha_entrega;
    private LocalDate fecha_pago;
    private int cantidad_cajas;
    private int estrellas_pedido;
    private boolean pago;
    private boolean activo;

    public Pedido() {
    }

    public Pedido(int id_revendedor, LocalDate fecha_ingreso, LocalDate fecha_entrega, LocalDate fecha_pago, int cantidad_cajas, int estrellas_pedido, boolean pago, boolean activo) {
        this.id_revendedor = id_revendedor;
        this.fecha_ingreso = fecha_ingreso;
        this.fecha_entrega = fecha_entrega;
        this.fecha_pago = fecha_pago;
        this.cantidad_cajas = cantidad_cajas;
        this.estrellas_pedido = estrellas_pedido;
        this.pago = pago;
        this.activo = activo;
    }

    public Pedido(int id_pedido, int id_revendedor, LocalDate fecha_ingreso, LocalDate fecha_entrega, LocalDate fecha_pago, int cantidad_cajas, int estrellas_pedido, boolean pago, boolean activo) {
        this.id_pedido = id_pedido;
        this.id_revendedor = id_revendedor;
        this.fecha_ingreso = fecha_ingreso;
        this.fecha_entrega = fecha_entrega;
        this.fecha_pago = fecha_pago;
        this.cantidad_cajas = cantidad_cajas;
        this.estrellas_pedido = estrellas_pedido;
        this.pago = pago;
        this.activo = activo;
    }

    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }

    public int getId_revendedor() {
        return id_revendedor;
    }

    public void setId_revendedor(int id_revendedor) {
        this.id_revendedor = id_revendedor;
    }

    public LocalDate getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(LocalDate fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    public LocalDate getFecha_entrega() {
        return fecha_entrega;
    }

    public void setFecha_entrega(LocalDate fecha_entrega) {
        this.fecha_entrega = fecha_entrega;
    }

    public LocalDate getFecha_pago() {
        return fecha_pago;
    }

    public void setFecha_pago(LocalDate fecha_pago) {
        this.fecha_pago = fecha_pago;
    }

    public int getCantidad_cajas() {
        return cantidad_cajas;
    }

    public void setCantidad_cajas(int cantidad_cajas) {
        this.cantidad_cajas = cantidad_cajas;
    }

    public int getEstrellas_pedido() {
        return estrellas_pedido;
    }

    public void setEstrellas_pedido(int estrellas_pedido) {
        this.estrellas_pedido = estrellas_pedido;
    }

    public boolean isPago() {
        return pago;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "Pedido{" + "id_pedido=" + id_pedido + ", id_revendedor=" + id_revendedor + ", fecha_ingreso=" + fecha_ingreso + ", fecha_entrega=" + fecha_entrega + ", fecha_pago=" + fecha_pago + ", cantidad_cajas=" + cantidad_cajas + ", estrellas_pedido=" + estrellas_pedido + ", pago=" + pago + ", activo=" + activo + '}';
    }
    
    

}
