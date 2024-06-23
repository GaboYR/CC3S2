package pc4postfecha.cc3s2;

import org.mockito.Mock;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.assertj.core.api.Assertions.assertThat;
public class CommandFactoryTest {
    @Mock
    CommandFactory commandFactory;

    @Mock
    Command command;

    @Test
    public void testCommandFactory() {
        assertNotNull(commandFactory);
    }

    @Test
    public void testCommand() {
        assertNotNull(command);
    }

}
