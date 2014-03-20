#include <iostream>
using namespace std;

int main(){
  int n;
  cout << "Enter a positive Odd Integer: ";
  cin >> n;
  while (n%2==0||n<0)
    {
      cout <<"Enter a positive Odd Integer: ";
      cin >> n;
    } 
  for(int r=1;r<=n;r++)
    {
      for(int c=1;c<=n;c++)
	{
	  if(r==(n+1)/2&&(r!=c))
	    cout << "+";
	  else if(c==(n+1)/2&&(c!=r))
	    cout << "+";
	  else if(r==c)
	    cout << "x";
	  else if((r+c)==(n+1))
	    cout << "x";
	  else 
	    cout << " ";
	}
      cout << endl;
    }
  return 0;
}
