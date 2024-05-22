package Ejercicio1refactorizado;
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
