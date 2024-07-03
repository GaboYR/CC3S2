package examenfinal.sprint2;

public class Temperatura implements Alerta, Notificaciones, Acciones {
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
    public class Humedad implements Alerta, Notificaciones, Acciones {
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
        @Override
        public String showNotification(){
            return "Notificacion de temperatura";
        }
        @Override
        public String showAction(){
            return "Activar sistema de riego";
        }
    }
}
