package Ejercicio1refactorizado;

import java.util.HashMap;
import java.util.HashSet;

public class EmployeeManager {
    private HashMap<String, Employee> databaseEmployees = new HashMap<>();

    public void addEmployee(String name, String department) {
        // Añade un empleado al departamento
        Employee employee = new Employee(name, department);
        databaseEmployees.put(name, employee);
        System.out.println("Empleado " + name + " con departamento en " + department + " añadido");
    }

    public void removeEmployee(String name) {
        // Elimina un empleado
        databaseEmployees.remove(name);
        System.out.println("Empleado " + name + " eliminado");
    }

    public void changeDepartment(String employeeName, String newDepartment) {
        // Cambia un empleado de departamento
        Employee employee = databaseEmployees.get(employeeName);
        if (employee != null) {
            String oldDepartment = employee.getDepartamento();
            employee.setDepartamento(newDepartment);
            System.out.println("Se cambió el departamento de " + employeeName + " de " + oldDepartment + " a " + newDepartment);
        } else {
            System.out.println("Empleado " + employeeName + " no encontrado");
        }
    }

    public void printDepartmentReport(String department) {
        // Imprime un informe del departamento
        System.out.println("Informe del departamento " + department);
        System.out.println("Ocupado por :");
        for (Employee employee : databaseEmployees.values()) {
            if (employee.getDepartamento().equals(department)) {
                System.out.println(employee.getNombre());
            }
        }
    }

    public void printAllDepartments() {
        // Imprime todos los departamentos
        System.out.println("Lista de todos los departamentos");
        HashSet<String> depas = new HashSet<>();
        for (Employee employee : databaseEmployees.values()) {
            depas.add(employee.getDepartamento());
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
        // Change department
        employeeManager.changeDepartment("Juan", "RR HH");
        // print department report
        employeeManager.printDepartmentReport("Ventas");
        employeeManager.printDepartmentReport("RR HH");
        employeeManager.printDepartmentReport("Marketing");
        // print all departments
        employeeManager.printAllDepartments();
    }
}
