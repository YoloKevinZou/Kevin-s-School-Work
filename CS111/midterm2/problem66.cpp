#include <iostream>
using namespace std;

int main(){
  int a, b;srand(time(0));
  cout << "Enter a positive integer at most 20: ";
  cin >> a;
  cout << "Enter another poisitive integer at most 20: ";
  cin >> b;
  int array[a][b];
  for(int r = 0;r < a;r++)
    {
      for( int c = 0; c < b; c++)
	{
	  array[r][c]=rand()%6+1;
	}
      cout << endl;
    }
  for (int r = 0; r < a; r++)
    {
      for ( int c = 0; c < b;c++)
	{
	  cout << array[r][c] << " ";
	}
      cout << endl;
    }
  cout << "The picture is: " << endl;
  
  for ( int r = 0; r < a; r++)
    {
      for ( int c = 0; c < b; c++)
	{
	  if(array[r][c]%2==0)
	    cout << "X"<< " ";
	  else
	    cout << "O"<< " ";
	}
      cout << endl;
    }
  
  return 0;
}
