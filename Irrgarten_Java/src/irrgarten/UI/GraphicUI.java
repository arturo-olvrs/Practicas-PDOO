package irrgarten.UI;

import irrgarten.Directions;
import irrgarten.GameState;

/**
 * Interfaz de usuario gráfica. Implementa la interfaz UI.
 * 
 * @see UI
 * @author Arturo Olivares Martos
 * @author Joaquín Avilés de la Fuente
 */
public class GraphicUI extends javax.swing.JFrame implements UI {
    
    /**
     * Cursor para seleccionar la dirección en la que se moverá el jugador
     */
    private Cursors cursor;
    
    /**
     * Creates new form GraphicUI
     */
    public GraphicUI() {
        initComponents();

        // Primer parámetro: referencia al propio JFrame
        // Segundo parámetro: activa el modo modal, para que el cuadro de diálogo no ceda el foco mientras esté abierto.
        cursor = new Cursors(this, true);

        ganador_mark.setVisible(false); // Para que la etiqueta de ganador no sea visible.
        setVisible(true); // Para que la ventana de la aplicación sea visible.
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titulo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        laberinto = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        monstruos = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        jugadores = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        log = new javax.swing.JTextArea();
        jugador = new javax.swing.JTextField();
        player_mark = new javax.swing.JLabel();
        jugadores_mark = new javax.swing.JLabel();
        monstruos_mark = new javax.swing.JLabel();
        log_mark = new javax.swing.JLabel();
        ganador_mark = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        titulo.setFont(new java.awt.Font("Liberation Sans", 1, 36)); // NOI18N
        titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo.setText("Irrgarten");
        titulo.setAlignmentY(0.0F);
        titulo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));

        laberinto.setEditable(false);
        laberinto.setColumns(20);
        laberinto.setFont(new java.awt.Font("Liberation Mono", 0, 15)); // NOI18N
        laberinto.setRows(5);
        laberinto.setDoubleBuffered(true);
        laberinto.setDragEnabled(true);
        laberinto.setFocusable(false);
        laberinto.setPreferredSize(new java.awt.Dimension(318, 318));
        jScrollPane1.setViewportView(laberinto);

        monstruos.setEditable(false);
        monstruos.setColumns(20);
        monstruos.setLineWrap(true);
        monstruos.setRows(5);
        monstruos.setFocusable(false);
        jScrollPane2.setViewportView(monstruos);

        jugadores.setEditable(false);
        jugadores.setColumns(20);
        jugadores.setLineWrap(true);
        jugadores.setRows(5);
        jugadores.setFocusable(false);
        jScrollPane3.setViewportView(jugadores);

        log.setEditable(false);
        log.setColumns(20);
        log.setLineWrap(true);
        log.setRows(5);
        log.setDoubleBuffered(true);
        log.setFocusable(false);
        jScrollPane4.setViewportView(log);

        jugador.setEditable(false);
        jugador.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        jugador.setText("jTextField1");
        jugador.setFocusable(false);
        jugador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jugadorActionPerformed(evt);
            }
        });

        player_mark.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        player_mark.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        player_mark.setText("Jugador Actual");
        player_mark.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jugadores_mark.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        jugadores_mark.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jugadores_mark.setText("Jugadores");
        jugadores_mark.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));

        monstruos_mark.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        monstruos_mark.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        monstruos_mark.setText("Monstruos");
        monstruos_mark.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));

        log_mark.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        log_mark.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        log_mark.setText("Resumen");
        log_mark.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));

        ganador_mark.setBackground(new java.awt.Color(0, 0, 0));
        ganador_mark.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        ganador_mark.setForeground(new java.awt.Color(0, 0, 204));
        ganador_mark.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ganador_mark.setText("¡Ganador!");
        ganador_mark.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 204), 3));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ganador_mark, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(player_mark, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jugador, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jugadores_mark, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(monstruos_mark, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane2))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(log_mark, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane4)))
                        .addGap(24, 24, 24))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jugador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(player_mark, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ganador_mark, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(log_mark, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(monstruos_mark, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jugadores_mark, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addComponent(jScrollPane1))
                .addGap(16, 16, 16))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    /**
     * Método que se ejecuta al pulsar el botón de siguiente turno.
     * @param evt Evento de pulsar el botón
     */
    private void jugadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jugadorActionPerformed
        
    }//GEN-LAST:event_jugadorActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ganador_mark;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextField jugador;
    private javax.swing.JTextArea jugadores;
    private javax.swing.JLabel jugadores_mark;
    private javax.swing.JTextArea laberinto;
    private javax.swing.JTextArea log;
    private javax.swing.JLabel log_mark;
    private javax.swing.JTextArea monstruos;
    private javax.swing.JLabel monstruos_mark;
    private javax.swing.JLabel player_mark;
    private javax.swing.JLabel titulo;
    // End of variables declaration//GEN-END:variables
    

    /**
     * {@inheritDoc}
     * Implementación de la interfaz UI gráfica.
     * Hace uso de la clase Cursors para obtener la dirección en la que se moverá el jugador.
     */
    @Override
    public Directions nextMove(){
        return this.cursor.getDirection();
    }

    /**
     * {@inheritDoc}
     * Implementación de la interfaz UI gráfica.
     */
    @Override
    public void showGame(GameState gameState){
        
        this.laberinto.setText(gameState.getLabyrinth());
        this.monstruos.setText(gameState.getMonsters());
        this.jugadores.setText(gameState.getPlayers());
        this.log.setText(gameState.getLog());

        this.jugador.setText("Player " + Integer.toString(gameState.getCurrentPlayer()));


        if (gameState.getWinner()) {
            // Mostrar la etiqueta de ganador
            this.ganador_mark.setVisible(true);
        }

        repaint(); // Para actualizar la interfaz gráfica
    }
    
}