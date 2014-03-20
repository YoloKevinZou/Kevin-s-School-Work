#include <iostream>
#include <fstream>
using namespace std;

int main(){
  ifstream myIn("output.txt");
  if(myIn==0) exit(0);
  while(!myIn.eof())
    {
      string line;
      getline(myIn, line);
      cout << line;
    }
  return 0;
}
