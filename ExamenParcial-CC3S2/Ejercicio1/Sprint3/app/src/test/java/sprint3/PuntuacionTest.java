package test.java.sprint3;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class PuntuacionTest {
    public boolean checkCorrectas(int correctas,int usuario){
        assertEquals(correctas, usuario);
    }
    public boolean checkIncorrectas(int incorrectas, int usuario){
        assertEquals(incorrectas, usuario);
    }
    @Test
    public void checkShowQuestions(){
        assertEquals(checkCorrectas(1,1),true);
        assertEquals(checkIncorrectas(1,1),true);
        assertEquals(checkCorrectas(2,1),false);
        assertEquals(checkIncorrectas(2,1),false);
    }
}
