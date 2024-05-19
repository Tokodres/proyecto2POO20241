package logica;

import java.util.ArrayList;

public class JSnake {
	private int matriz[][] =  new int[20][20];
	public final static int IZQ = 37;
	public final static int ARR = 38;
	public final static int DER = 39;
	public final static int ABA = 40;
	private int filaManzana;
	private int ColManzana;
	private int prevCabezaFila;
	private int prevCabezaCol;

	private ArrayList<Cordenadas> cuerpoSerpiente = new ArrayList<Cordenadas>();

	public ArrayList<Cordenadas> getCuerpoSerpiente() {
		return cuerpoSerpiente;
	}

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
		this.filaManzana = fila;
		this.ColManzana = col;
		this.matriz[fila][col] = 1;
	}
	private void nuevaSerpiente() {
		int fila = 10;
		int col = 4;
		this.matriz[fila][col] = 2;
	}


	private int valorAleatorio() {
		return (int)(Math.random()*20);
	}
	public void hacerJugada(String Direccion) {
		this.hacerMovimiento(Direccion);
	}

	public void hacerMovimiento(String Direccion) {
		if(Direccion != null) {
			if(Direccion.equalsIgnoreCase("ABA")) {
				for(int i=19; i>=0;i--) {
					for(int j=0;j<20;j++) {
						if(this.matriz[i][j] ==2) {
							int x=i;
							int prevI = i;
							int prevJ = j;
							if(x+1 <20) {
								if(x<20 && this.matriz[x+1][j]==0) {
									this.matriz[x+1][j]=this.matriz[i][j];
								}else if(this.matriz[x+1][j]==1) {
									cuerpoSerpiente.add(new Cordenadas(this.filaManzana,this.ColManzana));

									this.matriz[x+1][j] = 0;
									this.matriz[x+1][j] =this.matriz[i][j];
									this.nuevaManzana();
								}
								this.matriz[prevI][prevJ] = 2;
								this.matriz[i][j]=0;
							}
						}
					}
				}
			}
			else if (Direccion.equalsIgnoreCase("ARR")) {
				for (int i = 0; i < 20; i++) {
					for (int j = 0; j < 20; j++) {
						if (this.matriz[i][j] == 2) {
							int x = i;
							if (x > 0 && this.matriz[x - 1][j] == 0) { 
								this.matriz[x - 1][j] = this.matriz[i][j]; 
								this.matriz[i][j] = 0; 
							}
						}
					}
				}
			}else if (Direccion.equalsIgnoreCase("IZQ")) {
				for (int i = 0; i < 20; i++) {
					for (int j = 0; j < 20; j++) {
						if (matriz[i][j] == 2) {
							int x = j;
							if (x > 0 && matriz[i][x - 1] == 0) { 
								matriz[i][x-1] = this.matriz[i][j];
								matriz[i][j] = 0; 
							}
						}
					}
				}  
			}else if (Direccion.equalsIgnoreCase("DER")) {
				for (int i = 0; i < 20; i++) {
					for (int j = 19; j >= 0; j--) {
						if (matriz[i][j] == 2) {
							int x = j;
							if(x+1 <20) {
								if (x < 20 && matriz[i][x + 1] == 0) { 
									matriz[i][x+1] = this.matriz[i][j];
									matriz[i][j] = 0; 
								}
							}
						}
					}
				}  
			}
		}	
	}


}
