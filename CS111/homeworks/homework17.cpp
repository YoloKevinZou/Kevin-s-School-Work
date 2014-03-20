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

  // find the highest grade in class
  int max=grades[0];
  for( int i=0; i < size; i++)
    {
      if(grades[i]>max)
	max=grades[i]; 
    }
  
  //prints out the names and grades
  for ( int i = 0 ; i < size ; ++i )
    {
      cout << i+1 << ". " << names[i]  << " has the grade: " << grades[i] << endl;
    }

  // prints out the name the grade for those who got the highest grade.
  for( int i= 0; i < size; i++)
    {
      if (grades[i]==max)
	{
	  cout << names[i] << " has the highest grade: " << grades[i] << endl;
	}
    }
  
  //prints out the class average grade
  int sum=0;
  for (int i = 0; i < size; i++)
    {
      sum+=grades[i];
    } 
  double average=sum/((double)size);
  cout  << "Average grade for the class is: " << average << endl;
  
  //prints out the student who has the grade of lower then average
  for(int i=0;i<size;i++)
    {
      if(grades[i]<average)
	cout << names[i] << " has below average grades: " << grades[i] << endl;
    }
  
  return 0;
}
