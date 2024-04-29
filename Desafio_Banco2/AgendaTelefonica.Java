import java.util.Scanner;

public class AgendaTelefonica {
    private static final int MAX_CONTACTOS = 10;
    private Contacto[] contactos;
    private int cantidadContactos;
    private Scanner scanner;

    public AgendaTelefonica() {
        contactos = new Contacto[MAX_CONTACTOS];
        cantidadContactos = 0;
        scanner = new Scanner(System.in);
    }

    public void agregarContacto(String nombre, String telefono) {
        boolean encontrado = buscarContactoPorNombre(nombre);
        if (encontrado) {
            System.out.println("¡Advertencia! Ya existe un contacto con ese nombre en la agenda.");
        }
        if (cantidadContactos < MAX_CONTACTOS) {
            contactos[cantidadContactos++] = new Contacto(nombre, telefono);
            System.out.println("Contacto agregado correctamente.");
        } else {
            System.out.println("La agenda está llena, no se puede agregar más contactos.");
        }
    }

    public boolean buscarContactoPorNombre(String nombre) {
        boolean encontrado = false;
        for (int i = 0; i < cantidadContactos; i++) {
            if (contactos[i].getNombre().equalsIgnoreCase(nombre)) {
                encontrado = true;
                System.out.println("Nombre: " + contactos[i].getNombre() + ", Teléfono: " + contactos[i].getTelefono());
            }
        }
        if (!encontrado) {
            System.out.println("No se encontraron contactos con ese nombre.");
        }
        return encontrado;
    }

    public void mostrarContactos() {
        if (cantidadContactos == 0) {
            System.out.println("La agenda está vacía.");
        } else {
            System.out.println("Contactos en la agenda:");
            for (int i = 0; i < cantidadContactos; i++) {
                System.out.println("Nombre: " + contactos[i].getNombre() + ", Teléfono: " + contactos[i].getTelefono());
            }
        }
    }

    public void modificarContacto(String nombre, String nuevoTelefono) {
        boolean encontrado = false;
        for (int i = 0; i < cantidadContactos; i++) {
            if (contactos[i].getNombre().equalsIgnoreCase(nombre)) {
                contactos[i].setTelefono(nuevoTelefono);
                encontrado = true;
                System.out.println("Contacto modificado correctamente.");
            }
        }
        if (!encontrado) {
            System.out.println("No se encontró ningún contacto con ese nombre.");
        }
    }

    public void eliminarContacto(String nombre) {
        boolean encontrado = false;
        for (int i = 0; i < cantidadContactos; i++) {
            if (contactos[i].getNombre().equalsIgnoreCase(nombre)) {
                for (int j = i; j < cantidadContactos - 1; j++) {
                    contactos[j] = contactos[j + 1];
                }
                cantidadContactos--;
                encontrado = true;
                System.out.println("Contacto eliminado correctamente.");
            }
        }
        if (!encontrado) {
            System.out.println("No se encontró ningún contacto con ese nombre.");
        }
    }

    public void vaciarAgenda() {
        cantidadContactos = 0;
        System.out.println("Agenda vaciada correctamente.");
    }

    public static void main(String[] args) {
        AgendaTelefonica agenda = new AgendaTelefonica();
        int opcion;

        do {
            System.out.println("\nMENU");
            System.out.println("1. Agregar contacto");
            System.out.println("2. Mostrar contactos");
            System.out.println("3. Buscar contacto por nombre");
            System.out.println("4. Modificar contacto");
            System.out.println("5. Eliminar contacto");
            System.out.println("6. Vaciar agenda");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = agenda.scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese nombre del contacto: ");
                    String nombre = agenda.scanner.next();
                    System.out.print("Ingrese teléfono del contacto: ");
                    String telefono = agenda.scanner.next();
                    agenda.agregarContacto(nombre, telefono);
                    break;
                case 2:
                    agenda.mostrarContactos();
                    break;
                case 3:
                    System.out.print("Ingrese nombre del contacto a buscar: ");
                    String nombreBuscar = agenda.scanner.next();
                    agenda.buscarContactoPorNombre(nombreBuscar);
                    break;
                case 4:
                    System.out.print("Ingrese nombre del contacto a modificar: ");
                    String nombreModificar = agenda.scanner.next();
                    System.out.print("Ingrese nuevo teléfono del contacto: ");
                    String nuevoTelefono = agenda.scanner.next();
                    agenda.modificarContacto(nombreModificar, nuevoTelefono);
                    break;
                case 5:
                    System.out.print("Ingrese nombre del contacto a eliminar: ");
                    String nombreEliminar = agenda.scanner.next();
                    agenda.eliminarContacto(nombreEliminar);
                    break;
                case 6:
                    agenda.vaciarAgenda();
                    break;
                case 7:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
            }
        } while (opcion != 7);

        agenda.scanner.close();
    }
}
