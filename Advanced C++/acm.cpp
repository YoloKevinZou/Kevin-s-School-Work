#include <iostream>
#include <cstdlib>
#include <cmath>
#include <sstream>
#include <set>
#include <fstream>
#include <iterator>

using namespace std;

bool ok(int q[], int c, int size){
  for(int i=0; i<c; i++)
    {
      if(q[c]==q[i]||(q[c]<0&&q[c-1]<0)||(q[c]>0&&q[c-1]>0)||(abs(q[c])==abs(q[c-1]))||(abs(q[0])==abs(q[size-1]))) 
	return false;
    } 
  return true;
}

void backtrack(int &col){
  col--;
}

void toString(int a[], int size, set<string>  &s){
     
     ostringstream convert;
	 for(int i =0; i<size; i++){
		convert<<a[i]<<" ";
	 }
	 s.insert(convert.str());
}


void swap(int a[], int size, set<string>  &s){

int *temp = new int[size];
temp[0]=1;
int count = size/2;
for(int i = 1; i < size; i++){
        if(i%2==0){
        temp[i]=count;
        count--;
        }
        else{
        temp[i]=0;
        }
}

for(int i = 0; i < size; i++){
        if(a[i]==-1)
        temp[i]=-1;
}

int x;
for(int i = 2; i < size; i=i+2){
        x=temp[i];
        for(int j = 1; j < size; j++){
        if(abs(a[i])==-1*a[j])
                temp[j]=-1*x;
        }
}

toString(temp,size, s);
}

void print_Set(set<string>  &s){

	for(set<string>::iterator i=s.begin();i!=s.end();++i){
		cout<<(*i);
	cout<<endl;
	}
}

void print(int q[], int size){

  for(int i=0; i<size; i++){      
    cout<<q[i]<<" ";
  }
cout<<endl<<endl;
}

void output(){
     
}
int main(){
    

  ofstream outfile;
  outfile.open("seating.txt");
  outfile.close();
  int n;
  cout << "Enter Number of Teams: ";
  cin >> n;
  for(int i=1; i <= n; i++)
  {
          
  outfile.open("seating.txt", std::ios::app);  
  set<string> set;
  int s=i*2;
  
  int *q = new int[s];
  
  for(int j = 0; j < s; j++)
  q[j]=0;
  int c=1;
  q[0]=1;
  
  bool from_backtrack=false;


  while(true)
  {  
     while(c<s)
     {
	             if(!from_backtrack)
	             q[c]=-1*((s/2)+1);
	             from_backtrack=false;
	             
                 while(q[c]<s/2+1)
                 {
                  q[c]++;
                  if(q[c]==0)
	              ++q[c];
	                     if(q[c]==s/2+1)
	                     {
		                 backtrack(c);
		                 continue;
	                     }
                   if(ok(q,c,s)) {
                               break;}
                   }
              if(c==0) break;
              c++;
              }
      

      if(c==0){
      break;
      }    
      
      swap(q,s,set);  
      c--;
      from_backtrack=true;
      
    }
    
    outfile << "Case " << s/2 << ": " <<set.size() << endl;
    cout << "Case " << s/2 << ": " <<set.size() << endl;
    
    for(std::set<string>::iterator x=set.begin(); x!=set.end(); ++x){
		outfile << (*x);
	outfile << endl;
	}
	
	outfile.close();
	
    print_Set(set);
       
}
    system("Pause");
  return 0; 
}
