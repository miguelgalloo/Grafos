
package grafos;

/**
 *
 * @author G A L L O メ - C A S T R O メ - GEPETTO
 */
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Path2D;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Ventanagrafo extends JFrame {

    private final Grafo grafo;
    private final PanelGrafo panelGrafo;

    public Ventanagrafo() {
        this(new Grafo());
    }

    public Ventanagrafo(Grafo grafo) {

        this.grafo = grafo;
        this.panelGrafo = new PanelGrafo();

        configurarVentana();
        agregarComponentes();

    }

    public static void mostrar(Grafo grafo) {

        SwingUtilities.invokeLater(() ->
                new Ventanagrafo(grafo).setVisible(true));

    }

    public static void mostrar() {

        mostrar(new Grafo());

    }

    private void configurarVentana() {

        setTitle("Grafo");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(900, 650);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

    }

    private void agregarComponentes() {

        JButton botonActualizar =
                new JButton("Actualizar vista");

        botonActualizar.addActionListener(
                e -> panelGrafo.repaint());

        add(panelGrafo, BorderLayout.CENTER);
        add(botonActualizar, BorderLayout.SOUTH);

    }

    private class PanelGrafo extends JPanel {

        private static final int RADIO_VERTICE = 24;

        public PanelGrafo() {

            setPreferredSize(
                    new Dimension(800, 550));

            setBackground(Color.WHITE);

        }

        @Override
        protected void paintComponent(Graphics g) {

            super.paintComponent(g);

            Graphics2D g2d =
                    (Graphics2D) g.create();

            g2d.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            try {

                int cantidadVertices =
                        grafo.getVertices().size();


                int ancho = getWidth();
                int alto = getHeight();

                int centroX = ancho / 2;
                int centroY = alto / 2;

                int radioCirculo =
                        Math.max(
                                120,
                                Math.min(ancho, alto) / 2 - 80
                        );

                int[] posicionesX =
                        new int[cantidadVertices];

                int[] posicionesY =
                        new int[cantidadVertices];

                for (int i = 0;
                        i < cantidadVertices;
                        i++) {

                    double angulo =
                            (2 * Math.PI * i)
                            / cantidadVertices
                            - Math.PI / 2;

                    posicionesX[i] =
                            centroX
                            + (int) (radioCirculo
                            * Math.cos(angulo));

                    posicionesY[i] =
                            centroY
                            + (int) (radioCirculo
                            * Math.sin(angulo));

                }

                int[][] matriz =
                        grafo.getMatrizAdyacencia();

                g2d.setStroke(
                        new BasicStroke(2f));

                for (int i = 0;
                        i < cantidadVertices;
                        i++) {

                    for (int j = 0;
                            j < cantidadVertices;
                            j++) {

                        if (matriz[i][j] == 0) {
                            continue;
                        }

                        if (!grafo.isDirigido()
                                && j < i) {
                            continue;
                        }

                        dibujarArista(
                                g2d,
                                posicionesX[i],
                                posicionesY[i],
                                posicionesX[j],
                                posicionesY[j],
                                matriz[i][j],
                                grafo.isDirigido()
                        );

                    }

                }

                for (int i = 0;
                        i < cantidadVertices;
                        i++) {

                    dibujarVertice(
                            g2d,
                            grafo.getVertices()
                                    .get(i)
                                    .getDato(),
                            posicionesX[i],
                            posicionesY[i]
                    );

                }

            } finally {

                g2d.dispose();

            }

        }

        private void dibujarArista(
        Graphics2D g2d,
        int x1,
        int y1,
        int x2,
        int y2,
        int peso,
        boolean dirigida) {

    int dx = x2 - x1;
    int dy = y2 - y1;

    double distancia =
            Math.hypot(dx, dy);

    // BUCLE
    if (distancia == 0) {

        g2d.setColor(new Color(80, 80, 80));

        g2d.drawArc(
                x1 - 20,
                y1 - 55,
                40,
                40,
                0,
                360);

        String texto =
                String.valueOf(peso);

        g2d.setFont(
                g2d.getFont().deriveFont(
                        java.awt.Font.BOLD,
                        16f));

        FontMetrics fm =
                g2d.getFontMetrics();

        int ancho =
                fm.stringWidth(texto);

        g2d.setColor(Color.WHITE);

        g2d.fillRect(
                x1 + 22,
                y1 - 50,
                ancho + 8,
                20);

        g2d.setColor(Color.RED);

        g2d.drawString(
                texto,
                x1 + 25,
                y1 - 35);

        if (dirigida) {

            Path2D.Double flecha =
                    new Path2D.Double();

            flecha.moveTo(x1 + 15, y1 - 20);
            flecha.lineTo(x1 + 5, y1 - 25);
            flecha.lineTo(x1 + 10, y1 - 10);

            flecha.closePath();

            g2d.setColor(
                    new Color(80, 80, 80));

            g2d.fill(flecha);
        }

        return;
    }

    double inicioX =
            x1 + (RADIO_VERTICE * dx)
            / distancia;

    double inicioY =
            y1 + (RADIO_VERTICE * dy)
            / distancia;

    double finX =
            x2 - (RADIO_VERTICE * dx)
            / distancia;

    double finY =
            y2 - (RADIO_VERTICE * dy)
            / distancia;

    g2d.setColor(
            new Color(80, 80, 80));

    g2d.drawLine(
            (int) inicioX,
            (int) inicioY,
            (int) finX,
            (int) finY);

    int medioX =
            ((int) inicioX + (int) finX) / 2;

    int medioY =
            ((int) inicioY + (int) finY) / 2;

    String texto =
            String.valueOf(peso);

    g2d.setFont(
            g2d.getFont().deriveFont(
                    java.awt.Font.BOLD,
                    16f));

    FontMetrics fm =
            g2d.getFontMetrics();

    int ancho =
            fm.stringWidth(texto);

    int alto =
            fm.getHeight();

    // Fondo blanco
    g2d.setColor(Color.WHITE);

    g2d.fillRect(
            medioX - ancho / 2 - 4,
            medioY - alto + 4,
            ancho + 8,
            alto);

    // Texto rojo
    g2d.setColor(Color.RED);

    g2d.drawString(
            texto,
            medioX - ancho / 2,
            medioY);

    g2d.setColor(
            new Color(80, 80, 80));

    if (dirigida) {

        dibujarFlecha(
                g2d,
                inicioX,
                inicioY,
                finX,
                finY);
    }
}

        private void dibujarFlecha(
                Graphics2D g2d,
                double x1,
                double y1,
                double x2,
                double y2) {

            double angulo =
                    Math.atan2(
                            y2 - y1,
                            x2 - x1);

            int longitud = 12;

            double anguloFlecha =
                    Math.PI / 8;

            Path2D.Double flecha =
                    new Path2D.Double();

            flecha.moveTo(x2, y2);

            flecha.lineTo(
                    x2 - longitud
                    * Math.cos(
                            angulo - anguloFlecha),
                    y2 - longitud
                    * Math.sin(
                            angulo - anguloFlecha));

            flecha.lineTo(
                    x2 - longitud
                    * Math.cos(
                            angulo + anguloFlecha),
                    y2 - longitud
                    * Math.sin(
                            angulo + anguloFlecha));

            flecha.closePath();

            g2d.fill(flecha);

        }

        private void dibujarVertice(
                Graphics2D g2d,
                String etiqueta,
                int x,
                int y) {

            int diametro =
                    RADIO_VERTICE * 2;

            g2d.setColor(
                    new Color(
                            70,
                            130,
                            180));

            g2d.fillOval(
                    x - RADIO_VERTICE,
                    y - RADIO_VERTICE,
                    diametro,
                    diametro);

            g2d.setColor(Color.BLACK);

            g2d.drawOval(
                    x - RADIO_VERTICE,
                    y - RADIO_VERTICE,
                    diametro,
                    diametro);

            g2d.setColor(Color.WHITE);

            FontMetrics fm =
                    g2d.getFontMetrics();

            int anchoTexto =
                    fm.stringWidth(etiqueta);

            int altoTexto =
                    fm.getAscent();

            g2d.drawString(
                    etiqueta,
                    x - anchoTexto / 2,
                    y + altoTexto / 2 - 2);

        }

    }

}