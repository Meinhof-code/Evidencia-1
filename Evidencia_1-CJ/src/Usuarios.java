public class Usuarios {
    private String id;
    private String nombre;

    public Usuarios(String id, String nombre){
        this.id = id;
        this.nombre = nombre;
    }

    public String getId(){
        return id;
    }
    public String getNombre(){
        return nombre;
    }
}
