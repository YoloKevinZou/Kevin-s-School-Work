#include <iostream>
using namespace std;

void getGrades(int grades[], int size)
{
  for ( int i = 0; i < size; i++)
    {
      cout << "Enter grade for " << i+1 << ":";
      cin >> grades[i];
    }
}

void printGrades(int grades[],int size)
{
  for ( int i = 0; i < size; i++)
    {
      cout << grades[i] << " ";
    }
  cout << endl;
}

void swapMin(int grades[], int size, int index)
{
  int min = grades[index];
  int minIndex=index;
  for(int i = index+1; i < size; i++)
    {
      if(min>grades[i])
	{
	  min=grades[i];
	  minIndex=i;
	}
    }
  int temp=grades[index];
  grades[index]=grades[minIndex];
  grades[minIndex]=temp;

  // cout << "Min: " << min << endl;
  //cout << "Index: " << minIndex << endl;
}

void sort(int grades[], int size)
{
  for ( int i = 0; i < size-1; i++)
    {
      swapMin(grades, size, i);
    }
}

int main(){
  int size;
  cout << "Enter the number of grades: ";
  cin >> size;
  int grades[size];
  getGrades(grades, size);
  printGrades(grades, size);
  //swapMin(grades, size, 0);
  sort(grades,size); 
  printGrades(grades, size); 
  return 0;
}
