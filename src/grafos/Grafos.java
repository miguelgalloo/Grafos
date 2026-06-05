
package grafos;

/**
 *
 * @author G A L L O メ
 */

import javax.swing.JOptionPane;

public class Grafos {

    public static void main(String[] args) {

        Grafo grafo = new Grafo();

        int opcion;

        do {

            String entrada = JOptionPane.showInputDialog(
                    null,
                    "MENU GRAFOS\n\n"
                    + "1. Agregar vertices\n"
                    + "2. Agregar arista\n"
                    + "3. Mostrar vertices\n"
                    + "4. Mostrar lista de adyacencia\n"
                    + "5. Mostrar matriz de adyacencia\n"
                    + "6. Mostrar matriz de incidencia\n"
                    + "7. DFS\n"
                    + "8. BFS\n"
                    + "9. Camino minimo\n"
                    + "10. Eliminar vertice\n"
                    + "11. Mostrar Grafo\n"
                    + "0. Salir\n\n"
                    + "Ingrese una opcion:"
            );

            if (entrada == null) {
                opcion = 0;
            } else {

                try {
                    opcion = Integer.parseInt(entrada);
                } catch (NumberFormatException e) {
                    opcion = -1;
                }

            }

            switch (opcion) {

                case 1:
                    grafo.agregarVertice();
                    break;

                case 2:
                    grafo.agregarArista();
                    break;

                case 3:
                    grafo.mostrarVertices();
                    break;

                case 4:
                    grafo.mostrarListaAdyacencia();
                    break;

                case 5:
                    grafo.mostrarMatrizAdyacencia();
                    break;

                case 6:
                    grafo.mostrarMatrizIncidencia();
                    break;

                case 7:
                    grafo.dfs();
                    break;

                case 8:
                    grafo.bfs();
                    break;

                case 9:
                    grafo.caminoMinimo();
                    break;

                case 10:
                    grafo.eliminarVertice();
                    break;

                case 11:

                    if (grafo.getVertices().isEmpty()) {

                        JOptionPane.showMessageDialog(
                                null,
                                "No hay vertices para mostrar."
                        );

                    } else {

                        Ventanagrafo.mostrar(grafo);

                    }

                    break;

                case 0:
                    JOptionPane.showMessageDialog(
                            null,
                            "Saliendo..."
                    );
                    break;

                default:
                    JOptionPane.showMessageDialog(
                            null,
                            "Opcion invalida"
                    );

            }

        } while (opcion != 0);

    }

}