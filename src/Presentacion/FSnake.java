package Presentacion;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;

import Logica.JSnake;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.TimerTask;


public class FSnake extends JFrame implements ActionListener {
	private JButton bNuevoJuego;
	private JPanel pElementos;
	public JLabel lManzanas;
	private JLabel lPuntos;
	private JLabel lTiempo;
	private int segundos = 0;
	private static int manzanas = 0;
	private static int puntuacion = 0;
	private JSnake jsnake;
	private Cuadricula cuadricula;
	private Timer loop;
	private String Direccion;
	private TimerTask timerTask;
	private ArrayList<Integer> puntajes = new ArrayList<Integer>();
	
	public static void main(String[] args) {
		new FSnake();
	}
	public static void incrementManzanas() {
		manzanas++;
		puntuacion++;
	}
	public FSnake() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(617, 666);
		this.setTitle("Culebrita POO");
		this.setLayout(new BorderLayout());
		this.bNuevoJuego = new JButton("Nuevo Juego");
		this.lManzanas = new JLabel("Manzanas: 0 ");
		this.lPuntos = new JLabel("Puntos: 0");
		this.lTiempo = new JLabel("Tiempo: "+this.segundos);
		this.pElementos = new JPanel();
		this.pElementos.setLayout(new GridLayout(1, 4, 10, 10));
		this.pElementos.add(this.bNuevoJuego);
		this.pElementos.add(this.lManzanas);
		this.pElementos.add(this.lPuntos);
		this.pElementos.add(this.lTiempo);

		loop = new javax.swing.Timer(125, this);
		loop.start();

		this.bNuevoJuego.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Direccion = null;
				nuevoJuego();
			}
		});
		this.cuadricula = new Cuadricula();
		this.add(this.pElementos, BorderLayout.NORTH);
		this.add(this.cuadricula, BorderLayout.CENTER);
		this.cuadricula.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {}

			@Override
			public void keyReleased(KeyEvent e) {
				teclaOprimida(e);
			}

			@Override
			public void keyPressed(KeyEvent e) {}
		});

		this.setResizable(false);
		this.setVisible(true);
	}
	protected void actualizarTiempo() {
		this.segundos++;
		this.lTiempo.setText("Tiempo: "+this.segundos);
	}


	protected void teclaOprimida(KeyEvent e) {
		if (e.getKeyCode() == JSnake.ABA) {
			this.Direccion = "ABA";
		} else if (e.getKeyCode() == JSnake.ARR) {
			this.Direccion = "ARR";
		} else if (e.getKeyCode() == JSnake.IZQ) {
			this.Direccion = "IZQ";
		} else if (e.getKeyCode() == JSnake.DER) {
			this.Direccion = "DER";
		}
	}

	protected void nuevoJuego() {
		if(this.puntuacion != 0) {
			this.puntajes.add(this.puntuacion);
		}
		this.puntuacion = 0;
		this.segundos = 0;
		this.manzanas = 0;
		this.jsnake = new JSnake();
		this.cuadricula.setJSnake(this.jsnake);
		this.cuadricula.repaint();
		this.cuadricula.setFocusable(true);
		this.cuadricula.requestFocusInWindow();
		this.tiempo();
		loop.start();
	}
	public void tiempo() {
		if(this.timerTask !=null) {
			this.timerTask.cancel();
		}
		this.timerTask = new TimerTask() {
			@Override
			public void run() {
				actualizarTiempo();
			}
		};
		java.util.Timer timer = new java.util.Timer();
		timer.scheduleAtFixedRate(this.timerTask, 0, 1000);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (this.jsnake != null) {
			this.jsnake.hacerJugada(this.Direccion);


			if (this.jsnake.isJuegoTerminado()) {
				loop.stop();
				this.timerTask.cancel();
				JOptionPane.showMessageDialog(this, "Game Over!");

			} else {
				this.cuadricula.repaint();
				this.lManzanas.setText("Manzanas: "+this.manzanas);
				this.lPuntos.setText("Puntos: "+this.puntuacion);
			}


		}
	}
	
}
