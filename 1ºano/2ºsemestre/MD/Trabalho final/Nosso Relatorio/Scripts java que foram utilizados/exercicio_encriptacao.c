/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/cFiles/main.c to edit this template
 */

/* 
 * File:   main.c
 * Author: HP
 *
 * Created on 24 de junho de 2024, 15:46
 */

#include <stdio.h>
#include <stdlib.h>

/*
 * 
 */
int main(int argc, char** argv) {
    int a=0, b=0;
    do{
     b=(2*a+2)%29;
     printf("\n%d",b);
     a++;
    }while (a<=28);
    return (0);
}

