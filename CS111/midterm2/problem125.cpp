#include <iostream>
using namespace std;

int biggestDigit(int x)
{
  if (x<10) return x;
  int b=biggestDigit(x/10);
  if (x%10>b) return x%10;
  return b;
}

int main() {
  cout << biggestDigit(1760) << endl;
  return 0;
}
