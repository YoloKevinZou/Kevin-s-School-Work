#include <iostream>
using namespace std;

int lastDigit(int x)
{
  if(x<10)
    return x;
  return lastDigit(x%10);
}

int main()
{
  int n;
  cout << "Enter an integer that is at most 100: ";
  cin >> n;
  int array[n];
  cout << "Enter 7 numbers: ";
  for(int i=0; i<n; i++)
    {
      cin >> array[i];
    }

  for( int i = n-1; i>=0; --i)
    {
      cout << lastDigit(array[i]) << " ";
    }
  cout << endl;
  return 0;
}
