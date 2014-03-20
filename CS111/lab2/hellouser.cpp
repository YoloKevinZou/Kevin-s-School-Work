#include<iostream>
using namespace std;
int main()
{
  string firstname; string lastname; // variable for last and first name
  cout << "Enter your first name: "; // tells the user to input first name 
  cin >> firstname; // user's input for firstname variable
 cout << "Enter Your last name: "; // tells the user to input last name
  cin >> lastname; // user's input for the lastname variable
  cout << "Hello " <<firstname << lastname << endl; // displaying "hello" then follow by the user's first name then last name.
  return 0;
}
