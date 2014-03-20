#include <iostream>
using namespace std;

int main(){
  int n;
  cout << "How many words? ";
  cin >> n;
  string names[n];
  cout << "Enter " << n << " words: "; 
  for(int i = 0; i < n; i++)
    {
      cin >> names[i];
    }
  
  for(int i = n-1; i>=0; i--)
    {
      cout << names[i] << " ";
    }
  cout << endl;
  return 0;
}
