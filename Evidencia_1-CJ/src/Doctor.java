public class Doctor extends Usuarios{
    private String especialidad;
    public Doctor(String id, String nombre, String especialidad){
        super(id, nombre);
        this.especialidad = especialidad;
    }

    public String getEspecialidad(){
        return especialidad;
    }
}
