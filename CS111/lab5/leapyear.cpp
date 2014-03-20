#include <iostream>
using namespace std;

int main()
{
  //read the year from the user
  int year;
  cout << "Enter a year: ";
  cin >> year;

  //find the remainder when dividing the number by 4
  int remainder = year % 4;

  //check to see if remainder is 0
  if ( remainder == 0 )
    {
      cout << "The year is a leap year." << endl;
    }
  else
    {
      cout << "The year is not a leap year." << endl;
    }

  return 0;
}

/*
Something to think about:
Are the { } need for the if statements described above?
*/
