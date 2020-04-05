public class Entidad {
    private String nombre;
    private String codigoAcceso;
    private double totalDonado;

    public Entidad(String nombre, String codigoAcceso) {
        this.nombre = nombre;
        this.codigoAcceso = codigoAcceso;
        this.totalDonado = 0;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getCodigoAcceso() {
        return this.codigoAcceso;
    }

    public double getTotalDonado() {
        return this.totalDonado;
    }

    public void incrementarTotal(double cantidad) {
        this.totalDonado += cantidad;
    }
}