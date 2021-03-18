package Aplicacion;

import Persistencia.colabora;
import Persistencia.conexionOracle;
import Persistencia.manejaColabora;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Álvaro rofa
 */
public class VentanaColaboracion extends javax.swing.JFrame {

    DefaultTableModel modeloTColabora = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    manejaColabora manejaCol = null;

    public VentanaColaboracion(conexionOracle co) throws SQLException {
        manejaCol = new manejaColabora(co);
        tablaColaboracion = new JTable(modeloTColabora);
        initComponents();
        dibujarTablaColaboracion();
        pideColaboracion();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); ///Para cerrar solamente esa pestaña, no el programa
    }

    private void dibujarTablaColaboracion() {
        tablaColaboracion.setModel(modeloTColabora);
        String[] columnasTabla = {"Código", "Nombre", "Especialidad", "Colaboración"};
        modeloTColabora.setColumnIdentifiers(columnasTabla);

        // Para no permitir el redimensionamiento de las columnas con el ratón
        tablaColaboracion.getTableHeader().setResizingAllowed(false);

        // Así se fija el ancho de las columnas
        tablaColaboracion.getColumnModel().getColumn(0).setPreferredWidth(25);
        tablaColaboracion.getColumnModel().getColumn(1).setPreferredWidth(80);
        tablaColaboracion.getColumnModel().getColumn(2).setPreferredWidth(80);
        tablaColaboracion.getColumnModel().getColumn(3).setPreferredWidth(120);
    }

    private void pideColaboracion() throws SQLException {
        //vaciarTablaColaboracion();
        String codCaso = introducirCaso.getText();
        rellenarTablaColabora(manejaCol.listaColaboracion(codCaso));

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tablaColaboracion = new javax.swing.JTable();
        introducirCaso = new java.awt.TextField();
        textoCaso = new java.awt.Label();
        buttonColabora = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tablaColaboracion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tablaColaboracion);

        introducirCaso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                introducirCasoActionPerformed(evt);
            }
        });

        textoCaso.setText("Introduce Código del Caso");

        buttonColabora.setText("Mostrar");
        buttonColabora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonColaboraActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(textoCaso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(introducirCaso, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addComponent(buttonColabora))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 658, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(introducirCaso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoCaso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonColabora))
                .addContainerGap(74, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void introducirCasoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_introducirCasoActionPerformed

    }//GEN-LAST:event_introducirCasoActionPerformed

    private void buttonColaboraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonColaboraActionPerformed
        // Mostrar por pais AQUI
        vaciarTablaColaboracion();
        try {
            pideColaboracion();
        } catch (SQLException ex) {
            Logger.getLogger(VentanaColaboracion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buttonColaboraActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonColabora;
    private java.awt.TextField introducirCaso;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaColaboracion;
    private java.awt.Label textoCaso;
    // End of variables declaration//GEN-END:variables

    private void vaciarTablaColaboracion() {
        while (modeloTColabora.getRowCount() > 0) {
            modeloTColabora.removeRow(0);
        }
    }

    private void rellenarTablaColabora(ArrayList<colabora> listaColaboracion) {

        Object[] columna = new Object[4];
        int numRegistros = listaColaboracion.size();
        for (int i = 0; i < numRegistros; i++) {
            columna[0] = listaColaboracion.get(i).getCodExperto();
            columna[1] = listaColaboracion.get(i).getCodCaso();
            columna[2] = listaColaboracion.get(i).getFecha();
            columna[3] = listaColaboracion.get(i).getDescripcionColaboracion();
            modeloTColabora.addRow(columna);
        }

    }

}
