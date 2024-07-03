package examenfinal.sprint1;

public class Temperatura implements Alerta{
    private double temperatura;
    // constructores
    public Temperatura() {
    }
    public double getTemperatura() {
        return temperatura;
    }
    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }
    // Muestra si hay alerta o no
    @Override
    public String showAlerta(){
        if (temperatura > 30){
            return "Alerta de temperatura alta";
        } else {
            return "Temperatura normal";
        }
    }
}
