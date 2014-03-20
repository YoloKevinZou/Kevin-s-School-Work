#include<iostream>
using namespace std;

/**
 * This function gets the number from user and store it into the array
 *
 * Parameter: 2d array of number, 
 * Parameter: the row.
 * Parameter: the column.
 * Return: none
 */

void getNumbers( int numbers[][100], int row, int col )
{
  for ( int r = 0 ; r < row ; ++r )
    for ( int c = 0 ; c < col ; ++c )
      {
	cout << r << ", " << c << " enter number: ";
	cin >> numbers[r][c];
      }
}

/**
 * This function prints the number in the array
 *
 * Parameter: 2d array of numbers
 * Parameter: row number
 * Parameter: column number
 * Return: none.
 */

void printNumbers( int numbers[][100], int row, int col )
{
  for ( int r = 0 ; r < row ; ++r )
    {
      for ( int c = 0 ; c < col ; ++c )
	cout << numbers[r][c] << " ";

      cout << endl;
    }
}

/**
 * This function gets the max number from the column's max
 *
 * Parameter: array of number
 * Parameter: the size
 * Return: the max of the column's max
 */

int getMax(int number[], int size)
{
  int max = number[0];
  for ( int i = 1; i < size; i++)
    if(max<number[i])
      max=number[i];
  return max;
}

/**
 * This function gets the max value of the column's
 *
 * Parameter: 2d array of numbers
 * Parameter: number of rows
 * Parameter: number of column
 * Parameter: the maxValues array
 * Return: None
 */

void getMaxColValues( int numbers[][100], int row, int col, int maxValues[] )
{
  for ( int c = 0 ; c < col ; ++c )
    {
      int max = numbers[0][c];
      for ( int r = 1 ; r < row ; ++r )
	if ( max < numbers[r][c] )
	  max = numbers[r][c];
      
      maxValues[c] = max;
    }
}

int main()
{
  int numbers[100][100];
  int row, col;
  cout << "Enter number of rows and columns: ";
  cin >> row >> col;

  getNumbers( numbers, row, col );

  printNumbers( numbers, row, col );

  int maxValues[col];

  getMaxColValues( numbers, row, col, maxValues );

  cout << "Max values are: ";
  for ( int i = 0 ; i < col ; ++i )
    cout << maxValues[i] << " ";
  cout << endl;

  cout << "Largest value: " << getMax(maxValues, col)<< endl;

  return 0;

}
