package Control;

import java.awt.Frame;

import javax.swing.JFrame;

import Interface.SnakeGame;

public class Aplicacion {
	public static void main(String[] args) throws Exception{
		int boardWidth = 600;
		int boardHeight = boardWidth;
		
		JFrame Snake = new JFrame("Culebrita");
		Snake.setVisible(true);
		Snake.setSize(boardWidth,boardHeight);
		Snake.setLocationRelativeTo(null);
		Snake.setResizable(false);
		Snake.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		SnakeGame snakeGame = new SnakeGame(boardWidth, boardHeight);
		Snake.add(snakeGame);
		Snake.pack();
	}
	
}
