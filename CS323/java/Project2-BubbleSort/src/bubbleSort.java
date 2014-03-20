import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class bubbleSort {

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

		System.out.println();

		// put the values of the input file into array
		for (int i = 0; sc.hasNext(); i++) {
			dataSorted[i] = sc.nextInt();
		}
		
		PrintStream out = new PrintStream(new FileOutputStream(
				"bubbleSort.txt"));
		
		// bubble sort the array
		for (int startIndex = 0, endIndex = dataCount - 1; startIndex < endIndex; endIndex--) {

			for (int walkerIndex = startIndex; walkerIndex < endIndex; walkerIndex++) {

				if (dataSorted[walkerIndex] > dataSorted[walkerIndex + 1]) {
					int temp = dataSorted[walkerIndex];
					dataSorted[walkerIndex] = dataSorted[walkerIndex + 1];
					dataSorted[walkerIndex + 1] = temp;
				
				}
			}
			printArray(dataSorted,out);
		}

		

		// write the array to file output file
		/*for (int i = 0; i < dataSorted.length; i++) {
			out.println(dataSorted[i]);
		}*/

	}
	
	private static void printArray(int[] array, PrintStream out){
		
		out.print(iterationCount + ") ");
		
		for(int i = 0; i < array.length; i++){
			out.print(array[i]+ " ");
		}
		
		out.println();
		iterationCount++;
	}
	
}
