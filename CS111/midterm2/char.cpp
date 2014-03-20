#include <iostream>
using namespace std;

int main(){
  srand(time(0));
  int n; 
  char random1=rand()%26+'A'; 
  char random2=rand()%26+'A';
  cout << "Enter a positive integer: ";
  cin >> n;
  
  for(int r=1;r<=n;r++)
    {
      for (int c = 1; c<=n; c++)
	{
	  if(((r+c)%2)==0)
	    cout << random1;
	  else 
	    cout << random2;
	}
      cout << endl;
    }
  return 0;
}
