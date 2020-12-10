package hw2;

import api.Defaults;
import api.Outcome;


/*
 * Plays a game of cricket
 * @author Bryanna Adamson
 */

public class CricketGame {

	private int bowlsPerOver; //sets to the givenBowlsPerOver
	private int oversPerInnings; // sets to the givenOversPerInning
	private int totalInnings; // stores the number of innings played in each game
	private int numPlayers; // sets to the givenNumPlayers
	private int totalOuts; //stores the number of outs
	private int scoreSide0; // stores the score of side 0
	private int scoreSide1; // stores the score of side 1
	private int overCount; // stores the over count
	private int bowlCount; // stores the bowl count
	private boolean inPlay; //stores whether the ball is in play
	private boolean running; // stores whether the player is running
	private boolean atBat; // stores which side is atBat
	private boolean gameEnds; // stores whether the game is ended
	private int inningsPerGame; // sets to the givenInningsPerGame


	/*
	 * Constructs a CricketGame using the public default values
	 */
	public CricketGame() {
		oversPerInnings = Defaults.DEFAULT_OVERS_PER_INNINGS;
		totalInnings = Defaults.DEFAULT_NUM_INNINGS;
		numPlayers = Defaults.DEFAULT_NUM_PLAYERS;
		bowlsPerOver = Defaults.DEFAULT_BOWLS_PER_OVER;
		atBat = false; //false because side 0 always starts first
		overCount = 0;
		totalInnings = 0;
		totalOuts = 0;

	}

	/*
	 * Constructs a CricketGame with the given configuration parameters
	 * @param
	 * givenBowlsPerOver - number of times the bowler bowls in each over
	 * givenOversPerInnings - maximum number of overs per innings
	 * givenTotalInnings - total number of innings
	 * givenNumPlayers - number of players on each side
	 */
	public CricketGame(int givenBowlsPerOver, int givenOversPerInnings, int givenTotalInnings, int givenNumPlayers) {
		if (totalInnings % 2 == 1) {
			totalInnings += 1;
			gameEnds = true;
		}
		bowlsPerOver = givenBowlsPerOver;
		oversPerInnings = givenOversPerInnings;
		inningsPerGame = givenTotalInnings;
		numPlayers = givenNumPlayers;
		atBat = false;  //false because side 0 always starts first
		overCount = 0;
		totalInnings = 0;
		totalOuts = 0;
	}

