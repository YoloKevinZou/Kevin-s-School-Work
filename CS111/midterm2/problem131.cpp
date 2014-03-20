#include <iostream>
using namespace std;

double plusTax(int cost, double rate)
{
  double Tax=cost*rate/100;
  int rounded = (int)(Tax+0.5);  
  return rounded+cost;
}

int main() {
  int cost = 100; // cost is 100 cents
  double taxRate = 4.8; // tax is at 4.8 percent
  cout << "With tax that is " << plusTax(cost, taxRate) << " cents." << endl;
  return 0;
}
