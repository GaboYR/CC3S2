package examenfinal.sprint2;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class TemperaturaTest {
    // Creacion y uso de stubs y fakes

    @Test
    public void testTemperaturaAlta() {
        Temperatura temperatura = new Temperatura();
        temperatura.setTemperatura(40);
        assertEquals("Alerta de temperatura alta", temperatura.showAlerta());
    }
    @Test
    public void testTemperaturaNormal(){
        Temperatura temperatura = new Temperatura();
        temperatura.setTemperatura(20);
        assertEquals("Temperatura normal", temperatura.showAlerta());
    }
    
}
