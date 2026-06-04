/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grafos;

import java.util.ArrayList;
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

    String nombre = JOptionPane.showInputDialog(
            "Nombre del vertice:"
    );

    if (nombre == null || nombre.trim().isEmpty()) {
        return;
    }

    if (buscarVertice(nombre) != null) {

        JOptionPane.showMessageDialog(
                null,
                "El vertice ya existe."
        );
        return;

    }

    vertices.add(new Nodo(nombre));

    JOptionPane.showMessageDialog(
            null,
            "Vertice agregado."
    );

}
    public void agregarArista() {

    String linea = JOptionPane.showInputDialog(
            null,
            "Formato: A-B-10"
    );

    if (linea == null || linea.trim().isEmpty()) {
        return;
    }

    String[] datos = linea.split("-");

    if (datos.length != 3) {

        JOptionPane.showMessageDialog(
                null,
                "Formato incorrecto."
        );

        return;
    }

    String origen = datos[0];
    String destino = datos[1];

    int peso;

    try {

        peso = Integer.parseInt(datos[2]);

    } catch (NumberFormatException e) {

        JOptionPane.showMessageDialog(
                null,
                "Peso invalido."
        );

        return;
    }

    Nodo n1 = buscarVertice(origen);
    Nodo n2 = buscarVertice(destino);

    if (n1 == null || n2 == null) {

        JOptionPane.showMessageDialog(
                null,
                "Alguno de los vertices no existe."
        );

        return;
    }

    aristas.add(new Arista(n1, n2, peso));

    int i = vertices.indexOf(n1);
    int j = vertices.indexOf(n2);

    matrizAdyacencia[i][j] = peso;

    if (!dirigido) {
        matrizAdyacencia[j][i] = peso;
    }

    JOptionPane.showMessageDialog(
            null,
            "Arista agregada."
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
                  .append("(")
                  .append(matrizAdyacencia[i][j])
                  .append(") ");

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

        sb.append(String.format("%4s", vertices.get(i).getDato()));

        for (int j = 0; j < vertices.size(); j++) {

            sb.append(String.format("%5d",
                    matrizAdyacencia[i][j]));

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

        sb.append(String.format("E%-4d", i + 1));

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

        System.out.println("DFS pendiente por implementar.");

    }

    public void bfs() {

        System.out.println("BFS pendiente por implementar.");

    }

    public void caminoMinimo() {

        System.out.println("Camino minimo pendiente por implementar.");

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

}