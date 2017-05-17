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
	
<<<<<<< HEAD
	
	private BufferedImage gameBoard;
=======
	private static BufferedImage gameBoard;
>>>>>>> gameBoardGraphics
	private BufferedImage finalBoard;
	private BufferedImage scoreCount;
	
		
	private int x;
	private int y;
	private int score;
	private int x_Score;
	private int y_Score;
	private Font scoreFont;
	
	
	public static boolean spotsTaken[][] = new boolean[4][4];
	public static int tileNums[][] = new int [4][4];
	public static ArrayList<Tiles> tileGrid = new ArrayList<Tiles>();
	
	public GameBoard(int x, int y)
	{
		this.x = x;
		this.y = y;
		score = 0;
		x_Score = 477;
		y_Score = 150;
		scoreFont = new Font("", Font.BOLD, 36);
		
		
		
		gameBoard = new BufferedImage(BOARD_WIDTH, BOARD_HEIGHT, BufferedImage.TYPE_INT_RGB);
		finalBoard = new BufferedImage(BOARD_WIDTH, BOARD_HEIGHT, BufferedImage.TYPE_INT_RGB);
		
		///
		tileNums[0][0] = 2;
		tileNums[0][1] = 2;
		tileNums[1][1] = 2;
		tileNums[1][2] = 2;
		tileNums[0][3] = 2;
		tileNums[1][3] = 2;
		///
		
		createBoardImage();
		//putRandomTile();
		//putRandomTile();
		
		for(int row = 0; row < ROWS; row++)			// For testing
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
		
<<<<<<< HEAD
=======
		g.setColor(Color.BLUE);
		
>>>>>>> gameBoardGraphics
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
	
	public static void createStartingGrid(Graphics2D g)
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
		setTakenSpots();
	}
	
	public void render(Graphics2D g)
	{
		Graphics2D g2d = (Graphics2D) finalBoard.getGraphics();
		g2d.drawImage(gameBoard, 0, 0, null);
		
		g.drawImage(finalBoard, x, y, null);
		g.setFont(scoreFont);
		g.drawString("" + score, x_Score, y_Score);
		g2d.dispose();
	}
	
	
	public static void moveTilesLeft()
	{
		int listSpot = 0;
		
		for(int row = 0; row < ROWS; row++)
		{
			for(int col = 0; col < COLS; col++)
			{
				//System.out.println("(" + row + ", " + col + ")");
				if(col > 0)
				{
					if(spotsTaken[row][col - 1]) // If the tile to the left is tanken
					{
						if(tileNums[row][col] == tileNums[row][col - 1]) // Checks if the numbers are equal
						{
							System.out.print(row + " " + col);
							System.out.println("same");
							
							tileNums[row][col - 1] += tileNums[row][col]; // Adds numbers together
							tileNums[row][col] = 0; // Sets added number to nothing
						}
						
					}
					if(!spotsTaken[row][col - 1])
					{
						System.out.print(row + " " + col + ", ");
						System.out.println("not");
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
