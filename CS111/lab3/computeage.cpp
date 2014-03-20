#include <iostream>
using namespace std;

int main()
{
//current year
int currentYear = 2011;

//user's birth year
int birthYear;

//prompt user for bith year
cout << "What year were you born? ";

//read in user input
cin >> birthYear;

//compute age
int age = currentYear - birthYear;

//display computed age to the user
cout << "You are about " << age << " years old." << endl;

return 0;
}
