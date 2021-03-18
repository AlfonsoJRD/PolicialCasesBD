/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicacion;

import Persistencia.conexionOracle;
import Persistencia.experto;
import Persistencia.manejaExperto;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author usuario
 */
public class VentanaExpertos extends javax.swing.JFrame {

    DefaultTableModel modeloTExpertos = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    manejaExperto manejaExp = null;

    public VentanaExpertos(conexionOracle co) throws SQLException {
        manejaExp = new manejaExperto(co);
        tExperto = new JTable(modeloTExpertos);
        initComponents();
        dibujarTablaExpertos();
        pideExpertos();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); ///Para cerrar solamente esa pestaña, no el programa
    }

    private void dibujarTablaExpertos() {
        tExperto.setModel(modeloTExpertos);
        String[] columnasTabla = {"Código", "Nombre", "País", "Sexo", "Especialidad"};
        modeloTExpertos.setColumnIdentifiers(columnasTabla);

        // Para no permitir el redimensionamiento de las columnas con el ratón
        tExperto.getTableHeader().setResizingAllowed(false);

        // Así se fija el ancho de las columnas
        tExperto.getColumnModel().getColumn(0).setPreferredWidth(25);
        tExperto.getColumnModel().getColumn(1).setPreferredWidth(140);
        tExperto.getColumnModel().getColumn(2).setPreferredWidth(80);
        tExperto.getColumnModel().getColumn(3).setPreferredWidth(8);
        tExperto.getColumnModel().getColumn(4).setPreferredWidth(122);

    }

    private void rellenarTablaExperto(ArrayList<experto> expertos) {
        Object[] columna = new Object[5];
        int numRegistros = expertos.size();
        for (int i = 0; i < numRegistros; i++) {
            columna[0] = expertos.get(i).getCodExperto();
            columna[1] = expertos.get(i).getNombre();
            columna[2] = expertos.get(i).getPais();
            columna[3] = expertos.get(i).getSexo();
            columna[4] = expertos.get(i).getEspecialidad();
            modeloTExpertos.addRow(columna);
        }
    }

    private void pideExpertos() throws SQLException {
        vaciarTablaExpertos();
        rellenarTablaExperto(manejaExp.listaExpertos());
    }

    private void vaciarTablaExpertos() {
        while (modeloTExpertos.getRowCount() > 0) {
            modeloTExpertos.removeRow(0);
        }
    }

    private void pideExpertosPorPais() throws SQLException {
        String paisSeleccionado = buttonPais.getText();
        rellenarTablaExperto(manejaExp.listaExpertosPorPais(paisSeleccionado));
    }

    private void pideSexoTeclado() throws SQLException {
        String sexo = buttonSexo.getText();
        int numeroSexo = manejaExp.sexoExperto(sexo);
        String mensaje = null;
        if ("M".equals(sexo) || "M".equals(sexo)) {
            mensaje = "Hay " + numeroSexo + " hombres en la base de datos.";
            mesajeSexo.setText(mensaje);
            mesajeSexo.setVisible(true);
            ///Ahora llamariamos al metodo que impripe esto por pantalla          

        } else if ("F".equals(sexo) || "f".equals(sexo)) {
            mensaje = "Hay " + numeroSexo + " mujeres en la base de datos.";
            mesajeSexo.setText(mensaje);
            mesajeSexo.setVisible(true);
            ///Ahora llamariamos al metodo que impripe esto por pantalla
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jScrollPane1 = new javax.swing.JScrollPane();
        tExperto = new javax.swing.JTable();
        buttonPorPais = new javax.swing.JButton();
        label1 = new java.awt.Label();
        buttonListarTodos = new javax.swing.JButton();
        buttonPais = new java.awt.TextField();
        etiquetaSexo = new java.awt.Label();
        buttonSexo = new java.awt.TextField();
        buttonContar = new javax.swing.JButton();
        mesajeSexo = new java.awt.Label();
        jMenuBar1 = new javax.swing.JMenuBar();

        jMenu1.setText("jMenu1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tExperto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Código", "Nombre", "País", "Sexo", "Especialidad"
            }
        ));
        jScrollPane1.setViewportView(tExperto);

        buttonPorPais.setText("Filtrar por País");
        buttonPorPais.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPorPaisActionPerformed(evt);
            }
        });

        label1.setText("País");

        buttonListarTodos.setText("Listar Todos");
        buttonListarTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonListarTodosActionPerformed(evt);
            }
        });

        buttonPais.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPaisActionPerformed(evt);
            }
        });

        etiquetaSexo.setText("Sexo");

        buttonSexo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSexoActionPerformed(evt);
            }
        });

        buttonContar.setText("Contar");
        buttonContar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonContarActionPerformed(evt);
            }
        });

        mesajeSexo.setText("Introduzca Sexo");
        mesajeSexo.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                mesajeSexoPropertyChange(evt);
            }
        });
        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonPais, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(71, 71, 71)
                        .addComponent(buttonPorPais)
                        .addGap(42, 42, 42)
                        .addComponent(buttonListarTodos)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(etiquetaSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(buttonContar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mesajeSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(262, 262, 262))))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(buttonPais, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(buttonPorPais)
                        .addComponent(buttonListarTodos)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(etiquetaSexo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(buttonContar)
                        .addComponent(buttonSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(mesajeSexo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonPaisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPaisActionPerformed
        // TODO add your handling code here:
        vaciarTablaExpertos();
        try {
            pideExpertosPorPais();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }//GEN-LAST:event_buttonPaisActionPerformed

    private void buttonPorPaisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPorPaisActionPerformed
        // Mostrar por pais AQUI
        vaciarTablaExpertos();
        try {
            pideExpertosPorPais();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

    }//GEN-LAST:event_buttonPorPaisActionPerformed

    private void buttonListarTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonListarTodosActionPerformed
        // Hay que mostrar TODO aqui
        vaciarTablaExpertos();
        try {
            pideExpertos();
        } catch (SQLException ex) {
            System.out.println("Error a la hora de mostrar Expertos: " + ex.getMessage());
        }
    }//GEN-LAST:event_buttonListarTodosActionPerformed

    private void buttonSexoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSexoActionPerformed

        try {
            pideSexoTeclado();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

    }//GEN-LAST:event_buttonSexoActionPerformed

    private void buttonContarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonContarActionPerformed

        try {
            pideSexoTeclado();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }//GEN-LAST:event_buttonContarActionPerformed

    private void mesajeSexoPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_mesajeSexoPropertyChange
        // TODO add your handling code here:
        try {
            pideSexoTeclado();
        } catch (SQLException ex) {
            Logger.getLogger(VentanaExpertos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_mesajeSexoPropertyChange

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonContar;
    private javax.swing.JButton buttonListarTodos;
    private java.awt.TextField buttonPais;
    private javax.swing.JButton buttonPorPais;
    private java.awt.TextField buttonSexo;
    private java.awt.Label etiquetaSexo;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private java.awt.Label label1;
    private java.awt.Label mesajeSexo;
    private javax.swing.JTable tExperto;
    // End of variables declaration//GEN-END:variables
}
