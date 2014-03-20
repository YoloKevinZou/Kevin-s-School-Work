#include <iostream>
using namespace std;

int main(){
  int number; //identify variable
  int counter=1; // initialized counter at 1
  cout << "Enter a positive number greater than 1: "; // tells user to input number
  
  cin >> number; // stores in value for the number variable

  while(counter <= number) //comparison condition for starting of while loop
    {
      if(number%counter==0) //comparison condition of an if statement
	{
	  cout << counter << " "; // executes only if the above comparison is true
	}
      counter++; // add 1 to counter if the comparison for while loop is true
    }  
  return 0; 
}
