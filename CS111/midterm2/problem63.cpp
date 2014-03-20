#include <iostream>
using namespace std;

int main(){
  int size;
  cout << "Enter a positive integer: ";
  cin >> size;
  for ( int r = 1; r <= size; r++)
    {
      for (int c = 1 ; c <= ((2*size)-1); c++)
	{
	  if (r==size||c+r==5||c-r==3)
	    cout << "*";
	  else 
	    cout << " ";
	}
      cout << endl;
    }
  return 0;
}
