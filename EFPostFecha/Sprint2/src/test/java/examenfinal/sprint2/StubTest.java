package examenfinal.sprint2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class StubTest {
    @Mock
    Temperatura temperatura = mock(Temperatura.class);
    @Mock
    Lluvia lluvia = mock(Lluvia.class);
    @Mock
    Temperatura temperaturaFake = mock(Temperatura.class);
    // Test para lluvia fuerte con stub
    @Test
    public void testTemperatura() {
        when(temperatura.showAlerta()).thenReturn("Alerta de temperatura alta");
        when(temperatura.showNotification()).thenReturn("Notificacion de temperatura");
        when(temperatura.showAction()).thenReturn("Activar sistema de riego");

        assertEquals("Alerta de temperatura alta", temperatura.showAlerta());
        assertEquals("Notificacion de temperatura", temperatura.showNotification());
        assertEquals("Activar sistema de riego", temperatura.showAction());
    }
    
    @Test
    public void testLluvia() {
        when(lluvia.showAlerta()).thenReturn("Alerta de lluvia fuerte");
        when(lluvia.showNotification()).thenReturn("Notificacion de lluvia");
        when(lluvia.showAction()).thenReturn("Activar sistema de riego");

        assertEquals("Alerta de lluvia fuerte", lluvia.showAlerta());
        assertEquals("Notificacion de lluvia", lluvia.showNotification());
        assertEquals("Activar sistema de riego", lluvia.showAction());
    }

    // Implementacion de fakes
    @Test
    public void testTemperaturaFake() {
        Temperatura temperaturaFake = new Temperatura();
        temperaturaFake.setTemperatura(35);
        assertEquals("Alerta de temperatura alta", temperaturaFake.showAlerta());
        assertEquals("Notificacion de temperatura", temperaturaFake.showNotification());
        assertEquals("Activar sistema de riego", temperaturaFake.showAction());
    }
    @Test
    public void testLluviaFake() {
        Lluvia lluviaFake = new Lluvia();
        lluviaFake.setCantidadLluvia(60);
        assertEquals("Alerta de lluvia fuerte", lluviaFake.showAlerta());
        assertEquals("Notificacion de lluvia", lluviaFake.showNotification());
        assertEquals("Activar sistema de riego", lluviaFake.showAction());
    }
}
