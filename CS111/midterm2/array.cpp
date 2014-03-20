#include <iostream>
using namespace std;

int max(int array[][4], int row, int col)
{
  int max=0;
  for(int r=0;r<row;r++)
    {
      for (int c = 0; c<col;c++)
	{
	  if(max<array[r][c])
	    max=array[r][c];
	}
    }
  return max;
}

int main(){
  int array[3][4]={{1,2,3,4},{3,4,5,6},{7,8,9,0}};
  cout << max(array,3,4) << endl;
  return 0;
}
