package Presentacion;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import logica.JSnake;

public class FSnake extends JFrame implements ActionListener{
	private JButton bNuevoJuego;
	private JPanel pElementos;
	private JLabel lManzanas;
	private JLabel lPuntos;
	private JSnake jsnake; 
	private Cuadricula cuadricula;
	private Timer loop;
	private String Direccion;
	public FSnake() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(616,665);
		this.setTitle("Culebrita POO");
		this.setLayout(new BorderLayout());
		this.bNuevoJuego = new JButton("Nuevo Juego");
		this.lManzanas = new JLabel("Manzanas: 0");
		this.lPuntos = new JLabel("Puntos: 0");
		this.pElementos = new JPanel();
		this.pElementos.setLayout(new GridLayout(1,3,10,10));
		this.pElementos.add(this.bNuevoJuego);
		this.pElementos.add(this.lManzanas);
		this.pElementos.add(this.lPuntos);

		loop = new Timer(100,this);
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
	protected void teclaOprimida(KeyEvent e) {
		if(e.getKeyCode() == JSnake.ABA) {
			this.Direccion = "ABA";
		}
		else if(e.getKeyCode() == JSnake.ARR) {
			this.Direccion = "ARR";
		}
	}







	protected void nuevoJuego() {
		this.jsnake = new JSnake();
		this.cuadricula.setJSnake(this.jsnake);
		this.cuadricula.repaint();
		this.cuadricula.setFocusable(true);
		this.cuadricula.requestFocusInWindow();


	}







	public static void main(String[] args) {
		new FSnake();

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(this.jsnake != null) {
			this.jsnake.hacerJugada(this.Direccion);
			this.cuadricula.repaint();
		}


	}
}