	/*
	 * Bowls the ball and updates the game state depending on the given outcome.
	 * @param outcome - outcome of this bowl
	 */
	public void bowl(Outcome outcome) {
		/*
		 * The ball knocks over a rod behind the batsman, called the wicket.
		 * Then the batsman is immediately out.
		 */
		if (outcome == Outcome.WICKET) { //The ball knocks over a rod behind the batsman, called the wicket. Then the batsman is immediately out.
			if (gameEnds == false && inPlay != true) {
				bowlCount += 1;
				totalOuts  += 1;
				if (totalOuts == numPlayers - 1) {  // when out < numPlayers - 1 --> switch sides
					totalInnings += 1;
					bowlCount = 0;
					overCount = 0;
					totalOuts = 0;
					if (atBat == true)
						atBat = false;
					else
						atBat = true;
				}
			}

				if (bowlCount == bowlsPerOver) {
					overCount += 1;
					bowlCount = 0;
				}
				if (overCount == oversPerInnings) {
					overCount = 0;
					bowlCount = 0;
					totalOuts = 0;
					totalInnings += 1;

					if (atBat == true)
						atBat = false;
					else
						atBat = true;
				}
		}

		/*
		 * The ball hits the batsman and the referee thinks the ball would have hit the wicket, if only the batsman had not gotten in the way.
		 * This is called an "LBW" or "leg before wicket". Then the batsman is immediately out.
		 */
		if (outcome == Outcome.LBW) {
			if (gameEnds != true)
				totalOuts += 1;
				bowlCount += 1;
				if (totalOuts == numPlayers - 1) {
					totalOuts = 0;
					bowlCount = 0;
					overCount = 0;
					if (atBat == true)
						atBat = false;
					else
						atBat = true;
				}
					if (bowlCount == bowlsPerOver) {
						overCount += 1;
						bowlCount = 0;
					}
					if (overCount == oversPerInnings) {
						overCount = 0;
						bowlCount = 0;
						totalOuts= 0;
						totalInnings += 1;
						if (atBat == true)
							atBat = false;
						else
							atBat = true;
					}
					if (totalInnings == inningsPerGame)
						gameEnds = true;
			}

		/*
		 * The batsman hits the ball and it bounces or rolls outside the boundary.
		 *  This is also a "boundary" but is worth four runs.
		 */
		if (outcome == Outcome.BOUNDARY_FOUR) {
			if (gameEnds != true ) {
				bowlCount += 1;
				if (atBat == false)
					scoreSide0 += 4;
				else
					scoreSide1 += 4;
				if (bowlCount == bowlsPerOver) {
					overCount += 1;
					bowlCount = 0;
				}
				if (overCount == oversPerInnings) {
					overCount = 0;
					bowlCount = 0;
					totalOuts = 0;
					totalInnings += 1;
					if (atBat == true)
						atBat = false;
					else
						atBat = true;
				}
				if (totalInnings == inningsPerGame) {
					gameEnds = true;
				}
			}
		}

		/*
		 * The batsman hits the ball and it flies outside the boundary of the cricket field.
		 * This is called a "boundary" and it counts as six runs. (The batsman doesn't actually run.)
		 */
		if (outcome == Outcome.BOUNDARY_SIX ) {
			if (gameEnds != true && inPlay != true) {
				bowlCount +=1;
				if (atBat == false)
					scoreSide0 += 6;
				else
					scoreSide1 += 6;

				if (bowlCount == bowlsPerOver) {
					overCount += 1;
					bowlCount = 0;
				}
				if (overCount == oversPerInnings) {
					overCount = 0;
					bowlCount = 0;
					totalOuts = 0;
					totalInnings += 1;
					if (atBat == true)
						atBat = false;
					else
						atBat = true;
				}
				if (totalInnings == inningsPerGame)
					gameEnds = true;
			}
		}

		/*
		 * The batsman hits the ball and it flies in the air and one of the players from the other side catches it.
		 * Then the batsman is immediately out.
		 */
		if (outcome == Outcome.CAUGHT_FLY) {
			if (gameEnds != true)
				totalOuts += 1;
				bowlCount += 1;
				if (totalOuts == numPlayers - 1) {
					bowlCount = 0;
					overCount = 0;
					totalOuts = 0;
					if (atBat == true)
						atBat = false;
					else
						atBat = true;
			}
							if (bowlCount == bowlsPerOver) {
					overCount += 1;
					bowlCount = 0;
				}
				if (overCount == oversPerInnings) {
					overCount = 0;
					bowlCount = 0;
					totalOuts = 0;
					totalInnings += 1;
					if (atBat == true)
						atBat = false;
					else
						atBat = true;
				}
				if (totalInnings == inningsPerGame)
					gameEnds = true;
		}

		/*
		 * The ball is bowled so badly that it doesn't go anywhere near the batsman.
		 * This is called a "wide" and earns one run for the batting team.
		 */
		if (outcome == Outcome.WIDE) {
			if (gameEnds != true) {
				if (atBat == false)
					scoreSide0 += 1;
				else
					scoreSide1 += 1;
			}
		}

		/*
		 * The bowler breaks some other rule in the way he bowls the ball (e.g., "throwing" it instead of "bowling" it).
		 * This is called a "no-ball" and earns one run for the batting team.
		 */
		if (outcome == Outcome.NO_BALL) {
			if (gameEnds != true) {
				if (atBat == false)
					scoreSide0 += 1;
				else
					scoreSide1 +=1;
			}
		}

		/*
		 * The batsman hits the ball and none of the above occurs. We will say in this case that the ball is "in play".
		 */
		if (outcome == Outcome.HIT) {
			if (gameEnds != true) {
			inPlay = true;
			bowlCount += 1;
			if (bowlCount == bowlsPerOver) {
				overCount += 1;
				bowlCount = 0;
			}

			if (totalInnings == inningsPerGame)
				gameEnds = true;
			}
		}
	}

