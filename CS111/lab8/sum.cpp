#include<iostream>
using namespace std;

int main(){
  int sum=0;
  int counter=1;
  for(;counter<=10;counter++)
    {
      int number;
      cout << "Enter number: ";
      cin>>number;
      sum = sum +number;
    }
  cout << "The total is: " << sum << endl;
  return 0;
}
