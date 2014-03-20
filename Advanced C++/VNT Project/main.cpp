#include<iostream>
#include "VNT.h"

using namespace std;

int main(){

//creates a vnt with 5 rows and 5 columns
vnt a(5,5);

//add elements into the VNT
a.add(24);
a.add(14);
a.add(10);
a.add(20);
a.add(11);

a.add(23);
a.add(15);
a.add(13);
a.add(12);
a.add(33);

a.add(14);
a.add(17);
a.add(17);
a.add(69);
a.add(16);

a.add(25);
a.add(22);
a.add(29);
a.add(26);
a.add(21);

a.add(40);
a.add(25);
a.add(36);
a.add(31);
a.add(30);

//display the VNT with elements sorted
cout << "VERY NEAT TABLE" << endl;
cout << a;

cout << "The minimum value of table is: " << a.getMin() << "\n" << endl;
cout << "Table after removing the a minimum value " << endl;
cout << a;

//testing the find function to see if a number is in VNT
cout << "Testing find function" << endl;
if(a.find(1))
cout << "The number is in the Matrix" << endl;
else
cout << "The number is not in the Matrix" << endl;

cout << endl;



//putting a unsorted array into a VNT and gets it sorted
vnt x(3,3);
//int array[9] = {-8, 19, 6, 39, 3, -7, 100, -18, 30};

int array[9] = {-8, 15, 6, 39, 37, -7, 1002, -18, 30};
x.sort(array,9);

cout << "Array before sort" << endl;
for(int i = 0; i < 9; i++){
        cout << array[i] << " ";
}

cout << endl;
x.sort(array,9); 

cout << "Array after sort" << endl; 
for(int i = 0; i < 9; i++)
                        cout<<array[i]<<", ";
                cout<<endl;


/*vnt y(3,3);

y.add(5);
y.add(10);
y.add(15);
y.add(20);
y.add(25);
y.add(30);
y.add(35);
y.add(40);
y.add(45);

cout << "Y: " << endl;
cout << y;

if(y.find(16))
cout << "The number is in the Matrix" << endl;
else
cout << "The number is not in the Matrix" << endl;*/
system("PAUSE");
return 0;

}
