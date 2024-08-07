package examenfinal.sprint2;

public class Viento implements Alerta, Notificaciones, Acciones{
    private double velocidadViento;
    // constructores
    public Viento(){
        this.velocidadViento = 0;
    }
    public double getVelocidad(){
        return velocidadViento;
    }
    public void setVelocidadViento(double velocidadViento){
        this.velocidadViento = velocidadViento;
    }
    // Muestra si hay alerta o no
    @Override
    public String showAlerta(){
        if (velocidadViento > 20){
            return "Alerta de viento fuerte";
        } else {
            return "Viento normal";
        }
    }
    @Override
    public String showNotification(){
        return "Notificación de viento";
    }
    @Override
    public String showAction(){
        return "Cerrar persianas";
    }
}
