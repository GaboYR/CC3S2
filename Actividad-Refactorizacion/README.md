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

Salida en consola:

![](/images/ej1i1.png)

## Ejercicio 2: aplicación de patrones de diseño para refactorización

## Ejercicio 3: Refactorización para introducir principios SOLID

## Ejercicio 4: Refactorización de código para mejorar la prueba y la cobertura del código
