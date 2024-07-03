package examenfinal.sprint1;

public class Humedad implements Alerta{
    private double porcentajeHumedad;
    // constructores
    public Humedad() {
    }
    public double getHumedad() {
        return porcentajeHumedad;
    }
    public void setHumedad(double porcentajeHumedad) {
        this.porcentajeHumedad = porcentajeHumedad;
    }
    // Muestra si hay alerta o no
    @Override
    public String showAlerta(){
        if (porcentajeHumedad > 80){
            return "Alerta de humedad alta";
        } else {
            return "Humedad normal";
        }
    }
}
