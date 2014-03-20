#include <iostream>
#include <fstream>
using namespace std;

int main(){ int argc, char *argv[])
{

  for ( int i = 1 ; i < argc; i++)

    cout << "--------------" << argv[i] << "-----------" << endl;

  fstream infile;
  infile.open(argv[i], fstream
