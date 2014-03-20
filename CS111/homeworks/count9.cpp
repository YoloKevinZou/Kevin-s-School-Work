#include<iostream>
using namespace std;

/*
Write a recursive function that will figure out...
how many times the digit 9 appeared in a number.
*/

int countNine( int x )
{
  if ( x < 10 )
    {
      if ( x == 9 )
	return 1;
      return 0;
    }

  return countNine( x%10 ) + countNine( x/10 );
}

int main()
{
  cout << "Enter a number: ";
  int number;
  cin >> number;

  cout << "The number " << number << " has "
       << countNine( number ) << " number of 9's." << endl;

  return 0;
}
