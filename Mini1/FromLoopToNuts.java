package mini1;

import java.util.Random;

/*
 *@author Bryanna Adamson
 */

public class FromLoopToNuts {

	private FromLoopToNuts() { }

	 private static String reverse(String s)
	 {
	 String result = ""; // start with empty string
	 for (int i = s.length() - 1; i >= 0; i = i - 1)
	 {
	 result += s.charAt(i); // add on characters one at a time
	 }
	 return result;
	 }

	public static int countMatches(java.lang.String s, java.lang.String t) {
		int count = 0;
		for(int i = 0; i < s.length(); i++) {
			if(i < t.length() && i < s.length()) {
				if(s.charAt(i) == t.charAt(i))
					count += 1;
			}
		}

		return count;
	}

	public static int countSubstrings(java.lang.String t, java.lang.String s) {
		int count = 0;
		int offset = 0;
		int index;
		//int sub =
		//int str =
		while((index = s.indexOf(t, offset))!= -1) {
			offset = index + t.length();
			count++;
		}
		return count;
	}

	public static int countSubstringsWithOverlap(java.lang.String t, java.lang.String s) {
		int count = 0;
		int sub = t.length();
		int str = s.length();

		for(int i = 0; i <= str - sub; i++) {
			int j;
			for(j = 0; j < sub; j++) {
				if(s.charAt(i+j) != t.charAt(j))
					break;
			}
			if(j==sub) {
				count ++;
				j = 0;
			}
		}
		return count;
	}

	public static boolean differByOneSwap(java.lang.String s, java.lang.String t) {
		int lengthS = s.length();
		int lengthT = t.length();
		int previous = -1;
		int current = -1;
		int count = 0;

		if (lengthS != lengthT)
			return false;


		for(int i = 0; i < s.length(); i++) {
			if(s.charAt(i) != t.charAt(i))
				count+=1;
			previous = current;
			current = i;
			if(count > 2)
				return false;
		}

		if (s.charAt(previous) == t.charAt(current) && s.charAt(current) == t.charAt(previous))
			return true;

		return false;
	}

	public static java.lang.String eliminateRuns(java.lang.String s)	{
		String ourString="";

	    for (int i=0; i<s.length() ; i++){
	        if(i==0)
	        	ourString = ""+s.charAt(i);
	        else
	        	if(s.charAt(i-1)!=s.charAt(i)){
	        		ourString+=s.charAt(i);
	        }
	    }
	    return ourString;
	    }

	public static int findEscapeCount(double x, double y, int maxIterations) {
		int count = 1;
		double a = x;
		double b= y;
		double tempA;
		double tempB;
		while((a*a + b*b) < 4) {
			tempA = a*a - b*b +x;
			tempB = 2*a*b + y;
			a = tempA;
			b = tempB;
			count++;
			if(count == maxIterations)
				return maxIterations;

		}
		return count;
	}

	public static boolean isArithmetic(java.lang.String text) {
		if(text == "")
			return true;
		String[] words = text.split(",");
        int[] arr = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            try {
                arr[i] = Integer.parseInt(words[i]);
            } catch (Exception e) {
                return false;
            }
        }
        if (arr.length > 1) {
            int a = arr[0], d = arr[1] - a;
            for (int i = 2; i < arr.length; i++) {
                if (arr[i] != arr[i-1] + d) {
                    return false;
                }
            }
            return true;
        } else if (arr.length == 0) {
            return false;
        }
        return true;
    }

	public static int threeInARow(java.util.Random rand, int bound) {
		int count = 0;
		int num1 = -1;
		int num2 = -2;
		int num3 = -3;
		//String str = "";
		while(num1 != num2 || num2 != num3) {
			int rand1 = rand.nextInt(bound);
			num3 = num2;
			num2 = num1;
			num1 = rand1;
			//str += num1;
				count++;

		}

		return count;
	}


	public static void main(String args[]) {
		System.out.println(findEscapeCount(1,0.5,10));


	}
}
