package Persistencia;

import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class manejaColabora {

    // Creamos un objeto de tipo "conexionOracle"
    conexionOracle conexion = null;

    // Creamos un PreparedStatement como atributo de la clase manejaExperto para
    // utilizarlo en los diferentes métodos
    PreparedStatement ps = null;

    /**
     * Implementa operaciones sobre la tabla COLABORA
     *
     * @param c conexión con Oracle
     */
    public manejaColabora(conexionOracle c) {
        conexion = c;
    }

    /**
     * Comprueba si existe una colaboración en la tabla de COLABORA dado su
     * código
     *
     * @param codExperto
     * @param codCaso
     * @param fecha
     * @return
     * @throws SQLException si ocurre alguna anomalía
     */
    public boolean existeColaboracion(String codExperto, String codCaso, String fecha) throws SQLException {
        boolean existeColaboracion = false;
        try {
            Statement stmt;
            stmt = conexion.conn.createStatement();
            ps = conexion.conn.prepareStatement("select * from COLABORA where codCaso = ? AND codExperto = ? AND fecha = ?");
            ps.setString(1, codCaso);
            ps.setString(2, codExperto);
            ps.setString(3, fecha);
            ResultSet res = ps.executeQuery();
            if (res.next() == true) {
                existeColaboracion = true;
            }
            stmt.close();
        } catch (SQLException ex) {
            System.out.println("Ha ocurrido un error en existeColaboracion: " + ex.getMessage());
            System.out.println(ex.getSQLState());
            System.out.println(ex.getErrorCode());
        }
        return existeColaboracion;
    }
    
    public ArrayList<colabora> listaColaboracion(String codCaso) throws SQLException {
        ArrayList<colabora> lista = new ArrayList();
        try {
            Statement stmt = conexion.conn.createStatement();
            PreparedStatement prep = conexion.conn.prepareStatement(
            "SELECT e.CODEXPERTO,e.NOMBRE,e.ESPECIALIDAD,c.DESCRIPCION_COLABORACION FROM COLABORA c INNER JOIN EXPERTO e ON (C.CODEXPERTO=e.CODEXPERTO)WHERE c.CODCASO = ? GROUP BY e.CODEXPERTO,e.NOMBRE,e.ESPECIALIDAD,c.DESCRIPCION_COLABORACION");
            prep.setString(1, codCaso);
            ResultSet res = prep.executeQuery();
            while (res.next()) {
                colabora col = new colabora(res.getString(1),
                        res.getString(2),
                        res.getString(3),
                        res.getString(4));
                lista.add(col);
            }
            stmt.close();
        } catch (SQLException ex) {
            System.out.println("Ha ocurrido un error en listaColaboracion: " + ex.getMessage());
            System.out.println(ex.getSQLState());
            System.out.println(ex.getErrorCode());
        }
        return lista;
    }
    
    public ArrayList<colabora> mostrarColaboracion() throws SQLException {
        ArrayList<colabora> lista = new ArrayList();
        try {
            Statement stmt = conexion.conn.createStatement();
            PreparedStatement prep = conexion.conn.prepareStatement(
            "SELECT * FROM COLABORA ORDER BY CODEXPERTO");
            ResultSet res = prep.executeQuery();
            while (res.next()) {
                colabora col = new colabora(res.getString(1),
                        res.getString(2),
                        res.getString(3),
                        res.getString(4));
                lista.add(col);
            }
            stmt.close();
        } catch (SQLException ex) {
            System.out.println("Ha ocurrido un error en mostrarColaboracion: " + ex.getMessage());
            System.out.println(ex.getSQLState());
            System.out.println(ex.getErrorCode());
        }
        return lista;
    }

    /**
     * Inserta una colaboración en la tabla COLABORA
     *
     * @param col
     * @throws SQLException si ocurre alguna anomalía
     */
    public void insertaColaboracion(colabora col) throws SQLException {
        conexion.inicioTransaccion();
        if (!existeColaboracion(col.getCodExperto(), col.getCodCaso(), col.getFecha())) {
            manejaCaso usoCaso = new manejaCaso(conexion);
            manejaExperto Experto = new manejaExperto(conexion);
            Scanner sc = new Scanner(System.in);
            if (!Experto.existeExperto(col.getCodExperto())) {
                String nom, sex, pais, esp;
                System.out.print("Datos del Experto");
                System.out.println("Nombre: ");
                nom = sc.nextLine();
                System.out.println("Pais: ");
                pais = sc.nextLine();
                System.out.println("Sexo: ");
                sex = sc.nextLine();
                System.out.println("Especialidad: ");
                esp = sc.nextLine();
                experto exp = new experto(col.getCodExperto(), nom, pais, sex, esp);
                Experto.insertaExperto(exp);
                /*
                Aqui ya hbaremos insertado el experto en el caso en el que NO EXISTIERA el mismo
                 */
            }
            if (!usoCaso.existeCaso(col.getCodCaso())) {
                String nombre, fini, ffin;
                System.out.println("Nombre del Caso: ");
                nombre = sc.nextLine();
                System.out.println("Fecha Inicio: ");
                fini = sc.nextLine();
                System.out.println("Fecha Fin: ");
                ffin = sc.nextLine();
                caso nuevoCaso = new caso(col.getCodCaso(), nombre, fini, ffin);
                usoCaso.insertaCaso(nuevoCaso);
            }
            /*
            nuevoCaso INSERTADO
             */
            try {
                ps = conexion.conn.prepareStatement("insert into COLABORA values (?,?,?,?)");
                ps.setString(1, col.getCodExperto());
                ps.setString(2, col.getCodCaso());
                ps.setString(3, col.getFecha());
                ps.setString(4, col.getDescripcionColaboracion());
                ps.executeUpdate();
                ps.close();

                conexion.finTransaccionCommit();
            } catch (SQLException exc) {
                /*
                Si falla, hago rollBack
                 */
                conexion.finTransaccionRollback();
                System.out.println("Error al Insertar en la tabla Colabrora: " + exc.getMessage());
                System.out.println(exc.getSQLState());
                System.out.println(exc.getErrorCode());
            }
        }

    }
    
    public void eliminarColaboracion(String codExperto, String codCaso)  throws SQLException{
        try{
                ps = conexion.conn.prepareStatement("delete from COLABORA where codExperto = ? AND codCaso = ?");
                ps.setString(1, codExperto);
                ps.setString(2, codCaso);
               // ps.setString(3, fech);
                ps.executeUpdate();
                ps.close();
            }catch(SQLException ex){
                System.out.println("Error al eliminar Colaboracion");
                System.out.println(ex.getMessage());
                System.out.println(ex.getSQLState());
                System.out.println(ex.getErrorCode());
            }
    }
   
}
