#include <iostream>
using namespace std;

void bars(int x)
{
  for (int i =0; i <x; i++)
    cout << "*";
}

int main(){
  int row;
  cout << "Enter an integer at most 100: ";
  cin >> row;
  int array[row];
  for ( int i = 0; i < row; i++)
    {
      cin >> array[i];
    }

  for ( int i = 0; i < row; i++)
    {
      bars(array[i]);
      cout << endl;    
    }




  return 0;
}

