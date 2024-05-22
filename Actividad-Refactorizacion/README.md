# Refactorizacion

## Ejercicio 1 : Refactorización para mejorar la cohesión y reducir el acoplamiento

Para que el codigo sea funcional, debemos agregar ciertos atributos.

Por ejemplo, tenemos:

```java
private HashMap<String, String> databaseEmployees = new HashMap<String, String>();
```

Lo usaremos para el almacenamiento de cada empleado y su respectivo departamento.

Metodo `addEmployee`:

```java
public void addEmployee(String name, String department) {
    // Añade un empleado al departamento
    databaseEmployees.put(name, department);
    System.out.println("Empleado\t" + name + " con departamento en " +department + " añadido");
}
```

Metodo `removeEmployee` :

```java
public void removeEmployee(String name) {
    // Elimina un empleado
    databaseEmployees.remove(name);
    System.out.println("Empleado " + name + " eliminado");
}
```

Metodo `changeDepartment` :

```java
public void changeDepartment(String employeeName, String newDepartment) {
    // Cambia un empleado de departamento
    System.out.println("Se cambio el departamento de " + employeeName + " de " + databaseEmployees.get(employeeName) + " a " + newDepartment);
    databaseEmployees.replace(employeeName, newDepartment);  
    // System.out.println("Departamento cambiado");
}
```

Metodo `printDepartmentReport` :

```java
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
```

Metodo `printAllDepartments`:

```java
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
```

Funcion *main*:

```java
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
```

Salida en consola:

![imagen](/Actividad-Refactorizacion/images/ej1i1.png)

### Analisis del LCOM

Falta completar

### Refactorizacion del Ejercicio1

```java
public class Employee {
    private String nombre;
    private String departamento;
    // constructor
    public Employee(String nombre, String departamento) {
        this.nombre = nombre;
        this.departamento = departamento;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDepartamento() {
        return departamento;
    }
    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
}
```

Clase `EmployeeManager`

```java
import java.util.HashSet;

public class EmployeeManager {
    //private HashMap<String, String> databaseEmployees = new HashMap<String, String>();
    private HashSet<Employee> databaseEmployees = new HashSet<Employee>();
    public void addEmployee(String name, String department) {
        // Añade un empleado al departamento
        //databaseEmployees.put(name, department);
        databaseEmployees.add(new Employee(name, department));
        System.out.println("Empleado\t" + name + " con departamento en " + department + " añadido");
    }

    public void removeEmployee(String name) {
        // Elimina un empleado
        //databaseEmployees.remove(name);
        databaseEmployees.remove(new Employee(name, null));
        System.out.println("Empleado " + name + " eliminado");

    }

    public void changeDepartment(String employeeName, String newDepartment) {
        // Cambia un empleado de departamento
        //System.out.println("Se cambio el departamento de " + employeeName + " de " + databaseEmployees.get(employeeName) + " a " + newDepartment);
        System.out.println("Se cambio el departamento de " + employeeName + " de " + databaseEmployees.stream().filter(e -> e.getNombre().equals(employeeName)).findFirst().get().getDepartamento() + " a " + newDepartment);
        //databaseEmployees.replace(employeeName, newDepartment);  
        //databaseEmployees.remove(employeeName);
        databaseEmployees.remove(new Employee(employeeName, null));
        System.out.println("Departamento cambiado");
    }

    public void printDepartmentReport(String department) {
        // Imprime un informe del departamento

        System.out.println("Informe del departamento " + department);
        System.out.println("Ocupado por :");
        /*for (String employees : databaseEmployees.keySet()) {
            if (databaseEmployees.get(employees).equals(department)) {
                System.out.println(employees);
            }
        }*/
        for (Employee employee : databaseEmployees) {
            if (employee.getDepartamento().equals(department)) {
                System.out.println(employee.getNombre());
            }
        }
    }

    public void printAllDepartments() {
        // Imprime todos los departamentos
        System.out.println("Lista de todos los departamentos");
        /*HashSet<String> depas = new HashSet<>();
        for (String department : databaseEmployees.values()) {
            depas.add(department);
            //System.out.println(department);
        }
        for (String deps : depas) {
            System.out.println(deps);
        }
        */
        HashSet<String> depas = new HashSet<>();
        for (Employee employee : databaseEmployees) {
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
        // Change departament
        employeeManager.changeDepartment("Juan", "RR HH");
        // print department report
        employeeManager.printDepartmentReport("Ventas");
        employeeManager.printDepartmentReport("RR HH");
        employeeManager.printDepartmentReport("Marketing");
        // print all dep
        employeeManager.printAllDepartments();
    }
}

```

Salida en consola:

![imagen1](/Actividad-Refactorizacion/images/ej1i2.png)

Notamos que la salida en consola tanto para el codigo original y el refactorizado devuelven el mismo resultado, sin embargo la legibilidad y la eficiencia ha mejorado ligeramente.

## Ejercicio 2: aplicación de patrones de diseño para refactorización

## Ejercicio 3: Refactorización para introducir principios SOLID

## Ejercicio 4: Refactorización de código para mejorar la prueba y la cobertura del código
