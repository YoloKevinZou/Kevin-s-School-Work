#include <iostream>
using namespace std;

int main()
{
  string name;
  cout << "Enter some text: ";
  getline(cin, name);

  cout << name.substr(1) << endl;

  return 0;

}
