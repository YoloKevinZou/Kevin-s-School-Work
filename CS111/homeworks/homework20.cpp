#include <iostream>
using namespace std;

/**
 * This function reads in the name from user and store into the array
 *
 * Parameter: names array
 * Parameter: the size
 * return: none
 */

void readNames(string names[],int size)
{
  for(int i = 0; i < size; i++)
    {
      cout << "Enter name " << i+1 << ": ";
      cin >> names[i];
    }
}

/**
 * This function prints out the names in the array
 *
 * Parameter: names array
 * Parameter: the size
 * return: none
 */

void printNames(string names[], int size)
{
  for ( int i = 0;i < size; i++)
    cout << names[i] << " ";
  cout << endl;
}

/**
 * this function sorts the name in the array into alphabetical order
 *
 * Parameter: names array
 * Parameter: the size
 * return: none
 */

void sort(string names[], int size)
{
    string temp;
    for ( int i = 0; i < size;i++)
      {
	temp=names[i];
	int index;
	
	for(index=i-1; index>=0 && names[index]>temp;index--)
	  {
	    names[index+1]=names[index];
	  }
	names[index+1]=temp;
	
      }
}


int main(){
  int size;
  cout << "Enter the amount of students: ";
  cin >> size;
  
  string names[size];
  readNames(names,size);
  sort(names,size);
  printNames(names,size);
  return 0;
}
