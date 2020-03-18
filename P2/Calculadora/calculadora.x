/* 
    Desarrollo de Sistemas Distribuidos
    Práctica 2: Llamadas remotas a procediminetos (RPC)
    Autor: Francisco Domínguez Lorente

    calculadora.x - Especificación del protocolo

    Compilación: rpcgen -NCa calculadora.x
*/

program CALCULADORA {
    version CALCULADORA1 {
        int SUMAR (int a, int b) = 1;
        int RESTAR(int a, int b) = 2;
        int MULTP (int a, int b) = 3;
        int DIVID (int a, int b) = 4;
        int POWER (int a, int b) = 5;
        int MODULO(int a, int b) = 6;
    } = 1;
} = 0x20000001;