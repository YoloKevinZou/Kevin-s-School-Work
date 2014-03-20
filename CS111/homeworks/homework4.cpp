#include <iostream>
using namespace std;

int main()
{
  int year; // identify a variable as year

  cout << "Enter a year "; // asks the user to input a year
  cin >> year; // stores in value for variable year
  
  if (year%400==0) // check to c if the input value is divisible by 400.
    cout << "It is a leap year"<<endl; // if the value is divisible by 400 it will display the message "It is a leap year"

  else if(year%100==0) // check to c if the input value is divisible by 100 after the previous calculation.
    cout << "It is NOT a leap year"<<endl; // if the input value is divisible by 100 then it is not a leap year

  else if(year%4==0) // check to see if the input value is divisible by after previous calculation.
    cout<< "It is a leap year."<<endl; //if the input value is divisible by 4 then it will display the message "It is a leap year.
 
 else 
   cout << "It is Not a leap year"<<endl; // if all of the above are false it will display the message it is not a leap year.

return 0;
}
