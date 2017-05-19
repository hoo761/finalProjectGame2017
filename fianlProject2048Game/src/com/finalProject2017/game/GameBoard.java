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
	
	private static BufferedImage gameBoard;
	
	private BufferedImage finalBoard;
	private BufferedImage scoreCount;
	
		
	private int x;
	private int y;
	private int score;
	private int x_Score;
	private int y_Score;
	private Font scoreFont;
	
	private static boolean shouldRender;
	private static boolean gridRefreshed;
	
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
		
		createBoardImage();
	}
	
	private void createBoardImage()
	{
		Graphics2D g = (Graphics2D) gameBoard.getGraphics();
		g.setColor(new Color(50, 50, 50));
		g.fillRect(0, 0, BOARD_WIDTH, BOARD_HEIGHT);
		
		g.setColor(Color.CYAN);
		
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
				listSpot++;
			}
		}
		putRandomTile();
		putRandomTile();
		setTakenSpots();
		shouldRender = true;
		gridRefreshed = true;
		refreshGrid();
	}
	
	public static void refreshGrid()
	{
		int listSpot = 0;
		gridRefreshed = false;
		shouldRender = false;
		tileGrid.clear();
		for(int row = 0; row < ROWS; row++)	
		{
			for(int col = 0; col < COLS; col ++)
			{
				int x = SPACING + SPACING * col + Tiles.WIDTH * col;
				int y = SPACING + SPACING * row + Tiles.HEIGHT * row;
				
				tileGrid.add(new Tiles(tileNums[row][col], x, y));
				listSpot++;
			}
		}
		
		if(tileGrid.size() == 16)
		{
			shouldRender = true;
			gridRefreshed = true;
		}
		setTakenSpots();
		
		
		for(int row = 0; row < ROWS; row++)			// For testing
		{
			for(int col = 0; col < COLS; col ++)
			{
				System.out.print(tileNums[row][col] + " ,");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public void render(Graphics2D g)
	{
		BufferedImage finalBoard = new BufferedImage(BOARD_WIDTH, BOARD_HEIGHT, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = (Graphics2D) finalBoard.getGraphics();
		g2d.drawImage(gameBoard, 0, 0, null);
		
		if(shouldRender)
		{	
			gridRefreshed = false;
			int spot = 0;
			for (int row = 0; row < ROWS; row++) 
			{
				for (int col = 0; col < COLS; col++) 
				{
					tileGrid.get(spot).render(g2d);
					spot++;
				}
			}
			gridRefreshed = true;
		}
		g.drawImage(finalBoard, x, y, null);
		g.setFont(scoreFont);
		g.drawString("" + score, x_Score, y_Score);
		g2d.dispose();
	}
	
	
	public static void moveTilesLeft()
	{
		
		for(int row = 0; row < ROWS; row++)
		{
			for(int col = 0; col < COLS; col++)
			{
				if(col > 0 && tileNums[row][col] > 0)
				{
					if(spotsTaken[row][col - 1]) // If the tile to the left is tanken
					{
						if(tileNums[row][col] == tileNums[row][col - 1]) // Checks if the numbers are equal
						{
							tileNums[row][col - 1] += tileNums[row][col]; // Adds numbers together
							tileNums[row][col] = 0; // Sets added number to nothing
							setTakenSpots();
						}
						
					}
					if(!spotsTaken[row][col - 1] && col == 1 && tileNums[row][col - 1] == 0) // col 1
					{
						tileNums[row][col - 1] = tileNums[row][col];
						tileNums[row][col] = 0;
						setTakenSpots();
					}
					else if(!spotsTaken[row][col - 1] && col == 2 && tileNums[row][col - 1] == 0) // col 2
					{
						tileNums[row][col - 1] = tileNums[row][col];
						tileNums[row][col] = 0;
						setTakenSpots();
						if(!spotsTaken[row][col - 2] && tileNums[row][col - 2] == 0)
						{
							tileNums[row][col - 2] = tileNums[row][col - 1];
							tileNums[row][col - 1] = 0;
							setTakenSpots();
						}
						else if(spotsTaken[row][col - 2] && tileNums[row][col - 2] == tileNums[row][col - 1])
						{
							tileNums[row][col - 2] += tileNums[row][col - 1];
							tileNums[row][col - 1] = 0;
							setTakenSpots();	
						}
					}
					else if(!spotsTaken[row][col - 1] && col == 3 && tileNums[row][col - 1] == 0) // col 3
					{
						tileNums[row][col - 1] = tileNums[row][col];
						tileNums[row][col] = 0;
						setTakenSpots();
						if(!spotsTaken[row][col - 2] && tileNums[row][col - 2] == 0)
						{
							tileNums[row][col - 2] = tileNums[row][col - 1];
							tileNums[row][col - 1] = 0;
							setTakenSpots();
							if(!spotsTaken[row][col - 3] && tileNums[row][col - 3] == 0)
							{
								tileNums[row][col - 3] = tileNums[row][col - 2];
								tileNums[row][col - 2] = 0;
								setTakenSpots();
							}
							else if(spotsTaken[row][col - 3] && tileNums[row][col - 3] == tileNums[row][col - 2])
							{
								tileNums[row][col - 3] += tileNums[row][col - 2];
								tileNums[row][col - 2] = 0;
								setTakenSpots();
							}
						}
						else if(spotsTaken[row][col - 2] && tileNums[row][col - 2] == tileNums[row][col - 1])
						{
							tileNums[row][col - 2] += tileNums[row][col - 1];
							tileNums[row][col - 1] = 0;
							setTakenSpots();
						}
					}
				}			
			}
		}
		putRandomTile();
		refreshGrid();
	}
	
	public static void moveTilesRight()
	{
		
		for(int row = ROWS - 1; row > -1; row--)
		{
			for(int col = COLS - 1; col > -1; col--)
			{
				if(col < 3 && tileNums[row][col] > 0)
				{
					if(spotsTaken[row][col + 1]) // If the tile to the Right is tanken
					{
						if(tileNums[row][col] == tileNums[row][col + 1]) // Checks if the numbers are equal
						{
							tileNums[row][col + 1] += tileNums[row][col]; // Adds numbers together
							tileNums[row][col] = 0; // Sets added number to nothing
							setTakenSpots();
						}
						
					}
					else if(!spotsTaken[row][col + 1] && col == 2 && tileNums[row][col + 1] == 0) // col 2
					{
						tileNums[row][col + 1] = tileNums[row][col];
						tileNums[row][col] = 0;
						setTakenSpots();
					}
					else if(!spotsTaken[row][col + 1] && col == 1 && tileNums[row][col + 1] == 0) // col 1
					{
						tileNums[row][col + 1] = tileNums[row][col];
						tileNums[row][col] = 0;
						setTakenSpots();
						if(!spotsTaken[row][col + 2] && tileNums[row][col + 2] == 0)
						{
							tileNums[row][col + 2] = tileNums[row][col + 1];
							tileNums[row][col + 1] = 0;
							setTakenSpots();
						}
						else if(spotsTaken[row][col + 2] && tileNums[row][col + 2] == tileNums[row][col + 1])
						{
							tileNums[row][col + 2] += tileNums[row][col + 1];
							tileNums[row][col + 1] = 0;
							setTakenSpots();	
						}
					}
					else if(!spotsTaken[row][col + 1] && col == 0 && tileNums[row][col + 1] == 0) // col 0
					{
						tileNums[row][col + 1] = tileNums[row][col];
						tileNums[row][col] = 0;
						setTakenSpots();
						if(!spotsTaken[row][col + 2] && tileNums[row][col + 2] == 0)
						{
							tileNums[row][col + 2] = tileNums[row][col + 1];
							tileNums[row][col + 1] = 0;
							setTakenSpots();
							if(!spotsTaken[row][col + 3] && tileNums[row][col + 3] == 0)
							{
								tileNums[row][col + 3] = tileNums[row][col + 2];
								tileNums[row][col + 2] = 0;
								setTakenSpots();
							}
							else if(spotsTaken[row][col + 3] && tileNums[row][col + 3] == tileNums[row][col + 2])
							{
								tileNums[row][col + 3] += tileNums[row][col + 2];
								tileNums[row][col + 2] = 0;
								setTakenSpots();
							}
						}
						else if(spotsTaken[row][col + 2] && tileNums[row][col + 2] == tileNums[row][col + 1])
						{
							tileNums[row][col + 2] += tileNums[row][col + 1];
							tileNums[row][col + 1] = 0;
							setTakenSpots();
							if(!spotsTaken[row][col + 3] && tileNums[row][col + 3] == 0)
							{
								tileNums[row][col + 3] = tileNums[row][col + 2];
								tileNums[row][col + 2] = 0;
								setTakenSpots();
							}
							else if(spotsTaken[row][col + 3] && tileNums[row][col + 3] == tileNums[row][col + 2])
							{
								tileNums[row][col + 3] += tileNums[row][col + 2];
								tileNums[row][col + 2] = 0;
								setTakenSpots();
							}
						}
					}
				}			
			}
		}
		putRandomTile();
		refreshGrid();
	}
	
	public static void moveTilesUp()
	{
		for(int col = 0; col < COLS; col++)
		{
			for(int row = 0; row < ROWS; row++)
			{
				if(row > 0 && tileNums[row][col] > 0)
				{
					if(spotsTaken[row - 1][col]) // If the tile to the left is tanken
					{
						if(tileNums[row][col] == tileNums[row - 1][col]) // Checks if the numbers are equal
						{
							tileNums[row - 1][col] += tileNums[row][col]; // Adds numbers together
							tileNums[row][col] = 0; // Sets added number to nothing
							setTakenSpots();
						}
						
					}
					if(!spotsTaken[row - 1][col] && row == 1 && tileNums[row - 1][col] == 0) // col 1
					{
						tileNums[row - 1][col] = tileNums[row][col];
						tileNums[row][col] = 0;
						setTakenSpots();
					}
					else if(!spotsTaken[row - 1][col] && row == 2 && tileNums[row - 1][col] == 0) // col 2
					{
						tileNums[row - 1][col] = tileNums[row][col];
						tileNums[row][col] = 0;
						setTakenSpots();
						if(!spotsTaken[row - 2][col] && tileNums[row - 2][col] == 0)
						{
							tileNums[row - 2][col] = tileNums[row - 1][col];
							tileNums[row -1][col] = 0;
							setTakenSpots();
						}
						else if(spotsTaken[row - 2][col] && tileNums[row - 2][col] == tileNums[row - 1][col])
						{
							tileNums[row - 2][col] += tileNums[row - 1][col];
							tileNums[row - 1][col] = 0;
							setTakenSpots();	
						}
					}
					else if(!spotsTaken[row - 1][col] && row == 3 && tileNums[row - 1][col] == 0) // col 3
					{
						tileNums[row - 1][col] = tileNums[row][col];
						tileNums[row][col] = 0;
						setTakenSpots();
						if(!spotsTaken[row - 2][col] && tileNums[row - 2][col] == 0)
						{
							tileNums[row - 2][col] = tileNums[row - 1][col];
							tileNums[row - 1][col] = 0;
							setTakenSpots();
							if(!spotsTaken[row - 3][col] && tileNums[row - 3][col] == 0)
							{
								tileNums[row - 3][col] = tileNums[row - 2][col];
								tileNums[row - 2][col] = 0;
								setTakenSpots();
							}
							else if(spotsTaken[row - 3][col] && tileNums[row - 3][col] == tileNums[row - 2][col])
							{
								tileNums[row - 3][col] += tileNums[row - 2][col];
								tileNums[row - 2][col] = 0;
								setTakenSpots();
							}
						}
						else if(spotsTaken[row - 2][col] && tileNums[row - 2][col] == tileNums[row - 1][col])
						{
							tileNums[row - 2][col] += tileNums[row - 1][col];
							tileNums[row - 1][col] = 0;
							setTakenSpots();
						}
					}
				}			
			}
		}
		putRandomTile();
		refreshGrid();
	}
	
	public static void moveTilesDown()
	{
		for(int col = COLS - 1; col > -1; col--)
		{
			for(int row = ROWS - 1; row > -1; row--)
			{
				if(row < 3 && tileNums[row][col] > 0)
				{
					if(spotsTaken[row + 1][col]) // If the tile to the left is tanken
					{
						if(tileNums[row][col] == tileNums[row + 1][col]) // Checks if the numbers are equal
						{
							tileNums[row + 1][col] += tileNums[row][col]; // Adds numbers together
							tileNums[row][col] = 0; // Sets added number to nothing
							setTakenSpots();
						}
						
					}
					if(!spotsTaken[row + 1][col] && row == 2 && tileNums[row + 1][col] == 0) // col 1
					{
						tileNums[row + 1][col] = tileNums[row][col];
						tileNums[row][col] = 0;
						setTakenSpots();
					}
					else if(!spotsTaken[row + 1][col] && row == 1 && tileNums[row + 1][col] == 0) // col 2
					{
						tileNums[row + 1][col] = tileNums[row][col];
						tileNums[row][col] = 0;
						setTakenSpots();
						if(!spotsTaken[row + 2][col] && tileNums[row + 2][col] == 0)
						{
							tileNums[row + 2][col] = tileNums[row + 1][col];
							tileNums[row +1][col] = 0;
							setTakenSpots();
						}
						else if(spotsTaken[row + 2][col] && tileNums[row + 2][col] == tileNums[row + 1][col])
						{
							tileNums[row + 2][col] += tileNums[row + 1][col];
							tileNums[row + 1][col] = 0;
							setTakenSpots();	
						}
					}
					else if(!spotsTaken[row + 1][col] && row == 0 && tileNums[row + 1][col] == 0) // col 3
					{
						tileNums[row + 1][col] = tileNums[row][col];
						tileNums[row][col] = 0;
						setTakenSpots();
						if(!spotsTaken[row + 2][col] && tileNums[row + 2][col] == 0)
						{
							tileNums[row + 2][col] = tileNums[row + 1][col];
							tileNums[row + 1][col] = 0;
							setTakenSpots();
							if(!spotsTaken[row + 3][col] && tileNums[row + 3][col] == 0)
							{
								tileNums[row + 3][col] = tileNums[row + 2][col];
								tileNums[row + 2][col] = 0;
								setTakenSpots();
							}
							else if(spotsTaken[row + 3][col] && tileNums[row + 3][col] == tileNums[row + 2][col])
							{
								tileNums[row + 3][col] += tileNums[row + 2][col];
								tileNums[row + 2][col] = 0;
								setTakenSpots();
							}
						}
						else if(spotsTaken[row + 2][col] && tileNums[row + 2][col] == tileNums[row + 1][col])
						{
							tileNums[row + 2][col] += tileNums[row + 1][col];
							tileNums[row + 1][col] = 0;
							setTakenSpots();
						}
					}
				}			
			}
		}
		putRandomTile();
		refreshGrid();
	}
	
	public static void setValues(int value, int x, int y, int spot)
	{
		tileGrid.get(spot).setValue(value);
		tileGrid.get(spot).setX(x);
		tileGrid.get(spot).setY(y);
	}
	
	public static void putRandomTile()
	{
		if(!isGridFull())	
		{
			int randomCol = getRandomNumSpot();
			int randomRow = getRandomNumSpot();
			
			if(spotsTaken[randomRow][randomCol])
			{
				putRandomTile();
			}
			else
			{
				tileNums[randomRow][randomCol] = getRandomNumTileNum();
				setTakenSpots();
				
			}
		}
	}
	
	public static int getSpot(int row, int col)
	{
		int spot = 0;
		
		if(row == 0 && col == 0)
			spot = 0;
		else if(row == 0 && col == 1)
			spot = 1;
		else if(row == 0 && col == 2)
			spot = 2;
		else if(row == 0 && col == 3)
			spot = 3;
		else if(row == 1 && col == 0)
			spot = 4;
		else if(row == 1 && col == 1)
			spot = 5;
		else if(row == 1 && col == 2)
			spot = 6;
		else if(row == 1 && col == 3)
			spot = 7;
		else if(row == 2 && col == 0)
			spot = 8;
		else if(row == 2 && col == 1)
			spot = 9;
		else if(row == 2 && col == 2)
			spot = 10;
		else if(row == 2 && col == 3)
			spot = 11;
		else if(row == 3 && col == 0)
			spot = 12;
		else if(row == 3 && col == 1)
			spot = 13;
		else if(row == 3 && col == 2)
			spot = 14;
		else if(row == 3 && col == 3)
			spot = 15;
		return spot;
	}
	
	public static int getXSpot(int randomCol)
	{
		return SPACING + SPACING * randomCol + Tiles.WIDTH * randomCol;
	}
	
	public  static int getYSpot(int randomRow)
	{
		return SPACING + SPACING * randomRow + Tiles.WIDTH * randomRow;
	}
	
	public static int getRandomNumSpot()
	{
		int randomNum = (int)(Math.random() * 5 - 1);
		return randomNum;
	}
	
	public static int getRandomNumTileNum()
	{
		int random;
		int tile = 2;
		switch(random = (int)(Math.random() * 5 + 1))
		{
			case 1:
				tile = 2;
				break;
			case 2:
				tile = 2;
				break;
			case 3:
				tile = 2;
				break;
			case 4:
				tile = 2;
				break;
			case 5:
				tile = 4;
				break;
		}
		return tile;
	}
	
	public static boolean isGridRefreshed()
	{
		return gridRefreshed;
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
	
	public static boolean isGridFull()
	{
		int amount = 0;
		for(int row = 0; row < ROWS; row++)			// Adds all the tiles to the game board
		{
			for(int col = 0; col < COLS; col ++)
			{
				if(spotsTaken[row][col])
					amount++;
			}
		}
		return (amount == 16);
	}
	
	public void restart()
	{
		
	}
}
