#include <iostream>
using namespace std;

int main(){
  int num;
  cout << "Enter a number: ";
  cin >> num;
  for(int i=2;i<num;i++)
    {
      if(num%i==0)
	{
	  cout << "Composite" << endl;
	  exit(1);
       	}
    }
  cout << "prime"<< endl;
  return 0;
}
