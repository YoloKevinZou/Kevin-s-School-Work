#include <iostream>
using namespace std;

/**
 * This function will ask the user to enter a number
 *
 * Parameter: None - function doesn't need any parameters
 * Return: num - the number enter by user
 */

int getNumber()
{
  int num;
  cout << "Enter a number: ";
  cin >> num;
  return num;
}

/**
 * This function will ask the user for the digit to count
 *
 * Parameter: none- this function does not need parameters;
 * Return: Digit - the digit enter by the user
 */

int getDigit()
{
  int digit;
  cout <<"Enter a single digit: ";
  cin >> digit;
  return digit;
}

/**
 * This function counts the amount of digit in a number
 *
 * Parameter - the number and the digit entered.
 * Return - returns 0 if its a single digit number and is not equal to the digit enter.
 * Return - returns 1 if its a the digit number is equal to the digit enter
 */
 
int digitCounter(int num, int digit)
{
  if(num<10)
    {
      if(num==digit)
	return 1;
      return 0;
    } 
  return digitCounter(num%10,digit)+digitCounter(num/10,digit);
}
  
int main()
{
  int number = getNumber();
  int digit = getDigit();
  int count = digitCounter( number, digit );
  //make output look nice
  cout << "The digit " << digit << " appeared " << count << " time";
  //add plural form of time if count is greater than 1
  if ( count != 1 )
    cout << "s";
  cout << "." << endl;
  return 0;
}
