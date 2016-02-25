/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.PessoaBO;

/**
 *
 * @author rodrigo
 */
public class PessoaDAO extends Conexao {

    private SetorDAO setorDAO;

    public PessoaDAO() throws SQLException {

        super();
        setorDAO = new SetorDAO();

    }

    public int alterarPessoaUsuariaSistema(PessoaBO pessoaBO) throws SQLException {
        this.setPreparedStatement("UPDATE pessoa SET nome = ?, email = ?, telefone = ?, login = ?, senha = ?, status = ?, id_setor = ?, tipo_pessoa = 1 WHERE id_pessoa = ?");
        this.getPreparedStatement().setString(1, pessoaBO.getNome());
        this.getPreparedStatement().setString(2, pessoaBO.getEmail());
        this.getPreparedStatement().setString(3, pessoaBO.getTelefone());
        this.getPreparedStatement().setString(4, pessoaBO.getUsuario());
        this.getPreparedStatement().setString(5, pessoaBO.getSenha());
        this.getPreparedStatement().setInt(6, pessoaBO.getStatus());
        this.getPreparedStatement().setInt(7, pessoaBO.getSetorBO().getIdSetor());
        getPreparedStatement().setInt(8, pessoaBO.getIdPessoa());
        return this.getPreparedStatement().executeUpdate();
    }

    public int inserirPessoaUsuariaSistema(PessoaBO pessoaBO) throws SQLException {
        this.setPreparedStatement("INSERT INTO pessoa(id_pessoa, nome, email, telefone, login, senha, status, id_setor, tipo_pessoa ) VALUES (nextval('seq_pessoa'),?,?,?,?,?,?,?,?) ");
        this.getPreparedStatement().setString(1, pessoaBO.getNome());
        this.getPreparedStatement().setString(2, pessoaBO.getEmail());
        this.getPreparedStatement().setString(3, pessoaBO.getTelefone());
        this.getPreparedStatement().setString(4, pessoaBO.getUsuario());
        this.getPreparedStatement().setString(5, pessoaBO.getSenha());
        this.getPreparedStatement().setInt(6, pessoaBO.getStatus());
        this.getPreparedStatement().setInt(7, pessoaBO.getSetorBO().getIdSetor());
        this.getPreparedStatement().setInt(8, 1);

        return this.getPreparedStatement().executeUpdate();
    }

    public int alterarPessoaNaoUsuariaSistema(PessoaBO pessoaBO) throws SQLException {
        this.setPreparedStatement("UPDATE pessoa SET nome = ?, email = ?, telefone = ?, status = ?, id_setor = ?, tipo_pessoa = 2 WHERE id_pessoa = ?");
        this.getPreparedStatement().setString(1, pessoaBO.getNome());
        this.getPreparedStatement().setString(2, pessoaBO.getEmail());
        this.getPreparedStatement().setString(3, pessoaBO.getTelefone());
        this.getPreparedStatement().setInt(4, pessoaBO.getStatus());
        this.getPreparedStatement().setInt(5, pessoaBO.getSetorBO().getIdSetor());
        getPreparedStatement().setInt(6, pessoaBO.getIdPessoa());
        return this.getPreparedStatement().executeUpdate();
    }

    public int inserirPessoaNaoUsuariaSistema(PessoaBO pessoaBO) throws SQLException {
        this.setPreparedStatement("INSERT INTO pessoa(id_pessoa, nome, email, telefone, status, id_setor, tipo_pessoa ) VALUES (nextval('seq_pessoa'),?,?,?,?,?,?) ");
        this.getPreparedStatement().setString(1, pessoaBO.getNome());
        this.getPreparedStatement().setString(2, pessoaBO.getEmail());
        this.getPreparedStatement().setString(3, pessoaBO.getTelefone());
        this.getPreparedStatement().setInt(4, pessoaBO.getStatus());
        this.getPreparedStatement().setInt(5, pessoaBO.getSetorBO().getIdSetor());
        this.getPreparedStatement().setInt(6, 2);

        return this.getPreparedStatement().executeUpdate();
    }

    public int excluirPessoa(PessoaBO pessoaBO) throws SQLException {

        this.setPreparedStatement(" DELETE FROM pessoa WHERE id_pessoa = ? ");
        this.getPreparedStatement().setInt(1, pessoaBO.getIdPessoa());
        return this.getPreparedStatement().executeUpdate();

    }

