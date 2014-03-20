#include <iostream>
using namespace std;

int getNumber()
{
  int num;
  cout <<"Enter a number: ";
  cin >> num;
  return num;
}

int smallestFactor( int num )
{
  for (int i=2;i<=num;i++)
    {
      if(num%i== 0)
	return i;
      return num;
    }
}
int main()
{
  int number = getNumber();
  int factor = smallestFactor(number); 
  if(factor == number)
    cout << "The number " << number << " is prime." << endl;
  else 
    cout << "the number " << number << " is composite." << endl;
 // cout << "Number: " << number << endl;
  cout << "Smallest factor: " << factor << endl;
  return 0;
}
