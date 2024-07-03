package examenfinal.sprint1;

public class Lluvia {
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
    
    public String showAlerta(){
        if (cantidadLluvia > 50){
            return "Alerta de lluvia fuerte";
        } else {
            return "Lluvia normal";
        }
        
    }
        
}
