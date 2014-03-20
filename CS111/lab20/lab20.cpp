#include <iostream>
using namespace std;

void getNamesAndGrades( string names[],int grades[], int size)
{
  //read in names 
  for ( int i = 0; i < size ; i++)
    {
      cout << "Enter name " << i+1 << ": ";
      cin >> names[i];
    }
  
  for ( int i = 0; i < size; i++)
    {
      cout << "Enter grade for " << names[i] << ": ";
      cin >> grades[i];
    }
}

void printNamesAndGrades( string names[], int grades[], int size)
{
  for ( int i = 0; i < size; i ++)
    {
      cout << names[i] << " has grade is: " << grades[i] << endl;
    }
}

int findName(string names[], int size, string name)
{
  for ( int i = 0; i < size; i++)
    {
      if (names[i]==name)
	return i;
    }
  return -1;
}


int main()
{
  int size;
  cout << "Enter number of students: ";
  cin >> size;

  string names[size];
  int grades[size];
  
  getNamesAndGrades( names, grades, size); 
  printNamesAndGrades( names, grades , size);

  string name;
  cout << "Enter a name: ";
  cin >> name;

  int nameIndex =findName(names,size,name);

  cout << "Index: " << nameIndex << endl;

  if (nameIndex==-1)
    cout << "Name not found. " << endl;
  else 
    cout << names[nameIndex] << " has grade " << grades[nameIndex] << endl;
  
  return 0;
}
