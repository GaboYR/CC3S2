package examenfinal.sprint2;

import org.junit.jupiter.api.Test;

public class Sprint2Test {
    @Test
    public void testShowActionViento(){
        Viento accion = new Viento();
        assert(accion.showAction().equals("Cerrar persianas"));
    
    }
    @Test
    public void testNotificationViento() {
        Viento notificaciones = new Viento();
        assert(notificaciones.showNotification().equals("Notificación de viento"));
    }
}
