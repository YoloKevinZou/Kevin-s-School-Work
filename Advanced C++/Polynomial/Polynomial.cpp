#include <iostream>
#include <string>
#include <sstream>
#include <fstream>

using namespace std;

struct Node
{
    int coefficient;    // the coefficient of the term
    int exponent;       // the exponent of the term
    Node* next;
    
    //default constructor for node for dummy nodes
    Node()
    {
             coefficient=0;
             exponent=0;
             next=NULL; 
    }
           
    //2 parameter constructor let us do node(x,y) to store coefficient and store exponent      
    Node(int x, int y)
    {
             coefficient=x;
             exponent=y;
             next=NULL;         
    }
             // NULL if there is no next node    
};

class poly
{
    
    private:
        Node* head;     
        string list;
     
    public:
    
    //default constructor for poly, create 1 with only dummy noce
    poly(){
           head = new Node();  
    }
    
    //Constructor which takes a string
    //splits the strings and add them into polynomial
    poly(string s){
           
           head = new Node();
    
           string x;
           string y;
           
   	       istringstream stringSplitter(s);
   	       
           while(stringSplitter>>x>>y){
				add(atoi(x.c_str()),atoi(y.c_str()));
			}        
    }
    
    //Copy constructor for pass by value used
    poly(const poly& p)
    {          
              head = new Node();
              Node* b=p.head->next;
              
              while(b!= NULL){
                      add(b->coefficient, b->exponent);  
                      b=b->next;
              }
    }
    
   //destructor for the polynomial 
   ~poly()
   {
           Node* p = head;
           Node* n;
           while(p!= NULL){
                     n=p->next;
                     delete p;
                     p=n;                          
           }
   }
   
   //add node into the link list
   //it add it in base on the exponent power
   //highest exponent always get added to the left
   //if the exponent is the same we just added to the existing node
   void add(int x, int y)
   {    
        Node* p=head;
        
        if(x==0){
                 return;
        }
            
        while(p->next != NULL){
                          
               if( y == p->next->exponent){
                   p->next->coefficient = p->next->coefficient + x;
                   return;
                }
                
               if( y > p->next->exponent){
                   Node* n = new Node(x,y);
                   n->next=p->next;
                   p->next = n;
                   return;
               }
               p=p->next;
            }   
               
         if(p->next==NULL){
         p->next=new Node(x,y);
         }
   }
   
   //overload the + operator so we can add 2 polynomials
   //using poly x, poly y; x+y;
   poly operator+(poly& x)
    {    
         poly temp;
         Node *a=head->next;
         Node *b=x.head->next;
         while(a != NULL){
                       temp.add(a->coefficient,a->exponent);
                       a=a->next;
                       }
         
         while(b != NULL){
                       temp.add(b->coefficient, b->exponent);
                       b=b->next;
                       }
         
         return temp;
    }
    
    //overload the + operator so we can add 2 polynomials
    //using poly x, poly y; x-y;
    poly operator-(poly& x)
    {    
         poly temp;
         Node *a=head->next;
         Node *b=x.head->next;
         
         while(a != NULL){
                       temp.add(a->coefficient,a->exponent);
                       a=a->next;
                       }
         
         while(b != NULL){
                       temp.add((-1)*b->coefficient, b->exponent);
                       b=b->next;
                       }
         

         
         return temp;
    }
    
    //overload the - operator so we can add 2 polynomials
    //using poly x, poly y; x*y;
    poly operator*(poly x)
    {    
         poly temp;
         Node *a=head->next;
         Node *b;
         
         while(a!=NULL){
                        b=x.head->next;
                        while(b!=NULL){
                                       temp.add(a->coefficient*b->coefficient, a->exponent+b->exponent);
                                       b=b->next;
                                       }
                                       a=a->next;
                        }
         
         Node* empty = temp.head->next;
         
         return temp;
    }
    
    //overload the = operator so we can add 2 polynomials
    //using poly x, poly y; x=y;
    //set the values on y into x
    poly& operator=(const poly & s){
          
          if(this==&s) return *this;
          
          Node* p = head;
          Node* n;
          
          while(p!= NULL){
                     n=p->next;
                     delete p;
                     p=n;                          
          }
          
          head = new Node();
          Node* b=s.head->next;
              
          while(b!= NULL){
                    add(b->coefficient, b->exponent);  
                    b=b->next;
          }
          
          return *this;
          }
    
    friend ostream& operator<<(ostream& os,  poly s);
};

//overload the << operator
//allow us to display the entire poly using 
//cout << poly x;
ostream& operator<<(ostream& os,  poly s)
{
         Node* p=s.head->next;
                  
         if(p==NULL){
         os << "0 0";
         }
         
         while(p!= NULL){
                   if(p->coefficient!=0){
                   os << p->coefficient << " " << p->exponent << " ";
                   }
                   p = p->next;
                   }   
                       
         os << endl;
                     
         return os;
};


int main(){
    
	string line;
	ofstream outfile;
    
    ifstream infile ("input-polynomials.txt"); 
    outfile.open ("expected_output.txt");
    
    //opens the textfile and take all the lines and turn it into a polynomial.
    //output the new results of sum,subtraction, multiplication into an output textfile.
    if (infile.is_open()){
       while ( infile.good() ){
             getline (infile,line);
             
             if(!line.empty()){
                               
             cout << "original 1: " << line << endl;
             outfile << "original 1: " << line << endl;
             
             poly a(line);
             getline (infile,line);
             poly b(line);
             
             cout << "original 2: " << line;
             outfile << "original 2: " << line;
             cout << endl;
             
             cout << "canonical 1: ";
             outfile << endl << "canonical 1: " << a;
             cout << a;
             
             cout << "canonical 2: ";
             outfile << "canonical 2: " << b;
             cout << b;
             
             cout << "sum: " << a+b;
             outfile << "sum: " << a+b;
             
             cout << "difference: " << a-b;
             outfile << "difference: " << a-b;
             
             cout << "product: " << a*b;
             outfile << "product: " << a*b << endl;
             
             cout << endl;
             }      
       }
       
     infile.close();
     outfile.close();
    }
    
    else cout << "Unable to open file"; 
    
    system("PAUSE");
    return 0;
}

