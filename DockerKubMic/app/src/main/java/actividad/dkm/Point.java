package actividad.dkm;

public class Point {
    int x,y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int distance(Point p) {
        return Math.abs(this.x - p.x) + Math.abs(this.y - p.y);
    }
}