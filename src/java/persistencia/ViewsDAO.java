/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.sql.SQLException;

/**
 *
 * @author rodrigo
 */
public class ViewsDAO extends Conexao{
    
    
    
    public ViewsDAO() throws SQLException{
    
    super();
    }
    
    public int contarNumeroAcoesPorObjetivo(int idObjetivo) throws SQLException {
        // AcaoBO acaoBO = new AcaoBO();
        int i = 0;
        this.setPreparedStatement(" Select COUNT(*) as total_acoes from acao  where id_objetivo = ? Group by id_objetivo ");
        this.getPreparedStatement().setInt(1, idObjetivo);
        this.setResultSet(this.getPreparedStatement().executeQuery());
        if (this.getResultSet().next()) {
            
            i = this.getResultSet().getInt("total_acoes");
        }
        return i;
        

    }
    
    public int contarNumeroAcoesPorObjetivoConcluidas(int idObjetivo) throws SQLException {
        // AcaoBO acaoBO = new AcaoBO();
        int i = 0;
        this.setPreparedStatement(" Select COUNT(*) as total_acoes_concluidas from acao  where id_objetivo = ? and status = 4 Group by id_objetivo ");
        this.getPreparedStatement().setInt(1, idObjetivo);
        this.setResultSet(this.getPreparedStatement().executeQuery());
        if (this.getResultSet().next()) {
            
            i = this.getResultSet().getInt("total_acoes_concluidas");
        }
        return i;

        //bairro ivone, 3 esq 1d
    }
    
    public int contarNumeroAcoesPorObjetivoEmAndamentos(int idObjetivo) throws SQLException {
        // AcaoBO acaoBO = new AcaoBO();
        int i = 0;
        this.setPreparedStatement(" Select COUNT(*) as total_acoes_em_andamento from acao  where id_objetivo = ? and status = 3 Group by id_objetivo ");
        this.getPreparedStatement().setInt(1, idObjetivo);
        this.setResultSet(this.getPreparedStatement().executeQuery());
        if (this.getResultSet().next()) {
            
            i = this.getResultSet().getInt("total_acoes_em_andamento");
        }
        return i;

        //bairro ivone, 3 esq 1d
    }
    
        public int contarNumeroAcoesPorObjetivoAtivas(int idObjetivo) throws SQLException {
        // AcaoBO acaoBO = new AcaoBO();
        int i = 0;
        this.setPreparedStatement(" Select COUNT(*) as total_acoes_ativas from acao  where id_objetivo = ? and status = 1 Group by id_objetivo ");
        this.getPreparedStatement().setInt(1, idObjetivo);
        this.setResultSet(this.getPreparedStatement().executeQuery());
        if (this.getResultSet().next()) {
            
            i = this.getResultSet().getInt("total_acoes_ativas");
        }
        return i;

        //bairro ivone, 3 esq 1d
    }
                public int contarNumeroAcoesPorObjetivoInativas(int idObjetivo) throws SQLException {
        // AcaoBO acaoBO = new AcaoBO();
        int i = 0;
        this.setPreparedStatement(" Select COUNT(*) as total_acoes_inativas from acao  where id_objetivo = ? and status = 2 Group by id_objetivo ");
        this.getPreparedStatement().setInt(1, idObjetivo);
        this.setResultSet(this.getPreparedStatement().executeQuery());
        if (this.getResultSet().next()) {
            
            i = this.getResultSet().getInt("total_acoes_inativas");
        }
        return i;

        //bairro ivone, 3 esq 1d
    }
            

}
