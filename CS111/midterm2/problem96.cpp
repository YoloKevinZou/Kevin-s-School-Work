#include <iostream>
using namespace std;

int digitProduct(int x)
{
  if(x<10)
    return x;
  return digitProduct(x/10)*(x%10);
}
int main(){
  int n;
  cout << "Enter a positive integer n: ";
  cin >> n;
  if(n<0)
    {
      cout << "The number is invalid. ";
      exit(1);
    }

  cout << digitProduct(n) << endl;
  return 0;
}
