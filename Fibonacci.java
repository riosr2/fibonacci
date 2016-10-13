//package fibonacci;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;

public class Fibonacci{
	
static int nn = 1200, mm = 101;
static BigInteger[] fibNums = {BigInteger.valueOf(1),BigInteger.valueOf(0)};

	public static void main(String[] args) throws FileNotFoundException {
		// Parses through command line arguments for the start number (args[0]) and the number of fibonacci numbers to be calculated (args[1]).
		if(args.length > 0) nn = Integer.parseInt(args[0]);
		if(args.length > 1) mm = Integer.parseInt(args[1]);
		
		//makes a new PrintWriter that will create and write to a file called TIMING.txt.
		PrintWriter writer = new PrintWriter("TIMING.txt");
		//Writes so that there is 20 character spaces between each entry.
		writer.printf("%-10s%-20s%-20s%n", "n:", "fib(n),", "time(msec)");
		writer.print("============================================================");
		writer.print("============================================================");
		writer.print("============================================================");
		writer.print("============================================================");
		writer.print("============================================================%n");
		//Loops through mm times to find mm consecutive fib numbers times.
		for(int i = 0; i < mm; i++){
			//initializes variables
			long time3 = 0;
			BigInteger fibNum = BigInteger.valueOf(0);
			//Loops through three times to get an average.
			for(int j = 0; j < 3; j++){
				//takes time before computing.
				long time1 = System.nanoTime();
				fibNum = fib(nn)[0];
				//takes time after computing
				long time2 = System.nanoTime();
				//subtracts the two and adds it to the total time.
				time3 = time3 + (time2 - time1);
			}
			//divides total time by three(to get the average)
			//multiplies by .000001 to get millisecs
			double averageTime = (time3/3) * .000001;
			//Prints out answers in elided form and average time
			writer.printf("%d%18s%16f%n", nn, elide(fibNum), averageTime);
			//increments nn
			nn += 1;
		}
		//closes the PrintWriter.
		writer.close();
	}
	
	public static String elide(BigInteger N){
		//Method to shrink the numbers to a readble size in the format (first 3 numbers)(how many numbers in between)(last 4 numbers)
		String strNum = N.toString();
		if(strNum.length() < 10){
			return strNum;
		}
		else{
			//Gets first three numbers
			String firstThree = strNum.substring(0, 3);
			//Finds out how many numbers are between the first three and last four numbers.
			int count = strNum.length() - 7;
			//Gets last four numbers
			String lastFour = strNum.substring(strNum.length()-4);
			return firstThree + "(" + count + ")" + lastFour;
		}
	}

	//efficient recursive fibonacci method! Use of a look up array prevents us from repeating recursive calls when not needed
	public static BigInteger[] fib(int n){
		if(n == 1){
			return fibNums;
		}
		else{
			//Stores the array returned by fib(n-1)
			BigInteger[] temp = fib(n-1);
			//Creates a new array that will be returned
			BigInteger[] answer = new BigInteger[2];
			//The second element of this array is the first element of fib(n-1)
			answer[1] = temp[0];
			//The first element of this array is the sum of the first and second element of fib(n-1)
			answer[0] = temp[1].add(temp[0]);
			//returns this array
			return answer;
		}
	}
	

	//Just returns the nth G number, because the method GArray returns an array.
	public static BigInteger G(int n){
		return GArray(n)[0]; 
	}
	

	//As part of an exercise a G number is an extension of fibonacci numbers, except we add the previous two terms instead of one.
	public static BigInteger[] GArray(int n){
		if(n < 2){
			BigInteger[] answer = {BigInteger.valueOf(1),BigInteger.valueOf(1), BigInteger.valueOf(1)};
			return answer;
		}
		else{
			//Stores the array returned by GArray(n-1)
			BigInteger[] temp = GArray(n-1);
			//Creates a new array that will be returned
			BigInteger[] answer = new BigInteger[3];
			//The third element of this array is the second element of g(n-1)
			answer[2] = temp[1];
			//The second element of this array is the first element of g(n-1)
			answer[1] = temp[0];
			//The first element of this array is the sum of the first and second and third element of g(n-1)
			answer[0] = temp[1].add(temp[0]).add(temp[2]);
			//returns this array
			return answer;
		}
	}
}
