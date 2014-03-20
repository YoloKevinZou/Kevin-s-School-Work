/*This program is almost done, it gives the right output
but I think there's something wrong with the operator<< because
I can't use it to output the safe array. The only way it works is
with a for loop*/

#include<iostream>
#include <cstdlib>
#include<cassert>
using namespace std;

template <class T> class SA;

template <class T>
ostream& operator<<(ostream& os,  SA<T> s);

template <class T>
class SA{
	private:
		int low, high;
		T* p;
	public:
// default constructor
// allows for writing things like SA a;
   SA(){low=0; high=-1;p=NULL;}
// 2 parameter constructor lets us write
// SA x(10,20);
   SA(int l, int h){
		if((h-l+1)<=0){		
			cout<< "constructor error in bounds definition"<<endl;
			system("PAUSE");
			exit(1);
		}
		low=l;
		high=h;
		p=new T[h-l+1];
	}
// single parameter constructor lets us
// create a SA almost like a "standard" one by writing
// SA x(10); and getting an array x indexed from 0 to 9
	SA(int i){
		low=0; high=i-1;
		p=new T[i];
	}

// copy constructor for pass by value and
// initialization
	SA(const SA<T> & s){
		int size=s.high-s.low+1;
		p=new T[size];
		for(int i=0; i<size; i++){
			p[i]=s.p[i];
		}
		low=s.low;
		high=s.high;
	}
// destructor
	~SA(){ delete [] p; }
//overloaded [] lets us write
//SA x(10,20); x[15]= 100;
	T& operator[](int i){
		if(i<low || i > high){
			cout<< "index "<<i<<"out of range second bracket"<<endl;
			system("PAUSE");
            exit(1);
		}
		return p[i-low];
	}
// overloaded assignment lets us assign
// one SA to another
	SA& operator=(const SA<T> & s){
		if(this==&s)return *this;
		delete [] p;
		int size=s.high-s.low+1;
		p=new T[size];
		for(int i=0; i<size; i++){
			p[i]=s.p[i];
		}
		low=s.low;
		high=s.high;
		return *this;
	}

// overloads << so we can directly print SAs
friend ostream& operator<<<T>(ostream& os,  SA<T> s);
};

template<class T>
ostream& operator<<(ostream& os, SA<T> s){
	int size=s.high-s.low+1;
	for(int i=0; i<size; i++){
		cout<<s.p[i] << " ";
	}
	cout << endl;
	return os;
};

template <class T> class safeMatrix;

template <class T>
ostream& operator<<(ostream& os,  safeMatrix<T> s);

template <class T>
class safeMatrix{

  private: 
           int lowerrow, upperrow, lowercol, uppercol;
           SA < SA<T> > internal;
  public:
         
         //default constructor for the safeMatrix
         //allow to write things like safeMatrix a;
         safeMatrix(){
                      cout << "Empty Matrixs. " << endl;
                      lowerrow=0;
                      upperrow=-1;
                      internal=NULL;
         }
         
         // single parameter constructor lets us
         // create a square safeMatrix with the number of rows = columns
         // safeMatrix x(10); sets up a matrix with index 0 to 9 for column and rows
         safeMatrix(int square_size) : lowercol(0), uppercol(square_size-1), lowerrow(0), upperrow(square_size-1){
         internal = SA< SA<T> > (square_size);
         for(int i = 0; i < square_size; i++){
                 internal[i]= SA<T>(square_size);       
                 }
         }  
         
         // 2 parameter constructor lets us write
         // safeMatrix x(10,20), the first parameter represent the number of rows in the matrix and the second parameter represents the number of columns
         safeMatrix(int rows, int cols) : lowercol(0),uppercol(cols-1),lowerrow(0),upperrow(rows-1){
         
         internal = SA< SA<T> > (rows);
         for (int i = 0; i < rows; i++){
             internal[i]= SA<T>(cols);
             }
         }
         
