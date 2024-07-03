package examenfinal.sprint2;

public class Presion implements Alerta{
    private double presion;
    // constructores
    public Presion() {
    }
    public double getPresion() {
        return presion;
    }
    public void setPresion(double presion) {
        this.presion = presion;
    }
    // Muestra si hay alerta o no
    @Override
    public String showAlerta(){
        if (presion > 500){
            return "Alerta de presion alta";
        } else {
            return "Presion normal";
        }
    }
}
