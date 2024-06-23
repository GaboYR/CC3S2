package pc4postfecha.cc3s2;

public class Mapa {
    public char[][] mapa = {{' ',' ','1',' ',' '},
                            {' ','1',' ',' ',' '},
                            {'1',' ',' ','1','2'},
                            {' ','1',' ',' ',' '},
                            {' ',' ',' ',' ',' '}};
    // Metodos
    public boolean isValidPosition(int x, int y) {
        return x >= 0 && x < mapa.length && y >= 0 && y < mapa[0].length;
    }   
    public void placeTower(Tower tower, int x, int y) {
        if (isValidPosition(x, y)) {
            mapa[x][y] = 'T';
        }
    }
    public void printMapa() {
        System.out.println("Mapa:");
        for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j < mapa[i].length; j++) {
                System.out.print("[" + mapa[i][j] + "]");
            }
            System.out.println();
        }
    }
    // Ubicar la base en el mapa
    public Point getBase() {
        for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j < mapa[i].length; j++) {
                if (mapa[i][j] == '2') {
                    return new Point(i, j);
                }
            }
        }
        return null;
    }
    // Addtower por cada hijo
    public void addCannonTower(int x,int y) {
        mapa[x][y] = 'C';
    }
    public void addLaserTower(int x,int y) {
        mapa[x][y] = 'L';
    }
    public void addArrowTower(int x,int y) {
        mapa[x][y] = 'A';
    }
    public void removeTower(int x,int y) {
        mapa[x][y] = ' ';
    }

}