    public PessoaBO carregarPessoaPorNome(String nome) throws SQLException {
        PessoaBO pessoaBO = new PessoaBO();

        this.setPreparedStatement(" SELECT id_pessoa, nome FROM pessoa WHERE nome = ? ");
        this.getPreparedStatement().setString(1, nome);
        this.setResultSet(getPreparedStatement().executeQuery());
        while (this.getResultSet().next()) {
            pessoaBO.setIdPessoa(getResultSet().getInt("id_pessoa"));
            pessoaBO.setNome(getResultSet().getString("nome"));
        }
        return pessoaBO;
    }

    public PessoaBO verificarCredencialPessoaPorUsuarioESenha(String usuario, String senha) throws SQLException {
        PessoaBO pessoaBO = null;

        this.setPreparedStatement(" SELECT * FROM pessoa WHERE login = ? AND senha = ? AND tipo_pessoa = 1 ");
        this.getPreparedStatement().setString(1, usuario);
        this.getPreparedStatement().setString(2, senha);
        this.setResultSet(getPreparedStatement().executeQuery());
        if (this.getResultSet().next()) {
            pessoaBO = new PessoaBO();
            pessoaBO.setIdPessoa(this.getResultSet().getInt("id_pessoa"));
            pessoaBO.setNome(this.getResultSet().getString("nome"));
        }
        return pessoaBO;
    }

    public boolean verificarUsuarioESenha(PessoaBO pessoaBO) throws SQLException {

        PessoaBO usuario = this.verificarCredencialPessoaPorUsuarioESenha(pessoaBO.getUsuario(), pessoaBO.getSenha());
        if (usuario != null) {
            return true;
        } else {
            return false;
        }

    }

    public List<PessoaBO> filtarPessoaPorPalavraChave(String palavraChave) throws SQLException {
        List<PessoaBO> pessoas = new ArrayList<PessoaBO>();
        this.setPreparedStatement("SELECT id_pessoa, nome, telefone, email, login, status, id_setor FROM pessoa where nome ilike ?");
        this.getPreparedStatement().setString(1, "%" + palavraChave + "%");

        this.setResultSet(this.getPreparedStatement().executeQuery());
        while (this.getResultSet().next()) {
            PessoaBO pessoaBO = new PessoaBO();
            pessoaBO.setIdPessoa(this.getResultSet().getInt("id_pessoa"));
            pessoaBO.setNome(this.getResultSet().getString("nome"));
            pessoaBO.setTelefone(this.getResultSet().getString("telefone"));
            pessoaBO.setEmail(this.getResultSet().getString("email"));
            pessoaBO.setUsuario(this.getResultSet().getString("login"));
            pessoaBO.setStatus(this.getResultSet().getInt("status"));
            pessoaBO.setSetorBO(setorDAO.carregarSetorPorId(this.getResultSet().getInt("id_setor")));
            pessoas.add(pessoaBO);
            // EX.           setorBO.setInstituicaoBO(instituicaoDAO.listarInstituicaoPorId(this.getResultSet().getInt("id_instituicao")));

        }
        return pessoas;
    }

    public List<PessoaBO> listarPessoas() throws SQLException {
        List<PessoaBO> pessoas = new ArrayList<PessoaBO>();
        this.setPreparedStatement("SELECT id_pessoa, nome, telefone, email, login, status, id_setor FROM pessoa");
        this.setResultSet(this.getPreparedStatement().executeQuery());
        while (this.getResultSet().next()) {
            PessoaBO pessoaBO = new PessoaBO();
            pessoaBO.setIdPessoa(this.getResultSet().getInt("id_pessoa"));
            pessoaBO.setNome(this.getResultSet().getString("nome"));
            pessoaBO.setTelefone(this.getResultSet().getString("telefone"));
            pessoaBO.setEmail(this.getResultSet().getString("email"));
            pessoaBO.setUsuario(this.getResultSet().getString("login"));
            pessoaBO.setStatus(this.getResultSet().getInt("status"));
            pessoaBO.setSetorBO(setorDAO.carregarSetorPorId(this.getResultSet().getInt("id_setor")));
            pessoas.add(pessoaBO);
            // EX.           setorBO.setInstituicaoBO(instituicaoDAO.listarInstituicaoPorId(this.getResultSet().getInt("id_instituicao")));

        }
        return pessoas;
    }

}
