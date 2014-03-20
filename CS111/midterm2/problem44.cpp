#include <iostream>
using namespace std;

int main(){
  int size;
  cout << "Enter an integer: ";
  cin >> size;
  for ( int r = 1; r <= size; r++)
    {
      for( int c = 1; c <= size; c++)
	{
	  if(r==1||c==1||r+c==size+1)
	    cout << "*";
	  else
	    cout << " ";
	}
      cout << endl;
    }
  return 0;
}
