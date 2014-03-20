#include <iostream>
using namespace std;

int main(){
 
  //identify variable size and ask the user to input the number size.
  int size;
  cout <<"Enter an even integer size ";
  cin >> size;

  //if the user input is odd it will tell user to reinput the integer
  while(size%2!=0)
    {
      cout << "Enter an even integer size ";
      cin >> size;
    }

  // creates the row loop base on the size entered.
  for(int r=1;r<=size;r++)
    {
      
      //creates the column loop base on the size entered.
      for(int c=1;c<=size;c++)
	{
	  
	  //prints out * at the position when column is 1, when column is the last on a row, and on the last row of the size.
	  if(c==1||c==size||r==size)
	    cout << "*";
	  else cout <<" ";
	}
      cout <<endl;
    }
  return 0;
}

