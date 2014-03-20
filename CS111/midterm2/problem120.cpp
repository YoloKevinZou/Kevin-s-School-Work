#include <iostream>
using namespace std;

int bar(int x){
  for ( int c = 0; c < x; c++)
    cout << "*";
  cout << endl;
}

int main()
{
  int n;
  int digit[5];
  cout << "Enter a 5 digit integer: ";
  cin >> n;
  for(int i =0; i < 5; i++) {
    digit[i]=n%10;
    n=n/10;
  }
  for (int i = 4; i >=0; i--) 
    bar(digit[i]);
  return 0;
}
