package hw4;

import java.awt.Color;

import api.Cell;
import api.Icon;
import api.Piece;
import api.Position;


/*
 * Creates the SnakePiece for BlockAddiction
 * @author Bryanna Adamson
 */
public class SnakePiece extends AbstractPiece {

	private Cell[] cells; //creates an array of cells
	private int transCounter; //transform counter, used in snakes transform method
	private int tC1; //transform counter 1, used in snakes transform method
	private int tC2; //transform counter 2, used in snakes transform method
	private int tC3; ////transform counter, used in snakes transform method
	private static final Position[] sequence = { // creates the array of positions used in the transform for SnakePiece
			new Position(0, 0),
			new Position(0, 1),
			new Position(0, 2),
			new Position(1, 2),
			new Position(1, 1),
			new Position(1, 0),
			new Position(2, 0),
			new Position(2, 1),
			new Position(2, 2),
			new Position(1, 2),
			new Position(1, 1),
			new Position(1, 0),
			};

	 /*Constructs a diagonal piece the extends the AbstractPiece class
	  * @param position, icons
	  * position - the given initial position of the piece
	  * icons - the given array of Icons for the peice
	  * @throws IllegalArgumentException
	  */
	public SnakePiece(Position position, Icon[] icons) throws IllegalArgumentException{
		super(position,icons);
		transCounter = 0;
		tC1 = 11;
		tC2 = 10;
		tC3 = 9;
		cells = new Cell[4];
		cells[0] = new Cell(icons[0], new Position(0, 0));
		cells[1] = new Cell(icons[1], new Position(1, 0));
		cells[2] = new Cell(icons[2], new Position(1, 1));
		cells[3] = new Cell(icons[3], new Position(1, 2));
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
	    copy[3] = new Cell(cells[3]);

	    return copy;
	 }

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

		row = cells[3].getRow() + position.row();
		col = cells[3].getCol() + position.col();
		b = cells[3].getIcon();
		abs[3] = new Cell(b, new Position(row, col));

		return abs;
	}

	public void setCells(Cell[] givenCells) {
		cells = new Cell[givenCells.length];
	    cells[0] = new Cell(givenCells[0]);
	    cells[1] = new Cell(givenCells[1]);
	    cells[2] = new Cell(givenCells[2]);
	    cells[3] = new Cell(givenCells[3]);


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

		if(transCounter == 11) {
			transCounter = 0;
			cells[0].setPosition(sequence[transCounter]);
		}
		  else {
			  cells[0].setPosition(sequence[transCounter+1]);
			  transCounter++;
		  }

		if(tC1 == 11) {
			tC1 = 0;
			cells[1].setPosition(sequence[tC1]);
		 }
		  else {
			  cells[1].setPosition(sequence[tC1+1]);
			  tC1++;
		  }

		if(tC2 == 11) {
			tC2 = 0;
			cells[2].setPosition(sequence[tC2]);
		}
		  else {
			  cells[2].setPosition(sequence[tC2+1]);
			  tC2++;
		  }

		if(tC3 == 11) {
			tC3 = 0;
			cells[3].setPosition(sequence[tC3]);
		}
		  else {
			  cells[3].setPosition(sequence[tC3+1]);
			  tC3++;
		  }
	  }

	@Override
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
