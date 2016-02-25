package modelo;

import java.util.Objects;

public class SetorBO {

    private int idSetor;
    private String nomeSetor;
    private String descricaoSetor;
    private int status;
    private String statusString;
    private InstituicaoBO instituicaoBO;

    public SetorBO() {
    }

    public SetorBO(int idSetor, String nomeSetor, String descricaoSetor, int status, InstituicaoBO instituicaoBO) {

        this.setIdSetor(idSetor);
        this.setNomeSetor(nomeSetor);
        this.setDescricaoSetor(descricaoSetor);
        this.setStatus(status);
        this.setInstituicaoBO(instituicaoBO);
    }

    public String getNomeSetor() {
        return nomeSetor;
    }

    public void setNomeSetor(String nomeSetor) {
        this.nomeSetor = nomeSetor;
    }

    public String getDescricaoSetor() {
        return descricaoSetor;
    }

    public void setDescricaoSetor(String descricaoSetor) {
        this.descricaoSetor = descricaoSetor;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getIdSetor() {
        return idSetor;
    }

    public void setIdSetor(int idSetor) {
        this.idSetor = idSetor;
    }

    public String getStatusString() {

        if (status == 1) {
            statusString = "Ativo";

        } else {
            statusString = "Inativo";
        }

        return statusString;
    }


    public InstituicaoBO getInstituicaoBO() {
        return instituicaoBO;
    }

    public void setInstituicaoBO(InstituicaoBO instituicaoBO) {
        this.instituicaoBO = instituicaoBO;
    }

    @Override
    public String toString() {
        return this.getNomeSetor();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.nomeSetor);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SetorBO other = (SetorBO) obj;
        if (!Objects.equals(this.nomeSetor, other.nomeSetor)) {
            return false;
        }
        return true;
    }

}
