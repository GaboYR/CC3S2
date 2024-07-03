package examenfinal.sprint2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HumedadTest {
    @Test
    public void testHumedadAlta() {
        Humedad humedad = new Humedad();
        humedad.setHumedad(90);
        assertEquals("Alerta de humedad alta", humedad.showAlerta());
    }
    @Test
    public void testHumedadNormal(){
        Humedad humedad = new Humedad();
        humedad.setHumedad(50);
        assertEquals("Humedad normal", humedad.showAlerta());
    }

}
