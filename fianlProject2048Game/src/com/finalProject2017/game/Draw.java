package com.finalProject2017.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Draw 
{
	public static final int gridHeight = 800;
	public static final int gridWidth = 800;
	public static final int gridThickness = 10;

	
	public static BufferedImage grid;
	
	public static Color gridColor = new Color(196, 196, 196);
	public static Color backgroundColor = new Color(149, 255, 242);
	
	public static void drawGrid()
	{
		Graphics2D g = (Graphics2D)grid.getGraphics();
		grid = new BufferedImage(gridWidth, gridHeight, BufferedImage.TYPE_INT_ARGB);
		
		g.setColor(gridColor);
		g.fillRect(0, 0, 100, gridThickness);
		
		g.dispose();
		
	}
	
	public void update()
	{
		
	}
	
	public void render(Graphics2D g)
	{
		g.drawImage(grid, 350, 0, null);
	}
}
