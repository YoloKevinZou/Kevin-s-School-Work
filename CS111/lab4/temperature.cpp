#include <iostream>
using namespace std;

int main()
{
  int f;
  cout << " Enter a temperature in degrees Fahrenheit :" ;
  cin >> f;

  double c;
  c = (f-32) * 5 / (double) 9; //makes 9 to 9.0
  cout << "In Celsius that is: " << c << endl;
  return 0;
}
