#include <iostream>
using namespace std;

int main(){;
  int num;int sum=0;
  cout << "Enter a positive integer: ";
  cin >> num;
  for ( int i = 1; i <= num; i++)
    {
      cout << i*i << " ";
    }
  cout << endl;  
  for ( int i = 1; i <= num; i++)
    {
      sum+=i*i;
    }  
  cout << "sum to " << sum <<endl;

 return 0;
}
