#include <iostream>
using namespace std;

int main(){
  int row,col;
  cout << "Enter the amount of row: ";
  cin >> row;
  cout << "Enter the amount of column: ";
  cin >> col;

  int grades[row][col];

  for(int r=0;r<row;r++)
    {
      for(int c=0;c<col;c++)
	{
	  cout << r << "," << c << " grade: ";
	  cin >> grades[r][c];
	}
    }
  for (int r=0;r<row;r++)
    {
      for(int c=0;c<col;c++)
        {
          cout << grades[r][c] << " ";
        }
      cout << endl;
    }
  return 0;
}
