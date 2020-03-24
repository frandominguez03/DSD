import glob
import sys

from calculadora import Calculadora

from thrift.transport import TSocket
from thrift.transport import TTransport
from thrift.protocol import TBinaryProtocol
from thrift.server import TServer

import logging
logging.basicConfig(level=logging.DEBUG)

class CalculadoraHandler:
    def __init__(self):
        self.log = {}

    def ping(self):
        print('Me han hecho ping!')

    def suma(self, n1, n2):
        print('Sumando ' + str(n1) + ' con ' + str(n2))
        return n1+n2
    
    def resta(self, n1, n2):
        print('Restando ' + str(n1) + ' con ' + str(n2))
        return n1-n2

    def multp(self, n1, n2):
        print('Multiplicando ' + str(n1) + ' por ' + str(n2))
        return n1*n2
    
    def dividir(self, n1, n2):
        print('Dividiendo ' + str(n1) + ' entre ' + str(n2))
        resultado = float(n1/n2)
        return resultado
    
    def power(self, n1, n2):
        print('Elevando ' + str(n1) + ' a ' + str(n2))
        return n1**n2
    
    def modulo(self, n1, n2):
        print('Modulo de ' + str(n1) + ' entre ' + str(n2))
        return n1%n2

    def sumavec(self, vec1, vec2):
        print('Sumando')
        print(vec1)
        print(' con ')
        print(vec2)
        vec3 = []
        for i in range(len(vec1)):
            valor = vec1[i] + vec2[i]
            vec3.append(valor)
        
        return vec3

    def multpvec(self, vec1, vec2):
        print('Multiplicando')
        print(vec1)
        print(' con ')
        print(vec2)
        vec3 = []
        for i in range(len(vec1)):
            valor = vec1[i] * vec2[i]
            vec3.append(valor)
        
        return vec3

    def productovec(self, vec1, vec2):
        print('Producto Vectorial de')
        print(vec1)
        print(' con ')
        print(vec2)
        vec3 = []
        valor1 = (vec1[1]*vec2[2])-(vec1[2]*vec2[1])
        valor2 = (vec1[2]*vec2[0])-(vec1[0]*vec2[2])
        valor3 = (vec1[0]*vec2[1])-(vec1[1]*vec2[0])
        vec3.append(valor1)
        vec3.append(valor2)
        vec3.append(valor3)

        return vec3

if __name__ == '__main__':
    handler = CalculadoraHandler()
    processor = Calculadora.Processor(handler)
    transport = TSocket.TServerSocket(host='127.0.0.1', port=9090)
    tfactory = TTransport.TBufferedTransportFactory()
    pfactory = TBinaryProtocol.TBinaryProtocolFactory()

    server =  TServer.TSimpleServer(processor, transport, tfactory, pfactory)
    
    print('Iniciando servidor...')
    server.serve()
    print('done')