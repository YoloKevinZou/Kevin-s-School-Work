#include <iostream>
using namespace std;

int main(){
  int number;
  cout << "Enter a number ";
  cin >> number; 
 for(int i=0;i<=number;i++)
    {
      cout <<i<<":"<< i*i+1<< endl;
    }
  return 0;
} 
