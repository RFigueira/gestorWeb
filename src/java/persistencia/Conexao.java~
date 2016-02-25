package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conexao {

    private Connection con;
    private PreparedStatement pstm;
    private ResultSet rs;

//   // // Banco Local
    public Conexao() throws SQLException {
        DriverManager.registerDriver(new org.postgresql.Driver());
        this.con = DriverManager.getConnection("jdbc:postgresql://localhost/gestor", "postgres", "300591");
    }

    public Connection getConexao() throws SQLException {
        if (this.con == null) {
            this.con = DriverManager.getConnection("jdbc:postgresql://localhost/gestor", "postgres", "300591");
        }
        return this.con;
    }

    public PreparedStatement getPreparedStatement() {
        return pstm;
    }

    public void setPreparedStatement(String sql) throws SQLException {
        this.pstm = con.prepareStatement(sql);
    }

    public ResultSet getResultSet() {
        return this.rs;
    }

    public void setResultSet(ResultSet rs) {
        this.rs = rs;
    }

}

