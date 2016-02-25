/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author rodrigo
 */
@ManagedBean
@RequestScoped
public class MensagemFormularioMB {

    private String classCss;
    private String timpoMensagem;
    private String mensagem;
    private boolean  apresentaMensagemFormulario;

    public MensagemFormularioMB() {

    }
    

    public String getClassCss() {
        return classCss;
    }

    public void setClassCss(String classCss) {
        this.classCss = classCss;
    }

    public String getTimpoMensagem() {
        return timpoMensagem;
    }

    public void setTimpoMensagem(String timpoMensagem) {
        this.timpoMensagem = timpoMensagem;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String apresentaMensagemSucessoCadastro() {
        //classCss = this.monstraClass(2);
        return "Dados Cadastrados com sucesso";

    }

    public String apresentaMensagemErroCadastro() {
        //  classCss = this.monstraClass(1);
        return "Erro ao cadastrar dados.";
    }

    public String apresentaMesagemSucessoAlteracao() {
        //classCss = this.monstraClass(2);
        return "Dados alterados com sucesso";
    }

    public String erroDeLoginOuSenha() {
        return "Login ou Senha inválida, tenta novamente.";
    }

    public String apresentaMesagemSucessoExcluir() {
        //classCss = this.monstraClass(2);
        return "Item Excluído com sucesso.";
    }

    public String apresentaMesagemErroExclusao() {
        //classCss = this.monstraClass(2);
        return "Erro ao excluir item, recarregue a página e tente novamente.";
    }

    //1 - erro
    //2 - sucesso
    //3 - erro em paginas cinzas
    public String monstraClass(int tipoMensagem) {
        if (tipoMensagem == 1) {
            return "bs-callout bs-callout-danger";
        } else if (tipoMensagem == 2) {
            return "bs-callout bs-callout-info";
        } 
        else if (tipoMensagem == 3) {
            return "bs-callout-cinza bs-callout-danger";
        }
        return "";
    }

    String apresentaMensagemErroValidacao(String msgErro) {
        return msgErro;
    }

    public boolean isApresentaMensagemFormulario() {
        return apresentaMensagemFormulario;
    }

    public void setApresentaMensagemFormulario(boolean apresentaMensagemFormulario) {
        this.apresentaMensagemFormulario = apresentaMensagemFormulario;
    }

}
