package controle;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import modelo.InstituicaoBO;
import modelo.SetorBO;
import persistencia.InstituicaoDAO;
import persistencia.SetorDAO;

//@Named(value = "")
@ManagedBean
@RequestScoped
public class SetorMB {

    private SetorBO setorBO;
    private SetorDAO setorDAO;
    private List<SetorBO> setores;
    private String palavraChave;
    private InstituicaoDAO instituicaoDAO;
    private List<InstituicaoBO> instituicoes;
    private boolean apresentaMensagemFormulario = false;
    private final MensagemFormularioMB mensagemFormularioMB;
    private String mensageFormulario;
    private String classCss;

    public SetorMB() throws SQLException, ParseException {

        setorBO = new SetorBO();
        setorBO.setStatus(1);
        setorDAO = new SetorDAO();
        mensagemFormularioMB = new MensagemFormularioMB();
        setores = this.listarSetores();
        instituicaoDAO = new InstituicaoDAO();
        this.instituicoes = instituicaoDAO.listarInstituicao("SELECT * FROM instituicao");

    }

    //getsSeters
    public SetorBO getSetorBO() {
        return setorBO;
    }

    public void setSetorBO(SetorBO setorBO) {
        this.setorBO = setorBO;
    }

    public List<SetorBO> getSetores() throws SQLException {
        return setores;
    }

    public String getPalavraChave() {
        return palavraChave;
    }

    public void setPalavraChave(String palavraChave) {
        this.palavraChave = palavraChave;
    }

    public boolean isApresentaMensagemFormulario() {
        return apresentaMensagemFormulario;
    }

    public void setApresentaMensagemFormulario(boolean apresentaMensagemFormulario) {
        this.apresentaMensagemFormulario = apresentaMensagemFormulario;
    }

    public String getMensageFormulario() {
        return mensageFormulario;
    }

    public void setMensageFormulario(String mensageFormulario) {
        this.mensageFormulario = mensageFormulario;
    }

    public String getClassCss() {
        return classCss;
    }

    public void setClassCss(String classCss) {
        this.classCss = classCss;
    }

    public void novo() throws SQLException {

        setorBO = new SetorBO();
        setores = this.listarSetores();

    }

    public String salvar() throws SQLException {

        if (validadeFormulario()) {
            int retorno;
            if (setorBO.getIdSetor() > 0) {
                retorno = this.setorDAO.alterar(setorBO);
                if (retorno > 0) {
                    mensageFormulario = mensagemFormularioMB.apresentaMesagemSucessoAlteracao();
                    classCss = mensagemFormularioMB.monstraClass(2);
                } else {
                    mensageFormulario = mensagemFormularioMB.apresentaMensagemErroCadastro();
                    classCss = mensagemFormularioMB.monstraClass(1);
                }

            } else {
                retorno = this.setorDAO.inserir(setorBO);
                if (retorno > 0) {
                    mensageFormulario = mensagemFormularioMB.apresentaMensagemSucessoCadastro();
                    classCss = mensagemFormularioMB.monstraClass(2);

                } else {
                    mensageFormulario = mensagemFormularioMB.apresentaMensagemErroCadastro();
                    classCss = mensagemFormularioMB.monstraClass(1);
                }
            }
            apresentaMensagemFormulario = true;

        }
        return "CadastrarSetor";

    }

    public void excluir(SetorBO setorBO) throws SQLException {

        int retorno = this.setorDAO.excluir(setorBO);
        if (retorno > 0) {
            mensageFormulario = mensagemFormularioMB.apresentaMesagemSucessoExcluir();
            classCss = mensagemFormularioMB.monstraClass(2);

        } else {
            mensageFormulario = mensagemFormularioMB.apresentaMesagemErroExclusao();
            classCss = mensagemFormularioMB.monstraClass(1);

        }
        apresentaMensagemFormulario = true;

        setores = this.listarSetores();

    }

    public String editar(SetorBO setorBO) throws SQLException {
        this.setorBO = setorBO;
        return "CadastrarSetor";
    }

    public List<SetorBO> listarSetores() throws SQLException {

        return this.setorDAO.listarSetores("SELECT * FROM setor");

    }

    public SetorBO carregarSetoresPorNome() throws SQLException {

        return this.setorDAO.carregarSetorPorNome(this.setorBO.getNomeSetor());
    }

    public SetorBO carregarSetoresPorId() throws SQLException {

        return this.setorDAO.carregarSetorPorId(this.setorBO.getIdSetor());

    }

    public void ordenarPorID() throws SQLException {

        this.setores = this.setorDAO.listarSetores("SELECT * FROM setor ORDER BY id_setor");

    }

    public void ordenarPorStatus() throws SQLException {

        this.setores = this.setorDAO.listarSetores("SELECT * FROM setor ORDER BY status");
    }

    public List<InstituicaoBO> getInstituicoes() {
        return instituicoes;
    }

    public void setInstituicoes(List<InstituicaoBO> instituicoes) {
        this.instituicoes = instituicoes;
    }

    public List<SetorBO> filtarSetoresPorPalavraChave() throws SQLException {
        if (palavraChave != null && palavraChave.length() > 0) {
            return setores = this.setorDAO.filtrarSetorPorPalavraChave(palavraChave);
        }
        return setores;

    }

    private boolean validadeFormulario() {

        String msgErro = "";
        if (setorBO.getNomeSetor().length() < 1) {

            msgErro += "- O campo NOME deve ser preenchido.<br />";

        }
        if (setorBO.getDescricaoSetor().length() < 1) {
            msgErro += "- O campo DESCRIÇÃO deve ser preenchido.<br />";

        }

        if (setorBO.getInstituicaoBO().getIdInstituicao() == 0) {
            msgErro += "- Um Campus deve ser selecionado.<br />";

        }
        if (msgErro.length() > 0) {
            this.apresentarMensagemErroValidacao(msgErro);
            return false;
        }
        return true;
    }

    private void apresentarMensagemErroValidacao(String msgErro) {
        apresentaMensagemFormulario = true;
        mensageFormulario = mensagemFormularioMB.apresentaMensagemErroValidacao(msgErro);
        classCss = mensagemFormularioMB.monstraClass(1);
    }

}
