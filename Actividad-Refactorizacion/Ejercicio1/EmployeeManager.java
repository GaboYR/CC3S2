import java.util.HashMap;
import java.util.HashSet;

public class EmployeeManager {
    private HashMap<String, String> databaseEmployees = new HashMap<String, String>();
    public void addEmployee(String name, String department) {
        // Añade un empleado al departamento
        databaseEmployees.put(name, department);
        System.out.println("Empleado\t" + name + " con departamento en " + department + " añadido");
    }

    public void removeEmployee(String name) {
        // Elimina un empleado
        databaseEmployees.remove(name);
        System.out.println("Empleado " + name + " eliminado");

    }

    public void changeDepartment(String employeeName, String newDepartment) {
        // Cambia un empleado de departamento
        System.out.println("Se cambio el departamento de " + employeeName + " de " + databaseEmployees.get(employeeName) + " a " + newDepartment);
        databaseEmployees.replace(employeeName, newDepartment);  
        // System.out.println("Departamento cambiado");
    }

    public void printDepartmentReport(String department) {
        // Imprime un informe del departamento

        System.out.println("Informe del departamento " + department);
        System.out.println("Ocupado por :");
        for (String employees : databaseEmployees.keySet()) {
            if (databaseEmployees.get(employees).equals(department)) {
                System.out.println(employees);
            }
        }
    }

    public void printAllDepartments() {
        // Imprime todos los departamentos
        System.out.println("Lista de todos los departamentos");
        HashSet<String> depas = new HashSet<>();
        for (String department : databaseEmployees.values()) {
            depas.add(department);
            //System.out.println(department);
        }
        for (String deps : depas) {
            System.out.println(deps);
        }
    }
    public static void main(String[] args) {
        EmployeeManager employeeManager = new EmployeeManager();
        // Add employees
        employeeManager.addEmployee("Juan", "Ventas");
        employeeManager.addEmployee("Pedro", "Ventas");
        employeeManager.addEmployee("Maria", "Marketing");
        employeeManager.addEmployee("Carlos", "Ventas");
        employeeManager.addEmployee("Beto", "RR HH");
        // Remove employees
        employeeManager.removeEmployee("Pedro");
        // Change departament
        employeeManager.changeDepartment("Juan", "RR HH");
        // print department report
        employeeManager.printDepartmentReport("Ventas");
        employeeManager.printDepartmentReport("RR HH");
        // print all dep
        employeeManager.printAllDepartments();
    }
}
