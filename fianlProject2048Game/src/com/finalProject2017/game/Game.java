package com.finalProject2017.game;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class Game extends JPanel implements KeyListener, Runnable
{

	private static final long serialVersionUID = 1L;
	public static final int WINDOW_WIDTH = 640;
	public static final int WINDOW_HEIGHT = 900;
	public static final Font main = new Font("Times New Roman", Font.PLAIN, 28);
	public static final int NAME_X_POS = 20;
	public static final int NAME_Y_POS = 150;
	public static final int HS_X_POS = 420;
	public static final int HS_Y_POS = 100;
	
	private Thread game;
	private BufferedImage image = new BufferedImage(WINDOW_WIDTH, WINDOW_HEIGHT, BufferedImage.TYPE_INT_RGB);;
	private boolean running;
	private boolean shouldRender;
	private Tiles tile;
	private GameBoard board;
	private int gameName;
	private Font gameFont;
	private Font HS_font;
	private Font messageFont;
	private int score;
	
	public Game()
	{
		setFocusable(true);
		setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
		addKeyListener(this);
		
		gameName = 2048;
		gameFont = new Font("", Font.BOLD, 100);
		HS_font = new Font("", Font.BOLD, 36);
		messageFont = new Font("", Font.BOLD, 24);
		
		board = new GameBoard((WINDOW_WIDTH / 2) - 265, 330);
	}
	
	public void update()
	{
		
	}
	
	public void render()
	{
		Graphics2D g = (Graphics2D) image.getGraphics();
		g.setColor(new Color(250, 250, 250));
		g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
		
		g.setFont(gameFont);
		g.setColor(new Color(0, 0, 0));
		g.drawString("" + gameName, NAME_X_POS, NAME_Y_POS);
		
		g.setFont(HS_font);
		g.drawString("SCORE", HS_X_POS, HS_Y_POS);
		
		g.setFont(messageFont);
		g.drawString("PRESS 'R' TO RESTART THE GAME", 30, 250);
		
		
		board.render(g);
		g.dispose();
		
		Graphics2D g2d = (Graphics2D) getGraphics();
		g2d.drawImage(image, 0, 0, null);
		g2d.dispose();
		
	}
	
	public Graphics2D getGameGraphics()
	{
		Graphics2D g = (Graphics2D) image.getGraphics();
		return g;
	}
	
	public void run() 
	{	
		while(running)
		{
			update();
			shouldRender = true;
			
			if(shouldRender)
				render();
		}	
	}
	
	
	public synchronized void start()
	{
		if(running) return;
		running = true;
		game = new Thread(this, "game");	
		game.start();
	}
	
	public synchronized void stop()
	{
		if(!running) return;
		running = false;
		System.exit(0);
	}

	public void keyPressed(KeyEvent e) 
	{
		int keyCode = e.getKeyCode();
		
		if(keyCode == KeyEvent.VK_LEFT)
		{
			GameBoard.moveTilesLeft();
		}	
		if(keyCode == KeyEvent.VK_UP)
		{
			
		}
		
		if(keyCode == KeyEvent.VK_R)
		{
			restart();
		}
	}

	public void keyReleased(KeyEvent e) 
	{
		
	}

	public void keyTyped(KeyEvent e) 
	{

	}
	
	
	public void restart()
	{
		score = 0;
	}
}


// http://stackoverflow.com/questions/10966821/appropriate-way-to-draw-to-a-jpanel-for-2d-game-development
