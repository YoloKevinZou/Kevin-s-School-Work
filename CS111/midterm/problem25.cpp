#include <iostream>
using namespace std;

int main(){
  int n;
  cout <<"Enter a positive integer: " ;
  cin >> n;
  if(n<0) exit(1);
  for(int i=1;i<=n;i++)
    {
      cout << "Hello ";
    }
  cout << endl;
  return 0;
}
