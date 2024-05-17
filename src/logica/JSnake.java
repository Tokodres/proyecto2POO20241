package logica;

public class JSnake {
	private int matriz[][] =  new int[21][21];

	public final static int IZQ = 37;
	public final static int ARR = 38;
	public final static int DER = 39;
	public final static int ABA = 40;

	public int[][] getMatriz() {
		return matriz;
	}

	public JSnake() {
		this.nuevaManzana();
		this.nuevaSerpiente();
	}

	private void nuevaManzana() {
		int fila;
		int col;
		do {
			fila =  valorAleatorio();
			col = valorAleatorio();
		}while(this.matriz[fila][col] != 0);
		this.matriz[fila][col] = 1;
	}
	private void nuevaSerpiente() {
		int fila;
		int col;
		do {
			fila =  valorAleatorio();
			col = valorAleatorio();
		}while(this.matriz[fila][col] != 0 && this.matriz[fila][col] != 1);
		this.matriz[fila][col] = 2;
	}


	private int valorAleatorio() {
		return (int)(Math.random()*20);
	}

	public void hacerJugada(String Direccion) {
		if(Direccion != null) {
			if(Direccion.equalsIgnoreCase("ABA")) {
				for(int i=20; i>=0;i--) {
					for(int j=0;j<=20;j++) {
						if(this.matriz[i][j] ==2) {
							int x=i;
							if(x<20 && this.matriz[x+1][j]==0) {
								this.matriz[x+1][j]=this.matriz[i][j];
								this.matriz[i][j]=0;
							}
						}
					}
				}
			}
			else if(Direccion.equalsIgnoreCase("ARR")) {
				for(int i=0; i<=20;i++) {
					for(int j=20;j<=0;j--) {
						if(this.matriz[j][i] ==2) {
							int x=i;
							if(x<20 && this.matriz[x+1][j]==0) {
								this.matriz[x+1][j]=this.matriz[i][j];
								this.matriz[i][j]=0;
							}
						}
					}
				}
			}

		}
	}
}
