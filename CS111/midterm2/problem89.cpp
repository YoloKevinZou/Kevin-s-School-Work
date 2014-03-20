#include <iostream>
using namespace std;

int sixCount(int x)
{
  if (x<10)
    {
      if(x==6)
	return 1;
      return 0;
    }
  return sixCount(x/10)+sixCount(x%10);
}

int main() {
  cout << sixCount(16) << endl; // prints 1
  cout << sixCount(666) << endl; // prints 3
  cout << sixCount(777) << endl; // prints 0
  return 0;
}
