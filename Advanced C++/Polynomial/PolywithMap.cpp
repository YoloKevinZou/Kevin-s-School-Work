#include <cstdlib>
#include <iostream>
#include <map>
#include <sstream>
#include <fstream>
using namespace std;
//Polynomial using maps
int main(int argc, char *argv[])
{
    map<int,int> poly1,poly2,sum,sub,mult;
    
    string x, y, line, line2;
    int coef, exp;
	ofstream outfile;
    
    ifstream infile("input-polynomials.txt"); 
    outfile.open ("expected_output.txt");
    
    map<int,int>:: reverse_iterator p;
    map<int,int>:: reverse_iterator j; 
    
    if (infile.is_open())
    {
       while ( infile.good() )
       {     
             //read the first line and splits the strings
             //to insert it into map poly1
             getline(infile,line);
             if(!line.empty())
             {
             cout << "original 1: " << line << endl;
             outfile << "original 1: " << line << endl;
             istringstream stringSplitter(line);
                           while(stringSplitter>>x>>y)
                           {
                            coef=atoi(x.c_str());
                            exp=atoi(y.c_str());
                            poly1[exp]+=coef;
                            if(poly1[exp]==0)
                            poly1.erase(exp);
                            }
             
             //read the following line and splits the strings
             //to insert it into map poly2              
             getline(infile,line2);
             cout << "original 2: " << line2 << endl;
             outfile << "original 2: " << line2 << endl;
             istringstream stringSplitter2(line2);
                           while(stringSplitter2>>x>>y)
                           {
                           coef=atoi(x.c_str());
                           exp=atoi(y.c_str());
                           poly2[exp]+=coef;
                           if(poly2[exp]==0)
                           poly2.erase(exp);
                           }
             
             //prints out poly 1 with the polynomial in canonical form
             cout << "canonical 1:";
             outfile << "canonical 1:";
                  for(p=poly1.rbegin();p!=poly1.rend();p++)
                  {
                  cout << " " <<(*p).second<<" " << (*p).first;
                  outfile << " " <<(*p).second<<" " << (*p).first;
                  }
             if(poly1.empty()){ 
             cout << " 0 0";
             outfile << " 0 0";
             }
             
             //prints out poly 2 with the polynomial in canonical form
             cout << endl;
             cout << "canonical 2:";
             outfile << endl;
             outfile << "canonical 2:";
             
                  for(j=poly2.rbegin();j!=poly2.rend();j++)
                  {
                  cout << " " <<(*j).second<< " " << (*j).first;
                  outfile << " " <<(*j).second<< " " << (*j).first;
                  }
             if(poly2.empty()){ 
             cout << " 0 0";
             outfile  << " 0 0";
             }
             
             //adds the 2 polynomials
             sum=poly1;
                  for(p=poly2.rbegin();p!=poly2.rend();p++)
                  {
                  sum[p->first]+=p->second;
                  if(sum[p->first]==0)
                  sum.erase(p->first);
                  } 
             
             //prints the sum
             cout << endl;
             cout << "sum:";
             outfile << endl;
             outfile << "sum:";
                  for(p=sum.rbegin();p!=sum.rend();p++)
                  {
                  cout << " " <<(*p).second <<" " <<(*p).first;
                  outfile << " " <<(*p).second <<" " <<(*p).first;
                  }
             if(sum.empty()){ 
             cout << " 0 0";
             outfile  << " 0 0";
             }        
             
             //subtracts the 2 polynomials
             sub=poly1;
                  for(p=poly2.rbegin();p!=poly2.rend();p++)
                  {
                  sub[p->first]-=p->second;
                  if(sub[p->first]==0)
                  sub.erase(p->first);
                  }
             
             //prints the differences
             cout << endl;
             cout << "difference:";
             outfile << endl;
             outfile << "difference:";
                  for(p=sub.rbegin();p!=sub.rend();p++)
                  {
                  cout << " " <<(*p).second <<" " <<(*p).first;
                  outfile << " " <<(*p).second <<" " <<(*p).first;
                  }
             if(sub.empty()){ 
             cout << " 0 0";
             outfile  << " 0 0";
             } 
             
             //multiples 2 polynomials
             for(p=poly1.rbegin();p!=poly1.rend();p++){
                for(j=poly2.rbegin();j!=poly2.rend();j++){            
                   mult[p->first+j->first]+=p->second*j->second;
                }
             }
             
             //prints the product
             cout << endl;
             cout << "product:";
             outfile << endl;
             outfile << "product:";
             for(p=mult.rbegin();p!=mult.rend();p++){
                if(mult[p->first]==0){
                continue;
                }
                cout <<" " << (*p).second << " " <<(*p).first;
                outfile <<" " << (*p).second << " " <<(*p).first;
             }
             if(mult.empty()){ 
             cout << " 0 0"; 
             outfile  << " 0 0";
             }
             
             cout << endl << endl;
             outfile << endl << endl;
             
             //clears all the current polynomial for loop use
             poly1.clear();
             poly2.clear();
             sum.clear();
             sub.clear();
             mult.clear();
             } 
       }      
     infile.close();
     outfile.close();
    }
    
    system("PAUSE");
    return 0;
}
