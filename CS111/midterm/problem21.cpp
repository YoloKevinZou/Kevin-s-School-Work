#include <iostream>
using namespace std;

int main(){
  int n;
  cout << "Enter a positive integer: ";
  cin >> n;
  if(n<0) exit(1);
  for(int i=1;n>=i;n--)
    {
      cout << n << " ";
    }
  return 0;
}
