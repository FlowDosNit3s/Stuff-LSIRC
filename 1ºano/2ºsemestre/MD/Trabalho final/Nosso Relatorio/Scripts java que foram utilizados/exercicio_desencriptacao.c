
#include <stdio.h>
#include <stdlib.h>

/*
 * 
 */
int main(int argc, char** argv) {
    int a=0, b=0;
    do{
     b=15*(a-2)%29;
     printf("\n%d",b);
     a++;
    }while (a<=28);
    return (0);
}
