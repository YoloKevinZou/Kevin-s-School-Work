#include <iostream>
using namespace std;

int main(){
  int n;
  cout << "enter a positive integer: ";
  cin >> n;
  for (int r=1;r<=n;r++)
    {
      for (int c=1;c<=n;c++)
	{
	  if(r==n||c==1||r==c)
	    cout << "*";
	  else cout << " ";
	}
      cout << endl;
    }
  return 0;
}
