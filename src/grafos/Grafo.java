
package grafos;

/**
 *
 * @author G A L L O メ - C A S T R O メ - GEPETTO
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.LinkedList;
import javax.swing.JOptionPane;

public class Grafo {

    private ArrayList<Nodo> vertices;
    private ArrayList<Arista> aristas;
    private int[][] matrizAdyacencia;
    private boolean dirigido;

    

    public Grafo() {

        vertices = new ArrayList<>();
        aristas = new ArrayList<>();
        matrizAdyacencia = new int[50][50];
        dirigido = false;
       

    }

    public ArrayList<Nodo> getVertices() {
        return vertices;
    }

    public int[][] getMatrizAdyacencia() {
        return matrizAdyacencia;
    }

    public boolean isDirigido() {
        return dirigido;
    }

    private Nodo buscarVertice(String nombre) {

        for (Nodo n : vertices) {

            if (n.getDato().equalsIgnoreCase(nombre)) {
                return n;
            }

        }

        return null;
    }

    public void agregarVertice() {

    String entrada = JOptionPane.showInputDialog(
            "Ingrese los vértices separados por guion (-):"
    );

    if (entrada == null || entrada.trim().isEmpty()) {
        return;
    }

    String[] nombres = entrada.split("-");

    int agregados = 0;

    for (String nombre : nombres) {

        nombre = nombre.trim();

        if (nombre.isEmpty()) {
            continue;
        }

        if (buscarVertice(nombre) == null) {
            vertices.add(new Nodo(nombre));
            agregados++;
        }
    }

    JOptionPane.showMessageDialog(
            null,
            agregados + " vértice(s) agregado(s)."
    );
}
    public void agregarArista() {

    String entrada = JOptionPane.showInputDialog(
            null,
            "Formato: A-B-5,B-C-3,C-D-2"
    );

    if (entrada == null || entrada.trim().isEmpty()) {
        return;
    }

    String[] aristasIngresadas = entrada.split(",");

    int agregadas = 0;

    for (String linea : aristasIngresadas) {

        String[] datos = linea.trim().split("-");

        if (datos.length != 3) {
            continue;
        }

        String origen = datos[0].trim();
        String destino = datos[1].trim();

        int peso;

        try {
            peso = Integer.parseInt(datos[2].trim());
        } catch (NumberFormatException e) {
            continue;
        }

        Nodo n1 = buscarVertice(origen);
        Nodo n2 = buscarVertice(destino);

        if (n1 == null || n2 == null) {
            continue;
        }

        aristas.add(new Arista(n1, n2, peso));

        int i = vertices.indexOf(n1);
        int j = vertices.indexOf(n2);

        matrizAdyacencia[i][j] = peso;

        if (!dirigido) {
            matrizAdyacencia[j][i] = peso;
        }

        agregadas++;
    }

    JOptionPane.showMessageDialog(
            null,
            agregadas + " arista(s) agregada(s)."
    );
}
    public void mostrarVertices() {

    if (vertices.isEmpty()) {

        JOptionPane.showMessageDialog(
                null,
                "No hay vertices."
        );

        return;
    }

    StringBuilder sb = new StringBuilder();

    for (Nodo n : vertices) {

        sb.append(n.getDato()).append("\n");

    }

    JOptionPane.showMessageDialog(
            null,
            sb.toString(),
            "VERTICES",
            JOptionPane.INFORMATION_MESSAGE
    );
}

    public void mostrarListaAdyacencia() {

    if (vertices.isEmpty()) {

        JOptionPane.showMessageDialog(
                null,
                "No hay vertices."
        );

        return;
    }

    StringBuilder sb = new StringBuilder();

    sb.append("LISTA DE ADYACENCIA\n\n");

    for (int i = 0; i < vertices.size(); i++) {

        sb.append(vertices.get(i).getDato())
          .append(" -> ");

        for (int j = 0; j < vertices.size(); j++) {

            if (matrizAdyacencia[i][j] != 0) {

                sb.append(vertices.get(j).getDato())
                  .append(" ");

            }

        }

        sb.append("\n");

    }

    JOptionPane.showMessageDialog(
            null,
            sb.toString(),
            "Lista de Adyacencia",
            JOptionPane.INFORMATION_MESSAGE
    );
}
   public void mostrarMatrizAdyacencia() {

    if (vertices.isEmpty()) {

        JOptionPane.showMessageDialog(
                null,
                "No hay vertices."
        );

        return;
    }

    StringBuilder sb = new StringBuilder();

    sb.append("     ");

    for (Nodo n : vertices) {

        sb.append(String.format("%5s", n.getDato()));

    }

    sb.append("\n");

    for (int i = 0; i < vertices.size(); i++) {

        sb.append(String.format("%4s",
                vertices.get(i).getDato()));

        for (int j = 0; j < vertices.size(); j++) {

            sb.append(String.format("%5d",
                    matrizAdyacencia[i][j] != 0 ? 1 : 0));

        }

        sb.append("\n");

    }

    JOptionPane.showMessageDialog(
            null,
            sb.toString(),
            "Matriz de Adyacencia",
            JOptionPane.INFORMATION_MESSAGE
    );
}



    public void mostrarMatrizIncidencia() {

    if (vertices.isEmpty() || aristas.isEmpty()) {

        JOptionPane.showMessageDialog(
                null,
                "No hay suficientes datos."
        );

        return;
    }

    StringBuilder sb = new StringBuilder();

    sb.append("      ");

    for (int i = 0; i < aristas.size(); i++) {

        sb.append(String.format("A%-4d", i + 1));

    }

    sb.append("\n");

    for (Nodo vertice : vertices) {

        sb.append(String.format("%-6s",
                vertice.getDato()));

        for (Arista arista : aristas) {

            if (arista.getOrigen() == vertice
                    || arista.getDestino() == vertice) {

                sb.append(String.format("%-5d", 1));

            } else {

                sb.append(String.format("%-5d", 0));

            }

        }

        sb.append("\n");

    }

    JOptionPane.showMessageDialog(
        null,
        sb.toString(),
        "Matriz de Incidencia",
        JOptionPane.INFORMATION_MESSAGE
);
}

    public void dfs() {

    if (vertices.isEmpty()) {

        JOptionPane.showMessageDialog(
                null,
                "No hay vertices."
        );

        return;
    }

    String nombre = JOptionPane.showInputDialog(
            null,
            "Vertice inicial:"
    );

    Nodo inicio = buscarVertice(nombre);

    if (inicio == null) {

        JOptionPane.showMessageDialog(
                null,
                "Vertice no encontrado."
        );

        return;
    }

    boolean[] visitado = new boolean[vertices.size()];

    StringBuilder recorrido = new StringBuilder();

    dfsRecursivo(
            vertices.indexOf(inicio),
            visitado,
            recorrido
    );

    JOptionPane.showMessageDialog(
            null,
            recorrido.toString(),
            "DFS",
            JOptionPane.INFORMATION_MESSAGE
    );
}
    public void bfs() {

    if (vertices.isEmpty()) {

        JOptionPane.showMessageDialog(
                null,
                "No hay vertices."
        );

        return;
    }

    String nombre = JOptionPane.showInputDialog(
            null,
            "Vertice inicial:"
    );

    Nodo inicio = buscarVertice(nombre);

    if (inicio == null) {

        JOptionPane.showMessageDialog(
                null,
                "Vertice no encontrado."
        );

        return;
    }

    boolean[] visitado =
            new boolean[vertices.size()];

    Queue<Integer> cola =
            new LinkedList<>();

    StringBuilder recorrido =
            new StringBuilder();

    int indice =
            vertices.indexOf(inicio);

    visitado[indice] = true;

    cola.add(indice);

    while (!cola.isEmpty()) {

        int actual = cola.poll();

        recorrido.append(
                vertices.get(actual).getDato()
        ).append(" -> ");

        for (int i = 0; i < vertices.size(); i++) {

            if (matrizAdyacencia[actual][i] != 0
                    && !visitado[i]) {

                visitado[i] = true;

                cola.add(i);

            }

        }

    }

    JOptionPane.showMessageDialog(
            null,
            recorrido.toString(),
            "BFS",
            JOptionPane.INFORMATION_MESSAGE
    );
}

    private void dfsRecursivo(
        int actual,
        boolean[] visitado,
        StringBuilder recorrido) {

    visitado[actual] = true;

    recorrido.append(
            vertices.get(actual).getDato()
    ).append(" -> ");

    for (int i = 0; i < vertices.size(); i++) {

        if (matrizAdyacencia[actual][i] != 0
                && !visitado[i]) {

            dfsRecursivo(
                    i,
                    visitado,
                    recorrido
            );

        }

    }

}
    
    public void eliminarVertice() {

    String nombre = JOptionPane.showInputDialog(
            null,
            "Nombre del vertice a eliminar:"
    );

    if (nombre == null || nombre.trim().isEmpty()) {
        return;
    }

    Nodo nodo = buscarVertice(nombre);

    if (nodo == null) {

        JOptionPane.showMessageDialog(
                null,
                "Vertice no encontrado."
        );

        return;
    }

    int indice = vertices.indexOf(nodo);

    vertices.remove(indice);

    aristas.removeIf(a ->
            a.getOrigen() == nodo
            || a.getDestino() == nodo);

    for (int i = indice; i < vertices.size(); i++) {

        for (int j = 0; j < 50; j++) {

            matrizAdyacencia[i][j] =
                    matrizAdyacencia[i + 1][j];

        }

    }

    for (int j = indice; j < vertices.size(); j++) {

        for (int i = 0; i < 50; i++) {

            matrizAdyacencia[i][j] =
                    matrizAdyacencia[i][j + 1];

        }

    }

    JOptionPane.showMessageDialog(
            null,
            "Vertice eliminado."
    );

}


public void caminoMinimo() {

    String origenNombre = JOptionPane.showInputDialog(
            "Vertice origen:");

    String destinoNombre = JOptionPane.showInputDialog(
            "Vertice destino:");

    Nodo origen = buscarVertice(origenNombre);
    Nodo destino = buscarVertice(destinoNombre);

    if (origen == null || destino == null) {

        JOptionPane.showMessageDialog(
                null,
                "Alguno de los vertices no existe."
        );

        return;
    }

    int n = vertices.size();

    int inicio = vertices.indexOf(origen);
    int fin = vertices.indexOf(destino);

    int[] distancia = new int[n];
    boolean[] visitado = new boolean[n];
    int[] anterior = new int[n];

    for (int i = 0; i < n; i++) {

        distancia[i] = Integer.MAX_VALUE;
        anterior[i] = -1;

    }

    distancia[inicio] = 0;

    for (int k = 0; k < n; k++) {

        int u = -1;
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {

            if (!visitado[i]
                    && distancia[i] < min) {

                min = distancia[i];
                u = i;

            }

        }

        if (u == -1) {
            break;
        }

        visitado[u] = true;

        for (int v = 0; v < n; v++) {

            if (matrizAdyacencia[u][v] > 0
                    && !visitado[v]) {

                int nuevaDistancia =
                        distancia[u]
                        + matrizAdyacencia[u][v];

                if (nuevaDistancia
                        < distancia[v]) {

                    distancia[v] =
                            nuevaDistancia;

                    anterior[v] = u;

                }

            }

        }

    }

    if (distancia[fin] == Integer.MAX_VALUE) {

        JOptionPane.showMessageDialog(
                null,
                "No existe camino."
        );

        return;
    }

    ArrayList<String> camino =
            new ArrayList<>();

    for (int v = fin;
            v != -1;
            v = anterior[v]) {

        camino.add(0,
                vertices.get(v).getDato());

    }

    StringBuilder sb =
            new StringBuilder();

    sb.append("Camino minimo:\n\n");

    for (int i = 0;
            i < camino.size();
            i++) {

        sb.append(camino.get(i));

        if (i < camino.size() - 1) {
            sb.append(" -> ");
        }

    }

    sb.append("\n\nPeso total: ")
      .append(distancia[fin]);

    JOptionPane.showMessageDialog(
            null,
            sb.toString()
    );
}}