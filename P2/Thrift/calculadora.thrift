service Calculadora {
    void ping(),
    i32 suma(1:i32 num1, 2:i32 num2),
    i32 resta(1:i32 num1, 2:i32 num2),
    i32 multp(1:i32 num1, 2:i32 num2),
    double dividir(1:i32 num1, 2:i32 num2),
    i32 power(1:i32 num1, 2:i32 num2),
    i32 modulo(1:i32 num1, 2:i32 num2),
    list<i32> sumavec(1:list<i32> num1, 2:list<i32> num2),
    list<i32> multpvec(1:list<i32> num1, 2:list<i32> num2),
    list<i32> productovec(1:list<i32> num1, 2:list<i32> num2),
    list<list<i32>> sumamatrices(1:list<list<i32>> m1, 2:list<list<i32>> num2),
}
