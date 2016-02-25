/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.sql.SQLException;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import modelo.PessoaBO;
import modelo.SetorBO;
import persistencia.PessoaDAO;
import persistencia.SetorDAO;

/**
 *
 * @author rodrigo
 */
@ManagedBean

@RequestScoped
public class PessoaMB {

    private PessoaBO pessoaBO;
    private List<SetorBO> setores;
    private List<PessoaBO> pessoas;
    private PessoaDAO pessoaDAO;
    private SetorDAO setorDAO;
    private String palavraChave;
    private boolean apresentaMensagemFormulario = false;
    private final MensagemFormularioMB mensagemFormularioMB;
    private String mensageFormulario;
    private String classCss;
    private boolean usuarioSistema;
    private String verificarSenha;

    public PessoaMB() throws SQLException {
        pessoaBO = new PessoaBO();
        pessoaBO.setStatus(1);
        pessoaDAO = new PessoaDAO();
        pessoas = this.listarPessoas();
        setorDAO = new SetorDAO();
        mensagemFormularioMB = new MensagemFormularioMB();
        setores = this.setorDAO.listarSetores("Select * FROM setor");

    }

    // <editor-fold defaultstate="collapsed" desc="Gets & Seter">
    public PessoaBO getPessoaBO() {
        return pessoaBO;
    }

    public void setPessoaBO(PessoaBO pessoaBO) {
        this.pessoaBO = pessoaBO;
    }

    public List<SetorBO> getSetores() {
        return setores;
    }

    public void setSetores(List<SetorBO> setores) {
        this.setores = setores;
    }

    public List<PessoaBO> getPessoas() {
        return pessoas;
    }

    public void setPessoas(List<PessoaBO> pessoas) {
        this.pessoas = pessoas;
    }

    public List<PessoaBO> listarPessoas() throws SQLException {
        return this.pessoaDAO.listarPessoas();
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

    public String getPalavraChave() {
        return palavraChave;
    }

    public void setPalavraChave(String palavraChave) {
        this.palavraChave = palavraChave;
    }

    public boolean isUsuarioSistema() {
        return usuarioSistema;
    }

    public void setUsuarioSistema(boolean usuarioSistema) {
        this.usuarioSistema = usuarioSistema;
    }

    public String getVerificarSenha() {
        return verificarSenha;
    }

    public void setVerificarSenha(String verificarSenha) {
        this.verificarSenha = verificarSenha;
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Metodos">
    public String salvar() throws SQLException {

        if (validadeFormulario()) {
            if (!usuarioSistema) {

                int retorno;
                if (pessoaBO.getIdPessoa() > 0) {

                    retorno = this.pessoaDAO.alterarPessoaNaoUsuariaSistema(pessoaBO);
                    if (retorno > 0) {
                        mensageFormulario = mensagemFormularioMB.apresentaMesagemSucessoAlteracao();
                        classCss = mensagemFormularioMB.monstraClass(2);
                    } else {
                        mensageFormulario = mensagemFormularioMB.apresentaMensagemErroCadastro();
                        classCss = mensagemFormularioMB.monstraClass(1);
                    }

                } else {
                    if (usuarioSistema) {
                        retorno = 1;// aqui vai o metodo quando o cara é usuario sistema
                    } else {
                        retorno = this.pessoaDAO.inserirPessoaNaoUsuariaSistema(pessoaBO);

                    }
                    if (retorno > 0) {
                        mensageFormulario = mensagemFormularioMB.apresentaMensagemSucessoCadastro();
                        classCss = mensagemFormularioMB.monstraClass(2);

                    } else {
                        mensageFormulario = mensagemFormularioMB.apresentaMensagemErroCadastro();
                        classCss = mensagemFormularioMB.monstraClass(1);
                    }
                }
            } else {

            }
            apresentaMensagemFormulario = true;

        }
        return "CadastrarPessoa";
    }

    public String editar(PessoaBO pessoaBO) throws Exception {

        this.pessoaBO = pessoaBO;
        return "CadastrarPessoa";
    }

    public void excluir(PessoaBO pessoaBO) throws SQLException {

        int retorno;
        retorno = this.pessoaDAO.excluirPessoa(pessoaBO);
        if (retorno > 0) {
            mensageFormulario = mensagemFormularioMB.apresentaMesagemSucessoExcluir();
            classCss = mensagemFormularioMB.monstraClass(2);

        } else {
            mensageFormulario = mensagemFormularioMB.apresentaMesagemErroExclusao();
            classCss = mensagemFormularioMB.monstraClass(1);

        }
        apresentaMensagemFormulario = true;
        pessoas = this.listarPessoas();
    }

    public String novo() throws SQLException {
        pessoaBO = new PessoaBO();
        pessoas = this.listarPessoas();

        return "CadastrarPessoa";
    }

    public List<PessoaBO> filtarPessoaPorPalavraChave() throws SQLException {
        if (palavraChave != null && palavraChave.length() > 0) {
            return pessoas = this.pessoaDAO.filtarPessoaPorPalavraChave(palavraChave);
        }
        return pessoas;
    }

    public void controlaUsuarioSistema() {
        this.usuarioSistema = true;
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="VALIDACAO">
    private boolean validadeFormulario() {

        String msgErro = "";
        if (pessoaBO.getNome().length() < 1) {

            msgErro += "- O campo NOME deve ser preenchido.<br />";

        }

        if (pessoaBO.getSetorBO().getIdSetor() == 0) {
            msgErro += "- Um Setor deve ser selecionado.<br />";

        }
        if (usuarioSistema) {

            if (pessoaBO.getSenha().length() <= 0) {
                msgErro += "- O campo SENHA deve ser preenchido.<br />";

            } else if (!pessoaBO.getSenha().equals(verificarSenha)) {

                msgErro += "- O campo REPITA SENHA deve ser preenchido e com o mesmo conteúdo do campo SENHA.<br />";

            } else if (pessoaBO.getUsuario().length() <= 0) {
                msgErro += "- O campo Usuario deve ser preenchido.<br />";

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

    // </editor-fold>
}
