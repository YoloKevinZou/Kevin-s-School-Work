#include<iostream>
using namespace std;

int main()
{
  int size;
  cout << "How many students are in the class? ";
  cin >> size;

  string names[size];

  //read in the names from the user
  for ( int i = 0 ; i < size ; ++i )
    {
      cout << "Enter name of student " << i+1 << ": ";
      cin >> names[i];
    }

  int grades[size];
  //ask the user for the grades
  for ( int i = 0 ; i < size ; ++i )
    {
      cout << "Enter the grade for " << names[i] << ": ";
      cin >> grades[i];
    }


  //prints out the names and grades
  for ( int i = 0 ; i < size ; ++i )
    {
      cout << i+1 << ". " << names[i] 
	   << " has the grade: " << grades[i] << endl;
    }
  
  return 0;
}
