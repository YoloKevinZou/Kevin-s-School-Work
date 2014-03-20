#include <iostream>
using namespace std;

int toBinary(int x){
  if(x<2)
    return x;
  return toBinary(x/2)*10+x%2;
}

int main(){
  cout << toBinary(123)<< endl;
  return 0;
}
