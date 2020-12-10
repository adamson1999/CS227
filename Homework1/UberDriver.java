package hw1;

\\@author Bryanna Adamson

public class UberDriver {

	/**
	 * Maximum number of passengers allowed in the vehicle at one time.
	 */
	 public static final int MAX_PASSENGERS = 4;

	 /**
	 * Cost to operate the vehicle per mile.
	 */
	 public static final double OPERATING_COST = 0.5;

	 private double mileRate;//stores the rate per mile
	 private double minuteRate; //stores the rate per minutes
	 private int totalMiles; //stores total miles
	 private int totalMinutes;//stores total minutes
	 private int passengers; //stores
	 private double credit; // stores the credit





	 /*Constructs an UberDriver with the given per-mile rate and per-minute rate
	  * @param givenPerMileRate, givenPerMinuteRate
	  * The rate per mile driven and the rate per minute driven
	  */
	 public UberDriver(double givenPerMileRate, double givenPerMinuteRate) {
		 totalMiles = 0;
		 totalMinutes = 0;
		 passengers = 0;
		 minuteRate = givenPerMinuteRate;
		 mileRate = givenPerMileRate;
		 credit = 0;

	 }
	 /*Returns the total number of miles driven
	  * @return
	  * total miles driven
	  */
	 public int getTotalMiles() {
		 return totalMiles;

	 }
	/*Returns the total minutes driven
	 * @return
	 * 	total minutes driven
	 */
	 public int getTotalMinutes() {
		 return totalMinutes;

	 }
	/*Drives the car for the miles and minutes given
	 * @param miles, minutes
	 */
	 public void drive(int miles, int minutes) {

		 totalMiles = totalMiles + miles;
		 totalMinutes = totalMinutes + minutes;

		 credit = credit + (minutes*minuteRate*passengers) + (miles*mileRate*passengers);


	 }
	/*Simulates sitting in the vehicle without moving for the given number of minutes.
	 * Equivalent to drive(0, minutes).
	 * @param minutes
	 */
	 public void waitAround(int minutes) {

		 totalMinutes = totalMinutes + minutes;

		 credit = credit + (minutes*minuteRate*passengers);



	 }
	/*Drives the vehicle for the given number of miles at the given speed. Equivalent to
	 * drive(miles, m), where m is the actual number of minutes required, rounded to the
	 * nearest integer.
	 * @param miles, averageSpeed
	 */
	 public void driveAtSpeed(int miles, double averageSpeed) {

		 totalMiles = totalMiles + miles;
		 int roundedMinutes = (int) Math.round((miles/averageSpeed)*60);
		 totalMinutes =  totalMinutes+roundedMinutes;
			 credit = credit + (roundedMinutes*minuteRate*passengers) + (miles*mileRate*passengers);






	 }
	  /*Returns the number of passengers currently in
	   * @ return
	   *passenger count
	   */
	 public int getPassengerCount() {
		 return passengers;

	 }
	 //Increases the passenger count by 1, not exceeding MAX_PASSENGERS.
	 public void pickUp() {

		passengers = Math.min(passengers+1,4);


	 }
	  //Decreases the passenger count by 1, not going below zero.
	 public void dropOff() {

		 passengers = Math.max(passengers-1,0);

	 }
	 /*Returns this UberDriver's total credits (money earned before deducting operating costs).
	  * @ return
	  * total credits
	  */
	 public double getTotalCredits() {
		 return credit;
	 }
	 /* Returns this UberDriver's profit (total credits, less operating costs).
	  * @return
	  * profits
	  */
	 public double getProfit() {
		 return credit - (totalMiles*OPERATING_COST);

	 }
	 /*Returns this UberDriver's average profit per hour worked.
	  * @return
	  * average profit per hour
	  */
	 public double getAverageProfitPerHour() {

		 return ((credit - (totalMiles*OPERATING_COST))*60)/totalMinutes;

	 }
}
