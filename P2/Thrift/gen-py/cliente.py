from calculadora import Calculadora

from thrift import Thrift
from thrift.transport import TSocket
from thrift.transport import TTransport
from thrift.protocol import TBinaryProtocol

transport = TSocket.TSocket('localhost', 9090)
transport = TTransport.TBufferedTransport(transport)
protocol = TBinaryProtocol.TBinaryProtocol(transport)
client = Calculadora.Client(protocol)

transport.open()

# Creamos las listas
a1 = [2, 5, 9, 6]
a2 = [1, 3, 4, 10]
a3 = [2, 1, 3, 5]
a4 = [5, 7, 5, 4]

# Listas para el producto vectorial
v1 = [3, 1, 7]
v2 = [7, 4, 4]

# Matrices
m1 = [[3, 1, 7], [8, 3, 6], [8, 5, 2]]
m2 = [[2, 5, 7], [4, 9, 3], [1, 9, 7]]

print('Hacemos ping al servidor')
client.ping()

resultado = client.suma(7, 2)
print('7+2=' + str(resultado))

resultado = client.resta(2, 5)
print('2-5=' + str(resultado))

resultado = client.multp(4, 8)
print('4*8=' + str(resultado))

resultado = client.dividir(9, 2)
print('9/2=' + str(resultado))

resultado = client.power(7, 3)
print('7^3=' + str(resultado))

resultado = client.modulo(7, 4)
print('7%4=' + str(resultado))

resultado = client.sumavec(a1, a2)
print(a1)
print(' + ')
print(a2)
print(resultado)

resultado = client.multpvec(a3, a4)
print(a3)
print(' * ')
print(a4)
print(resultado)

resultado = client.productovec(v1, v2)
print(v1)
print(' PV ')
print(v2)
print(resultado)

resultado = client.sumamatrices(m1, m2)
print(m1)
print(' + ')
print(m2)
print(resultado)

transport.close()