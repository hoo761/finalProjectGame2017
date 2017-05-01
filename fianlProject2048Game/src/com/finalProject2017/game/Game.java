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
	public static final int WINDOW_WIDTH = 800;
	public static final int WINDOW_HEIGHT = 900;
	public static final Font main = new Font("Times New Roman", Font.PLAIN, 28);
	
	private Thread game;
	private BufferedImage image = new BufferedImage(WINDOW_WIDTH, WINDOW_HEIGHT, BufferedImage.TYPE_INT_RGB);;
	private boolean running;
	private boolean shouldRender;
	private Tiles tile;
	private GameBoard board;
	
	public Game()
	{
		setFocusable(true);
		setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
		addKeyListener(this);
		
		board = new GameBoard(125, 330);
	}
	
	public void update()
	{
		
	}
	
	public void render()
	{
		Graphics2D g = (Graphics2D) image.getGraphics();
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
		board.render(g);
		g.dispose();
		
		Graphics2D g2d = (Graphics2D) getGraphics();
		g2d.drawImage(image, 0, 0, null);
		g2d.dispose();
		
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
			System.out.println("Left");
		}	
		if(keyCode == KeyEvent.VK_RIGHT)
		{
			System.out.println("Right");
		}
	}

	public void keyReleased(KeyEvent e) 
	{
		
	}

	public void keyTyped(KeyEvent e) 
	{

	}
	
}


// http://stackoverflow.com/questions/10966821/appropriate-way-to-draw-to-a-jpanel-for-2d-game-development
