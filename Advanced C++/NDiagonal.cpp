#include <iostream>
#include <cmath>
#include <cstdio>
using namespace std;

void populate(int* a, int size, int dimensions) {

    int max = pow(size,dimensions);
	for(int i = 0; i < max; i++)
		a[i] = i+1;
}

int diagonalSum(int* a, int size, int dimensions){

int sum=0;
for(int i = 0; i <size; i++){
        int x=0;
        for(int j=0;j<dimensions;j++){
                x+=pow(size,j)*i;
                }
                
                int i=*(a+x);
                sum+=ki;
                }
        return sum;
}

int main() {
	//N = 4
	//S = 4
	int array_4D_4S_1[4][4][4][4] = {0}; populate((int*)array_4D_4S_1, 4, 4);
	printf("D=4\tS=4\tSUM = %d\n", dsum((int*)array_4D_4S_1, 4, 4));
	//-------------------------------------
	//N = 2
	//S = 2
	int array_2D_2S_2[2][2] = {0}; populate((int*)array_2D_2S_2, 2, 2);
	printf("D=2\tS=2\tSUM = %d\n", dsum((int*)array_2D_2S_2, 2, 2));
	//-------------------------------------
	//N = 4
	//S = 2
	int array_4D_2S_3[2][2][2][2] = {0}; populate((int*)array_4D_2S_3, 2, 4);
	printf("D=4\tS=2\tSUM = %d\n", dsum((int*)array_4D_2S_3, 2, 4));
	//-------------------------------------
	//N = 5
	//S = 5
	int array_5D_5S_4[5][5][5][5][5] = {0}; populate((int*)array_5D_5S_4, 5, 5);
	printf("D=5\tS=5\tSUM = %d\n", dsum((int*)array_5D_5S_4, 5, 5));
	//-------------------------------------
	//N = 2
	//S = 3
	int array_2D_3S_5[3][3] = {0}; populate((int*)array_2D_3S_5, 3, 2);
	printf("D=2\tS=3\tSUM = %d\n", dsum((int*)array_2D_3S_5, 3, 2));
	//-------------------------------------
	//N = 3
	//S = 3
	int array_3D_3S_6[3][3][3] = {0}; populate((int*)array_3D_3S_6, 3, 3);
	printf("D=3\tS=3\tSUM = %d\n", dsum((int*)array_3D_3S_6, 3, 3));
	//-------------------------------------
	//N = 3
	//S = 4
	int array_3D_4S_7[4][4][4] = {0}; populate((int*)array_3D_4S_7, 4, 3);
	printf("D=3\tS=4\tSUM = %d\n", dsum((int*)array_3D_4S_7, 4, 3));
	//-------------------------------------
	//N = 2
	//S = 4
	int array_2D_4S_8[4][4] = {0}; populate((int*)array_2D_4S_8, 4, 2);
	printf("D=2\tS=4\tSUM = %d\n", dsum((int*)array_2D_4S_8, 4, 2));
	//-------------------------------------
	//N = 2
	//S = 3
	int array_2D_3S_9[3][3] = {0}; populate((int*)array_2D_3S_9, 3, 2);
	printf("D=2\tS=3\tSUM = %d\n", dsum((int*)array_2D_3S_9, 3, 2));
	//-------------------------------------
	//N = 2
	//S = 5
	int array_2D_5S_10[5][5] = {0}; populate((int*)array_2D_5S_10, 5, 2);
	printf("D=2\tS=5\tSUM = %d\n", dsum((int*)array_2D_5S_10, 5, 2));
	//-------------------------------------
	//N = 5
	//S = 3
	int array_5D_3S_11[3][3][3][3][3] = {0}; populate((int*)array_5D_3S_11, 3, 5);
	printf("D=5\tS=3\tSUM = %d\n", dsum((int*)array_5D_3S_11, 3, 5));
	//-------------------------------------
	//N = 5
	//S = 5
	int array_5D_5S_12[5][5][5][5][5] = {0}; populate((int*)array_5D_5S_12, 5, 5);
	printf("D=5\tS=5\tSUM = %d\n", dsum((int*)array_5D_5S_12, 5, 5));
	//-------------------------------------
	//N = 5
	//S = 2
	int array_5D_2S_13[2][2][2][2][2] = {0}; populate((int*)array_5D_2S_13, 2, 5);
	printf("D=5\tS=2\tSUM = %d\n", dsum((int*)array_5D_2S_13, 2, 5));
	//-------------------------------------
	//N = 5
	//S = 5
	int array_5D_5S_14[5][5][5][5][5] = {0}; populate((int*)array_5D_5S_14, 5, 5);
	printf("D=5\tS=5\tSUM = %d\n", dsum((int*)array_5D_5S_14, 5, 5));
	//-------------------------------------
	//N = 2
	//S = 3
	int array_2D_3S_15[3][3] = {0}; populate((int*)array_2D_3S_15, 3, 2);
	printf("D=2\tS=3\tSUM = %d\n", dsum((int*)array_2D_3S_15, 3, 2));
	//-------------------------------------
	//N = 2
	//S = 2
	int array_2D_2S_16[2][2] = {0}; populate((int*)array_2D_2S_16, 2, 2);
	printf("D=2\tS=2\tSUM = %d\n", dsum((int*)array_2D_2S_16, 2, 2));
	//-------------------------------------
	//N = 4
	//S = 3
	int array_4D_3S_17[3][3][3][3] = {0}; populate((int*)array_4D_3S_17, 3, 4);
	printf("D=4\tS=3\tSUM = %d\n", dsum((int*)array_4D_3S_17, 3, 4));
	//-------------------------------------
	//N = 2
	//S = 3
	int array_2D_3S_18[3][3] = {0}; populate((int*)array_2D_3S_18, 3, 2);
	printf("D=2\tS=3\tSUM = %d\n", dsum((int*)array_2D_3S_18, 3, 2));
	//-------------------------------------
	//N = 5
	//S = 5
	int array_5D_5S_19[5][5][5][5][5] = {0}; populate((int*)array_5D_5S_19, 5, 5);
	printf("D=5\tS=5\tSUM = %d\n", dsum((int*)array_5D_5S_19, 5, 5));
	//-------------------------------------
	//N = 2
	//S = 3
	int array_2D_3S_20[3][3] = {0}; populate((int*)array_2D_3S_20, 3, 2);
	printf("D=2\tS=3\tSUM = %d\n", dsum((int*)array_2D_3S_20, 3, 2));
	//-------------------------------------
	//N = 5
	//S = 5
	int array_5D_5S_21[5][5][5][5][5] = {0}; populate((int*)array_5D_5S_21, 5, 5);
	printf("D=5\tS=5\tSUM = %d\n", dsum((int*)array_5D_5S_21, 5, 5));
	//-------------------------------------
	//N = 3
	//S = 4
	int array_3D_4S_22[4][4][4] = {0}; populate((int*)array_3D_4S_22, 4, 3);
	printf("D=3\tS=4\tSUM = %d\n", dsum((int*)array_3D_4S_22, 4, 3));
	//-------------------------------------
	//N = 4
	//S = 3
	int array_4D_3S_23[3][3][3][3] = {0}; populate((int*)array_4D_3S_23, 3, 4);
	printf("D=4\tS=3\tSUM = %d\n", dsum((int*)array_4D_3S_23, 3, 4));
	//-------------------------------------
	//N = 2
	//S = 5
	int array_2D_5S_24[5][5] = {0}; populate((int*)array_2D_5S_24, 5, 2);
	printf("D=2\tS=5\tSUM = %d\n", dsum((int*)array_2D_5S_24, 5, 2));
	//-------------------------------------
	//N = 2
	//S = 5
	int array_2D_5S_25[5][5] = {0}; populate((int*)array_2D_5S_25, 5, 2);
	printf("D=2\tS=5\tSUM = %d\n", dsum((int*)array_2D_5S_25, 5, 2));
	//-------------------------------------
	//N = 4
	//S = 2
	int array_4D_2S_26[2][2][2][2] = {0}; populate((int*)array_4D_2S_26, 2, 4);
	printf("D=4\tS=2\tSUM = %d\n", dsum((int*)array_4D_2S_26, 2, 4));
	//-------------------------------------
	//N = 3
	//S = 3
	int array_3D_3S_27[3][3][3] = {0}; populate((int*)array_3D_3S_27, 3, 3);
	printf("D=3\tS=3\tSUM = %d\n", dsum((int*)array_3D_3S_27, 3, 3));
	//-------------------------------------
	//N = 5
	//S = 3
	int array_5D_3S_28[3][3][3][3][3] = {0}; populate((int*)array_5D_3S_28, 3, 5);
	printf("D=5\tS=3\tSUM = %d\n", dsum((int*)array_5D_3S_28, 3, 5));
	//-------------------------------------
	//N = 3
	//S = 3
	int array_3D_3S_29[3][3][3] = {0}; populate((int*)array_3D_3S_29, 3, 3);
	printf("D=3\tS=3\tSUM = %d\n", dsum((int*)array_3D_3S_29, 3, 3));
	//-------------------------------------
	//N = 2
	//S = 3
	int array_2D_3S_30[3][3] = {0}; populate((int*)array_2D_3S_30, 3, 2);
	printf("D=2\tS=3\tSUM = %d\n", dsum((int*)array_2D_3S_30, 3, 2));
	//-------------------------------------
	//N = 2
	//S = 4
	int array_2D_4S_31[4][4] = {0}; populate((int*)array_2D_4S_31, 4, 2);
	printf("D=2\tS=4\tSUM = %d\n", dsum((int*)array_2D_4S_31, 4, 2));
	//-------------------------------------
	//N = 2
	//S = 2
	int array_2D_2S_32[2][2] = {0}; populate((int*)array_2D_2S_32, 2, 2);
	printf("D=2\tS=2\tSUM = %d\n", dsum((int*)array_2D_2S_32, 2, 2));
	//-------------------------------------
	//N = 4
	//S = 2
	int array_4D_2S_33[2][2][2][2] = {0}; populate((int*)array_4D_2S_33, 2, 4);
	printf("D=4\tS=2\tSUM = %d\n", dsum((int*)array_4D_2S_33, 2, 4));
	//-------------------------------------
	//N = 5
	//S = 3
	int array_5D_3S_34[3][3][3][3][3] = {0}; populate((int*)array_5D_3S_34, 3, 5);
	printf("D=5\tS=3\tSUM = %d\n", dsum((int*)array_5D_3S_34, 3, 5));
	//-------------------------------------
	//N = 3
	//S = 3
	int array_3D_3S_35[3][3][3] = {0}; populate((int*)array_3D_3S_35, 3, 3);
	printf("D=3\tS=3\tSUM = %d\n", dsum((int*)array_3D_3S_35, 3, 3));
	//-------------------------------------
	//N = 5
	//S = 3
	int array_5D_3S_36[3][3][3][3][3] = {0}; populate((int*)array_5D_3S_36, 3, 5);
	printf("D=5\tS=3\tSUM = %d\n", dsum((int*)array_5D_3S_36, 3, 5));
	//-------------------------------------
	//N = 4
	//S = 2
	int array_4D_2S_37[2][2][2][2] = {0}; populate((int*)array_4D_2S_37, 2, 4);
	printf("D=4\tS=2\tSUM = %d\n", dsum((int*)array_4D_2S_37, 2, 4));
	//-------------------------------------
	//N = 2
	//S = 3
	int array_2D_3S_38[3][3] = {0}; populate((int*)array_2D_3S_38, 3, 2);
	printf("D=2\tS=3\tSUM = %d\n", dsum((int*)array_2D_3S_38, 3, 2));
	//-------------------------------------
	//N = 2
	//S = 4
	int array_2D_4S_39[4][4] = {0}; populate((int*)array_2D_4S_39, 4, 2);
	printf("D=2\tS=4\tSUM = %d\n", dsum((int*)array_2D_4S_39, 4, 2));
	//-------------------------------------
	//N = 3
	//S = 4
	int array_3D_4S_40[4][4][4] = {0}; populate((int*)array_3D_4S_40, 4, 3);
	printf("D=3\tS=4\tSUM = %d\n", dsum((int*)array_3D_4S_40, 4, 3));
	//-------------------------------------
	//N = 4
	//S = 4
	int array_4D_4S_41[4][4][4][4] = {0}; populate((int*)array_4D_4S_41, 4, 4);
	printf("D=4\tS=4\tSUM = %d\n", dsum((int*)array_4D_4S_41, 4, 4));
	//-------------------------------------
	//N = 5
	//S = 5
	int array_5D_5S_42[5][5][5][5][5] = {0}; populate((int*)array_5D_5S_42, 5, 5);
	printf("D=5\tS=5\tSUM = %d\n", dsum((int*)array_5D_5S_42, 5, 5));
	//-------------------------------------
	//N = 5
	//S = 4
	int array_5D_4S_43[4][4][4][4][4] = {0}; populate((int*)array_5D_4S_43, 4, 5);
	printf("D=5\tS=4\tSUM = %d\n", dsum((int*)array_5D_4S_43, 4, 5));
	//-------------------------------------
	//N = 5
	//S = 5
	int array_5D_5S_44[5][5][5][5][5] = {0}; populate((int*)array_5D_5S_44, 5, 5);
	printf("D=5\tS=5\tSUM = %d\n", dsum((int*)array_5D_5S_44, 5, 5));
	//-------------------------------------
	//N = 4
	//S = 4
	int array_4D_4S_45[4][4][4][4] = {0}; populate((int*)array_4D_4S_45, 4, 4);
	printf("D=4\tS=4\tSUM = %d\n", dsum((int*)array_4D_4S_45, 4, 4));
	//-------------------------------------
	//N = 3
	//S = 3
	int array_3D_3S_46[3][3][3] = {0}; populate((int*)array_3D_3S_46, 3, 3);
	printf("D=3\tS=3\tSUM = %d\n", dsum((int*)array_3D_3S_46, 3, 3));
	//-------------------------------------
	//N = 4
	//S = 5
	int array_4D_5S_47[5][5][5][5] = {0}; populate((int*)array_4D_5S_47, 5, 4);
	printf("D=4\tS=5\tSUM = %d\n", dsum((int*)array_4D_5S_47, 5, 4));
	//-------------------------------------
	//N = 5
	//S = 2
	int array_5D_2S_48[2][2][2][2][2] = {0}; populate((int*)array_5D_2S_48, 2, 5);
	printf("D=5\tS=2\tSUM = %d\n", dsum((int*)array_5D_2S_48, 2, 5));
	//-------------------------------------
	//N = 4
	//S = 4
	int array_4D_4S_49[4][4][4][4] = {0}; populate((int*)array_4D_4S_49, 4, 4);
	printf("D=4\tS=4\tSUM = %d\n", dsum((int*)array_4D_4S_49, 4, 4));
	//-------------------------------------
	//N = 4
	//S = 4
	int array_4D_4S_50[4][4][4][4] = {0}; populate((int*)array_4D_4S_50, 4, 4);
	printf("D=4\tS=4\tSUM = %d\n", dsum((int*)array_4D_4S_50, 4, 4));
	//-------------------------------------
	system("PAUSE");
	return 0;
}
