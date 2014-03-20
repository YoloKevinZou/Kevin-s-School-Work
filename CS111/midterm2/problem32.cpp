#include <iostream>
using namespace std;

int evenUp(int x){
  if(x<10)
    {
      if(x%2==0)
	return x+1;
      return x;
    }
  return evenUp(x/10)*10+evenUp(x%10);
}

int main() {
  cout << evenUp(10) << endl; // prints 11
  cout << evenUp(2662) << endl; // prints 3773
  cout << evenUp(19683) << endl; // prints 19793
}
