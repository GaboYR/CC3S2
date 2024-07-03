package examenfinal.sprint2;

import java.io.Console;

public class SistemaGestor {
    public static void main(String[] args) {
        System.out.println("Sistema Gestor de Clima");
        Temperatura temperatura = new Temperatura();
        Lluvia lluvia = new Lluvia();
        Viento viento = new Viento();
        Humedad humedad = new Humedad();
        Presion presion = new Presion();
        Console console = System.console();
        System.out.println("Temperatura: ");
        temperatura.setTemperatura(Double.parseDouble(console.readLine()));
        System.out.println("Lluvia: ");
        lluvia.setCantidadLluvia(Double.parseDouble(console.readLine()));
        System.out.println("Viento: ");
        viento.setVelocidadViento(Double.parseDouble(console.readLine()));
        System.out.println("Humedad: ");
        humedad.setHumedad(Double.parseDouble(console.readLine()));
        System.out.println("Presion: ");
        presion.setPresion(Double.parseDouble(console.readLine()));

        // Mostrar alertas
        System.out.println(temperatura.showAlerta());
        System.out.println(lluvia.showAlerta());
        System.out.println(viento.showAlerta());
        System.out.println(humedad.showAlerta());
        System.out.println(presion.showAlerta());

        // Mostrar notificaciones
        
        System.out.println(temperatura.showNotification());
    }
    
}
