package persistencia;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.SetorBO;

public class SetorDAO extends Conexao {

    private InstituicaoDAO instituicaoDAO;

    public SetorDAO() throws SQLException {

        super();
        instituicaoDAO = new InstituicaoDAO();
        

    }

    public int inserir(SetorBO setorBO) throws SQLException {

        this.setPreparedStatement(" INSERT INTO setor(id_setor, nome_setor, descricao_setor, status, id_instituicao) VALUES(nextval('seq_setor'), ?, ?, ?, ?) ");
        this.getPreparedStatement().setString(1, setorBO.getNomeSetor());
        this.getPreparedStatement().setString(2, setorBO.getDescricaoSetor());
        this.getPreparedStatement().setInt(3, setorBO.getStatus());
        this.getPreparedStatement().setInt(4, setorBO.getInstituicaoBO().getIdInstituicao());
        return this.getPreparedStatement().executeUpdate();
       
    }

    public int alterar(SetorBO setorBO) throws SQLException {

        this.setPreparedStatement(" UPDATE  setor SET nome_setor = ? , descricao_setor = ? , status = ? , id_instituicao = ? WHERE id_setor = ? ");
        this.getPreparedStatement().setString(1, setorBO.getNomeSetor());
        this.getPreparedStatement().setString(2, setorBO.getDescricaoSetor());
        this.getPreparedStatement().setInt(3, setorBO.getStatus());
        this.getPreparedStatement().setInt(4, setorBO.getInstituicaoBO().getIdInstituicao());
        this.getPreparedStatement().setInt(5, setorBO.getIdSetor());
        return this.getPreparedStatement().executeUpdate();

    }

    public int excluir(SetorBO setorBO) throws SQLException {

        this.setPreparedStatement(" DELETE FROM setor WHERE id_setor = ? ");
        this.getPreparedStatement().setInt(1, setorBO.getIdSetor());
        return this.getPreparedStatement().executeUpdate();

    }

    public SetorBO carregarSetorPorId(int idSetor) throws SQLException {

        SetorBO setorBO = new SetorBO();
        this.setPreparedStatement(" SELECT id_setor, nome_setor, descricao_setor, status, id_instituicao FROM setor WHERE id_setor = ?");
        this.getPreparedStatement().setInt(1, idSetor);
        this.setResultSet(getPreparedStatement().executeQuery());
        while (this.getResultSet().next()) {

            setorBO.setIdSetor(getResultSet().getInt("id_setor"));
            setorBO.setNomeSetor(getResultSet().getString("nome_setor"));
            setorBO.setDescricaoSetor(getResultSet().getString("descricao_setor"));
            setorBO.setStatus(getResultSet().getInt("status"));
            setorBO.setInstituicaoBO(instituicaoDAO.listarInstituicaoPorId(this.getResultSet().getInt("id_instituicao")));

        }
        return setorBO;
    }

    public SetorBO carregarSetorPorNome(String nomeSetor) throws SQLException {

        SetorBO setorBO = new SetorBO();
        this.setPreparedStatement(" SELECT id_setor, nome_setor FROM setor WHERE nome_setor = ?");
        this.getPreparedStatement().setString(1, nomeSetor);
        this.setResultSet(getPreparedStatement().executeQuery());
        while (this.getResultSet().next()) {
            setorBO.setIdSetor(getResultSet().getInt("id_setor"));
            setorBO.setNomeSetor(getResultSet().getString("nome_setor"));
        }
        
        return setorBO;
    }

//    public SetorBO filtrarSetorPorPalavraChave(String palavraChave) throws SQLException {
//
//        SetorBO setorBO = new SetorBO();
//        this.setPreparedStatement("SELECT id_setor, nome_setor, descricao_setor, status, id_instituicao FROM setor WHERE ILIKE descricao_setor = '%?%'");
//        this.getPreparedStatement().setString(1, palavraChave);
//        this.setResultSet(getPreparedStatement().executeQuery());
//        while (this.getResultSet().next()) {
//            setorBO.setIdSetor(getResultSet().getInt("id_setor"));
//            setorBO.setNomeSetor(getResultSet().getString("nome_setor"));
//            setorBO.setDescricaoSetor(getResultSet().getString("descricao_setor"));
//            setorBO.setStatus(getResultSet().getInt("status"));
//            setorBO.setInstituicaoBO(instituicaoDAO.listarInstituicaoPorId(this.getResultSet().getInt("id_instituicao")));
//        }
//        return setorBO;
//    }

    public List<SetorBO> listarSetores(String sql) throws SQLException {

        List<SetorBO> setores = new ArrayList<SetorBO>();
        this.setPreparedStatement(sql);
        this.setResultSet(this.getPreparedStatement().executeQuery());
        while (this.getResultSet().next()) {
            SetorBO setorBO = new SetorBO();
            setorBO.setIdSetor(this.getResultSet().getInt("id_setor"));
            setorBO.setNomeSetor(this.getResultSet().getString("nome_setor"));
            setorBO.setDescricaoSetor(this.getResultSet().getString("descricao_setor"));
            setorBO.setStatus(this.getResultSet().getInt("status"));
            setorBO.setInstituicaoBO(instituicaoDAO.listarInstituicaoPorId(this.getResultSet().getInt("id_instituicao")));
            setores.add(setorBO);

        }

        return setores;
    }
    
        public List<SetorBO> filtrarSetorPorPalavraChave(String palavraChave) throws SQLException {

        List<SetorBO> setores = new ArrayList<SetorBO>();
        this.setPreparedStatement("SELECT * FROM setor WHERE nome_setor ILIKE ? ");
                this.getPreparedStatement().setString(1, "%"+palavraChave + "%");

        this.setResultSet(this.getPreparedStatement().executeQuery());
        while (this.getResultSet().next()) {
            SetorBO setorBO = new SetorBO();
            setorBO.setIdSetor(this.getResultSet().getInt("id_setor"));
            setorBO.setNomeSetor(this.getResultSet().getString("nome_setor"));
            setorBO.setDescricaoSetor(this.getResultSet().getString("descricao_setor"));
            setorBO.setStatus(this.getResultSet().getInt("status"));
            setorBO.setInstituicaoBO(instituicaoDAO.listarInstituicaoPorId(this.getResultSet().getInt("id_instituicao")));
            setores.add(setorBO);

        }

        return setores;
    }

}
