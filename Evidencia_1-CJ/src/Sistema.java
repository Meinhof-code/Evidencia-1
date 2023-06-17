import java.util.ArrayList;
import java.util.List;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

public class Sistema {
    private List<Admin> admins;
    private List<Doctor> doctores;
    private List<Paciente> pacientes;
    private List<Cita> citas;

    public Sistema(){
        admins = new ArrayList<>();
        doctores = new ArrayList<>();
        pacientes = new ArrayList<>();
        citas = new ArrayList<>();
    }

    public boolean login(String id, String contrasena){
        for (Admin admin : admins){
            if (admin.getId().equals(id) && admin.getContrasena().equals(contrasena)){
                return true;
            }
        }
        return false;
    }

    public void agregarAdmin(String id, String nombre, String contrasena){
        Admin admin = new Admin(id, nombre, contrasena);
        admins.add(admin);
    }

    public void agregarDoctor(String id,String nombre, String especialidad){
        Doctor doctor = new Doctor(id, nombre, especialidad);
        doctores.add(doctor);
        guardarDatos();
    }

    public void agregarPaciente(String id, String nombre){
        Paciente paciente = new Paciente(id, nombre);
        pacientes.add(paciente);
        guardarDatos();
    }

    public void crearCita(String id, String fecha, String hora, String motivo, Doctor doctor, Paciente paciente){
        Cita cita = new Cita(id, fecha, hora, motivo, doctor, paciente);
        citas.add(cita);
        guardarDatos();
    }

    public Doctor getDoctor(String id){
        for (Doctor doctor : doctores){
            if (doctor.getId().equals(id)){
                return doctor;
            }
        }
        return null;
    }

    public Paciente getPaciente(String id){
        for (Paciente paciente : pacientes){
            if (paciente.getId().equals(id)){
                return paciente;
            }
        }
        return null;
    }

    public List<Cita> obtenerCitasDr(Doctor doctor){
        List<Cita> citasDoctor = new ArrayList<>();
        for (Cita cita : citas){
            if (cita.getDoctor().equals(doctor)){
                citasDoctor.add(cita);
            }
        }
        return citasDoctor;
    }

    public List<Cita> obtenerCitasPac(Paciente paciente){
        List<Cita> citasPaciente = new ArrayList<>();
        for (Cita cita : citas){
            if (cita.getPaciente().equals(paciente)){
                citasPaciente.add(cita);
            }
        }
        return citasPaciente;
    }

    public void guardarDatos(){
        String archivoDoctores = "doctores.csv";
        String archivoPacientes = "pacientes.csv";
        String archivoCitas = "citas.csv";

        try {
            BufferedWriter escritorDoctores = new BufferedWriter(new FileWriter(archivoDoctores));
            for (Doctor doctor : doctores){
                escritorDoctores.write(doctor.getId() + "," + doctor.getNombre() + "," + doctor.getEspecialidad());
                escritorDoctores.newLine();
            }
            escritorDoctores.close();

            BufferedWriter escritorCitas = new BufferedWriter(new FileWriter(archivoCitas));
            for (Cita cita : citas){
                escritorCitas.write(cita.getId() + "," + cita.getFecha() + "," + cita.getHora() + cita.getMotivo() + "," + cita.getDoctor().getId() + "," + cita.getPaciente().getId());
                escritorCitas.newLine();
            }
            escritorCitas.close();
            System.out.println("+-----------------------------------------------------------------+");
            System.out.println("|          Datos guardados exitosamente en archivos CSV           |");
            System.out.println("+-----------------------------------------------------------------+");

        }catch (IOException e){
            System.out.println("+-----------------------------------------------------------------+");
            System.out.println("|         Error al guardar los datos en archivos CSV              |");
            System.out.println("+-----------------------------------------------------------------+");
            e.printStackTrace();
        }
    }

    public void verificarCarpetaDB(){
        File dbFolder = new File("db");

        if(!dbFolder.exists()){
            dbFolder.mkdirs();
        }
        verificarArchivoDoctores();
        verificarArchivoPacientes();
        verificarArchivoCitas();
    }
    private void verificarArchivoDoctores(){
        File doctoresFile = new File("db/doctores.csv");
        if (!doctoresFile.exists()){
            try {
                doctoresFile.createNewFile();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    private void verificarArchivoPacientes(){
        File pacientesFile = new File("db/pacientes.csv");
        if (!pacientesFile.exists()){
            try {
                pacientesFile.createNewFile();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    private void verificarArchivoCitas(){
        File citasFile = new File("db/citas.csv");
        if (!citasFile.exists()){
            try {
                citasFile.createNewFile();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}

