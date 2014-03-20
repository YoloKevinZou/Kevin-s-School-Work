#include <iostream>
using namespace std;

int main(){
  string day; string month;
  cout <<"What is your day and month of birth: ";
  cin >> day >> month;
  if(day=="14"&&month=="March")
    cout <<"Happy birthday."<<endl;
  else
    cout << "Hello"<< endl;
  return 0;
}
