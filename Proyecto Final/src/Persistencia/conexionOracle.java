package Persistencia;

import java.sql.*;

public class conexionOracle {

    Connection conn = null;

    /**
     * Establece la conexión con el servidor
     *
     * @throws Exception si ocurre cualquier anormalidad
     */
    public conexionOracle() throws Exception {
        Class.forName("oracle.jdbc.OracleDriver");
        conn = DriverManager.getConnection("jdbc:oracle:thin:@172.17.20.75:1521:rabida", "DDSI_049", "DDSI_049");
    }
    /**
     * Implementa la desconexión con el servidor
     *
     * @throws SQLException si ocurre cualquier anormalidad
     */
    public void desconexion() throws SQLException {
        conn.close();
    }

    /**
     * Inicia una transacción
     *
     * @throws SQLException si ocurre cualquier anormalidad
     */
    public void inicioTransaccion() throws SQLException {
        conn.setAutoCommit(false);
    }

    /**
     * Finaliza una transacción con commint
     *
     * @throws SQLException si ocurre cualquier anormalidad
     */
    public void finTransaccionCommit() throws SQLException {
        /*
        Si todo va OK , se inserta
        */
        conn.commit();
        conn.setAutoCommit(true);
    }

    /**
     * Finaliza una transacción con rollback
     *
     * @throws SQLException si ocurre cualquier anormalidad
     */
    public void finTransaccionRollback() throws SQLException {
        conn.rollback();
    }

}
