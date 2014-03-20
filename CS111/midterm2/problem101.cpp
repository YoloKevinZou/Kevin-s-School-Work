#include <iostream>
using namespace std;

int main(){
  int n;
  cout << "Enter a positive integer: ";
  cin >> n;

  for(int r = 0; r <n; r++)
    {
      for (int c = 0; c<n; c++)
	{
	  if(((r+c)%2)==0)
	    cout << "X";
	  else 
	    cout << "O";
	}
      cout << endl;
    }
  return 0;
}
