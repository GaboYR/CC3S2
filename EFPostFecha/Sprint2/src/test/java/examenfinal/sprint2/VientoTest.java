package examenfinal.sprint2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VientoTest {
    @Test
    public void testVientoFuerte() {
        Viento viento = new Viento();
        viento.setVelocidadViento(50);
        assertEquals("Alerta de viento fuerte", viento.showAlerta());
    }
    @Test
    public void testVientoNormal(){
        Viento viento = new Viento();
        viento.setVelocidadViento(20);
        assertEquals("Viento normal", viento.showAlerta());
    }
        
}
