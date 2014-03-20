#include <iostream>
using namespace std;

int main(){
  int n;srand(time(0));
  cout << "Enter a positive integerat most 100: ";
  cin >> n;
  int array[100][100];
  for( int r = 0; r < n; r++)
    {
      for ( int c = 0; c < n; c++)
	{
	  array[r][c]=rand()%30+1;
	}
    }

  for(int r = 0; r < n; r++)
    {
      for ( int c = 0;c < n; c++)
	{
	  cout << array[r][c] << " ";
	}
      cout << endl;
    }
 
  cout << "The maximum entries in the " << n << " rows are: ";

  for (int r = 0; r < n; r++)
    {
      int max=0;
      for ( int c = 0; c < n; c++)
	{
	  if(max<array[r][c])
	    max=array[r][c];
	}
      cout << max << " ";
    }
  cout << endl; 
  return 0;
}
