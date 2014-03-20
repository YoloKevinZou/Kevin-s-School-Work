#include <iostream>
#include <fstream>
using namespace std;

//prints out the array
void printArray(int array[],int size){

	static int iterationCount = 1;
	ofstream output;
	output.open("selectionSort.txt", fstream::app);

	output << iterationCount << ") ";

	for(int i = 0; i < size; i++){
		output << array[i] << " ";
	}
	output << endl;

	output.close();
	iterationCount++;
}


int main(){
	
	ifstream myFile;
	myFile.open("data.txt");

	int dataCount = 0;

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

		//read the integer from the file into array
		for(int i = 0;!myFile.eof();i++){
			myFile >> dataSorted[i];
		}
	}
	
	myFile.close();
	
	//prints out original array
	printArray(dataSorted,dataCount);

	//selection sort the array
	for(int startIndex = 0, endIndex = dataCount-1; startIndex < endIndex; startIndex++){

		int minIndex = startIndex;

		for(int walkerIndex = startIndex+1; walkerIndex <= endIndex; walkerIndex++){

			if(dataSorted[walkerIndex] < dataSorted[minIndex]){
				minIndex=walkerIndex;
			}
		}

		int temp = dataSorted[startIndex];
		dataSorted[startIndex]=dataSorted[minIndex];
		dataSorted[minIndex]=temp;

		//prints out every iteration
		printArray(dataSorted,dataCount);
	}

	delete[] dataSorted;
	
	return 0;
}