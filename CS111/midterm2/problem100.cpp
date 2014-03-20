#include <iostream>
using namespace std;

int main(){
  string name;
  cout << "Enter a name: ";
  getline(cin, name);
  for(int i = 0; i < name.size(); i++)
    name[i]=toupper(name[i]);
  cout << name << endl;
  for ( int i = name.size(); i>=0; i--)
    cout << name[i];
  cout << endl; 
 return 0;
}
