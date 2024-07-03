package examenfinal.sprint1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PresionTest {
    @Test
    public void testPresionAlta() {
        Presion presion = new Presion();
        presion.setPresion(1050);
        assertEquals("Alerta de presion alta", presion.showAlerta());
    }
    @Test
    public void testPresionNormal(){
        Presion presion = new Presion();
        presion.setPresion(200);
        assertEquals("Presion normal", presion.showAlerta());
    }
}
