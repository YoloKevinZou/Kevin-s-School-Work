#include <iostream>
using namespace std;

void recursion(int n)
{
  if(n<10) 
    {
      cout << n;    
    }
  cout << n%10;
  recursion(n/10);
}

int main()
{
  int x;
  cout << "Enter an integer: ";
  cin >> x;
  recursion(x);
  return 0;
}
