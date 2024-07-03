package examenfinal.sprint1;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;


public class TempTestMocks {
    @Mock
    private Temperatura temperaturaService;
    @InjectMocks
    private SistemaGestor sistemaGestorService;
    // implementacion de fake
    @Test
    public void testTemperaturaNormal(){
        Temperatura mockTemperatura = mock(Temperatura.class);
        mockTemperatura.setTemperatura(20);
        verify(mockTemperatura).setTemperatura(20);

    }

    // implementacion de stub
    @Test
    public void testTemperaturaAlta() {
        Temperatura mockTemperatura = mock(Temperatura.class);
        mockTemperatura.setTemperatura(40);
        verify(mockTemperatura).setTemperatura(40);
    }
}
