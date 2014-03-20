#include <iostream>
#include <string>
#include <sstream>
#include <fstream>

using namespace std;

struct Node
{
    string s;
    int line;    // the line of the term
    int count;       // the counter of the term
    Node* next;
    
    //default constructor for node for dummy nodes
    Node()
    {
             line=0;
             count=0;
             next=NULL; 
    }
           
    //2 parameter constructor let us do node(x,y) to store coefficient and store exponent      
    Node(string x, int y)
    {
             s=x;   
             line=y;
             count=1;
             next=NULL;         
    }
             // NULL if there is no next node    
};

class index
{
    private:
        Node* head;     
     
    public:
    
    //default constructor for poly, create 1 with only dummy noce
    index(){
           head = new Node();  
    }
    
    //add the nodes into the linklist with the string being sorted in alphabetical order
    void add(string s, int ln)
    {
        Node *p = head; 
        while(p->next!=NULL){
              if(s.compare(p->next->s)==0&&ln==p->next->line){
              ++p->next->count;
              return;
              }
              
              else if(s.compare(p->next->s)<0){
              Node *x = new Node(s,ln);
              x->next=p->next;
              p->next=x;
              return;
              }
              
              p=p->next;
        }
        
        if(p->next==NULL)
        p->next=new Node(s, ln);
                
        }
    
    friend ostream& operator<<(ostream& os,  index a);
    
};

//overloading the << operator
//allows us to do cout << index;
ostream& operator<<(ostream& os,  index a){
         
         Node *previous = a.head;
         Node *current = a.head->next;
         Node *n = a.head->next->next;
         
         stringstream convert;
         
         string temp="";
         
         while(current!=NULL)
         {                   
                             if((current->s.compare(previous->s)!=0&&current->count==1)){
                             stringstream convert;
                             convert << current->line;
                             temp+= current->s +": " + convert.str();
                             }
                             else if(current->s.compare(previous->s)!=0&&current->count>1){
                             stringstream convert;
                             convert << current->line << "(" << current->count << ")";
                             temp+= current->s +": " + convert.str();
                             }
                             else if(current->s.compare(previous->s)==0&&current->count==1){
                             stringstream convert;
                             convert << current->line;
                             temp+= ", " + convert.str();
                             }
                             else if(current->s.compare(previous->s)==0&&current->count>1){
                             stringstream convert;
                             convert << current->line << "(" << current->count << ")";
                             temp+= ", " + convert.str();  
                             }
                          
          
         if(current->s.compare(n->s)!=0||current->next==NULL){
         os << temp << endl; 
         temp="";
         }
                        
         previous=previous->next;
         current=current->next;
         if(n->next!=NULL)
         n=n->next;
         }         
         return os;
                    
};

int main(){
 
	string line;
	ofstream outfile;
    int linenumber = 1;
    string x;
    index index;
    
    ifstream infile ("input-permuted.txt"); 
    outfile.open ("output-permuted.txt");
    
    //read each line and split them into strings
    //and add it into the linklist with its linenumber
	if (infile.is_open()){
       while ( infile.good() ){
             getline (infile,line);
             if(!line.empty()){
             istringstream stringSplitter(line);
             
             while(stringSplitter>>x){
		     index.add(x.c_str(),linenumber);
		     } 
		    
            linenumber++;   
		}
      }
	}
	
    cout << index;
    outfile << index;
	system("PAUSE");
	return 0;   
}
