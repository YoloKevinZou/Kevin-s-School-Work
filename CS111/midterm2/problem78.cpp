#include <iostream>
using namespace std;

int main(){
  int row, col;
  srand(time(0));
  cout << "Enter the amount of rows: ";
  cin >> row;
  cout << "Enter the amount of columns: ";
  cin >> col;
  int array[100][100];
  
  for(int r =0; r < row; r++)
    {
      for( int c = 0; c < col; c++)
	{
	  array[r][c]=rand()%9+1;
	}
    }

  for ( int r = 0; r < row; r++)
    {
      for ( int c = 0; c < col; c++)
	{
	  cout << array[r][c]<< " ";
	}
      cout << endl;
    }

  cout << "The following rows add to 7: ";

  for (int r = 0; r < row; r++)
    {
      int sum=0;
      for (int c = 0; c < col; c++)
	{
	  sum+=array[r][c];
	}
      if(sum==7)
	cout << r << " ";
    }
  cout << endl;
  return 0;
}
