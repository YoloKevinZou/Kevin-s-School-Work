#include <iostream>
using namespace std;

int main(){
  int n;
  cout << "Enter a postive integer: ";
  cin >> n;
  while (n>0)
    {
      cout << n << endl;
      n=n/10;
    }
  return 0;
}
