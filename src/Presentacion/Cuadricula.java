package Presentacion;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import logica.JSnake;

public class Cuadricula extends JPanel {
	private JSnake jsnake;

	public void setJSnake(JSnake jsnake) {
		this.jsnake = jsnake;
	}
	public Cuadricula() {

	}

	@Override
	public void paint(Graphics g) {	
		int divHeight = this.getHeight()/20;
		int divWidth = this.getWidth()/20;
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.black);
		g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
		g2d.setColor(Color.white);
		g2d.setStroke(new BasicStroke(0));
		for(int i=0; i<=this.getWidth(); i+=this.getWidth()/20) {
			g2d.drawLine(i, 0, i, this.getHeight());	
		}
		for(int i=0; i<this.getWidth(); i+=this.getWidth()/20) {
			g2d.drawLine(0, i, this.getWidth(), i);
		}
		if(this.jsnake !=null) {
			for(int i=0; i<21;i++) {
				for(int j=0;j<21;j++) {
					if(this.jsnake.getMatriz()[i][j] ==1) {
						g2d.setColor(Color.red);
						g2d.fillRect((j*divWidth)+1, (i*divHeight) +1, divWidth-1, divHeight-1);
					}else if(this.jsnake.getMatriz()[i][j] ==2) {
						g2d.setColor(Color.green);
						g2d.fillRect((j*divWidth)+1, (i*divHeight) +1, divWidth-1, divHeight-1);
					}
				}
			}
		}




	}



}
