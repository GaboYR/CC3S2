package pc4postfecha.cc3s2;

public class CommandFactory implements Command {
    Mapa mapa = new Mapa();
    @Override
    public void execute(String[] command) {
        if (command[1].equals("Cannon")) {
            mapa.addCannonTower(Integer.parseInt(command[2]), Integer.parseInt(command[3]));
            System.out.println("Torre Cannon colocada en ("+ command[2]+" , "+command[3] + ")");
        }
        else if (command[1].equals("Laser")) {
            mapa.addLaserTower(Integer.parseInt(command[2]), Integer.parseInt(command[3]));
            System.out.println("Torre Laser colocada en ("+ command[2]+" , "+command[3] + ")");
        }
        else if (command[1].equals("Arrow")) {
            mapa.addArrowTower(Integer.parseInt(command[2]), Integer.parseInt(command[3]));
            System.out.println("Torre Arrow colocada en ("+ command[2]+" , "+command[3] + ")");
        }
    }
}
