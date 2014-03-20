#include <iostream>
using namespace std;

int main(){
  int n;
  cout << "Enter an integer that is at most 20: ";
  cin >> n;;
  string words[n];
  cout << "Enter " << n << " words: ";
  for (int i=0;i<n;i++)
    {
      cin >> words[i];
    }
  int count[11];

  for ( int i=0; i < 11; i++) 
    count[i]=0; 
  
  for (int i=0;i<n;i++)
    {
      int len=words[i].length();
      count[len]++;
    }
  
  for(int i=0;i<11;i++)
    {
      if(count[i]!=0)
	cout << "Length " << i << ": count " << count[i] << endl;
    } 
 return 0;
}
