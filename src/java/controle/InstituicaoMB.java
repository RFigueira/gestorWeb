/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import genericos.ConfigurationBO;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import modelo.InstituicaoBO;
import persistencia.InstituicaoDAO;

/**
 *
 * @author rodrigo
 */
//@Named(value = "instituicaoMB")
@ManagedBean

@RequestScoped
public class InstituicaoMB {

    private InstituicaoBO instituicaoBO;
    private List<InstituicaoBO> instituicoes;
    private InstituicaoDAO instituicaoDAO;
    private String palavraChave;
    private boolean apresentaMensagemFormulario;
    private final MensagemFormularioMB mensagemFormularioMB;
    private String mensageFormulario;
    private String classCss;
    private String dataFundacao;

    public InstituicaoMB() throws SQLException, ParseException {

        instituicaoBO = new InstituicaoBO();
        instituicaoBO.setStatus(1);
        instituicaoDAO = new InstituicaoDAO();
        instituicoes = this.listarInstituicoes();
        mensagemFormularioMB = new MensagemFormularioMB();

    }

    public String getDataFundacao() {
        return dataFundacao;
    }

    public void setDataFundacao(String dataFundacao) {
        this.dataFundacao = dataFundacao;
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

    public InstituicaoBO getInstituicaoBO() {
        return instituicaoBO;
    }

    public void setInstituicaoBO(InstituicaoBO instituicaoBO) {
        this.instituicaoBO = instituicaoBO;
    }

    public List<InstituicaoBO> getInstituicoes() {
        return instituicoes;
    }

    public void setInstituicoes(List<InstituicaoBO> instituicoes) {
        this.instituicoes = instituicoes;
    }

    public String getPalavraChave() {
        return palavraChave;
    }

    public void setPalavraChave(String palavraChave) {
        this.palavraChave = palavraChave;
    }

    public void novo() throws SQLException, ParseException {

        instituicaoBO = new InstituicaoBO();
        instituicoes = this.listarInstituicoes();

    }

    public String salvar() throws SQLException, ParseException {

        if (validadeFormulario()) {
            int retorno;
            if (instituicaoBO.getIdInstituicao() > 0) {
                retorno = this.instituicaoDAO.alterar(instituicaoBO);
                if (retorno > 0) {
                    mensageFormulario = mensagemFormularioMB.apresentaMesagemSucessoAlteracao();
                    classCss = mensagemFormularioMB.monstraClass(2);
                } else {
                    mensageFormulario = mensagemFormularioMB.apresentaMensagemErroCadastro();
                    classCss = mensagemFormularioMB.monstraClass(1);

                }
            } else {
                retorno = this.instituicaoDAO.inserir(instituicaoBO);
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
        return "CadastrarInstituicao";//?faces-redirect=true";
    }

    public void excluir(InstituicaoBO instituicaoBO) throws SQLException, ParseException {

        int retorno;

        retorno = this.instituicaoDAO.excluir(instituicaoBO);
        if (retorno > 0) {

        } else {

        }
        instituicoes = this.listarInstituicoes();
    }

    public String editar(InstituicaoBO instituicaoBO) throws SQLException {

        this.instituicaoBO = instituicaoBO;

        return "CadastrarInstituicao"; //?faces-redirect=true não edita
    }

    public List<InstituicaoBO> listarInstituicoes() throws SQLException, ParseException {

        return this.instituicaoDAO.listarInstituicao("SELECT * FROM instituicao");
    }

    public List<InstituicaoBO> filtarInstituicaoPorPalavraChave() throws SQLException, ParseException {
        if (palavraChave != null && palavraChave.length() > 0) {
            return instituicoes = this.instituicaoDAO.filtarInstituicoaPorPalavraChave(palavraChave);
        }
        return instituicoes;

    }

    public boolean validadeFormulario() {

        String msgErro = "";
        if (instituicaoBO.getNome().length() < 1) {

            msgErro += "- O campo NOME deve ser preenchido.<br />";

        }

        if (instituicaoBO.getFundacao() == null) {
            msgErro += "- O campo FUNDAÇÃO deve ser preenchido.<br />";

        } else {
            try {
                Date hoje = new Date();
                Date date = ConfigurationBO.stringParaData(instituicaoBO.getFundacao());
                hoje.parse(instituicaoBO.getFundacao());
                if (date.getTime() > hoje.getTime()) {
                    msgErro += "- O campo FUNDAÇÃO deve ser preenchido com uma data menor que a de hoje.<br />";
                }

            } catch (Exception e) {
                msgErro += "- O campo FUNDAÇÃO deve ser preenchido com uma data válida.<br />";
            }
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
