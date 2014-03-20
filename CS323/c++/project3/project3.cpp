#include <iostream>
#include <fstream>
using namespace std;

void printArray(int array[],int size){

	static int iterationCount = 1;
	ofstream output;
	output.open("ModifedBubbleSort.txt", fstream::app);

	output << iterationCount << ") ";

	for(int i = 0; i < size; i++){
		output << array[i] << " ";
	}
	output << endl;
	iterationCount++;
	output.close();
}

int main(){
	
	ifstream myFile;
	myFile.open("data.txt");

	int dataCount;

	if(myFile.is_open()){

		while(!myFile.eof()){
			int x;
			myFile >> x;
			dataCount++;
		}
	}

	myFile.close();

	int* dataSorted = new int[dataCount];

	myFile.open("data.txt");

	if(myFile.is_open()){

		//store the integer from the file into the array
		for(int i = 0;!myFile.eof();i++){
			myFile >> dataSorted[i];
		}
	}
	
	myFile.close();
	
	//prints out the original array
	printArray(dataSorted,dataCount);

	//bubble sort the array
	for(int startIndex = 0, endIndex = dataCount-1; startIndex < endIndex; endIndex--){

		int swapCount = 0;

		for(int walkerIndex = startIndex; walkerIndex < endIndex; walkerIndex++){

			if(dataSorted[walkerIndex] > dataSorted[walkerIndex+1]){
				int temp = dataSorted[walkerIndex];
				dataSorted[walkerIndex] = dataSorted[walkerIndex+1];
				dataSorted[walkerIndex+1] = temp;
				swapCount++;
			}
		}

		if(swapCount == 0){
			startIndex = endIndex;
		}

		printArray(dataSorted,dataCount);
	}
	
	delete[] dataSorted;

	return 0;
}