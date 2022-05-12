package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import control.GameController;

public class GameBoard extends GamePanel{

private BoardCell[][] cells;
	
	public GameBoard(GameController gc) {
		super(gc);	
		this.setLayout(null);
		setSize(new Dimension(MainWindow.WIDTH-2*MainWindow.PLAYER_WIDTH, MainWindow.HEIGHT-MainWindow.TOP_HEIGHT));		
		this.setBackground(Color.gray);
		this.cells= new BoardCell[3][3];				
		for (int row=0; row< 3; row++) {
			for (int col=0; col< 3; col++) {
				BoardCell cell = new BoardCell(gc, row, col);
				cell.setBounds(
						boardZero().x+col*cellSize()+15,
						boardZero().y+row*cellSize()-BoardCell.CELL_PADDING,
						cellSize()-3*BoardCell.CELL_PADDING,
						cellSize()-3*BoardCell.CELL_PADDING
						);
				this.add(cell);
				this.cells[row][col]=cell;
			}
		}						
	}	
	
	private int cellSize() {
		int minDim = Integer.min(this.getWidth(),this.getHeight());
		return minDim/4;
	}
	
	private int boardSize() {
		return 3*cellSize();
	}
	
	private Point boardZero() {
		int x= (this.getWidth() - boardSize())/2;
		int y= (this.getHeight() - boardSize())/2;
		return new Point(x,y);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);	
		this.drawGrid(g);
	}	
	
	public void drawGrid(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.setStroke(new BasicStroke(6));
		for (int i=1; i<3; i++) {
			//Draw Vertical Line
			g2d.drawLine(
					boardZero().x +i*cellSize(),boardZero().y, 				// i-based x,  	upperLeft.y 
					boardZero().x+i*cellSize(),boardZero().y+boardSize() 	// i-based x,	boardBottom.y (boardBottom = y + boardSize)
					);
			
			//Draw Horizontal Line
			g2d.drawLine(
					boardZero().x,  boardZero().y+i*cellSize(), 			// upperLeft.x, i-based y
					boardZero().x+boardSize(), boardZero().y+i*cellSize()	// boardRight.x, i-based y (boardRight = x + boardSize)
					);
		}
		
	}
}
