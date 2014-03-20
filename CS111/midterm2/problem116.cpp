#include <iostream>
using namespace std;

int length(int x, int base)
{
  if(x<10)
    return 1;
  return 1+length(x/base,base);
}


int main(){
  int n;
  cout << "Entera positive integer: ";
  cin >> n;

  cout << "Digits in n: " << length(n,10) << endl;
  cout << "Binary digits in n : " << length(n,2) << endl;;
  return 0;
}
