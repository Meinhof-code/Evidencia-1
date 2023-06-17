import java.util.List;
import java.util.Scanner;
public class Main {
    public static void main(String[] args){
        Sistema sistema = new Sistema();
        Scanner scanner = new Scanner(System.in);

        //Agregegando al administrador principal
        sistema.agregarAdmin("001","Sebastian","1234");

        //Inicio de sesión

        boolean loginExitoso = false;
        int intentos = 3;

        while (!loginExitoso && intentos > 0){
            System.out.println("+------------------------INICIO DE SESIÓN------------------------+");
            System.out.println("ID de usuario: ");
            String idUsuario = scanner.nextLine();
            System.out.println("Contraseña: ");
            String contrasena = scanner.nextLine();
            System.out.println("+----------------------------------------------------------------+");

            if (sistema.login(idUsuario,contrasena)){
                System.out.println("+-----------------------------------------------------------------+");
                System.out.println("|                     Inicio de sesión exitoso                    |");
                System.out.println("+-----------------------------------------------------------------+");
                loginExitoso = true;
            }else {
                System.out.println("+-----------------------------------------------------------------+");
                System.out.println("|   Inicio de sesión fallido. Usuario o contraseña incorrectos    |");
                System.out.println("+-----------------------------------------------------------------+");
                intentos --;
                if (intentos > 0){
                    System.out.println("+-----------------------------------------------------------------+");
                    System.out.println("|                Le quedan " + intentos + " de 3                  |");
                    System.out.println("+-----------------------------------------------------------------+");
                }
            }
            System.out.println();
        }
        if (loginExitoso){
            //Menú de opciones
            int opcion;
            do{
                System.out.println("+------------------------MENÚ DE OPCIONES-------------------------+");
                System.out.println("| 1. Dar de alta a un Doctor                                      |");
                System.out.println("| 2. Dar de alta a un Paciente                                    |");
                System.out.println("| 3. Crear cita                                                   |");
                System.out.println("| 4. Ver citas de un Doctor                                       |");
                System.out.println("| 5. Salir                                                        |");
                System.out.println("+-----------------------------------------------------------------+");
                System.out.println("Seleccione una opción: ");
                opcion = scanner.nextInt();
                scanner.nextLine();


                switch (opcion){

                    case 1:
                        //Dar de alta a un doctor
                        System.out.println("------------------------Registro de Doctores------------------------");
                        System.out.println("ID (Se empieza con D, seguido de dos números): ");
                        String idDoctor = scanner.nextLine();
                        System.out.println("Nombre (Nombre completo): ");
                        String nombreDoctor = scanner.nextLine();
                        System.out.println("Especialidad: ");
                        String especialidadDoctor = scanner.nextLine();
                        System.out.println("-------------------------------------------------------------------");

                        sistema.agregarDoctor(idDoctor,nombreDoctor,especialidadDoctor);
                        System.out.println("+-----------------------------------------------------------------+");
                        System.out.println("|      Doctor " + nombreDoctor + " agregado con éxito             |");
                        System.out.println("+-----------------------------------------------------------------+");
                        break;

                    case 2:
                        //Dar de alta a un paciente
                        System.out.println("------------------------Registro de Pacientes------------------------");
                        System.out.println("ID: ");
                        String idPaciente = scanner.nextLine();
                        System.out.println("Nombre: ");
                        String nombrePaciente = scanner.nextLine();
                        System.out.println("--------------------------------------------------------------------");

                        sistema.agregarPaciente(idPaciente,nombrePaciente);
                        System.out.println("+-----------------------------------------------------------------+");
                        System.out.println("|                 Paciente agregado correctamente                 |");
                        System.out.println("+-----------------------------------------------------------------+");
                        break;

                    case 3:
                        //Crear una cita
                        System.out.println("------------------------Creación de citas------------------------");
                        System.out.println("ID: ");
                        String idCita = scanner.nextLine();
                        System.out.println("Fecha: ");
                        String fechaCita = scanner.nextLine();
                        System.out.println("Hora: ");
                        String horaCita = scanner.nextLine();
                        System.out.println("Motivo: ");
                        String motivoCita = scanner.nextLine();
                        System.out.println("ID del doctor: ");
                        String idDoctorCita = scanner.nextLine();
                        System.out.println("ID del paciente: ");
                        String idPacienteCita = scanner.nextLine();
                        System.out.println("--------------------------------------------------------------------");

                        Doctor doctorCita = sistema.getDoctor(idDoctorCita);
                        Paciente pacienteCita = sistema.getPaciente(idPacienteCita);
                        if (doctorCita != null && pacienteCita != null){
                            sistema.crearCita(idCita,fechaCita,horaCita,motivoCita,doctorCita,pacienteCita);
                            System.out.println("Cita creada con éxito");
                        }else {
                            System.out.println("+-----------------------------------------------------------------+");
                            System.out.println("|              Doctor o paciente no encontrado                    |");
                            System.out.println("+-----------------------------------------------------------------+");
                        }
                        break;

                    case 4:
                        // Ver citas de un doctor
                        System.out.println("-----------------------Buscador de citas------------------------");
                        System.out.println("ID del doctor: ");
                        String idDoctorCitas = scanner.nextLine();

                        Doctor doctorCitas = sistema.getDoctor(idDoctorCitas);
                        if (doctorCitas != null){
                            List<Cita> citasDoctor = sistema.obtenerCitasDr(doctorCitas);
                            if(!citasDoctor.isEmpty()){
                                System.out.println("Citas del doctor " + doctorCitas.getNombre() + ":");
                                for (Cita cita : citasDoctor) {
                                    System.out.println("ID: " + cita.getId());
                                    System.out.println("Fecha: " + cita.getFecha());
                                    System.out.println("Hora: " + cita.getHora());
                                    System.out.println("Motivo: " + cita.getMotivo());
                                    System.out.println("Paciente: " + cita.getPaciente().getNombre());
                                    System.out.println("--------------------------------------------------------------------");
                                    System.out.println();
                                }
                            }else {
                                System.out.println("+-----------------------------------------------------------------+");
                                System.out.println("|             El doctor no tiene citas programadas                |");
                                System.out.println("+-----------------------------------------------------------------+");
                            }
                        } else {
                            System.out.println("+-----------------------------------------------------------------+");
                            System.out.println("|                      Doctor no encontrado                       |");
                            System.out.println("+-----------------------------------------------------------------+");
                    }
                        break;

                    case 5:
                        // Salir
                        System.out.println("+-----------------------------------------------------------------+");
                        System.out.println("|                    Saliendo del sistema...                      |");
                        System.out.println("+-----------------------------------------------------------------+");
                        break;

                    default:
                        System.out.println("+-----------------------------------------------------------------+");
                        System.out.println("|               Opción inválida. Intente de nuevo                 |");
                        System.out.println("+-----------------------------------------------------------------+");
                        break;
                }
                System.out.println();
            }while (opcion != 5);
            sistema.guardarDatos();
        }else {
            System.out.println("+-----------------------------------------------------------------+");
            System.out.println("| Número máximo de intentos alcanzado. Saliendo del sistema...    |");
            System.out.println("+-----------------------------------------------------------------+");
        }
        scanner.close();
    }
}