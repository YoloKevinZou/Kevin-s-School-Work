#include <iostream>
using namespace std;

int sum2D(int array[][4], int row, int col)
{
  int sum=0;
  for (int r = 0; r < row; r++)
    {
      for ( int c = 0; c < col; c++)
	{
	  sum+=array[r][c];
	}
    }
  return sum;
}


int main()
{
  int array[3][4] = {{1,2,3,4},{1,2,3,4},{1,2,3,4}};
  cout << sum2D(array, 3, 4) << endl;
  return 0;
}
