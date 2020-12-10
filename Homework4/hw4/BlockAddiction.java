
package hw4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import api.AbstractGame;
import api.Generator;
import api.Position;

/**
 * @author Bryanna
 * Creates the game BlockAddiction, which is very similar to tetris. The differences
 * being the grid typically starts in a checkerboard pattern and that you dont want
 * to fill a row, but get three pieces to match icons(color)
 */
public class BlockAddiction extends AbstractGame {

	/*
	 * Constructs the game block addiction with prefilled rows in checkerboard pattern
	 * @ param height, width, gen, preFilledRows
	 * height - gives the height of the grid when constructed
	 * width - gives the width of the grid when constructed
	 * gen - allows us to use methods from generator class
	 * preFilledRows - number of row that will be in the checkerboard pattern at the beginning of the game
	 */
	public BlockAddiction(int height, int width, Generator gen, int preFillRows) {
		super(height,width,gen);
		if(preFillRows > 0) {
				for(int i = getHeight() - preFillRows; i < getHeight(); i++) {
					for(int j = 0; j < getWidth(); j++) {
						if((i%2 == 0 && j%2==0) || (i%2 != 0 && j%2 != 0))
							setBlock(i,j,gen.randomIcon());
					}
				}
		}
	}

	/*
	 * Constructs the game block addiction with and empty grid (i.e. no prefilled rows and no checkerboard pattern
	 * @ param height, width, and gen
	 * height - gives the height of the grid when constructed
	 * width - gives the width of the grid when constructed
	 * gen - allows us to use methods from generator class
	 */
	public BlockAddiction(int height, int width, Generator gen) {
		super(height, width, gen);
	}

	@Override
	public List<Position> determinePositionsToCollapse() {
		List<Position> finalCollapse = new ArrayList<Position>();
		List<Position> collapse = new ArrayList<Position>();
		for(int row = 0; row < getHeight(); row++) {
			for(int col = 0; col < getWidth(); col++) {
				if(getIcon(row,col)!=null) {
					if(row != 0 && row != getHeight()-1 && col != getWidth()-1) {
						if(getIcon(row,col).matches(getIcon(row,col+1))  && getIcon(row,col).matches(getIcon(row-1,col))) {
							collapse.add(new Position(row,col));
							collapse.add(new Position(row,col+1));
							collapse.add(new Position(row-1,col));
						}
					if(row != 0 && row != getHeight()-1 && col != getWidth()-1) {
						if(getIcon(row,col).matches(getIcon(row+1, col)) && getIcon(row,col).matches(getIcon(row-1, col))== true) {
							collapse.add(new Position(row,col));
							collapse.add(new Position(row+1,col));
							collapse.add(new Position(row-1,col));
						}
					}
					}
					if(row != 0 && col != 0 && col != getWidth()-1 && row != getHeight()-1) {
						if(getIcon(row,col).matches(getIcon(row, col-1)) && getIcon(row,col).matches(getIcon(row-1, col))){
							collapse.add(new Position(row,col));
							collapse.add(new Position(row,col-1));
							collapse.add(new Position(row-1,col));
						}
					}
					if(col != 0 && col != getWidth()-1 && row != getHeight()-1 ) {
						if(getIcon(row,col).matches(getIcon(row,col+1)) && getIcon(row,col).matches(getIcon(row,col-1))) {
								collapse.add(new Position(row,col));
								collapse.add(new Position(row,col+1));
								collapse.add(new Position(row,col-1));
						}
						if(getIcon(row,col).matches(getIcon(row, col-1)) && getIcon(row,col).matches(getIcon(row+1,col))) {
								collapse.add(new Position(row,col));
								collapse.add(new Position(row,col-1));
								collapse.add(new Position(row+1,col));
						}
					}
					if(row != getHeight()-1 && col != getWidth()-1) {
						if(getIcon(row,col).matches(getIcon(row,col+1)) && getIcon(row,col).matches(getIcon(row+1,col))) {
						collapse.add(new Position(row,col));
						collapse.add(new Position(row,col+1));
						collapse.add(new Position(row+1,col));
						}
					}
				}
			}
		}
		Collections.sort(collapse);
		for(int i = 0; i < collapse.size();i++) {
				if(!finalCollapse.contains(collapse.get(i)))
					finalCollapse.add(collapse.get(i));
		}
	return finalCollapse;
	}
}
