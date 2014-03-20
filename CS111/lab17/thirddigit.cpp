#include <iostream>
using namespace std;

int thirdDigit(int n)
{
  if(n<100)
    return 0;
  if (n<1000)
    return n%10;
 return thirdDigit(n/10);
}

int main() {
  cout << thirdDigit(777) << endl; //prints 7
  cout << thirdDigit(2048) << endl; //prints 4
  cout << thirdDigit(500125) << endl; //prints 0
  return 0;
}
