package Persistencia;

import java.sql.*;
import java.util.ArrayList;

public class manejaExperto {

    // Se crea un objeto de tipo "conexionOracle"
    conexionOracle conexion = null;

    // Se crea el PreparedStatement como atributo de la clase manejaExperto para
    // utilizarlo en los diferentes métodos
    PreparedStatement ps = null;

    /**
     * Implementa operaciones sobre la tabla EXPERTO
     *
     * @param c conexión con Oracle
     */
    public manejaExperto(conexionOracle c) {
        conexion = c;
    }

    /**
     * Se desea añadir al proyecto la posibilidad de insertar datos en la tabla
     * EXPERTO. Para ello, se debe añadir a la clase manejaExperto, el siguiente
     * método: insertaExperto (experto) Este método inserta en la tabla EXPERTO
     * los datos almacenados en un objeto de tipo experto. La correspondiente
     * excepción debe avisar de un posible error al realizar dicha inserción en
     * la tabla. Los datos se introducirán por teclado.
     *
     * @param pais
     * @return
     * @throws SQLException si ocurre alguna anomalía
     */
    public ArrayList<experto> listaExpertosPorPais(String pais) throws SQLException {
        ArrayList<experto> lista = new ArrayList();

        try {
            Statement stmt = conexion.conn.createStatement();
            PreparedStatement prep = conexion.conn.prepareStatement("select * from EXPERTO where pais = ? ORDER BY CODEXPERTO");
            prep.setString(1, pais);
            ResultSet res = prep.executeQuery();
            while (res.next()) {
                experto exp = new experto(res.getString(1),
                        res.getString(2),
                        res.getString(3),
                        res.getString(4),
                        res.getString(5));
                lista.add(exp);
            }
            stmt.close();
        } catch (SQLException ex) {
            System.out.println("Ha ocurrido un error en listaexpertoporpais: " + ex.getMessage());
            System.out.println(ex.getSQLState());
            System.out.println(ex.getErrorCode());
        }
        return lista;
    }

    /**
     * Comprueba si existe un experto
     *
     * @param codExperto
     * @return
     * @throws SQLException si ocurre alguna anomalía
     */
    public boolean existeExperto(String codExperto) throws SQLException {
        boolean existe = false;
        try {
            Statement stmt;
            stmt = conexion.conn.createStatement();
            ps = conexion.conn.prepareStatement("select * from EXPERTO where codEXPERTO = ?");
            ps.setString(1, codExperto);
            ResultSet res = ps.executeQuery();
            if (res.next() == true) {
                existe = true;
            }
            stmt.close();

        } catch (SQLException ex) {
            System.out.println("Ha ocurrido un error en existeExperto: " + ex.getMessage());
            System.out.println(ex.getSQLState());
            System.out.println(ex.getErrorCode());
        }
        return existe;
    }

    /**
     * inserta un experto
     *
     * @param exp
     * @throws SQLException si ocurre alguna anomalía
     */
    public void insertaExperto(experto exp) throws SQLException {
        try {
            //Statement stmt = conexion.conn.createStatement();
            ps = conexion.conn.prepareStatement("insert into EXPERTO values (?,?,?,?,?)");
            ps.setString(1, exp.getCodExperto());
            ps.setString(2, exp.getNombre());
            ps.setString(3, exp.getPais());
            ps.setString(4, exp.getSexo());
            ps.setString(5, exp.getEspecialidad());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException exc) {
            System.out.println("Error al Insertar en la tabla Experto: " + exc.getMessage());
            System.out.println(exc.getSQLState());
            System.out.println(exc.getErrorCode());
        }
    }

    public ArrayList<experto> listaExpertos() throws SQLException {
        ArrayList<experto> lista = new ArrayList();

        try {
            Statement stmt = conexion.conn.createStatement();
            ps = conexion.conn.prepareStatement("select * from EXPERTO ORDER BY CODEXPERTO");
            ResultSet res = ps.executeQuery();
            while (res.next()) {
                experto exp = new experto(res.getString(1),
                        res.getString(2),
                        res.getString(3),
                        res.getString(4),
                        res.getString(5));
                lista.add(exp);
            }
            stmt.close();
        } catch (SQLException ex) {
            System.out.println("Ha ocurrido un error en listaexpertoporpais: " + ex.getMessage());
            System.out.println(ex.getSQLState());
            System.out.println(ex.getErrorCode());
        }
        return lista;
    }

    public int sexoExperto(String sexo) throws SQLException {
        Statement stmt = conexion.conn.createStatement();
        ps = conexion.conn.prepareStatement("SELECT * FROM EXPERTO WHERE SEXO = ? ");

        if (sexo.equals("f"))///Si es una f minuscula, la cambiamos a mayuscula
        {
            sexo = "F";
        }
        if (sexo.equals("m")) {
            sexo = "M";
        }

        ps.setString(1, sexo);
        ResultSet res = ps.executeQuery();
        int cuenta = 0;
        while (res.next()) {
            cuenta++;
        }
        return cuenta;
    }

    public void eliminarExperto(String codigoExp) throws SQLException {
        try {
            ps = conexion.conn.prepareStatement("DELETE FROM EXPERTO WHERE CODEXPERTO = ?");
            ps.setString(1, codigoExp);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error al eliminar Experto");
            System.out.println(ex.getMessage());
            System.out.println(ex.getSQLState());
            System.out.println(ex.getErrorCode());
        }

    }
}
