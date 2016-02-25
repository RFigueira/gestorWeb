/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.ObjetivoBO;

/**
 *
 * @author rodrigo
 */
public class ObjetivoDAO extends Conexao {

    private InstituicaoDAO instituicaoDAO;
    private ViewsDAO viewsDAO;

    public ObjetivoDAO() throws SQLException {
        super();
        instituicaoDAO = new InstituicaoDAO();
        viewsDAO = new ViewsDAO();
    }

    public int inserirObjetivo(ObjetivoBO objetivoBO) throws SQLException {

        this.setPreparedStatement("INSERT INTO objetivo (id_objetivo, nome, descricao, status, id_instituicao) VALUES (nextval('seq_objetivo'),?,?,?,?)");
        this.getPreparedStatement().setString(1, objetivoBO.getNome());
        this.getPreparedStatement().setString(2, objetivoBO.getDescricao());
        this.getPreparedStatement().setInt(3, objetivoBO.getStatus());
        this.getPreparedStatement().setInt(4, objetivoBO.getInstituicaoBO().getIdInstituicao());
        return this.getPreparedStatement().executeUpdate();

    }

    public int alterarObjetivo(ObjetivoBO objetivoBO) throws SQLException {
        this.setPreparedStatement(" UPDATE objetivo SET nome = ?, descricao = ?, status = ?, id_instituicao = ? WHERE id_objetivo = ? ");
        this.getPreparedStatement().setString(1, objetivoBO.getNome());
        this.getPreparedStatement().setString(2, objetivoBO.getDescricao());
        this.getPreparedStatement().setInt(3, objetivoBO.getStatus());
        this.getPreparedStatement().setInt(4, objetivoBO.getInstituicaoBO().getIdInstituicao());
        this.getPreparedStatement().setInt(5, objetivoBO.getIdObjetivo());
        return this.getPreparedStatement().executeUpdate();

    }

    public int excluirObjetivo(ObjetivoBO objetivoBO) throws SQLException {

        this.setPreparedStatement(" DELETE FROM objetivo WHERE id_objetivo = ? ");
        this.getPreparedStatement().setInt(1, objetivoBO.getIdObjetivo());
        return this.getPreparedStatement().executeUpdate();

    }

    public ObjetivoBO carregarObjetivoPorNome(String nome) throws SQLException {
        ObjetivoBO objetivoBO = new ObjetivoBO();
        this.setPreparedStatement(" SELECT id_objetivo, nome FROM objetivo WHERE nome = ? ");
        this.getPreparedStatement().setString(1, nome);
        this.setResultSet(this.getPreparedStatement().executeQuery());
        while (this.getResultSet().next()) {
            objetivoBO.setIdObjetivo(this.getResultSet().getInt("id_objetivo"));
            objetivoBO.setNome(this.getResultSet().getString("nome"));
        }
        return objetivoBO;
    }

    public List<ObjetivoBO> listarObjetivos(String sql) throws SQLException {

        List<ObjetivoBO> objetivos = new ArrayList<ObjetivoBO>();
        this.setPreparedStatement(sql);
        this.setResultSet(this.getPreparedStatement().executeQuery());
        while (this.getResultSet().next()) {
            ObjetivoBO objetivoBO = new ObjetivoBO();
            objetivoBO.setIdObjetivo(this.getResultSet().getInt("id_objetivo"));
            objetivoBO.setNome(this.getResultSet().getString("nome"));
            objetivoBO.setDescricao(this.getResultSet().getString("descricao"));
            objetivoBO.setStatus(this.getResultSet().getInt("status"));
            objetivoBO.setInstituicaoBO(instituicaoDAO.listarInstituicaoPorId(this.getResultSet().getInt("id_instituicao")));
            objetivoBO.setNumeroDeAcoes(viewsDAO.contarNumeroAcoesPorObjetivo(this.getResultSet().getInt("id_objetivo")));
            objetivoBO.setNumeroAcoesConcluidas(viewsDAO.contarNumeroAcoesPorObjetivoConcluidas(this.getResultSet().getInt("id_objetivo")));
            objetivoBO.setNumeroAcoesEmAndamentos(viewsDAO.contarNumeroAcoesPorObjetivoEmAndamentos(this.getResultSet().getInt("id_objetivo")));
            objetivoBO.setNumeroAcoesAtivas(viewsDAO.contarNumeroAcoesPorObjetivoAtivas(this.getResultSet().getInt("id_objetivo")));
            objetivoBO.setNumeroAcoesInativas(viewsDAO.contarNumeroAcoesPorObjetivoInativas(this.getResultSet().getInt("id_objetivo")));
            objetivos.add(objetivoBO);
        }
        return objetivos;
    }

