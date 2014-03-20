#include <iostream>
using namespace std;

int getNumber()
{
  cout << "Enter a number: ";
  int num;
  cin >> num;
  return num;
}

int smallestFactor( int num )
{
  for (int f=2;f<=num;f++)
    {
      if(num%f== 0)
        return f;
      return num;
    }
}

int main()
{
  int number = getNumber();
  cout << number << " = ";

while( number > 1)
    {
  int f= smallestFactor( number );
  cout << f << " " ;
  number = number / f;
  if ( number != 1)
    cout << " * ";
    }
  cout << endl;
  return 0; 
}
