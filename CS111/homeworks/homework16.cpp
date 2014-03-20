#include <iostream>
using namespace std;

/**
 * This function asks the user for a number
 *
 * Parameter: None
 * Return: number enter by user.
 */

int getNumber()
{
  int num;
  cout << "Enter a Number: ";
  cin >> num;
  return num;
}

/**
 * This function adds 1 to even digit number
 * 
 * Parameter: int x
 * Return : the new number with even digits added
 */

int evenUp(int x)
{
  if(x<10)
    {
      if(x%2==0)
	return x+1;
      return x;    
    }
  if(x%2==0)
    return evenUp(x+1);
  return evenUp(x/10)*10+(x%10);
}

int main()
{
  int number = getNumber();
  int evenUpNumber = evenUp( number );
  cout << evenUpNumber << " - " << number << " = " << evenUpNumber - number << endl;
  return 0;
}
