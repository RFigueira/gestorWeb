/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import genericos.EmailBO;
import java.sql.SQLException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import modelo.PessoaBO;
import org.apache.commons.mail.EmailException;
import persistencia.PessoaDAO;
import seguranca.Acesso;

/**
 *
 * @author rodrigo
 */
//@Named(value = "loginMB")
@ManagedBean
@RequestScoped
public class LoginMB {

    private PessoaBO pessoaBO;
    private PessoaDAO pessoaDAO;
    private Acesso acesso;
    private String mensagem;
    private boolean apresentaMensagemFormulario;
    private EmailBO emailbo;
    private final MensagemFormularioMB mensagemFormularioMB;
    private String mensageFormulario;
    private String classCss;

    public LoginMB() throws SQLException {
        pessoaDAO = new PessoaDAO();
        acesso = new Acesso();
        pessoaBO = carregaUsuario();
        emailbo = new EmailBO();
        mensagemFormularioMB = new MensagemFormularioMB();

    }

    //Gets e setrs
    public PessoaBO getPessoaBO() {
        return pessoaBO;
    }

    public void setPessoaBO(PessoaBO pessoaBO) {
        this.pessoaBO = pessoaBO;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
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

    public void enviarEmail() throws EmailException {
        this.emailbo.enviaEmailT();
    }

    //Metodos
    public String logar() throws SQLException, EmailException {

        pessoaBO = pessoaDAO.verificarCredencialPessoaPorUsuarioESenha(this.pessoaBO.getUsuario(), this.pessoaBO.getSenha());
        if (pessoaBO == null) {

            mensageFormulario = mensagemFormularioMB.erroDeLoginOuSenha();
            classCss = mensagemFormularioMB.monstraClass(3);
            apresentaMensagemFormulario = true;

            return "faces/Login"; //?faces-redirect=true

        }
        this.acesso.inserirUsuarioNaSession(pessoaBO);
       // this.enviarEmail();
        return "web/Dashbord?faces-redirect=true";

    }

    private PessoaBO carregaUsuario() {

        PessoaBO pessoaBO = this.acesso.carregarUsuarioDaSession();
        if (pessoaBO == null) {
            return new PessoaBO();
        } else {
            return pessoaBO;
        }

    }

    public String logout() {
        this.acesso.excluirUsuarioDaSession();
        return "/Login?faces-redirect=true";
    }

    private void apresentarMensagemErroValidacao(String msgErro) {
        apresentaMensagemFormulario = true;
        mensageFormulario = mensagemFormularioMB.apresentaMensagemErroValidacao(msgErro);
        classCss = mensagemFormularioMB.monstraClass(3);
    }
}
