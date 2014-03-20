#include <iostream>
using namespace std;

int main(){
  int n; int array[5];
  cout << "Enter 5 single digit positive integers: "; 
  for ( int i = 0; i < 5; i++)
    cin >> array[i];
  int sum=0;
  for ( int i = 0; i < 5; i++)
    sum+=array[i];
  
  cout << "The sum is: " << sum << endl;
  return 0;
}
