#include <iostream>
using namespace std;

int main(){
  int n;
  cout <<"Enter an integer: ";
  cin >>n;
  for(int r=1;r<=n;r++)
    {
      int sum=r;
      cout << r << " ";
      for(int c=1;c<=r*r;c++)
	{
	  if(c>r)
	    {
	      cout << c << " ";
	      sum=sum+c;
	    }
	}
      cout <<"the sum is "<<sum<<endl;
    }
  return 0;
}
