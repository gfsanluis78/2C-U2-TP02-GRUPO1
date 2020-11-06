
package aivon.entidades;

public class Revendedor {
    private int id_revendedor;
    private String nombre;
    private String apellido;
    private String dni;
    private String tel;
    private String email;
    private int nivel;
    private boolean activo;

    public Revendedor() {
    }
    
     public Revendedor(String nombre, String apellido, String dni, String tel, String email) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.tel = tel;
        this.email = email;
        this.nivel = nivel;
    }
    
    public Revendedor(String nombre, String apellido, String dni, String tel, String email, int nivel, boolean activo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.tel = tel;
        this.email = email;
        this.nivel = nivel;
        this.activo = activo;
    }

    public Revendedor(int id_revendedor, String nombre, String apellido, String dni, String tel, int nivel, boolean activo) {
        this.id_revendedor = id_revendedor;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.tel = tel;
        this.nivel = nivel;
        this.activo = activo;
    }

    public int getId_revendedor() {
        return id_revendedor;
    }

    public void setId_revendedor(int id_revendedor) {
        this.id_revendedor = id_revendedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "Revendedor{" + "id_revendedor=" + id_revendedor + ", nombre=" + nombre + ", apellido=" + apellido + ", dni=" + dni + ", tel=" + tel + ", email=" + email + ", nivel=" + nivel + ", activo=" + activo + '}';
    }

    
    //-----------------------------------Metodos auxiliares-----------------------------------------------------------------
    
    public double calcularGananciasTotales (){
        
        return 0;
    }
    
    public double calcularGananciasPorCampaña (){
        
        return 0;
    }
    
           
    
    public int calcularNivel(){
        
        
        return 0;
    }
    
  
    
}
