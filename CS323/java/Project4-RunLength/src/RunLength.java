import java.io.*;
import java.util.Scanner;

public class RunLength {

	public static void main(String[] args) throws FileNotFoundException {

		int rowNumber, colNumber, minValue, maxValue, rowPosition = 0, colPosition = 0, value = 0, appearCount = 0;

		File inFile = new File("data.txt");

		Scanner sc = new Scanner(inFile);

		// get the header informations
		rowNumber = sc.nextInt();
		colNumber = sc.nextInt();
		minValue = sc.nextInt();
		maxValue = sc.nextInt();

		// initialize a 2d dynamic array

		int[][] data = new int[rowNumber][colNumber];

		// put all the values of the data into the array
		for (int i = 0; i < rowNumber; i++) {

			for (int j = 0; j < colNumber; j++) {

				data[i][j] = sc.nextInt();
				
				if(data[i][j] < minValue || data[i][j] > maxValue){
					System.out.println("Invalid input");
					System.exit(1);
				}
				
			}

		}

		// close the input file
		sc.close();

		PrintStream out = new PrintStream(new FileOutputStream("RunLength.txt"));

		// output the header into file
		out.print(rowNumber + " " + colNumber + " " + minValue + " " + maxValue
				+ "\n");

		// iterate through the array to compress the data
		for (int i = 0; i < rowNumber; i++) {

			for (int j = 0; j < colNumber; j++) {

				// set the first element of the array as the first value
				if (i == 0 && j == 0) {
					value = data[0][0];
					rowPosition = 0;
					colPosition = 0;
					appearCount = 1;
				}

				// if the value is the same, add 1 into count
				else if (data[i][j] == value) {
					appearCount++;
				}

				// if value is not the same, print out the output and set the
				// new value
				// and positions
				else {
					out.print(rowPosition + " " + colPosition + " " + value
							+ " " + appearCount + "\n");
					value = data[i][j];
					rowPosition = i;
					colPosition = j;
					appearCount = 1;
				}

			}

		}

		// output the last pair or set of the data
		out.print(rowPosition + " " + colPosition + " " + value + " "
				+ appearCount + "\n");

		// close the output file
		out.close();

	}
}
