
package hw4;

import java.awt.Color;
import java.util.Random;

import api.Generator;
import api.Icon;
import api.Piece;
import api.Position;
import src.examples.SamplePiece;

/**
 * Generator for Piece objects in BlockAddiction. Icons are
 * always selected uniformly at random, and the Piece types
 * are generated with the following probabilities:
 * <ul>
 * <li>LPiece - 10%
 * <li>DiagonalPiece - 25%
 * <li>CornerPiece - 15%
 * <li>SnakePiece - 10%
 * <li>IPiece - 40%
 * </ul>
 * The initial position of each piece is based on its
 * vertical size as well as the width of the grid (given
 * as an argument to getNext).  The initial column is always
 * width/2 - 1.  The initial row is:
 *  * <ul>
 * <li>LPiece - row = -2
 * <li>DiagonalPiece - row = -1
 * <li>CornerPiece - row = -1
 * <li>SnakePiece - row = -1
 * <li>IPiece - row = -2
 * </ul>
 *
 * @author Bryanna Adamson
 */
public class BasicGenerator implements Generator
{
  private Random rand;

  /**
   * Constructs a BasicGenerator that will use the given
   * Random object as its source of randomness.
   * @param givenRandom
   *   instance of Random to use
   */
  public BasicGenerator(Random givenRandom){
    rand = givenRandom;
  }

  @Override
  public Piece getNext(int width){
    int col = (width / 2) - 1;
    rand = new Random();
    int game = rand.nextInt(100);

    if (game < 10){ // 10% LPiece
    	Icon[] iconsL = {
      	      randomIcon(),
      	      randomIcon(),
      	      randomIcon(),
      	      randomIcon(),
      	    };
    	return new LPiece(new Position(-2, col), iconsL);
    }

    else if (game < 35){ // 25% Diagonal
    	Icon[] iconsDiagonal = {
        	      randomIcon(),
        	      randomIcon(),
        	    };
    	return new DiagonalPiece(new Position(-1, col), iconsDiagonal);
    }

    else if (game < 50){ // 15% Corner
    	Icon[] iconsCorner = {
      	      randomIcon(),
      	      randomIcon(),
      	      randomIcon()
      	    };
    	return new CornerPiece(new Position(-1, col), iconsCorner);
    }

    else if(game < 90){ // 40% IPiece
    	Icon[] iconsI = {
      	      randomIcon(),
      	      randomIcon(),
      	      randomIcon(),
      	    };
    	return new IPiece(new Position(-1, col), iconsI);
  	}

    else { //10% Snake
    	Icon[] iconsSnake = {
        	      randomIcon(),
        	      randomIcon(),
        	      randomIcon(),
        	      randomIcon()
        	    };
    	return new SnakePiece(new Position(-2, col), iconsSnake);
    }
  }

  @Override
  public Icon randomIcon(){
    return new Icon(Icon.COLORS[rand.nextInt(Icon.COLORS.length)]);
  }
}
