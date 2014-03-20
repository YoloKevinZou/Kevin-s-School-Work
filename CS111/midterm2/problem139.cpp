#include <iostream>
using namespace std;

void printSquare(int n)
{
  for(int r=1; r <= n; r++)
    {
      for (int c=1; c <= n; c++)
        {
          cout << "*";
        }
      cout << endl;
    }
}

int main(){
  int n;
  cout << "Enter a positive integer: ";
  cin >> n;
  for(int i=1; i <=n; i++)
    {
      printSquare(i);
      cout << endl;   
 }
  return 0;
}