    public ObjetivoBO filtrarObjetivoCompletoPorPalavraChave(String palavraChave) throws SQLException {

        ObjetivoBO objetivoBO = new ObjetivoBO();
        this.setPreparedStatement(" SELECT id_objetivo, nome, descricao, status FROM objetivo WHERE  nome ILIKE '?%'");
        this.getPreparedStatement().setString(1, palavraChave);
        this.setResultSet(getPreparedStatement().executeQuery());
        while (this.getResultSet().next()) {
            objetivoBO.setIdObjetivo(getResultSet().getInt("id_objetivo"));
            objetivoBO.setNome(getResultSet().getString("nome"));
            objetivoBO.setDescricao(getResultSet().getString("descricao"));
            objetivoBO.setStatus(getResultSet().getInt("status"));
        }
        return objetivoBO;
    }

    public ObjetivoBO listarInstituicaoPorId(int idObjetivos) throws SQLException {
        ObjetivoBO objetivoBO = new ObjetivoBO();
        this.setPreparedStatement(" SELECT id_objetivo, nome FROM objetivo WHERE id_objetivo = ? ");
        this.getPreparedStatement().setInt(1, idObjetivos);
        this.setResultSet(getPreparedStatement().executeQuery());
        while (this.getResultSet().next()) {
            objetivoBO.setIdObjetivo(this.getResultSet().getInt("id_objetivo"));
            objetivoBO.setNome(this.getResultSet().getString("nome"));
        }

        return objetivoBO;
    }

//    public ObjetivoBO filtrarObjetivoPorPalavraChaveP(String palavraChave) throws SQLException {
//
//        ObjetivoBO objetivoBO = new ObjetivoBO();
//        this.setPreparedStatement("SELECT id_objetivo, nome FROM objetivo WHERE  nome ILIKE  ? ");
//        this.getPreparedStatement().setString(1, palavraChave ,"%");
//        this.setResultSet(getPreparedStatement().executeQuery());
//        while (this.getResultSet().next()) {
//
//            objetivoBO.setIdObjetivo(getResultSet().getInt("id_objetivo"));
//            objetivoBO.setNome(getResultSet().getString("nome"));
//
//        }
//        return objetivoBO;
//    }

    public List<ObjetivoBO> filtrarObjetivoPorPalavraChave(String palavraChave) throws SQLException {

        List<ObjetivoBO> objetivos = new ArrayList<ObjetivoBO>();
        this.setPreparedStatement("SELECT * from objetivo  WHERE  nome ILIKE ?");
        this.getPreparedStatement().setString(1, "%"+palavraChave + "%");
        this.setResultSet(this.getPreparedStatement().executeQuery());
        while (this.getResultSet().next()) {
            ObjetivoBO objetivoBO = new ObjetivoBO();
            objetivoBO.setIdObjetivo(this.getResultSet().getInt("id_objetivo"));
            objetivoBO.setNome(this.getResultSet().getString("nome"));
            objetivoBO.setDescricao(this.getResultSet().getString("descricao"));
            objetivoBO.setStatus(this.getResultSet().getInt("status"));
            objetivoBO.setInstituicaoBO(instituicaoDAO.listarInstituicaoPorId(this.getResultSet().getInt("id_instituicao")));
            objetivoBO.setNumeroDeAcoes(viewsDAO.contarNumeroAcoesPorObjetivo(this.getResultSet().getInt("id_objetivo")));
            objetivoBO.setNumeroAcoesConcluidas(viewsDAO.contarNumeroAcoesPorObjetivoConcluidas(this.getResultSet().getInt("id_objetivo")));
            objetivoBO.setNumeroAcoesEmAndamentos(viewsDAO.contarNumeroAcoesPorObjetivoEmAndamentos(this.getResultSet().getInt("id_objetivo")));
            objetivoBO.setNumeroAcoesAtivas(viewsDAO.contarNumeroAcoesPorObjetivoAtivas(this.getResultSet().getInt("id_objetivo")));
            objetivoBO.setNumeroAcoesInativas(viewsDAO.contarNumeroAcoesPorObjetivoInativas(this.getResultSet().getInt("id_objetivo")));
            objetivos.add(objetivoBO);
        }
        return objetivos;

    }

}
