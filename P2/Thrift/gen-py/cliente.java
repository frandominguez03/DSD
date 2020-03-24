import org.apache.thrift.TException;
import org.apache.thrift.transport.TSSLTransportFactory;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TSSLTransportFactory.TSSLTransportParameters;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;

public class JavaClient {
    public static void main() {
        try {
            TTransport transport;

            transport = new TSocket("localhost", 9090);
            transport.open();

            TProtocol protocol = new  TBinaryProtocol(transport);
            Calculadora.Client client = new Calculadora.Client(protocol);

            perform(client);

            transport.close();

        } catch (TException x) {
            x.printStackTrace();
        }
    }

    private static void perform(Calculadora.Client client) throws TException {
        System.out.println("Hacemos ping al servidor");
        client.ping();

        // Arrays
        int[] a1 = {2, 5, 9, 6};
        int[] a2 = {1, 3, 4, 10};
        int[] a3 = {2, 1, 3, 4};
        int[] a4 = {5, 7, 5, 4};

        // Array para el producto vectorial
        int[] v1 = {3, 1, 7};
        int[] v2 = {7, 4, 4};

        // Matrices
        int[][] m1 = {{3, 1, 7}, {8, 3, 6}, {8, 5, 2}};
        int[][] m2 = {{2, 5, 7}, {4, 9, 3}, {1, 9, 7}};

        int suma = client.suma(7, 2);
        System.out.println("7+2=" + suma);

        int resta = client.resta(2, 5);
        System.out.println("2-5=" + resta);

        int multp = client.multp(4, 8);
        System.out.println("4*8=" + multp);
        
        int dividir = client.dividir(9, 2);
        System.out.println("9/2=" + dividir);
        
        int potencia = client.power(7, 3);
        System.out.println("7^3=" + potencia);

        int modulo = client.modulo(7, 4);
        System.out.println("7%4=" + modulo);

        int[] res1 = client.sumavec(a1, a2);
        System.out.println(res1);

        int[] res2 = client.multpvez(a3, a4);
        System.out.println(res2);

        int[] res3 = client.productovec(v1, v2);
        System.out.println(res3);

        int[][] res4 = client.sumamatrices(m1, m2);
        System.out.println(res4);
    }
}