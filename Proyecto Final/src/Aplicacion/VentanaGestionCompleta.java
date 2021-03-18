/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicacion;

import Persistencia.caso;
import Persistencia.colabora;
import Persistencia.conexionOracle;
import Persistencia.experto;
import Persistencia.manejaCaso;
import Persistencia.manejaColabora;
import Persistencia.manejaExperto;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showConfirmDialog;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author User
 */
public class VentanaGestionCompleta extends javax.swing.JFrame {

    DefaultTableModel modeloTExpertos = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    DefaultTableModel modeloTCasos = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    DefaultTableModel modeloTColabora = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    manejaColabora manejaCol = null;
    manejaExperto manejaExp = null;
    manejaCaso manejaCasos = null;

    /**
     * Creates new form VentanaGestionCompleta
     *
     * @param co
     * @throws java.sql.SQLException
     */
    public VentanaGestionCompleta(conexionOracle co) throws SQLException {
        manejaExp = new manejaExperto(co);
        tablaExperto = new JTable(modeloTExpertos);
        manejaCasos = new manejaCaso(co);
        tablaCasosPoliciales = new JTable(modeloTCasos);
        manejaCol = new manejaColabora(co);
        tablaColaboracion = new JTable(modeloTColabora);

        initComponents();
        dibujarTablaExpertos();
        //pideExpertos();
        dibujarTablaCasos();
        //pideCasos();
        dibujarTablaColaboracion();
        //pideColaboracion();
    }

