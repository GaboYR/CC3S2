# Refactorizacion

## Ejercicio 1 : Refactorización para mejorar la cohesión y reducir el acoplamiento

Esta primera parte se enfocara en la refactorizacion y no tanto en la "logica"

Tenemos el sgte codigo inicial :

```java
public class EmployeeManager {
    public void addEmployee(String name, String department) {
        // Añade un empleado al departamento
    }

    public void removeEmployee(String name) {
        // Elimina un empleado
    }

    public void changeDepartment(String employeeName, String newDepartment) {
        // Cambia un empleado de departamento
    }

    public void printDepartmentReport(String department) {
        // Imprime un informe del departamento
    }

    public void printAllDepartments() {
        // Imprime todos los departamentos
    }
}
```

Veamos las metricas:

* Ca : 0 (no hay clases dependientes de `EmployeeManager`)
* Ce : 0 (no depende de otras clases)
* LCOM : Alto
* LCOM4: Alto

Procedimiento para refactorizar:

En la clase inicial tenemos 5 metodos, podemos separar esto en dos clases para que cada una tenga una responsabilidad unica (principio SRP de SOLID)

Asi tendriamos:

* Clase `Employee`

```java
public class EmployeeManager {
    public void addEmployee(String name, String department) {
        // Añade un empleado al departamento
    }

    public void removeEmployee(String name) {
        // Elimina un empleado
    }

    public void changeDepartment(String employeeName, String newDepartment) {
        // Cambia un empleado de departamento
    }
}

```

* Clase Departament:

```java
public class Departament {
    public void printDepartmentReport(String department) {
        // Imprime un informe del departamento
    }

    public void printAllDepartments() {
        // Imprime todos los departamentos
    }
}
```

Calculo de metricas:

Para Employee:

* $Ca = 0$
* $Ce = 1$ (depende de Department)

Para Department:

* $Ca = 1$ (Employee depende de este)
* $Ce = 0$

Aun no tocaremos el principio SOLID a fondo, se vera mas adelante.

## Ejercicio 2: aplicación de patrones de diseño para refactorización

## Ejercicio 3: Refactorización para introducir principios SOLID

## Ejercicio 4: Refactorización de código para mejorar la prueba y la cobertura del código
