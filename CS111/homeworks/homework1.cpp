#include<iostream>
using namespace std;
int main()
{
  string firstname, lastname; // variable for last and first name                          
  
  cout << "Enter your first name: "; // display a message that tells the user to input first name                        
  cin >> firstname; //  stores in the string for firstname variable                                        
  cout << "Enter Your last name: "; // display a message that tells the user to input last name                           
  cin >> lastname; // stores in the variable for lastname variable                                      
  cout << "Hello " <<firstname <<" " << lastname << endl; // displaying "hello" then follow by the user's first name then last name.                                                                     
  return 0;
}

