#include <iostream>
using namespace std;

int main(){
  int a,b,sumMin=0;
  cout << "Enter a positive integer: ";
  cin >> a;
  cout << "Enter another positive integer: ";
  cin >> b;
  int array[a][b];
  for(int r = 0; r < a; r++)
    {
      for ( int c = 0; c < b; c++)
	{
	  cout << "Enter the value for " << r << " " << c << ": ";
	  cin >> array[r][c];
	}
    } 
  for( int r = 0; r < a; r++)
    {
      for (int c = 0; c < b; c++)
    {
      cout << array[r][c] << " ";
    }
      cout << endl;
}
  
  cout << "The minimum entry is: ";
  for(int c = 0; c < b; c++)
    {
      int min = array[0][c];
      for ( int r = 0; r < a; r++)
	if(min>array[r][c]) min=array[r][c];
      cout << min << " ";
      sumMin=sumMin+min;
    }

  cout << endl;
  cout << "The average minimum entry is: " << ((double)sumMin)/b << endl;
  return 0;
}
