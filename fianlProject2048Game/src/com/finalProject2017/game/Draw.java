package com.finalProject2017.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Draw 
{
	int gridHeight = 800;
	int gridWidth = 800;
	
	BufferedImage grid;
	
	Color gridColor = new Color(0, 0, 0);
	
	public void drawGrid()
	{
		Graphics2D g = (Graphics2D)grid.getGraphics();
		grid = new BufferedImage(gridWidth, gridHeight, BufferedImage.TYPE_INT_ARGB);
		
		g.setColor(gridColor);
		
	}
	
}
