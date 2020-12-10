package hw4;

import api.Cell;
import api.Icon;
import api.Piece;
import api.Position;

/*
 * An abstract class for Pieces. This class allows for the would be duplicate code for each
 * of the individual pieces to be put in here for all of them to be implemented. Code that is
 * put in abstract piece is each of the shift methods as well as clone
 * @author Bryanna Adamson
 */
public abstract class AbstractPiece implements Piece{

	protected Position position; // creates the variable for the position of the piece
	private Icon[] icons; //creates the icon array for the piece
	private Cell[] cells; // creates the array of cells for the piece

	/*
	 * Constructs an abstract piece for the pieces (LPiece, IPiece, CornerPiece, DiagonalPiece, and SnakePiece)
	 * to use code that would be duplicated in each one
	 * @param position, icons
	 * position - the given initial position of the piece
	 * icons - the given array of Icons for the peice
	 */
	protected AbstractPiece(Position position, Icon[] icons) throws IllegalArgumentException{
		this.position = position;
		this.icons = icons;
		cells = new Cell[4];
	}

	public Cell[] getAbsoluteCells() {
		return cells;
	}

	public Cell[] getCells() {
		return cells;
	}

	public Position getPosition() {
		return position;
	}

	public void transform() {}

	public void cycle() {}

	@Override
	public void setCells(Cell[] givenCells) {
		cells = new Cell[givenCells.length];
	    cells[0] = new Cell(givenCells[0]);
	    cells[1] = new Cell(givenCells[1]);
	    cells[2] = new Cell(givenCells[2]);
	    cells[3] = new Cell(givenCells[3]);
	}

	@Override
	public void shiftDown() {
		position = new Position(position.row() + 1, position.col());
	}

	@Override
	 public void shiftLeft() {
		position = new Position(position.row(), position.col() - 1);
	  }

	@Override
	public void shiftRight() {
		position = new Position(position.row(), position.col() + 1);
	  }

	public Piece clone() {
		 try{
		      // call the Object clone() method to create a shallow copy
		      AbstractPiece s = (AbstractPiece) super.clone();

		      // then make it into a deep copy (note there is no need to copy the position,
		      // since Position is immutable, but we have to deep-copy the cell array
		      // by making new Cell objects
		      s.cells = new Cell[cells.length];

		      for (int i = 0; i < cells.length; ++i){
		        s.setCells(s.getCells().clone());
		      }
		      return s;
		    }
		    catch (CloneNotSupportedException e){
		     // can't happen, since we know the superclass is cloneable
		      return null;
		      }
	}
}
