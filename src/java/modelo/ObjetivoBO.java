    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Objects;

/**
 *
 * @author rodrigo
 */
public class ObjetivoBO {

    private int idObjetivo;
    private String descricao;
    private int status;
    private String statusString;
    private InstituicaoBO instituicaoBO;
    private float percentualDeConquista;
    private int nivelObjetivo;
    private int numeroDeAcoes;
    private String nome;
    private int numeroAcoesConcluidas;
    private int numeroAcoesEmAndamentos;
    private int numeroAcoesAtivas;
    private int numeroAcoesInativas;

    public ObjetivoBO() {
    }

    public ObjetivoBO(int idObjetivo, String descricao, int status, String statusString, InstituicaoBO instituicaoBO, float percentualDeConquista, int nivelObjetivo, int numeroDeAcoes, String nome) {

        this.setIdObjetivo(idObjetivo);
        this.setDescricao(descricao);
        this.setStatus(status);
        this.setStatusString(statusString);
        this.setInstituicaoBO(instituicaoBO);
        this.setPercentualDeConquista(percentualDeConquista);
        this.setNivelObjetivo(nivelObjetivo);
        this.setNumeroDeAcoes(numeroDeAcoes);
        this.setNome(nome);

    }

    public int getIdObjetivo() {
        return idObjetivo;
    }

    public void setIdObjetivo(int idObjetivo) {
        this.idObjetivo = idObjetivo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusString() {

        if (status == 1) {
            statusString = "Ativo";
        } else if (status == 2) {
            statusString = "Inativo";
        } else if (status == 4) {
            statusString = "Concluído";
        } else if (status == 3) {
            statusString = "Em Andamento";
        } else {

            statusString = "N/A";

        }

        return statusString;
    }

    public void setStatusString(String statusString) {
        this.statusString = statusString;
    }

    public InstituicaoBO getInstituicaoBO() {
        return instituicaoBO;
    }

    public void setInstituicaoBO(InstituicaoBO instituicaoBO) {
        this.instituicaoBO = instituicaoBO;
    }

    public float getPercentualDeConquista() {
        return percentualDeConquista;
    }

    public void setPercentualDeConquista(float percentualDeConquista) {
        this.percentualDeConquista = percentualDeConquista;
    }

    public int getNivelObjetivo() {
        return nivelObjetivo;
    }

    public void setNivelObjetivo(int nivelObjetivo) {
        this.nivelObjetivo = nivelObjetivo;
    }

    public int getNumeroDeAcoes() {
        return numeroDeAcoes;
    }

    public void setNumeroDeAcoes(int numeroDeAcoes) {
        this.numeroDeAcoes = numeroDeAcoes;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return this.nome;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.nome);
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
        final ObjetivoBO other = (ObjetivoBO) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        return true;
    }

    public int getNumeroAcoesConcluidas() {
        return numeroAcoesConcluidas;
    }

    public void setNumeroAcoesConcluidas(int numeroAcoesConcluidas) {
        this.numeroAcoesConcluidas = numeroAcoesConcluidas;
    }

    public int getNumeroAcoesEmAndamentos() {
        return numeroAcoesEmAndamentos;
    }

    public void setNumeroAcoesEmAndamentos(int numeroAcoesEmAndamentos) {
        this.numeroAcoesEmAndamentos = numeroAcoesEmAndamentos;
    }

    public int getNumeroAcoesAtivas() {
        return numeroAcoesAtivas;
    }

    public int getNumeroAcoesInativas() {
        return numeroAcoesInativas;
    }

    public void setNumeroAcoesAtivas(int numeroAcoesAtivas) {
        this.numeroAcoesAtivas = numeroAcoesAtivas;
    }

    public void setNumeroAcoesInativas(int numeroAcoesInativas) {
        this.numeroAcoesInativas = numeroAcoesInativas;
    }

}
