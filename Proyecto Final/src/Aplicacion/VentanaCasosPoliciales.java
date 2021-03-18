package Aplicacion;

import Persistencia.caso;
import Persistencia.conexionOracle;
import Persistencia.manejaCaso;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Álvaro Rofa
 */
public class VentanaCasosPoliciales extends javax.swing.JFrame {
   
    DefaultTableModel modeloTCasos = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
       
    manejaCaso manejaCasos = null;
    
    public VentanaCasosPoliciales(conexionOracle co) throws SQLException {
        manejaCasos = new manejaCaso(co);
        jCasos = new JTable(modeloTCasos);
        initComponents();
        dibujarTablaCasos();
        pideCasos();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); ///Para cerrar solamente esa pestaña, no el programa
    }
    
    private void dibujarTablaCasos() {
        jCasos.setModel(modeloTCasos);
        String[] columnasTabla = {"Código Caso", "Nombre", "Fecha Inicio", "Fecha Fin"};
        modeloTCasos.setColumnIdentifiers(columnasTabla);

        // Para no permitir el redimensionamiento de las columnas con el ratón
        jCasos.getTableHeader().setResizingAllowed(false);

        // Así se fija el ancho de las columnas
        jCasos.getColumnModel().getColumn(0).setPreferredWidth(80);
        jCasos.getColumnModel().getColumn(1).setPreferredWidth(140);
        jCasos.getColumnModel().getColumn(2).setPreferredWidth(80);
        jCasos.getColumnModel().getColumn(3).setPreferredWidth(80);

    }
    
    private void rellenarTablaCasos (ArrayList<caso> casos) {
        Object[] columna = new Object[4];
        int numRegistros = casos.size();
        for (int i = 0; i < numRegistros; i++) {
            columna[0] = casos.get(i).getCodCaso();
            columna[1] = casos.get(i).getNombre();
            columna[2] = casos.get(i).getFechaInicio();
            columna[3] = casos.get(i).getFechaFin();
            modeloTCasos.addRow(columna);
        }
    }
    
   private void pideCasos() throws SQLException {
        rellenarTablaCasos(manejaCasos.listaCasos());
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jCasos = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jCasos.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jCasos);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 542, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable jCasos;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
