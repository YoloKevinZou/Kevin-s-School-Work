#include <iostream>
using namespace std;

int main(){
  int n,sum=0;
  cout <<"Enter a positive integer: ";
  cin >>n;
  if(n<0) exit(0);
  while(n>0)
    {
      cout << n%10 << " ";
      sum+=n%10;
      n=n/10;
    }
  cout << "sum to "<<sum<< endl;
  return 0;
}
