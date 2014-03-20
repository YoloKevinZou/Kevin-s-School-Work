#include <cmath>
#include <iostream>
using namespace std;

bool checkOdd(int x)
{
  int num=x;
  if((num%100)%2!=0)
    { 
      num=num/10;
      if((num%10)%2!=0)
	return true;
    }
  return false;
}

void checkSquare(int x)
{
  double num;
  num=sqrt(x);
  if((num*num)==x)
    cout << x << endl;
}

int main(){
  int count=0;
  double y;
  long i;
  for(i = 0; i < 999999999999999999; i++)
    {
      if(checkOdd(i))
	{
	  y=sqrt(i);
	  cout << i << " ";
	  if((int(y)*int(y))==i)
	    {
	      count++;
	      cout << i << endl;	
	      return 0;
	    }
	}
    }
  return 0;
}
