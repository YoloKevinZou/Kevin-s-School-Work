#include <iostream>
using namespace std;

void getNumber(int number[][100], int row, int col)
{
  for ( int r=0; r< row;r++)
    {
      for (int c =0; c< col; c++)
	{
	  cout << r << ", " << c << " value: ";
	  cin >> number[r][c];
	}
    }
      for ( int r = 0;r < row; r++)
	{
	  for (int c = 0 ; c < col; c++)
	    cout <<  number[r][c] << " ";
	  cout << endl;
	}
}


int max ( int array[], int size)
{
  int max = array[0];
  for (int i=1; i < size; i ++)
    if(max<array[i])
      max=array[i];
  return max;
}

void getMaxValues(int number[][100], int row, int col, int maxValues[])
{
  for ( int r = 0; r < row; r++)
    {
      maxValues[r] = max(number[r], col);
    }
}
int main()
{
  int number[100][100];
  int row, col;
  cout << "Enter row/col size: ";
  cin >> row >> col;

  getNumber( number, row, col );

  int maxValues[row];
  getMaxValues(number, row, col, maxValues);

  for ( int r = 0;r < row; r++)
    cout << maxValues[r] << " ";
  cout << endl;

  cout << max(maxValues, row)<< endl;
     
 return 0;
}
