/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package practicacalificada4.cc3s2;

public class Game {
    private Mapa mapa;
    public static void main(String[] args) {
        // Inicializamos el mapa
        Mapa mapa = new Mapa();
        //Player player = new Player();
        String userCommand;
        
        do{
            CommandFactory commandFactory = new CommandFactory();
            System.out.println("Ingrese un comando: \n" +
                    "START_WAVE\n" +
                    "PLACE_TOWER TYPE_TOWER x y\n" +
                    "exit\n");
            userCommand = System.console().readLine();
            if (userCommand.equals("START_WAVE")) {
                mapa.printMapa();
            }
            else if (userCommand.startsWith("PLACE_TOWER")) {
                // PLACE_TOWER Cannon 3 4
                String[] command = userCommand.split(" ");
                commandFactory.execute(command);
            }
        }while (!userCommand.equals("exit"));
    }
    public void placeTower(Tower tower, int x, int y) {
        if (mapa.isValidPosition(x, y)) {
            mapa.placeTower(tower, x, y);
        }
    }
}