	/*
	 * Returns the number of times the bowler has bowled so far during the current over,
	 * not counting wides or no-balls.
	 * @return number of bowls so far in the current over
	 */
	public int getBowlCount() {
		return bowlCount;
	}

	/*
	 * Returns the number of innings that have been completed.
	 * @ return number of completed innings
	 */
	public int getCompletedInnings() {
		return totalInnings;
	}

	/*
	 * Returns the number of players out in the current innings.
	 * @return number of players out
	 */
	public int getOuts() {
		return totalOuts;
	}

	/*
	 * Returns the number of completed overs for the current innings.
	 * @return number of completed overs for current inning
	 */
	public int getOverCount() {
		return overCount;
	}

	/*
	 * Returns the score for one of the two sides
	 * @param atBatSide - if true, returns the score for the side currently at bat;
	 * otherwise returns the score for the other side
	 * @return score for one of the two sides
	 */
	public int getScore(boolean battingSide) {
		if (battingSide == atBat)
			return scoreSide1;
		else
			return scoreSide0;




	}

	/*
	 * Returns true if the games is ended, false otherwise
	 * @return if  the game has ended
	 */
	public boolean isGameEnded() {
		if (totalInnings == inningsPerGame - 1 && scoreSide1 > scoreSide0)
			return true;
		else
			return gameEnds;
	}

	/*
	 * Returns true if the ball is currently in play.
	 * @ return true if the ball is currently in play, false otherwise
	 */
	public boolean isInPlay() {
		return inPlay;
	}

	/*
	 * Returns true if batsmen are currently running.
	 * @return true if batsmen are running, false otherwise
	 */
	public boolean isRunning() {
		return running;
	}

	/*
	 * Runs the batsman out (i.e., fielders knock over wicket while batsmen are running).
	 */
	public void runOut() {
		//if (gameEnds == false || running == true) {
			if(inPlay == true && running == true) {
				totalOuts += 1;
				running = false;
				inPlay = false;
				if (overCount == oversPerInnings) {
					overCount = 0;
					bowlCount = 0;
					totalOuts = 0;
					totalInnings += 1;
					if (atBat == true)
						atBat = false;
					else
						atBat = true;
				}
			}
		}


	/*
	 * Starts the batsmen running from one end of the pitch to the other.
	 */
	public void tryRun() {
		if(running == false && inPlay == true)
			running = true;
		else
			if(running == true && (gameEnds == false || inPlay == true) ) {
			if (atBat == false)
				scoreSide0 += 1;
			else
				scoreSide1 +=1;

			if (totalInnings == inningsPerGame && scoreSide1 > scoreSide0)
				gameEnds = true;

			}
	}

	/*
	 * Transitions from ball in play to ball not in play, without an out.
	 */
	public void safe() {
		if(gameEnds == false || inPlay == true) {
			if(running == true) {
				if (atBat == false)
					scoreSide0 += 1;
				else
					scoreSide1 +=1;
				if (overCount == oversPerInnings) {
					overCount = 0;
					bowlCount = 0;
					totalOuts = 0;
					totalInnings += 1;
					if (atBat == true)
						atBat = false;
					else
						atBat = true;
				}
			}
		inPlay = false;
		running = false;
		}
	}

	/*
	 * Returns 0 if side 0 is atBat or 1 if side 1 is atBat.
	 * @return 0 if side 0 is atBat or 1 if side 1 is atBat
	 */
	public int whichSideIsBatting() {
		if (atBat == true)
			return 1;
		else
			return 0;
	}
}
