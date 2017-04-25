package com.finalProject2017.game;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class Game extends JPanel implements KeyListener, Runnable
{

	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 800;
	public static final int HEIGHT = 900;
	public static final Font main = new Font("Times New Roman", Font.PLAIN, 28);
	private Thread game;
	private boolean running;
	
	public Game()
	{
		setFocusable(true);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		addKeyListener(this);
		setBackground(Color.BLUE);
	}
	
	public void update()
	{
		
	}
	
	public void render()
	{
		
	}
	
	public void run() 
	{	
		while(running)
		{
			update();
		}	
	}
	
	public void paintComponent(Graphics g) 
	{
		Graphics2D g2d = (Graphics2D) g;
		
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
			System.out.println("Left");
		if(keyCode == KeyEvent.VK_RIGHT)
			System.out.println("Right");
	}

	public void keyReleased(KeyEvent e) 
	{
		
	}

	public void keyTyped(KeyEvent e) 
	{

	}
	
}
