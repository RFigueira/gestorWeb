package persistencia;

import genericos.ConfigurationBO;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import modelo.InstituicaoBO;

public class InstituicaoDAO extends Conexao {

    public InstituicaoDAO() throws SQLException {
        super();
    }

    public int inserir(InstituicaoBO instituicaoBO) throws SQLException, ParseException {
        this.setPreparedStatement("INSERT INTO instituicao(id_instituicao, nome, telefone, fundacao, rua, bairro, cep, cidade, estado, pais, numero ,status) VALUES (nextval('seq_instituicao'),?,?,?,?,?,?,?,?,?,?,?)");
        this.getPreparedStatement().setString(1, instituicaoBO.getNome());
        this.getPreparedStatement().setString(2, instituicaoBO.getTelefone());
        this.getPreparedStatement().setDate(3, new java.sql.Date(ConfigurationBO.stringParaData(instituicaoBO.getFundacao()).getTime()));
        this.getPreparedStatement().setString(4, instituicaoBO.getRua());
        this.getPreparedStatement().setString(5, instituicaoBO.getBairro());
        this.getPreparedStatement().setString(6, instituicaoBO.getCep());
        this.getPreparedStatement().setString(7, instituicaoBO.getCidade());
        this.getPreparedStatement().setString(8, instituicaoBO.getEstado());
        this.getPreparedStatement().setString(9, instituicaoBO.getPais());
        this.getPreparedStatement().setString(10, instituicaoBO.getNumero());
        this.getPreparedStatement().setInt(11, instituicaoBO.getStatus());
        return this.getPreparedStatement().executeUpdate();

    }

    public int alterar(InstituicaoBO instituicaoBO) throws SQLException, ParseException {
        this.setPreparedStatement("UPDATE instituicao SET nome = ?, telefone = ?, fundacao = ?, rua = ?, bairro = ?, cep = ?, cidade = ?, estado = ?, pais = ?, numero = ? ,status = ? WHERE id_instituicao = ?");
        this.getPreparedStatement().setString(1, instituicaoBO.getNome());
        this.getPreparedStatement().setString(2, instituicaoBO.getTelefone());
        this.getPreparedStatement().setDate(3, new java.sql.Date(ConfigurationBO.stringParaData(instituicaoBO.getFundacao()).getTime()));

        this.getPreparedStatement().setString(4, instituicaoBO.getRua());
        this.getPreparedStatement().setString(5, instituicaoBO.getBairro());
        this.getPreparedStatement().setString(6, instituicaoBO.getCep());
        this.getPreparedStatement().setString(7, instituicaoBO.getCidade());
        this.getPreparedStatement().setString(8, instituicaoBO.getEstado());
        this.getPreparedStatement().setString(9, instituicaoBO.getPais());
        this.getPreparedStatement().setString(10, instituicaoBO.getNumero());
        this.getPreparedStatement().setInt(11, instituicaoBO.getStatus());
        this.getPreparedStatement().setInt(12, instituicaoBO.getIdInstituicao());
        return this.getPreparedStatement().executeUpdate();
    }

    public int excluir(InstituicaoBO instituicaoBO) throws SQLException {
        this.setPreparedStatement("DELETE FROM instituicao WHERE id_instituicao = ?");
        this.getPreparedStatement().setInt(1, instituicaoBO.getIdInstituicao());
        return this.getPreparedStatement().executeUpdate();

    }

