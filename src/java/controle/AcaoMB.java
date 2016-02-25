/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import modelo.AcaoBO;
import modelo.ObjetivoBO;
import persistencia.AcaoDAO;
import persistencia.ObjetivoDAO;

/**
 *
 * @author rodrigo
 */
//@ManagedBean(name="acaoMB")
@ManagedBean
@RequestScoped
public class AcaoMB {

    private AcaoDAO acaoDAO;
    private AcaoBO acaoBO;
    private List<ObjetivoBO> objetivos;
    private List<AcaoBO> acoes;
    private List<AcaoBO> acoesPaginadas;
    private ObjetivoDAO objetivoDAO;
    private String palavraChave;
    private boolean apresentaMensagemFormulario = false;
    private final MensagemFormularioMB mensagemFormularioMB;
    private String mensageFormulario;
    private String classCss;

    public AcaoMB() throws SQLException, ParseException {

        acaoBO = new AcaoBO();
        acaoBO.setStatus(1);
        acaoDAO = new AcaoDAO();
        acoes = this.listarAcoes();
        // acoesPaginadas = this.listarAcoesPaginadas(int limitador, int offset, String ordem, String sentido);
        objetivoDAO = new ObjetivoDAO();
        objetivos = objetivoDAO.listarObjetivos("SELECT * FROM objetivo order by id_objetivo desc");
        mensagemFormularioMB = new MensagemFormularioMB();

    }

    public AcaoBO getAcaoBO() {
        return acaoBO;
    }

    public void setAcaoBO(AcaoBO acaoBO) {
        this.acaoBO = acaoBO;
    }

    public List<ObjetivoBO> getObjetivos() {
        return objetivos;
    }

    public void setObjetivos(List<ObjetivoBO> objetivos) {
        this.objetivos = objetivos;
    }

    public List<AcaoBO> getAcoes() {
        return acoes;
    }

    public void setAcoes(List<AcaoBO> acoes) {
        this.acoes = acoes;
    }

    private List<AcaoBO> listarAcoes() throws SQLException, ParseException {
        return this.acaoDAO.listarAcoes("SELECT * FROM acao order by id_acao asc");
    }

    public boolean isApresentaMensagemFormulario() {
        return apresentaMensagemFormulario;
    }

    public void setApresentaMensagemFormulario(boolean apresentaMensagemFormulario) {
        this.apresentaMensagemFormulario = apresentaMensagemFormulario;
    }

    public AcaoDAO getAcaoDAO() {
        return acaoDAO;
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

    public String salvar() throws SQLException, ParseException {

        if (validadeFormulario()) {
            int retorno;
            if (acaoBO.getIdAcao() > 0) {
                retorno = this.acaoDAO.alterarAcao(acaoBO);
                if (retorno > 0) {
                    mensageFormulario = mensagemFormularioMB.apresentaMesagemSucessoAlteracao();
                    classCss = mensagemFormularioMB.monstraClass(2);
                } else {
                    mensageFormulario = mensagemFormularioMB.apresentaMensagemErroCadastro();
                    classCss = mensagemFormularioMB.monstraClass(1);

                }
            } else {
                retorno = this.acaoDAO.inserirAcao(acaoBO);
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

        return "CadastrarAcao";

    }

    public void novo() throws SQLException, ParseException {

        acaoBO = new AcaoBO();
        acoes = this.listarAcoes();

    }

    public String editar(AcaoBO acaoBO) {

        this.acaoBO = acaoBO;
        return "CadastrarAcao";
    }

    public void excluir(AcaoBO acaoBO) throws SQLException, ParseException {

        int retorno;

        retorno = acaoDAO.excluirAcao(acaoBO);
        if (retorno > 0) {
            mensageFormulario = mensagemFormularioMB.apresentaMesagemSucessoExcluir();
            classCss = mensagemFormularioMB.monstraClass(2);

        } else {
            mensageFormulario = mensagemFormularioMB.apresentaMesagemErroExclusao();
            classCss = mensagemFormularioMB.monstraClass(1);

        }
        apresentaMensagemFormulario = true;

        acoes = this.listarAcoes();
    }

    public List<AcaoBO> listarAcoesPaginadas(int limitador, int offset, String ordem, String sentido) throws SQLException, ParseException {
        return this.acaoDAO.listarAcoesPaginada(limitador, offset, ordem, sentido);
    }

    public List<AcaoBO> getAcoesPaginadas() {
        return acoesPaginadas;
    }

    public void setAcoesPaginadas(List<AcaoBO> acoesPaginadas) {
        this.acoesPaginadas = acoesPaginadas;
    }

    public List<AcaoBO> filtarAcoesPorPalavraChave() throws SQLException, ParseException {
        if (palavraChave != null && palavraChave.length() > 0) {

            return acoes = this.acaoDAO.filtarAcoesPorPalavraChave(this.palavraChave);
        }
        return acoes;

    }

    public String getPalavraChave() {
        return palavraChave;
    }

    public void setPalavraChave(String palavraChave) {
        this.palavraChave = palavraChave;
    }

    public List<AcaoBO> listarAcoesPorFiltro() throws SQLException, ParseException {

        try {
            return this.acaoDAO.listarAcoes("Select * FROM acao");
        } catch (SQLException ex) {
            return this.acaoDAO.listarAcoes("Select * FROM acao");
            //AQUI VAI A MENSAGEM DE ERRO

        } catch (ParseException ex) {
            return this.acaoDAO.listarAcoes("Select * FROM acao");
            //AQUI VAI A MENSAGEM DE ERRO
        }

    }
//Validacoes

    private boolean validadeFormulario() {

        String msgErro = "";
        if (acaoBO.getNome().length() < 1) {

            msgErro += "- O campo NOME deve ser preenchido.<br />";

        }
        if (acaoBO.getDescricao().length() < 1) {
            msgErro += "- O campo DESCRIÇÃO deve ser preenchido.<br />";

        }

        if (acaoBO.getData() == null) {
            msgErro += "- O campo FUNDAÇÃO deve ser preenchido.<br />";

        } else {
            try {
                Date hoje = new Date();
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date date = dateFormat.parse(acaoBO.getData());
                hoje.parse(acaoBO.getData());
                if (date.getTime() < hoje.getTime()) {
                    msgErro += "- O campo DATA deve ser preenchido com uma data maior que a de hoje.<br />";
                }

            } catch (Exception e) {
                msgErro += "- O campo DATA deve ser preenchido com uma data válida.<br />";
            }
        }

        if (acaoBO.getObjetivoBO().getIdObjetivo() == 0) {
            msgErro += "- Um Objetivo deve ser selecionado.<br />";

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
//acceptcharset="ISO-8859-1" lang="pt_BR" dentro do form para acentuacao

