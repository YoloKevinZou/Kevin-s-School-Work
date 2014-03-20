#include <iostream>
using namespace std;

int main(){
  srand(time(0));
  int max=20,low=10;
  int range=max-low;
  for(int i=1;i<=10;i++)
    {
      int randomNumber=10+(rand()%range+1);      
      cout<<randomNumber<<endl;
    }
  return 0;
}
