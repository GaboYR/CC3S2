package examenfinal.sprint1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LluviaTest {
    @Test
    public void testLluviaFuerte() {
        Lluvia lluvia = new Lluvia();
        lluvia.setCantidadLluvia(60);
        assertEquals("Alerta de lluvia fuerte", lluvia.showAlerta());
    }
    @Test
    public void testLluviaDebil(){
        Lluvia lluvia = new Lluvia();
        lluvia.setCantidadLluvia(20);
        assertEquals("Lluvia normal", lluvia.showAlerta());
    }
}
