#include <iostream>
#include <fstream>
using namespace std;

int main(){
  string n;
  cout << "Enter a line of words ";
  getline(cin, n);
  ofstream f("output.txt");
  if(f==0) 
    cerr << "That file can't be used." << endl;
  f << n << endl;
  return 0;
}
