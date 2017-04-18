package com.finalProject2017.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class Game extends JPanel implements KeyListener, Runnable
{

	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 400;
	public static final int HEIGHT = 630;
	public static final Font main = new Font("Bebas Neue Regular", Font.PLAIN, 28);
	private Thread game;
	private boolean running;
	
	public Game()
	{
		setFocusable(true);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		addKeyListener(this);
	}
	
	public void update()
	{
		if(Keyboard.pressed[KeyEvent.VK_RIGHT])
			System.out.println("Hit Right");
		if(Keyboard.pressed[KeyEvent.VK_LEFT])
			System.out.println("Hit Left");
		
		Keyboard.update();
	}
	
	public void render()
	{
		
	}
	
	@Override
	public void run() 
	{	
		while(running)
		{
			update();
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

	@Override
	public void keyPressed(KeyEvent e) 
	{
		Keyboard.keyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) 
	{
		Keyboard.keyReleased(e);
	}

	@Override
	public void keyTyped(KeyEvent e) 
	{

	}
	
}
