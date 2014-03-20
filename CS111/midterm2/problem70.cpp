#include <iostream>
using namespace std;

int main(){
  int n;srand(time(0));
  cout << "Enter an integer that is at most 100: ";
  cin >> n;
  int array[100][100];
  for(int r = 0; r < n; r++)
    {
      for ( int c = 0; c < n ; c++)
	{	  
	  array[r][c]=rand()%9+1;
	}
    }
  for ( int r = 0 ; r < n; r++)
     {
       for ( int c = 0; c < n; c++)
	 {
	cout << array[r][c] << " ";
	 }
       cout << endl;
     }

  cout << "The averages of the " << n  << " columns are: ";
  
  for ( int c = 0; c < n; c++)
    {
      int sum=0;
      for( int r = 0; r < n; r++)
	{
	  sum+=array[r][c];
	}
      cout << ((double)sum/n) << " ";
    }
  
  return 0;
}
