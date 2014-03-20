#include <iostream>
using namespace std;

int count9(int x)
{
  if (x<10)
    {
      if(X==9)
	return 1;
      return 0;
    }
  if(x==9)
    return 1+count9(x/10);
  return count(x/10);
}

int main()
{
