package Persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class manejaCaso {

    // Crea un objeto de tipo "conexionOracle"
    conexionOracle conexion = null;

    // Crea un PreparedStatement como atributo de la clase manejaExperto para
    // utilizarlo en los diferentes métodos
    PreparedStatement ps = null;

    /**
     * Implementa operaciones sobre la tabla CASO
     *
     * @param c conexión con Oracle
     */
    public manejaCaso(conexionOracle c) {
        conexion = c;
    }

    /**
     * Comprueba si existe un caso en la tabla de CASO_POLICIAL dado un código
     * de caso
     *
     * @param codCaso código del caso a buscar
     * @return existe
     * @throws SQLException si ocurre alguna anomalía
     */
    public boolean existeCaso(String codCaso) throws SQLException {
        boolean existecaso = false;
        try {
            Statement stmt;
            stmt = conexion.conn.createStatement();
            PreparedStatement prep = conexion.conn.prepareStatement("select * from CASO_POLICIAL where codCaso = ?");
            prep.setString(1, codCaso);
            ResultSet res = prep.executeQuery();
            if (res.next() == true) {
                existecaso = true;
            }
            stmt.close();
        } catch (SQLException ex) {
            System.out.println("Ha ocurrido un error en existeCaso: " + ex.getMessage());
            System.out.println(ex.getSQLState());
            System.out.println(ex.getErrorCode());
        }
        return existecaso;
    }

    /**
     * Inserta caso en la tabla de CASO_POLICIAL
     *
     * @param cs caso a insertar
     * @throws SQLException si ocurre alguna anomalía
     */
    public void insertaCaso(caso cs) throws SQLException {
        try {
            //Statement stmt = conexion.conn.createStatement();
            ps = conexion.conn.prepareStatement("insert into CASO_POLICIAL values (?,?,?,?)");
            ps.setString(1, cs.getCodCaso());
            ps.setString(2, cs.getNombre());
            ps.setString(3, cs.getFechaInicio());
            ps.setString(4, cs.getFechaFin());
               ps.executeUpdate();
            /*Statement stmt = conexion.conn.createStatement();
            stmt.executeUpdate("insert into EXPERTO values (exp.getCodExperto(),exp.getNombre(),exp.getPais(),exp.getSexo(),exp.getEspecialidad())");
            stmt.close();*/
        } catch (SQLException exc) {
            System.out.println("Error al Insertar en la tabla Caso: " + exc.getMessage());
            System.out.println(exc.getSQLState());
            System.out.println(exc.getErrorCode());
        }
    }
    
    public ArrayList<caso> listaCasos()throws SQLException {
        ArrayList<caso> lista = new ArrayList();

        try {
            Statement stmt = conexion.conn.createStatement();
            ps = conexion.conn.prepareStatement("select * from CASO_POLICIAL ORDER BY CODCASO");
            ResultSet res = ps.executeQuery();
            while (res.next()) {
                caso cass = new caso(res.getString(1),
                                          res.getString(2),
                                          res.getString(3),
                                          res.getString(4));
                lista.add(cass);
            }
            stmt.close();
        } catch (SQLException ex) {
            System.out.println("Ha ocurrido un error en listaCasos: " + ex.getMessage());
            System.out.println(ex.getSQLState());
            System.out.println(ex.getErrorCode());
        }
        return lista;
    }
    
    public void eliminarCaso(String codigoCaso) throws SQLException {
        try {
            ps = conexion.conn.prepareStatement("DELETE FROM CASO_POLICIAL WHERE CODCASO = ?");
            ps.setString(1, codigoCaso);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error al eliminar Caso");
            System.out.println(ex.getMessage());
            System.out.println(ex.getSQLState());
            System.out.println(ex.getErrorCode());
        }

    }
    
}