    public List<InstituicaoBO> listarInstituicao(String sql) throws SQLException, ParseException {
        List<InstituicaoBO> instituicoes = new ArrayList<InstituicaoBO>();
        this.setPreparedStatement(sql);
        this.setResultSet(this.getPreparedStatement().executeQuery());
        while (this.getResultSet().next()) {
            InstituicaoBO instituicaoBO = new InstituicaoBO();
            instituicaoBO.setIdInstituicao(this.getResultSet().getInt("id_instituicao"));
            instituicaoBO.setStatus(this.getResultSet().getInt("status"));
            instituicaoBO.setNome(this.getResultSet().getString("nome"));
            instituicaoBO.setRua(this.getResultSet().getString("rua"));
            instituicaoBO.setBairro(this.getResultSet().getString("bairro"));
            instituicaoBO.setCidade(this.getResultSet().getString("cidade"));
            instituicaoBO.setEstado(this.getResultSet().getString("estado"));
            instituicaoBO.setPais(this.getResultSet().getString("pais"));
            instituicaoBO.setTelefone(this.getResultSet().getString("telefone"));
            instituicaoBO.setCep(this.getResultSet().getString("cep"));
            instituicaoBO.setNumero(this.getResultSet().getString("numero"));
            instituicaoBO.setFundacao(ConfigurationBO.DataParaString(this.getResultSet().getDate("fundacao")));
            instituicoes.add(instituicaoBO);

        }

        return instituicoes;
    }

    public InstituicaoBO listarInstituicaoPorId(int idInstituicao) throws SQLException {
        InstituicaoBO instituicaoBO = new InstituicaoBO();
        this.setPreparedStatement(" SELECT id_instituicao, nome, cidade FROM instituicao WHERE id_instituicao = ? ");
        this.getPreparedStatement().setInt(1, idInstituicao);
        this.setResultSet(getPreparedStatement().executeQuery());
        while (this.getResultSet().next()) {
            instituicaoBO.setIdInstituicao(this.getResultSet().getInt("id_instituicao"));
            instituicaoBO.setNome(this.getResultSet().getString("nome"));
            instituicaoBO.setCidade(this.getResultSet().getString("cidade"));
        }

        return instituicaoBO;
    }

    //metodo para o conversor
    public InstituicaoBO carregarInstituicaoPorNome(String nome) throws SQLException {
        InstituicaoBO instituicaoBO = new InstituicaoBO();
        this.setPreparedStatement(" SELECT id_instituicao, nome FROM instituicao WHERE nome like ? ");
        this.getPreparedStatement().setString(1, nome + "%");
        this.setResultSet(getPreparedStatement().executeQuery());
        while (this.getResultSet().next()) {
            instituicaoBO.setIdInstituicao(this.getResultSet().getInt("id_instituicao"));
            instituicaoBO.setNome(this.getResultSet().getString("nome"));
        }

        return instituicaoBO;
    }

    //Caso consiga usar o metodo listar que recebe uma string sql, deletar esse metodo
    public List<InstituicaoBO> filtarInstituicoaPorPalavraChave(String palavraChave) throws SQLException, ParseException {
        List<InstituicaoBO> instituicoes = new ArrayList<InstituicaoBO>();
        this.setPreparedStatement("SELECT * FROM instituicao WHERE nome ILIKE ?");
        this.getPreparedStatement().setString(1, "%" + palavraChave + "%");

        this.setResultSet(this.getPreparedStatement().executeQuery());
        while (this.getResultSet().next()) {
            InstituicaoBO instituicaoBO = new InstituicaoBO();
            instituicaoBO.setIdInstituicao(this.getResultSet().getInt("id_instituicao"));
            instituicaoBO.setStatus(this.getResultSet().getInt("status"));
            instituicaoBO.setNome(this.getResultSet().getString("nome"));
            instituicaoBO.setRua(this.getResultSet().getString("rua"));
            instituicaoBO.setBairro(this.getResultSet().getString("bairro"));
            instituicaoBO.setCidade(this.getResultSet().getString("cidade"));
            instituicaoBO.setEstado(this.getResultSet().getString("estado"));
            instituicaoBO.setPais(this.getResultSet().getString("pais"));
            instituicaoBO.setTelefone(this.getResultSet().getString("telefone"));
            instituicaoBO.setCep(this.getResultSet().getString("cep"));
            instituicaoBO.setNumero(this.getResultSet().getString("numero"));
            instituicaoBO.setFundacao(ConfigurationBO.DataParaString(this.getResultSet().getDate("fundacao")));
            instituicoes.add(instituicaoBO);

        }

        return instituicoes;
    }
}
