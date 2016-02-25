/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import genericos.ConfigurationBO;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import modelo.AcaoBO;

/**
 *
 * @author rodrigo
 */
public class AcaoDAO extends Conexao {

    private ObjetivoDAO objetivoDAO;

    public AcaoDAO() throws SQLException {
        super();
        objetivoDAO = new ObjetivoDAO();

    }
 
    public int inserirAcao(AcaoBO acaoBO) throws SQLException, ParseException {

        this.setPreparedStatement("INSERT INTO acao (id_acao, nome, descricao, data_cadastro, status, id_objetivo) VALUES (nextval('Seq_acao'),?,?,?,?,?)");
        this.getPreparedStatement().setString(1, acaoBO.getNome());
        this.getPreparedStatement().setString(2, acaoBO.getDescricao());
        this.getPreparedStatement().setDate(3, new java.sql.Date(ConfigurationBO.stringParaData(acaoBO.getData()).getTime()));
        this.getPreparedStatement().setInt(4, acaoBO.getStatus());
        this.getPreparedStatement().setInt(5, acaoBO.getObjetivoBO().getIdObjetivo());

        return this.getPreparedStatement().executeUpdate();

    }

    public int alterarAcao(AcaoBO acaoBO) throws SQLException, ParseException {

        this.setPreparedStatement(" UPDATE acao SET nome = ?, descricao = ?, data_cadastro = ?, status = ?, id_objetivo = ? WHERE id_acao = ? ");
        this.getPreparedStatement().setString(1, acaoBO.getNome());
        this.getPreparedStatement().setString(2, acaoBO.getDescricao());
        this.getPreparedStatement().setDate(3, new java.sql.Date(ConfigurationBO.stringParaData(acaoBO.getData()).getTime()));

        this.getPreparedStatement().setInt(4, acaoBO.getStatus());
        this.getPreparedStatement().setInt(5, acaoBO.getObjetivoBO().getIdObjetivo());
        this.getPreparedStatement().setInt(6, acaoBO.getIdAcao());

        return this.getPreparedStatement().executeUpdate();

    }

    public int excluirAcao(AcaoBO acaoBO) throws SQLException {
        this.setPreparedStatement(" DELETE FROM acao WHERE id_acao = ? ");
        this.getPreparedStatement().setInt(1, acaoBO.getIdAcao());
        return this.getPreparedStatement().executeUpdate();

    }

    public List<AcaoBO> listarAcoes(String sql) throws SQLException, ParseException {
        List<AcaoBO> acoes = new ArrayList<AcaoBO>();
        this.setPreparedStatement(sql);
        this.setResultSet(this.getPreparedStatement().executeQuery());
        while (this.getResultSet().next()) {
            AcaoBO acaoBO = new AcaoBO();
            acaoBO.setIdAcao(this.getResultSet().getInt("id_acao"));
            acaoBO.setNome(this.getResultSet().getString("nome"));
            acaoBO.setDescricao(this.getResultSet().getString("descricao"));
            acaoBO.setObjetivoBO(objetivoDAO.listarInstituicaoPorId(this.getResultSet().getInt("id_objetivo")));
            acaoBO.setData(ConfigurationBO.DataParaString(this.getResultSet().getDate("data_cadastro")));
            acaoBO.setStatus(this.getResultSet().getInt("status"));
            acoes.add(acaoBO);
        }

        return acoes;
    }

    //lista com paginacao, parametro obrigatorios 
    public List<AcaoBO> listarAcoesPaginada(int limitador, int offset, String ordem, String sentido) throws SQLException, ParseException {
        List<AcaoBO> acoes = new ArrayList<AcaoBO>();
        this.setPreparedStatement("SELECT * FROM acao order by ? ? LIMIT ? OFFSET ?");
        this.getPreparedStatement().setString(1, ordem);
        this.getPreparedStatement().setString(2, sentido);
        this.getPreparedStatement().setInt(3, limitador);
        this.getPreparedStatement().setInt(4, offset);
        this.setResultSet(this.getPreparedStatement().executeQuery());
        while (this.getResultSet().next()) {
            AcaoBO acaoBO = new AcaoBO();
            acaoBO.setIdAcao(this.getResultSet().getInt("id_acao"));
            acaoBO.setNome(this.getResultSet().getString("nome"));
            acaoBO.setDescricao(this.getResultSet().getString("descricao"));
            acaoBO.setObjetivoBO(objetivoDAO.listarInstituicaoPorId(this.getResultSet().getInt("id_objetivo")));
            acaoBO.setData(ConfigurationBO.DataParaString(this.getResultSet().getDate("data_cadastro")));

            acaoBO.setStatus(this.getResultSet().getInt("status"));
            acoes.add(acaoBO);
        }

        return acoes;
    }

    public List<AcaoBO> filtarAcoesPorPalavraChave(String palavraChave) throws SQLException, ParseException {
        List<AcaoBO> acoes = new ArrayList<AcaoBO>();
        this.setPreparedStatement("SELECT * FROM acao where nome ILIKE ?");
        this.getPreparedStatement().setString(1, "%" + palavraChave + "%");

        this.setResultSet(this.getPreparedStatement().executeQuery());
        while (this.getResultSet().next()) {
            AcaoBO acaoBO = new AcaoBO();
            acaoBO.setIdAcao(this.getResultSet().getInt("id_acao"));
            acaoBO.setNome(this.getResultSet().getString("nome"));
            acaoBO.setDescricao(this.getResultSet().getString("descricao"));
            acaoBO.setObjetivoBO(objetivoDAO.listarInstituicaoPorId(this.getResultSet().getInt("id_objetivo")));
            acaoBO.setData(ConfigurationBO.DataParaString(this.getResultSet().getDate("data_cadastro")));

            acaoBO.setStatus(this.getResultSet().getInt("status"));
            acoes.add(acaoBO);
        }

        return acoes;
    }

}
