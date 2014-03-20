#include <iostream>
using namespace std;

int sixCount(int array[][6], int row, int col)
{
  int count=0;
  for ( int r=0; r<row; r++)
    {
      for (int c = 0; c < col; c++)
	{
	  if(array[r][c]==6)
	    count++;
	}
    }
  return count;
}


int main(){
  int arr[2][6]={{6,4,3,1,2,2},{6,6,5,2,3,6}};
  cout << sixCount(arr, 2, 6) << endl;
  return 0;
}
