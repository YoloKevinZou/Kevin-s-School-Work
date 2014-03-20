#include <iostream>
#include <map>
#include <string>
#include <sstream>
#include <fstream>
using namespace std;

int main(){
    
   	string line;
    int linenumber = 1;
    string x;
    
    typedef map<int, int> inner;
    typedef map<string, inner> outer;
    outer a;
    
    ofstream outfile;
    outfile.open ("output-permuted.txt");
    
    
    map<string, map<int,int> >::iterator oit;
    map<int, int >::iterator it;
    
    ifstream infile ("input-permuted.txt");
    //split the strings and store them into the outter and inner map
    //the inner map with key of line number
    //outter map with key of the string
   	if (infile.is_open()){
       while ( infile.good() ){
             getline (infile,line);
             if(!line.empty()){
             istringstream stringSplitter(line);
             int count = 1;
             while(stringSplitter>>x){
             a[x.c_str()][linenumber]+=count;
		     } 
            linenumber++;   
            }
       }
  }
  
  //prints out the map within a map
  for (oit = a.begin(); oit != a.end(); ++oit)
  {
    cout << oit->first << ":";
    outfile << oit->first << ":";
    
    inner& in = oit->second;
    
    for (it = in.begin(); it!= in.end(); ++it)
    {
        if(it->second==1&&it!=in.begin()){
        cout << ", " << it->first;
        outfile << ", " << it->first;
        }
        else if(it->second==1&&it==in.begin()){
        cout << " " << it->first;
        outfile << " " << it->first;
        }
        else if(it->second>1&&it!=in.begin()){
        cout << ", " <<it->first << "(" << it->second << ")";
        outfile << ", " <<it->first << "(" << it->second << ")";
        }
        else if(it->second>1&&it==in.begin()){
        cout << " " <<it->first << "(" << it->second << ")";
        outfile << " " <<it->first << "(" << it->second << ")";
        }
    }
    cout << endl;
    outfile << endl;
  }
          
 system("PAUSE");
 return 0;   
}
