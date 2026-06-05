
package grafos;

/**
 *
 * @author G A L L O メ - C A S T R O メ - GEPETTO
 */

public class Nodo {

    private String dato;

    public Nodo(String dato) {
        this.dato = dato;
    }

    public String getDato() {
        return dato;
    }

    public void setDato(String dato) {
        this.dato = dato;
    }

    @Override
    public String toString() {
        return dato;
    }
}