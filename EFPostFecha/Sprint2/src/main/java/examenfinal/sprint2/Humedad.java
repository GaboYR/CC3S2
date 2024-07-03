package examenfinal.sprint2;

public class Humedad implements Alerta, Notificaciones, Acciones {
    public static final double HUMEDAD_MAX = 80;
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
        if (porcentajeHumedad > HUMEDAD_MAX){
            return "Alerta de humedad alta";
        } else {
            return "Humedad normal";
        }
    }
    @Override
    public String showNotification(){
        return "Notificación de humedad";
    }
    @Override
    public String showAction(){
        return "Acción de humedad";
    }
}
