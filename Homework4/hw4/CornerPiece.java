package hw4;

import java.awt.Color;

import api.Cell;
import api.Icon;
import api.Piece;
import api.Position;


/*
 * Creates the CornerPiece for BlockAddiction
 * @author Bryanna Adamson
 */
public class CornerPiece extends AbstractPiece {

	private Cell[] cells; // creates an array of cells
	private static final Position[] sequenceC = // creates the array of positions used in the transform for CornerPiece
		  {
		       new Position(0,0),
		       new Position(0,1),
		       new Position(1,1),
		       new Position(1,0)
		  };

	 /*Constructs a corner piece the extends the AbstractPiece class
	 * @param position, icons
	 * position - the given initial position of the piece
	 * icons - the given array of Icons for the peice
	 */
	public CornerPiece(Position position, Icon[] icons) throws IllegalArgumentException{
		super(position,icons);
		cells = new Cell[3];
		cells[0] = new Cell(icons[0], new Position(0, 0));
		cells[1] = new Cell(icons[1], new Position(1, 0));
		cells[2] = new Cell(icons[2], new Position(1, 1));
	}

	public Position getPosition() {
		return position;
	}

	@Override
	public Cell[] getCells() {
	    Cell[] copy = new Cell[cells.length];
	    copy[0] = new Cell(cells[0]);
	    copy[1] = new Cell(cells[1]);
	    copy[2] = new Cell(cells[2]);
	    return copy;
	 }

	@Override
	public Cell[] getCellsAbsolute() {
		 Cell[] abs = new Cell[cells.length];

		    int row = cells[0].getRow() + position.row();
		    int col = cells[0].getCol() + position.col();
		    Icon b = cells[0].getIcon();
		    abs[0] = new Cell(b, new Position(row, col));

		    row = cells[1].getRow() + position.row();
		    col = cells[1].getCol() + position.col();
		    b = cells[1].getIcon();
		    abs[1] = new Cell(b, new Position(row, col));

		    row = cells[2].getRow() + position.row();
		    col = cells[2].getCol() + position.col();
		    b = cells[2].getIcon();
		    abs[2] = new Cell(b, new Position(row, col));

		    return abs;
	}

	@Override
	public void setCells(Cell[] givenCells) {
		cells = new Cell[givenCells.length];
	    cells[0] = new Cell(givenCells[0]);
	    cells[1] = new Cell(givenCells[1]);
	    cells[2] = new Cell(givenCells[2]);


	}

	public void shiftDown() {
		super.shiftDown();
	}

	public void shiftLeft() {
		super.shiftLeft();
	}

	public void shiftRight() {
		super.shiftRight();
	}

	@Override
	public void transform() {
		if (cells[0].getRow() == 0 && cells[0].getCol() == 0) {
			cells[0].setRowCol(0,1);
			cells[1].setRowCol(0,0);
			cells[2].setRowCol(1,0);
		}
		else
			if(cells[0].getRow() == 0 && cells[0].getCol() == 1) {
				cells[0].setRowCol(1,1);
				cells[1].setRowCol(0,1);
				cells[2].setRowCol(0,0);
			  }
			  else
				  if(cells[0].getRow() == 1 && cells[0].getCol() == 1) {
				    cells[0].setRowCol(1,0);
				    cells[1].setRowCol(1,1);
				    cells[2].setRowCol(0,1);
				  }
				  else
					  if(cells[0].getRow() == 1 && cells[0].getCol() == 0) {
						  cells[0].setRowCol(0,0);
						  cells[1].setRowCol(1,0);
						  cells[2].setRowCol(1,1);
					  }
	  }

	public void cycle() {
		int j = cells.length - 1;
		Icon a = cells[cells.length-1].getIcon();
		for(int i = cells.length - 2; i >=0;  i--){
			Icon b = cells[i].getIcon();
			cells[j].setIcon(b);
			j--;
		}
		  cells[0].setIcon(a);
	}

	@Override
	public Piece clone(){
		return super.clone();
	}
}
