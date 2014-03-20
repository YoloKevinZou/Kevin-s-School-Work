#include <iostream>
using namespace std;

int main(){
  int row, col; srand(time(0));
  cout << "Enter the row number: ";
  cin >> row;
  cout << "Enter the column number: ";
  cin >> col;
  int array[100][100];
  
  for(int r = 0; r < row; r++)
    {
      for(int c = 0; c < col; c++)
	{
	  cin>>  array[r][c];
	}
    }
  
  for ( int r = 0; r < row; r++)
    {
      for ( int c = 0; c < col; c++)
	{
	  cout << array[r][c] << " ";
	}
      cout << endl;
    }

  for ( int x = 0; x < row && x < col; x++)
    {
      int rowSum=0, colSum=0;
      for ( int i = 0; i < col; i++) 
	rowSum+=array[x][i];
      for ( int i = 0; i < row; i++)
	colSum+=array[i][x];
      if(rowSum==colSum)
	cout << "The row and column sums are equal at " << x << ".\n";
      
    }
  cout << endl << endl;
  

  /* int rowSum=0; int colSum=0;
     
  for ( int r = 0; r< row; r++)
  {
  for ( int c = 0; c < col; c++)
	{
	rowSum+=array[r][c];
	colSum+=array[c][r];
	}
	cout << "at "<< r << " row the sum is "<< rowSum << endl; 
	if(rowSum==colSum)
	cout << r;   
	}
	
	for ( int c = 0; c < col; c++)
	{
	for (int r = 0; r < row; r++)
  {
  colSum+=array[r][c];
  }
  cout << "at " << c << " column the sum is " << colSum << endl;
    }
  */
  return 0;
}
