/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aivon.modelos;

import aivon.entidades.Campaña;
import aivon.entidades.Pedido;
import aivon.entidades.Revendedor;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author MArio
 */
public class SeguimientoData {
    private Conexion con;
    private Connection c;
    private RevendedorData rd;
    private PedidoData ped;
    private CampañaData cd;
    
    List<Revendedor> revendedores;
    List<Campaña> campañas;
    int indice;

    public SeguimientoData(Conexion con) {
        this.con = con;
        this.c=con.getConnection();
        try {
            rd=new RevendedorData(con);
            cd=new CampañaData(con);
            ped= new PedidoData(con);
            revendedores=rd.buscarRevendedores();
            campañas=cd.buscarCampañas();
            
        } catch (Exception e) {
            
        }
    }
//#############################################################################
    public boolean buscarPedido(Revendedor revendedor, Campaña campaña){
        Pedido pedido=null;
        pedido=ped.buscarPedidoSeguimiento(revendedor.getId_revendedor(),campaña.getId_campaña() );
//        if(pedido!=null){
//            System.out.println("BUSCAR PEDIDO: el pedido es"+pedido.toString());
//        }
        return pedido==null;
    }
    
    public int revisar(){
        int cont=0;
        for (int i = 0; i < campañas.size(); i++) {
            System.out.println("REVISAR: "+campañas.get(i).toString());    
            if(campañas.get(i).isActiva()){
                    indice=i;
//                    indice--;
//                    System.out.println("REVISAR: indice encontrado "+indice);
                }
            }
//        for (int i = 0; i < revendedores.size(); i++) {
//            System.out.println("REVISAR: "+revendedores.get(i).toString());    
//        }
        if (campañas.size() >= 3) {
            System.out.println("REVISAR: "+campañas.size()+" campañas");
            for (Revendedor it : revendedores) {
//                System.out.println("REVISAR: Entro al 1 IF "+ it.toString());
                if (this.buscarPedido(it, campañas.get(indice))) {
//                    System.out.println("REVISAR: Entro al 2 IF "+(indice-1) + " "+it.toString());
                    if (this.buscarPedido(it, campañas.get(indice - 1))) {
//                        System.out.println("REVISAR: Entro al 2 IF "+(indice-2)+" "+it.toString());
                        if (this.buscarPedido(it, campañas.get(indice - 2))) {
                            rd.inhabilitarRevendedorSeguimiento(it.getDni());
                            cont++;
//                            System.out.println("REVISAR: Inhabilito revendedor");
                        }
                    }
                }
            }
        }
        return cont;
    }
}