         // 4 parameter constructor let us write
         // safeMatrix x(10,20,30,40), 
         // the amount of rows will be represented by rmax-rmin+1
         // the amount of columns will be represented by cmax-cmin+1
         safeMatrix(int rmin, int rmax, int cmin, int cmax) : lowerrow(rmin),upperrow(rmax),lowercol(cmin),uppercol(cmax){
                        
                        internal = SA< SA<T> > (rmin,rmax);
                        for(int i = rmin; i < rmax+1 ; i++){
                                internal[i]=SA<T>(cmin,cmax);
                                }
                        }
             
       //display "Destructor" whenever the safematrix destructor is called                           
       ~safeMatrix(){
                     cout << "Destructor being called";
                     cout << endl << endl;
        }
         
         //overload the [] operator for safeMatrix class which returns a safearray
         // let us write safeMatrix x(10,20), x[10][20]=15;
         // it will return the safearray that x[10] is located and do safearray[20] with it
         SA<T>& operator[](int i){
		 	if(i < lowerrow || i > upperrow){
			cout<< "index "<< i <<" out of range first bracket"<<endl;
			system("PAUSE");
            exit(1);
            }
            return internal[i];
            }
            
            //overloads the * operator so we can multiply 2 matrixs together
            // let us write safeMatrix x(10), safeMatrix y(10); x*y;
            safeMatrix<T> operator*(safeMatrix<T> s){ 
                          
                          int lcol = uppercol-lowercol+1;
                          int rrow = s.upperrow-s.lowerrow+1;
                                                    
                          if(lcol!=rrow){
                                         cout << "Matrix cannot be multiplied." << endl;
                                         system("PAUSE");
                                         exit(1);
                          }
                          
                          safeMatrix<T> x(lowerrow, upperrow, s.lowercol, s.uppercol);
                          
                          for(int l=lowerrow; l < upperrow+1; l++){
                           for (int m=s.lowercol; m < s.uppercol+1; m++){
                           x.internal[l][m] =0;
                           for (int k=lowercol; k < uppercol+1; k++)
                           x.internal[l][m] +=internal[l][k] * s.internal[k][m];
                            }
                           }                           
            return x;
            }
                          
    // overloads << so we can print out the entire matrix directly
    friend ostream& operator<<<T>(ostream& os,  safeMatrix<T> s);         
      
};

template <class T>
//overloads << so we can print the entire matrix directly
ostream& operator<<(ostream& os, safeMatrix<T> s){
         for(int i = s.lowerrow; i < s.upperrow+1; i++){
                         cout << s.internal[i];
         }
         return os;        
};


int main(){

//creates a safeMatrix using the 1 parameter constructors
safeMatrix<int> a(5);

int count=1;
//putting values into the Matrix A
for(int i = 0; i < 5; i++){
        for(int j = 0; j < 5; j++){
                a[i][j]=count;
                count++;
                }
}

//display the Matrix a using cout command
cout << "Matrix A" << endl << a;

//creates a safeMatrix using the 2 parameter constructors
safeMatrix<int> b(5,5);

count =1;

//putting values into the Matrix A
for(int i = 0; i < 5; i++){
        for(int j = 0; j < 5; j++){
                b[i][j]=count;
                count++;
                }
}

//display the Matrix b using cout command
cout << "Matrix B" << endl;
cout << b;

//creates a safeMatrix using the 4 parameter constructors
safeMatrix<int> c(5,9,5,9);
safeMatrix<int> d(5,9,2,4);

count = 1;

//putting values into the Matrix C
for(int i = 5; i <= 9; i++){
        for(int j = 5; j <= 9; j++){
                c[i][j]=count;
                count++;
                }
}

count =1;

//putting values into the Matrix D
for(int i = 5; i <= 9; i++){
        for(int j = 2; j <= 4; j++){
                d[i][j]=count;
                count++;
                }
}

cout << "Matrix C" << endl;
cout << c;

cout << "Matrix D" << endl;
cout << d;

//display the Matrix a*b using cout command and the * operator
cout << "Matrix A*B" << endl;
cout << a*b;

//display the Matrix c*d using cout command and the * operator
cout << "Matrix C*D" << endl;
cout << c*d;

system("PAUSE");
return 0;

}
