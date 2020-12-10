package mini3;

/*
 *@author Bryanna Adamson
 */
public class Combinations {

	public Combinations() {}

	public static int[][] getCombinations(int[] choices){
		 if(choices.length == 0) {
	           return null;
	       }else if(choices.length == 1) {

	           int m = choices[0];
	           int[][] arr = new int[m][1];
	           for(int i = 0; i < m; i++) {
	               arr[i][0] = i;
	           }
	           return arr;

	       }else {

	           int n = choices.length;
	           int[] newChoices = new int[n-1];
	           for(int i = 1; i < n; i++) {
	               newChoices[i-1] = choices[i];
	           }

	           int arr[][] = getCombinations(newChoices);
	           int size = arr.length;
	           int newArr[][] = new int[size*choices[0]][n];

	           int k = 0;
	           for(int i = 0; i < choices[0]; i++) {
	               for(int[] row: arr) {
	                   newArr[k][0] = i;
	                   for(int j = 1; j < n; j++) {
	                       newArr[k][j] = row[j-1];
	                   }
	                   k += 1;
	               }
	           }

	           return newArr;
	       }
	}
}
