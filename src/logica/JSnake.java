package logica;

import java.util.ArrayList;

public class JSnake {
    private int matriz[][] = new int[20][20];
    public final static int IZQ = 37;
    public final static int ARR = 38;
    public final static int DER = 39;
    public final static int ABA = 40;
    private int filaManzana;
    private int colManzana;
    private boolean juegoTerminado = false;

    private ArrayList<Cordenadas> cuerpoSerpiente = new ArrayList<>();

    public ArrayList<Cordenadas> getCuerpoSerpiente() {
        return cuerpoSerpiente;
    }

    public int[][] getMatriz() {
        return matriz;
    }

    public boolean isJuegoTerminado() {
        return juegoTerminado;
    }

    public JSnake() {
        this.nuevaManzana();
        this.nuevaSerpiente();
    }

    private void nuevaManzana() {
        int fila;
        int col;
        do {
            fila = valorAleatorio();
            col = valorAleatorio();
        } while (this.matriz[fila][col] != 0);
        this.filaManzana = fila;
        this.colManzana = col;
        this.matriz[fila][col] = 1;
    }

    private void nuevaSerpiente() {
        int fila;
        int col;
        do {
            fila = valorAleatorio();
            col = valorAleatorio();
        } while (this.matriz[fila][col] != 0 && this.matriz[fila][col] != 1);
        this.matriz[fila][col] = 2;
        cuerpoSerpiente.add(new Cordenadas(fila, col));
    }

    private int valorAleatorio() {
        return (int) (Math.random() * 20);
    }

    public void hacerJugada(String Direccion) {
        if (!juegoTerminado) {
            this.hacerMovimiento(Direccion);
        }
    }

    public void hacerMovimiento(String Direccion) {
        if (Direccion != null && !Direccion.isEmpty()) {
            int dirFila = 0;
            int dirCol = 0;

            if (Direccion.equalsIgnoreCase("ABA")) {
                dirFila = 1;
            } else if (Direccion.equalsIgnoreCase("ARR")) {
                dirFila = -1;
            } else if (Direccion.equalsIgnoreCase("IZQ")) {
                dirCol = -1;
            } else if (Direccion.equalsIgnoreCase("DER")) {
                dirCol = 1;
            }

            Cordenadas cabeza = cuerpoSerpiente.get(0);
            int nuevaFila = cabeza.getFila() + dirFila;
            int nuevaCol = cabeza.getColumna() + dirCol;

            // Verificar colisión con la pared
            if (nuevaFila < 0 || nuevaFila >= 20 || nuevaCol < 0 || nuevaCol >= 20) {
                juegoTerminado = true;
                return;
            }

            // Verificar colisión con el cuerpo
            if (matriz[nuevaFila][nuevaCol] == 2) {
                juegoTerminado = true;
                return;
            }

            if (matriz[nuevaFila][nuevaCol] == 1) { // Comer manzana
                cuerpoSerpiente.add(0, new Cordenadas(nuevaFila, nuevaCol));
                matriz[nuevaFila][nuevaCol] = 2;
                nuevaManzana();
            } else if (matriz[nuevaFila][nuevaCol] == 0) { // Movimiento normal
                cuerpoSerpiente.add(0, new Cordenadas(nuevaFila, nuevaCol));
                matriz[nuevaFila][nuevaCol] = 2;
                Cordenadas cola = cuerpoSerpiente.remove(cuerpoSerpiente.size() - 1);
                matriz[cola.getFila()][cola.getColumna()] = 0;
            }
        }
    }
}
