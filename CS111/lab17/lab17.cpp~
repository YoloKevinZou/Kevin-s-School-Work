#include<iostream>
using namespace std;


int getNumber()
{
  int x;
  cout << "Enter a number: ";
  cin >> x;
  return x;
}

void printNumber( int x )
{
  if ( x < 10 )
    {
      cout << x;
      return;
    }

  cout << x%10;
  printNumber(x/10);
}

int main()
{
  int x = getNumber();
  printNumber(x);
  cout << endl;
  return 0;
}
