package examenfinal.sprint2;

public class Lluvia implements Alerta, Notificaciones, Acciones{
    private double cantidadLluvia;
    //Contructores
    public Lluvia() {
    }
    //Getters y Setters
    public double getCantidadLluvia() {
        return cantidadLluvia;
    }
    public void setCantidadLluvia(double cantidadLluvia) {
        this.cantidadLluvia = cantidadLluvia;
    }
    // alerta de lluvia
    @Override
    public String showAlerta(){
        if (cantidadLluvia > 50){
            return "Alerta de lluvia fuerte";
        } else {
            return "Lluvia normal";
        }
    }
    @Override
    public String showNotification(){
        return "Notificacion de lluvia";
    }
    @Override
    public String showAction(){
        return "Activar sistema de riego";
    }
}
