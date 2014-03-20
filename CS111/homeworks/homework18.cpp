#include<iostream>
using namespace std;

void getNames( string names[], int size )
{
  for ( int i = 0 ; i < size ; ++i )
    {
      cout << "Enter name of student " << i+1 << ": ";
      cin >> names[i];
    }
}

void getGradesByName( string names[], int grades[], int size )
{
  for ( int i = 0 ; i < size ; ++i )
    {
      cout << "Enter grade for " << names[i] << ": ";
      cin >> grades[i];
    }
}

void printNamesAndGrades( string names[], int grades[], int size )
{
  for ( int i = 0 ; i < size ; ++i )
    cout << names[i] << " has grade " << grades[i] << endl;
}

/**
 * This function will try to look for a name in the names array.
 *
 * Param: names array
 * Param: size of names array
 * Param: the name in question
 *
 * Return: index of name in array or -1 if name is not found
 */
int findName( string names[], int size, string name )
{
  for ( int i = 0 ; i < size ; ++i )
    if ( names[i] == name )
      return i;
  return -1;
}

/**
 * This function looks for the highest grades in the names array.
 *
 * Param: Names array
 * Param: grades array
 * Param: int size
 *
 * Return: does not return anything
 */

void printHighestGrade(string names[],int grades[],int size)
{
  int max=grades[0];
  for(int i=0;i<size;i++)
    {
      if(grades[i]>max)
	{
	  max=grades[i]; 
       	}
    }
  for (int i=0; i < size; i++)
    {
      if (grades[i]==max)
        {
          cout << names[i] << " has the highest grade: " << grades[i] << endl;
        }
    }
}

/**
 * This function finds the class average
 * 
 * Param: Names array
 * Param: grades array
 * Param: int size
 * 
 * return the average number in doubles
 */

void printClassAverage(string names[],int grades[],int size)
{
  int sum=0;
  double average;
  for ( int i = 0; i < size; i++)
    {
      sum+=grades[i];
    }
  average=sum/((double)size);
  cout << "The class average is: " << average << endl; 
  for(int i=0;i<size;i++)
    {
      if(grades[i]<average)
        cout << names[i] << " has below average grades: " << grades[i] << endl;
    }
}

int main()
{
  int size;
  cout << "Enter number of students: ";
  cin >> size;

  string names[size];

  getNames( names, size );

  int grades[size];
  getGradesByName( names, grades, size );
  
  printNamesAndGrades( names, grades, size );


  //========= look up a grade
  cout << endl;
  cout << "Enter a name: ";
  string name;
  cin >> name;

  int nameIndex = findName( names, size, name );
  if ( nameIndex == -1 )
    cout << "Invalid student name." << endl;
  else
    cout << names[nameIndex] << " has the grade " << grades[nameIndex]<< endl;

  //display the students with highest grades
  printHighestGrade(names, grades, size);  
 
  //display the class average
  printClassAverage(names, grades, size);
  
  return 0;
}
