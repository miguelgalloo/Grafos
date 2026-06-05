
package grafos;

/**
 *
 * @author G A L L O メ - C A S T R O メ - GEPETTO
 */


public class Arista {

    private Nodo origen;
    private Nodo destino;
    private int peso;

    public Arista(Nodo origen, Nodo destino, int peso) {
        this.origen = origen;
        this.destino = destino;
        this.peso = peso;
    }

    public Nodo getOrigen() {
        return origen;
    }

    public void setOrigen(Nodo origen) {
        this.origen = origen;
    }

    public Nodo getDestino() {
        return destino;
    }

    public void setDestino(Nodo destino) {
        this.destino = destino;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    @Override
    public String toString() {
        return origen.getDato() + " -> " + destino.getDato() + " (" + peso + ")";
    }
}
