#include <iostream>
using namespace std;

int main(){
  int size,num,x; int count[10];
  for(int i = 0; i <= 9;i++)
    count[i]=0; 
  cout << "Enter a positive integer: ";
  cin >> size;
  for ( int i = 0; i < size; i++)
    {
      cout << "Enter a single digit integer: ";
      cin >> num;
      count[num]++;   
    }
  cout << "The following were not entered: ";
  for ( int i = 0; i <=9; i++)
    if (count[i]==0) cout << " " << i;
  cout << endl;
  return 0;
}

/* int main() {
   int n, x, count[10];
  for (int c = 0; c <= 9; c++) count[c] = 0;
  cout << "Enter a positive integer (at most 100): ";
  cin >> n;
  cout << "Enter " << n << " single digit integers: ";
  for (int c = 0; c < n; c++) {
    cin >> x;
    count[x]++;
  }
  cout << "The following were not entered:";
  for (int c = 0; c <= 9; c++)
    if (count[c] == 0) cout << " " << c;
  cout << endl;
  return 0;
  } */
