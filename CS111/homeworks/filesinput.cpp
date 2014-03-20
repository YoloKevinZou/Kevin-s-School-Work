#include <fstream>
#include <iostream>
using namespace std;

int main(){
  string x,y,z,a;
  ifstream myIn("output.txt");
  if(myIn==0) exit(0);  
  while(!myIn.eof())
    { 
      getline(myIn, x);    
      cout  << x << endl;
    }
 return 0;
}
