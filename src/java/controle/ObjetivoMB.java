
package controle;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import modelo.InstituicaoBO;
import modelo.ObjetivoBO;
import persistencia.InstituicaoDAO;
import persistencia.ObjetivoDAO;

@ManagedBean
@RequestScoped
public class ObjetivoMB {

    private ObjetivoBO objetivoBO;
    private ObjetivoDAO objetivoDAO;
    private List<InstituicaoBO> instituicoes;
    private List<ObjetivoBO> objetivos;
    private InstituicaoDAO instituicaoDAO;
    private String palavraChave;
    private boolean apresentaMensagemFormulario = false;
    private final MensagemFormularioMB mensagemFormularioMB;
    private String mensageFormulario;
    private String classCss;

    public ObjetivoMB() throws SQLException, ParseException {

        objetivoBO = new ObjetivoBO();
        objetivoBO.setStatus(1);
        objetivoDAO = new ObjetivoDAO();
        mensagemFormularioMB = new MensagemFormularioMB();
        objetivos = this.listarObjetivos();
        instituicaoDAO = new InstituicaoDAO();
        this.instituicoes = instituicaoDAO.listarInstituicao("SELECT * FROM instituicao");

    }

    // <editor-fold defaultstate="collapsed" desc="Gets & Seter">
    public List<InstituicaoBO> getInstituicoes() {
        return instituicoes;
    }

    public void setInstituicoes(List<InstituicaoBO> instituicoes) {
        this.instituicoes = instituicoes;
    }

    public ObjetivoBO getObjetivoBO() {
        return objetivoBO;
    }

    public void setObjetivoBO(ObjetivoBO objetivoBO) {
        this.objetivoBO = objetivoBO;
    }

    public List<ObjetivoBO> getObjetivos() {
        return objetivos;
    }

    public void setObjetivos(List<ObjetivoBO> objetivos) {
        this.objetivos = objetivos;
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

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="METODOS">
    public String salvar() throws SQLException {

        if (validadeFormulario()) {
            int retorno;
            if (objetivoBO.getIdObjetivo() > 0) {
                retorno = this.objetivoDAO.alterarObjetivo(objetivoBO);
                if (retorno > 0) {
                    mensageFormulario = mensagemFormularioMB.apresentaMesagemSucessoAlteracao();
                    classCss = mensagemFormularioMB.monstraClass(2);
                } else {
                    mensageFormulario = mensagemFormularioMB.apresentaMensagemErroCadastro();
                    classCss = mensagemFormularioMB.monstraClass(1);
                }
            } else {
                retorno = this.objetivoDAO.inserirObjetivo(objetivoBO);
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
        return "CadastrarObjetivo";

    }

    public void novo() throws SQLException {

        objetivoBO = new ObjetivoBO();
    }

    public void excluir(ObjetivoBO objetivoBO) throws SQLException {

        int retorno = this.objetivoDAO.excluirObjetivo(objetivoBO);
        if (retorno > 0) {
            mensageFormulario = mensagemFormularioMB.apresentaMesagemSucessoExcluir();
            classCss = mensagemFormularioMB.monstraClass(2);

        } else {
            mensageFormulario = mensagemFormularioMB.apresentaMesagemErroExclusao();
            classCss = mensagemFormularioMB.monstraClass(1);

        }
        apresentaMensagemFormulario = true;
        objetivos = this.listarObjetivos();
    }

    private List<ObjetivoBO> listarObjetivos() throws SQLException {

        return this.objetivoDAO.listarObjetivos("SELECT * FROM objetivo");
    }

    public String editar(ObjetivoBO objetivoBO) throws SQLException {

        this.objetivoBO = objetivoBO;

        return "CadastrarObjetivo";
    }

    public String getPalavraChave() {
        return palavraChave;
    }

    public void setPalavraChave(String palavraChave) {
        this.palavraChave = palavraChave;
    }

    public List<ObjetivoBO> filtarObjetivoPorPalavraChave() throws SQLException {
        if (palavraChave != null && palavraChave.length() > 0) {
            return objetivos = this.objetivoDAO.filtrarObjetivoPorPalavraChave(palavraChave);
        }
        return objetivos;
    }

// </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="VALIDACAO">
    private boolean validadeFormulario() {

        String msgErro = "";
        if (objetivoBO.getNome().length() < 1) {

            msgErro += "- O campo NOME deve ser preenchido.<br />";

        }
        if (objetivoBO.getDescricao().length() < 1) {
            msgErro += "- O campo DESCRIÇÃO deve ser preenchido.<br />";

        }

        if (objetivoBO.getInstituicaoBO().getIdInstituicao() == 0) {
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

// </editor-fold>
}
