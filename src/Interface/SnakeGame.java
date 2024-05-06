package Interface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class SnakeGame extends JPanel {
	
	int boardWidth; 
	int boardHeight;
	int pixelSize = 25;
	Pixel snakeHead;
	
	public SnakeGame(int boardWidth, int boardHeight) {
		this.boardWidth = boardWidth;
		this.boardHeight = boardHeight;
		setPreferredSize(new Dimension(this.boardWidth,this.boardHeight));
		setBackground(Color.black);
		snakeHead = new Pixel(5, 5);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}
	public void draw(Graphics g) {
		//Cuadricula
		for(int i = 0;i< boardWidth/pixelSize; i++) {
			g.drawLine(i * pixelSize,0,i* pixelSize, boardHeight);
			g.drawLine(0, i*pixelSize, boardWidth, i*pixelSize);
		}
		
		//Serpiente
		g.setColor(Color.green);
		g.fillRect(snakeHead.x * pixelSize, snakeHead.y * pixelSize, pixelSize, pixelSize );
		
	}
}
