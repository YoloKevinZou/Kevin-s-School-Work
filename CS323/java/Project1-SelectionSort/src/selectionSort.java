import java.io.*;
import java.util.Scanner;

public class selectionSort {
	
	private static int iterationCount = 1;
	
	public static void main(String[] args) throws FileNotFoundException {

		File file = new File("data.txt");

		Scanner sc = new Scanner(file);

		int dataCount = 0;

		// count the number of data in text
		while (sc.hasNext()) {
			dataCount++;
			sc.next();
		}

		sc.close();

		int[] dataSorted = new int[dataCount];

		sc = new Scanner(file);

		// put the values of input file into array
		for (int i = 0; sc.hasNext(); i++) {
			dataSorted[i] = sc.nextInt();
		}
		
		PrintStream out = new PrintStream(new FileOutputStream(
				"selectionSort.txt"));
		
		// loop through the away to do selection sort
		for (int startIndex = 0, endIndex = dataCount - 1; startIndex < endIndex; startIndex++) {

			int minIndex = startIndex;

			for (int walkerIndex = startIndex + 1; walkerIndex <= endIndex; walkerIndex++) {

				if (dataSorted[walkerIndex] < dataSorted[minIndex]) {
					minIndex = walkerIndex;
				}
			}

			// swap the values
			int temp = dataSorted[startIndex];
			dataSorted[startIndex] = dataSorted[minIndex];
			dataSorted[minIndex] = temp;
			printArray(dataSorted, out);
		}

	}

	private static void printArray(int[] array, PrintStream out) {
		
		out.print(iterationCount + ") ");
		
		for (int i = 0; i < array.length; i++) {
			out.print(array[i] + " ");
		}

		out.println();
		iterationCount++;

	}
}
