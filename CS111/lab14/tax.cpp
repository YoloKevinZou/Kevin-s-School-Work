#include <iostream>
using namespace std;

double getPrice()
{
  double price;
  cout << "Enter a price: ";
  cin >> price;
  return price;
}

double getTaxRate()
{
  double tax;
  cout << "enter tax in percent: ";
  cin >> tax;
  return tax;
}

double computeTotal(double price, double tax)
{
  return ((tax/100) + 1 )* price;
}

int main(){
  double price = getPrice();
  double tax=getTaxRate();
  double total=computeTotal(price, tax);
  cout << "Total cost is: " << total << endl;
}
