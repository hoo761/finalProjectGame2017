package com.finalProject2017.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

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
	public static ArrayList<Tiles> tileGrid = new ArrayList<Tiles>();
	
	public GameBoard(int x, int y)
	{
		this.x = x;
		this.y = y;
		
		gameBoard = new BufferedImage(BOARD_WIDTH, BOARD_HEIGHT, BufferedImage.TYPE_INT_RGB);
		finalBoard = new BufferedImage(BOARD_WIDTH, BOARD_HEIGHT, BufferedImage.TYPE_INT_RGB);
		createBoardImage();
		putRandomTile();
		putRandomTile();
		
		for(int row = 0; row < ROWS; row++)			// Adds all the tiles to the game board
		{
			for(int col = 0; col < COLS; col ++)
			{
				System.out.print(tileNums[col][row] + " ,");
			}
		}
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
		createStartingGrid(g);
	}
	
	public void createStartingGrid(Graphics2D g)
	{
		int listSpot = 0;
		
		for(int row = 0; row < ROWS; row++)			// Adds all the tiles to the game board
		{
			for(int col = 0; col < COLS; col ++)
			{
				int x = SPACING + SPACING * col + Tiles.WIDTH * col;
				int y = SPACING + SPACING * row + Tiles.HEIGHT * row;
				tileGrid.add(new Tiles(tileNums[row][col], x, y));
				tileGrid.get(listSpot).render(g);
				listSpot++;
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
	
	
	public static void moveTilesLeft(Graphics2D g)
	{
		int listSpot = 0;
		
		for(int row = 0; row < ROWS; row++)
		{
			for(int col = 0; col < COLS; col ++)
			{
				int x = SPACING + SPACING * col + Tiles.WIDTH * col;
				int y = SPACING + SPACING * row + Tiles.HEIGHT * row;
				
				if(row != 0)
				{
					if(spotsTaken[row - 1][col]) // If the tile above is tanken
					{
						if(tileNums[row][col] == tileNums[row - 1][col]) // Checks if the numbers are equal
						{
							tileNums[row - 1][col] += tileNums[row][col]; // Add two tiles together
							tileNums[row][col] = 0;
							spotsTaken[row][col] = false; // Set the spot below to not taken
							tileGrid.get(listSpot - 4).value = tileNums[row -1][col]; // Change the value to the new value
						}
						setValues(tileNums[row - 1][col], getXSpot(col), getYSpot(row - 1), listSpot - 4);
						tileGrid.get(listSpot - 4).render(g);
					}
					if(!spotsTaken[row][col])
					{
						switch(row)
						{
							case 0:
							{
								
								break;
							}
							case 1:
							{
								break;
							}
							case 2:
							{
								break;
							}
							case 3:
							{
								break;
							}
						}
					}
				}
				System.out.print(tileNums[col][row] + " ,");
				listSpot++;
			}
		}
		System.out.println();
	}
	
	public static void setValues(int value, int x, int y, int spot)
	{
		tileGrid.get(spot).value = value;
		tileGrid.get(spot).x = x;
		tileGrid.get(spot).y = y;
	}
	
	public void putRandomTile()
	{
		int randomCol = getRandomNumSpot();
		int randomRow = getRandomNumSpot();
		
		if(spotsTaken[randomCol][randomRow])
		{
			putRandomTile();
		}
		else
		{
			tileNums[randomCol][randomRow] = getRandomNumTileNum();
			setTakenSpots();
			
			Graphics2D g = (Graphics2D) gameBoard.getGraphics();
			new Tiles(tileNums[randomCol][randomRow], getXSpot(randomCol), getYSpot(randomRow)).render(g);
		}
		
	}
	
	public static int getXSpot(int randomCol)
	{
		return SPACING + SPACING * randomCol + Tiles.WIDTH * randomCol;
	}
	
	public  static int getYSpot(int randomRow)
	{
		return SPACING + SPACING * randomRow + Tiles.WIDTH * randomRow;
	}
	
	public int getRandomNumSpot()
	{
		int randomNum = (int)(Math.random() * 5 - 1);
		return randomNum;
	}
	
	public int getRandomNumTileNum()
	{
		int random;
		int tile = 2;
		switch(random = (int)(Math.random() * 2 + 1))
		{
			case 1:
				tile = 2;
				break;
			case 2:
				tile = 4;
				break;
		}
		return tile;
	}
	
	public static void setTakenSpots()
	{
		for(int row = 0; row < ROWS; row++)			// Adds all the tiles to the game board
		{
			for(int col = 0; col < COLS; col ++)
			{
				if(tileNums[row][col] >= 2)
				{
					spotsTaken[row][col] = true;
				}
				else
				{
					spotsTaken[row][col] = false;
				}
			}
		}
	}	
}
