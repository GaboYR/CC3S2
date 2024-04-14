package actividad.aaa;

public class Calculator {

    public int sumar(int numeroA, int numeroB) {
        return numeroA + numeroB;
    }

    public int restar(int numeroA, int numeroB) {
        return numeroA - numeroB;
    }

    public int multiplicacion(int numeroA, int numeroB) {
        return numeroA * numeroB;
    }

    public double division(int numeroA, int numeroB) {
        if (numeroB == 0) {
            throw new ArithmeticException("Division por cero");
        }
        else {
            if (numeroA == 0) {
                return 0;
            }
        }
        return (double) numeroA / numeroB;
    }
}