#include <iostream>
using namespace std;

int makeOne(int x){
  if(x<10)
    {
      if(x%2==0)
	return x;
      return 1;
    }
  return makeOne(x/10)*10+makeOne(x%10);
}

int main() {
  cout << makeOne(770) << endl; // prints 110
  cout << makeOne(13579) << endl; // prints 11111
  cout << makeOne(1000) << endl; // prints 1000
  return 0;
}