    private void dibujarTablaExpertos() {
        tablaExperto.setModel(modeloTExpertos);
        String[] columnasTabla = {"Código", "Nombre", "País", "Sexo", "Especialidad"};
        modeloTExpertos.setColumnIdentifiers(columnasTabla);

        // Para no permitir el redimensionamiento de las columnas con el ratón
        tablaExperto.getTableHeader().setResizingAllowed(false);

        // Así se fija el ancho de las columnas
        tablaExperto.getColumnModel().getColumn(0).setPreferredWidth(25);
        tablaExperto.getColumnModel().getColumn(1).setPreferredWidth(140);
        tablaExperto.getColumnModel().getColumn(2).setPreferredWidth(80);
        tablaExperto.getColumnModel().getColumn(3).setPreferredWidth(8);
        tablaExperto.getColumnModel().getColumn(4).setPreferredWidth(122);

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

    private void dibujarTablaCasos() {
        tablaCasosPoliciales.setModel(modeloTCasos);
        String[] columnasTabla = {"Código Caso", "Nombre", "Fecha Inicio", "Fecha Fin"};
        modeloTCasos.setColumnIdentifiers(columnasTabla);

        // Para no permitir el redimensionamiento de las columnas con el ratón
        tablaCasosPoliciales.getTableHeader().setResizingAllowed(false);

        // Así se fija el ancho de las columnas
        tablaCasosPoliciales.getColumnModel().getColumn(0).setPreferredWidth(80);
        tablaCasosPoliciales.getColumnModel().getColumn(1).setPreferredWidth(140);
        tablaCasosPoliciales.getColumnModel().getColumn(2).setPreferredWidth(80);
        tablaCasosPoliciales.getColumnModel().getColumn(3).setPreferredWidth(80);

    }

    private void rellenarTablaCasos(ArrayList<caso> casos) {
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
        rellenarTablaColabora(manejaCol.mostrarColaboracion());

    }

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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        etiquetaNombreExperto = new java.awt.TextField();
        etiquetaCodExperto = new java.awt.TextField();
        etiquetaPaisExperto = new java.awt.TextField();
        etiquetaSexoExperto = new java.awt.TextField();
        etiquetaEspecialidadExperto = new java.awt.TextField();
        buttonInsertarExperto = new javax.swing.JButton();
        buttonInsertarCasoPolicial = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        etiquetaNombreCaso = new java.awt.TextField();
        etiquetaCodCaso = new java.awt.TextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaExperto = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaCasosPoliciales = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        buttonEliminarExperto = new javax.swing.JButton();
        buttonEliminarCaso = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaColaboracion = new javax.swing.JTable();
        buttonInsertarColaboracion = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        etiquetaCodExpertoColaboracion = new java.awt.TextField();
        etiquetaCodCasoColaboracion = new java.awt.TextField();
        etiquetaDescripcionColaboracion = new java.awt.TextField();
        buttonEliminarColaboracion1 = new javax.swing.JButton();
        buttonListarTodo = new javax.swing.JButton();
        buttonLimpiarTodo = new javax.swing.JButton();
        etiquetaFechaInicio = new com.toedter.calendar.JDateChooser();
        etiquetaFechaFin = new com.toedter.calendar.JDateChooser();
        etiquetaFechaColaboracion = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Código");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("País");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Sexo");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Especialidad");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Nombre");

        etiquetaNombreExperto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                etiquetaNombreExpertoActionPerformed(evt);
            }
        });

        etiquetaCodExperto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                etiquetaCodExpertoActionPerformed(evt);
            }
        });

        etiquetaPaisExperto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                etiquetaPaisExpertoActionPerformed(evt);
            }
        });

        etiquetaSexoExperto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                etiquetaSexoExpertoActionPerformed(evt);
            }
        });

        etiquetaEspecialidadExperto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                etiquetaEspecialidadExpertoActionPerformed(evt);
            }
        });

        buttonInsertarExperto.setText("Insertar Experto");
        buttonInsertarExperto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonInsertarExpertoActionPerformed(evt);
            }
        });

        buttonInsertarCasoPolicial.setText("Insertar Caso Policial");
        buttonInsertarCasoPolicial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonInsertarCasoPolicialActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Código");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Nombre");

        etiquetaNombreCaso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                etiquetaNombreCasoActionPerformed(evt);
            }
        });

        etiquetaCodCaso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                etiquetaCodCasoActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Fecha Inicio");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Fecha Fin");

        tablaExperto.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tablaExperto);

        tablaCasosPoliciales.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tablaCasosPoliciales);

        jLabel10.setText("Listado de Expertos");

        jLabel11.setText("Listado de Casos Policiales");

        buttonEliminarExperto.setText("Eliminar Experto");
        buttonEliminarExperto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEliminarExpertoActionPerformed(evt);
            }
        });

        buttonEliminarCaso.setText("Eliminar Caso Policial");
        buttonEliminarCaso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEliminarCasoActionPerformed(evt);
            }
        });

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
        jScrollPane3.setViewportView(tablaColaboracion);

        buttonInsertarColaboracion.setText("Insertar Colaboración");
        buttonInsertarColaboracion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonInsertarColaboracionActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("Código del Experto");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("Código del Caso");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setText("Fecha de Incorporación");

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel15.setText("Descripción");

        etiquetaCodExpertoColaboracion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                etiquetaCodExpertoColaboracionActionPerformed(evt);
            }
        });

        etiquetaCodCasoColaboracion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                etiquetaCodCasoColaboracionActionPerformed(evt);
            }
        });

        etiquetaDescripcionColaboracion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                etiquetaDescripcionColaboracionActionPerformed(evt);
            }
        });

        buttonEliminarColaboracion1.setText("Eliminar Colaboración");
        buttonEliminarColaboracion1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEliminarColaboracion1ActionPerformed(evt);
            }
        });

        buttonListarTodo.setText("Listar Todo");
        buttonListarTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonListarTodoActionPerformed(evt);
            }
        });

        buttonLimpiarTodo.setText("Limpiar Todo");
        buttonLimpiarTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLimpiarTodoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(241, 241, 241)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 583, Short.MAX_VALUE)
                        .addComponent(jLabel11)
                        .addGap(206, 206, 206))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(58, 58, 58)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(etiquetaCodExperto, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(21, 21, 21)
                                        .addComponent(buttonInsertarExperto))
                                    .addComponent(etiquetaNombreExperto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(etiquetaPaisExperto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(58, 58, 58))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(38, 38, 38)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(etiquetaEspecialidadExperto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(etiquetaSexoExperto, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))))
                        .addGap(89, 89, 89)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(21, 21, 21)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(etiquetaCodCaso, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(buttonInsertarCasoPolicial)
                                        .addGap(186, 186, 186)
                                        .addComponent(buttonListarTodo)
                                        .addGap(18, 18, 18)
                                        .addComponent(buttonLimpiarTodo))
                                    .addComponent(etiquetaNombreCaso, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(etiquetaFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(etiquetaFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 587, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(buttonEliminarExperto))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(buttonEliminarCaso)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 542, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel12)
                                                .addComponent(jLabel13)
                                                .addComponent(jLabel15))
                                            .addGap(45, 45, 45))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addComponent(jLabel14)
                                            .addGap(18, 18, 18)))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(etiquetaDescripcionColaboracion, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                                        .addComponent(etiquetaCodCasoColaboracion, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                                        .addComponent(etiquetaCodExpertoColaboracion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(etiquetaFechaColaboracion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addComponent(buttonInsertarColaboracion))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 783, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(404, 404, 404)
                    .addComponent(buttonEliminarColaboracion1)
                    .addContainerGap(756, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(etiquetaCodExperto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonInsertarExperto))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(etiquetaNombreExperto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(buttonInsertarCasoPolicial)
                                .addComponent(buttonListarTodo)
                                .addComponent(buttonLimpiarTodo))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(etiquetaCodCaso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7)
                            .addComponent(etiquetaNombreCaso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(etiquetaPaisExperto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(etiquetaSexoExperto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(etiquetaEspecialidadExperto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(9, 9, 9))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(etiquetaFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(etiquetaFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonEliminarExperto)
                    .addComponent(buttonEliminarCaso))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel14)
                            .addComponent(etiquetaFechaColaboracion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(13, 13, 13)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(etiquetaCodExpertoColaboracion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(etiquetaCodCasoColaboracion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel15)
                            .addComponent(etiquetaDescripcionColaboracion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(buttonInsertarColaboracion)))
                .addContainerGap(61, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(609, Short.MAX_VALUE)
                    .addComponent(buttonEliminarColaboracion1)
                    .addGap(21, 21, 21)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void etiquetaNombreExpertoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_etiquetaNombreExpertoActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_etiquetaNombreExpertoActionPerformed

    private void etiquetaCodExpertoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_etiquetaCodExpertoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_etiquetaCodExpertoActionPerformed

    private void etiquetaPaisExpertoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_etiquetaPaisExpertoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_etiquetaPaisExpertoActionPerformed

    private void etiquetaSexoExpertoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_etiquetaSexoExpertoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_etiquetaSexoExpertoActionPerformed

    private void etiquetaEspecialidadExpertoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_etiquetaEspecialidadExpertoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_etiquetaEspecialidadExpertoActionPerformed

    private void buttonInsertarExpertoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonInsertarExpertoActionPerformed
        experto exp = new experto(etiquetaCodExperto.getText(),
                etiquetaNombreExperto.getText(),
                etiquetaPaisExperto.getText(),
                etiquetaSexoExperto.getText(),
                etiquetaEspecialidadExperto.getText());
        try {
            manejaExp.insertaExperto(exp);
            pideExpertos();
        } catch (SQLException ex) {
            Logger.getLogger(VentanaGestionCompleta.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }//GEN-LAST:event_buttonInsertarExpertoActionPerformed

    private void buttonInsertarCasoPolicialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonInsertarCasoPolicialActionPerformed

        if (etiquetaFechaFin.getDate() == null) {
            DateFormat d = new SimpleDateFormat("dd/MM/yyyy");
            String fi = d.format(etiquetaFechaInicio.getDate());
            caso c = new caso(etiquetaCodCaso.getText(), etiquetaNombreCaso.getText(), fi, null);
            try {
                manejaCasos.insertaCaso(c);
                vaciarTablaCasos();
                pideCasos();
            } catch (SQLException ex) {
                Logger.getLogger(VentanaGestionCompleta.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            DateFormat d = new SimpleDateFormat("dd/MM/yyyy");
            String fi = d.format(etiquetaFechaInicio.getDate());
            String ff = d.format(etiquetaFechaFin.getDate());
            if (etiquetaFechaInicio.getDate().compareTo(etiquetaFechaFin.getDate()) < 0) {
                caso c = new caso(etiquetaCodCaso.getText(), etiquetaNombreCaso.getText(), fi, ff);
                try {
                    manejaCasos.insertaCaso(c);
                    vaciarTablaCasos();
                    pideCasos();
                } catch (SQLException ex) {
                    Logger.getLogger(VentanaGestionCompleta.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_buttonInsertarCasoPolicialActionPerformed


    private void etiquetaNombreCasoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_etiquetaNombreCasoActionPerformed

    }//GEN-LAST:event_etiquetaNombreCasoActionPerformed

    private void etiquetaCodCasoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_etiquetaCodCasoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_etiquetaCodCasoActionPerformed

    private void buttonEliminarExpertoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEliminarExpertoActionPerformed
        // TODO add your handling code here:
        String cod_Exp = tablaExperto.getValueAt(tablaExperto.getSelectedRow(), 0).toString();
        int confirmacion = showConfirmDialog(null, "¿Seguro que quieres eliminar este Experto?", null, JOptionPane.YES_NO_OPTION);
        if (confirmacion == 0) {
            try {
                manejaExp.eliminarExperto(cod_Exp);
                vaciarTablaExpertos();
                pideExpertos();
                vaciarTablaColaboracion();
                pideColaboracion();
            } catch (SQLException ex) {
                Logger.getLogger(VentanaGestionCompleta.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_buttonEliminarExpertoActionPerformed

    private void buttonEliminarCasoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEliminarCasoActionPerformed
        // TODO add your handling code here:
        String cod_Cas = tablaCasosPoliciales.getValueAt(tablaCasosPoliciales.getSelectedRow(), 0).toString();
        int confirmacion = showConfirmDialog(null, "¿Seguro que quieres eliminar este Caso?", null, JOptionPane.YES_NO_OPTION);
        if (confirmacion == 0) {
            try {
                manejaCasos.eliminarCaso(cod_Cas);
                vaciarTablaCasos();
                pideCasos();
                vaciarTablaColaboracion();
                pideColaboracion();
            } catch (SQLException ex) {
                Logger.getLogger(VentanaGestionCompleta.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_buttonEliminarCasoActionPerformed

    private void buttonInsertarColaboracionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonInsertarColaboracionActionPerformed
        // TODO add your handling code here:
        DateFormat d = new SimpleDateFormat("dd/MM/yyyy");
        String fecha = d.format(etiquetaFechaColaboracion.getDate());
        
        colabora colabora = new colabora(etiquetaCodExpertoColaboracion.getText(),
                etiquetaCodCasoColaboracion.getText(),
                fecha,
                etiquetaDescripcionColaboracion.getText());
        try {
            manejaCol.insertaColaboracion(colabora);
            vaciarTablaColaboracion();
            pideColaboracion();
        } catch (SQLException ex) {
            Logger.getLogger(VentanaGestionCompleta.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_buttonInsertarColaboracionActionPerformed

    private void etiquetaCodExpertoColaboracionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_etiquetaCodExpertoColaboracionActionPerformed
        // TODO add your handling code here:
        
        
        
        
    }//GEN-LAST:event_etiquetaCodExpertoColaboracionActionPerformed

    private void etiquetaCodCasoColaboracionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_etiquetaCodCasoColaboracionActionPerformed
        // TODO add your handling code here:
        
        
        
    }//GEN-LAST:event_etiquetaCodCasoColaboracionActionPerformed

    private void etiquetaDescripcionColaboracionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_etiquetaDescripcionColaboracionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_etiquetaDescripcionColaboracionActionPerformed

    private void buttonEliminarColaboracion1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEliminarColaboracion1ActionPerformed
        // TODO add your handling code here:
        String cod_Exp = tablaColaboracion.getValueAt(tablaColaboracion.getSelectedRow(), 0).toString();
        String cod_Cas = tablaColaboracion.getValueAt(tablaColaboracion.getSelectedRow(), 1).toString();
        int confirmacion = showConfirmDialog(null, "¿Seguro que quieres eliminar esta colaboración?", null, JOptionPane.YES_NO_OPTION);
        if (confirmacion == 0) {
            try {
                manejaCol.eliminarColaboracion(cod_Exp, cod_Cas);
                vaciarTablaColaboracion();
                pideColaboracion();
            } catch (SQLException ex) {
                Logger.getLogger(VentanaGestionCompleta.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_buttonEliminarColaboracion1ActionPerformed

    private void buttonListarTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonListarTodoActionPerformed
        // TODO add your handling code here:
        limpiarTodo();
        try {
            pideExpertos();
            pideCasos();
            pideColaboracion();
        } catch (SQLException ex) {
            Logger.getLogger(VentanaGestionCompleta.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_buttonListarTodoActionPerformed

    private void buttonLimpiarTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLimpiarTodoActionPerformed
        // TODO add your handling code here:
        limpiarTodo();
    }//GEN-LAST:event_buttonLimpiarTodoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonEliminarCaso;
    private javax.swing.JButton buttonEliminarColaboracion1;
    private javax.swing.JButton buttonEliminarExperto;
    private javax.swing.JButton buttonInsertarCasoPolicial;
    private javax.swing.JButton buttonInsertarColaboracion;
    private javax.swing.JButton buttonInsertarExperto;
    private javax.swing.JButton buttonLimpiarTodo;
    private javax.swing.JButton buttonListarTodo;
    private java.awt.TextField etiquetaCodCaso;
    private java.awt.TextField etiquetaCodCasoColaboracion;
    private java.awt.TextField etiquetaCodExperto;
    private java.awt.TextField etiquetaCodExpertoColaboracion;
    private java.awt.TextField etiquetaDescripcionColaboracion;
    private java.awt.TextField etiquetaEspecialidadExperto;
    private com.toedter.calendar.JDateChooser etiquetaFechaColaboracion;
    private com.toedter.calendar.JDateChooser etiquetaFechaFin;
    private com.toedter.calendar.JDateChooser etiquetaFechaInicio;
    private java.awt.TextField etiquetaNombreCaso;
    private java.awt.TextField etiquetaNombreExperto;
    private java.awt.TextField etiquetaPaisExperto;
    private java.awt.TextField etiquetaSexoExperto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tablaCasosPoliciales;
    private javax.swing.JTable tablaColaboracion;
    private javax.swing.JTable tablaExperto;
    // End of variables declaration//GEN-END:variables

    private void vaciarTablaCasos() {
        while (modeloTCasos.getRowCount() > 0) {
            modeloTCasos.removeRow(0);
        }
    }

    private void limpiarTodo() {
        vaciarTablaCasos();
        vaciarTablaColaboracion();
        vaciarTablaExpertos();
    }
}
