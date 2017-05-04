package com.finalProject2017.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Tiles 
{
	
	public static final int WIDTH = 125;
	public static final int HEIGHT = 125;
	
	private int value;
	private Color tileColor;
	private Color textColor;
	private Font font;
	private int x;
	private int y;
	private BufferedImage tileImage;
	
	public Tiles(int value, int x, int y)
	{
		this.value = value;
		this.x = x;
		this.y = y;
		font = Game.main;
		tileImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		drawTile();
	}
	
	public void render(Graphics2D g)
	{
		g.drawImage(tileImage, x, y, null);
	}
	
	private void drawTile()
	{
		Graphics2D g = (Graphics2D) tileImage.getGraphics();
		
		if(value == 2)
		{
			tileColor = Color.BLUE;
			textColor = Color.RED;
		}
		else if(value == 4)
		{
			tileColor = Color.BLUE;
			textColor = Color.RED;
		}
		else if(value == 8)
		{
			tileColor = Color.BLUE;
			textColor = Color.RED;
		}
		else if(value == 16)
		{
			tileColor = Color.BLUE;
			textColor = Color.RED;
		}
		else if(value == 32)
		{
			tileColor = Color.BLUE;
			textColor = Color.RED;
		}
		else if(value == 64)
		{
			tileColor = Color.BLUE;
			textColor = Color.RED;
		}
		else if(value == 128)
		{
			tileColor = Color.BLUE;
			textColor = Color.RED;
		}
		else if(value == 256)
		{
			tileColor = Color.BLUE;
			textColor = Color.RED;
		}
		else if(value == 512)
		{
			tileColor = Color.BLUE;
			textColor = Color.RED;
		}
		else if(value == 1024)
		{
			tileColor = Color.BLUE;
			textColor = Color.RED;
		}
		else if(value == 2048)
		{
			tileColor = Color.BLUE;
			textColor = Color.RED;
		}	
		else
		{
			tileColor = Color.BLACK;
			textColor = Color.WHITE;
		}
		
		g.setColor(new Color(0, 0, 0));
		g.fillRect(0,  0, WIDTH, HEIGHT);
		
		g.setColor(tileColor);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		g.setColor(textColor);
		
		if(value <= 64)
		{
			font = Game.main.deriveFont(36f);
		}
		g.setFont(font);
		
		g.drawString("" + value, 40, 40);
		g.dispose();
	}
}
