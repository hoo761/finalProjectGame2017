package com.finalProject2017.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class GameBoard 
{
	
	public static final int ROWS = 4;
	public static final int COLS = 4;
	public static final int SPACING = 10;
	public static final int BOARD_HEIGHT = (COLS + 1) * SPACING + COLS * Tiles.WIDTH;
	public static final int BOARD_WIDTH = (ROWS + 1) * SPACING + ROWS * Tiles.WIDTH;
	
	private BufferedImage gameBoard;
	private BufferedImage finalBoard;
	
	private int x;
	private int y;
	
	public static boolean spotsTaken[][] = new boolean[4][4];
	public static int tileNums[][] = new int [4][4];
	
	public GameBoard(int x, int y)
	{
		this.x = x;
		this.y = y;
		
		gameBoard = new BufferedImage(BOARD_WIDTH, BOARD_HEIGHT, BufferedImage.TYPE_INT_RGB);
		finalBoard = new BufferedImage(BOARD_WIDTH, BOARD_HEIGHT, BufferedImage.TYPE_INT_RGB);
		
		
		tileNums[0][0] = 2;
		tileNums[0][1] = 4;
		tileNums[0][2] = 8;
		tileNums[0][3] = 16;
		tileNums[1][0] = 32;
		tileNums[1][1] = 64;
		tileNums[1][2] = 128;
		tileNums[1][3] = 256;
		tileNums[2][0] = 512;
		tileNums[2][1] = 1024;
		tileNums[2][2] = 2048;
		tileNums[2][3] = 0;
		tileNums[3][0] = 512;
		tileNums[3][1] = 1024;
		tileNums[3][2] = 2048;
		tileNums[3][3] = 0;
		
		
		createBoardImage();
	}
	
	private void createBoardImage()
	{
		Graphics2D g = (Graphics2D) gameBoard.getGraphics();
		g.setColor(new Color(50, 50, 50));
		g.fillRect(0, 0, BOARD_WIDTH, BOARD_HEIGHT);
		
		g.setColor(Color.BLUE);
		
		for(int row = 0; row < ROWS; row++)			// Creates all the spaces for the tiles
		{
			for(int col = 0; col < COLS; col ++)
			{
				int x = SPACING + SPACING * col + Tiles.WIDTH * col;
				int y = SPACING + SPACING * row + Tiles.HEIGHT * row;
				g.fillRect(x, y, Tiles.WIDTH, Tiles.HEIGHT);
			}
		}
		
		for(int row = 0; row < ROWS; row++)			// Adds all the tiles to the game board
		{
			for(int col = 0; col < COLS; col ++)
			{
				int x = SPACING + SPACING * col + Tiles.WIDTH * col;
				int y = SPACING + SPACING * row + Tiles.HEIGHT * row;
				new Tiles(tileNums[row][col], x, y).render(g);
			}
		}
	}
	
	public void render(Graphics2D g)
	{
		Graphics2D g2d = (Graphics2D) finalBoard.getGraphics();
		g2d.drawImage(gameBoard, 0, 0, null);
		
		g.drawImage(finalBoard, x, y, null);
		g2d.dispose();
	}
	
	
}
