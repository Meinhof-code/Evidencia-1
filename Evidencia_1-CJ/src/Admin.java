public class Admin extends Usuarios{
    private String contrasena;

    public Admin(String id, String nombre, String contrasena){
        super(id, nombre);
        this.contrasena = contrasena;
    }

    public String getContrasena(){
        return contrasena;
    }

}
