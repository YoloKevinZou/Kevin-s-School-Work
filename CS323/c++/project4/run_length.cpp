#include <iostream>
#include <fstream>
using namespace std;

int main(){
	
	int rowNumber, colNumber, minValue, maxValue, rowPosition, colPosition, value, appearCount;

	ifstream inFile;
	inFile.open("data.txt");

	//get the header informations
	inFile >> rowNumber;
	inFile >> colNumber;
	inFile >> minValue;
	inFile >> maxValue;

	//initialize a 2d dynamic array
	int **data = new int*[rowNumber];

	for(int i = 0; i < rowNumber; i++){
		data[i] = new int[colNumber];
	}

	//put all the values of the data into the array
	if(inFile.is_open()){	

		for(int i = 0; i < rowNumber; i++){

			for(int j = 0; j < colNumber; j++){

				if(inFile < minValue || inFile > maxValue){
					cout << "Invalid input" << endl;
					exit(1);
				}

				inFile >> data[i][j];

			}

		}
	}

	//close the input file
	inFile.close();

	ofstream outFile;
	outFile.open("RunLength.txt");

	//output the header into file
	outFile << rowNumber << " " << colNumber << " " << minValue << " " << maxValue << endl;

	//iterate through the array to compress the data
	for(int i = 0; i < rowNumber; i++){

		for(int j = 0; j < colNumber; j++){

			//set the first element of the array as the first value
			if(i==0 && j == 0){
				value = data[0][0];
				rowPosition = 0;
				colPosition = 0;
				appearCount = 1;
			}

			//if the value is the same, add 1 into count
			else if(data[i][j] == value){
				appearCount++;
			}

			//if value is not the same, print out the output and set the new value
			// and positions
			else{
				outFile << rowPosition << " " << colPosition << " " << value << " " << appearCount << endl;
				value = data[i][j];
				rowPosition = i;
				colPosition = j;
				appearCount = 1;
			}

		}

	}

	//output the last pair or set of the data
	outFile << rowPosition << " " << colPosition << " " << value << " " << appearCount << endl;

	//close the output file
	outFile.close();

	//delete the array from memory to prevent memory leaks
	for(int i = 0; i < rowNumber; i++){
			delete [] data[i];
	}

	delete [] data;



	return 0;
}