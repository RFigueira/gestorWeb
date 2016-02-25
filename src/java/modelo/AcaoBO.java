/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author rodrigo
 */
public class AcaoBO {

    private int idAcao;
    private String nome;
    private ObjetivoBO objetivoBO;
    private int status;
    private String data;
    private int idTipoAcao;
    private String statusString;
    private float porcentagemDaAcaoNoObjetivo;
    private String descricao;
    private int totalAcoes;

    public AcaoBO() {
    }

    public AcaoBO(int idAcao, String nome, ObjetivoBO objetivoBO, int status, String data, int idTipoAcao, float porcentagemDaAcaoNoObjetivo) {
        this.setIdAcao(idAcao);
        this.setData(data);
        this.setIdTipoAcao(idTipoAcao);
        this.setObjetivoBO(objetivoBO);
        this.setStatus(status);
        this.setNome(nome);
        this.setPorcentagemDaAcaoNoObjetivo(porcentagemDaAcaoNoObjetivo);
        this.setTotalAcoes(totalAcoes);

    }

    public int getIdAcao() {
        return idAcao;
    }

    public void setIdAcao(int idAcao) {
        this.idAcao = idAcao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ObjetivoBO getObjetivoBO() {
        return objetivoBO;
    }

    public void setObjetivoBO(ObjetivoBO objetivoBO) {
        this.objetivoBO = objetivoBO;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getIdTipoAcao() {
        return idTipoAcao;
    }

    public void setIdTipoAcao(int idTipoAcao) {
        this.idTipoAcao = idTipoAcao;
    }

    public String getStatusString() {
        if (status == 1) {
          return  statusString = "Ativo";

        } else if (status == 2) {

          return  statusString = "Inativo";
        } else if (status == 3) {
            return statusString = "Em Andamento";
        } else if (status == 4) {
            return statusString = "Concluído";
        }

        return "N/A";
    }

    public void setStatusString(String statusString) {
        this.statusString = statusString;
    }

    public float getPorcentagemDaAcaoNoObjetivo() {
        return porcentagemDaAcaoNoObjetivo;
    }

    public void setPorcentagemDaAcaoNoObjetivo(float porcentagemDaAcaoNoObjetivo) {
        this.porcentagemDaAcaoNoObjetivo = porcentagemDaAcaoNoObjetivo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return this.nome;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.nome);
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
        final AcaoBO other = (AcaoBO) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        return true;
    }

    public int getTotalAcoes() {
        return totalAcoes;
    }

    public void setTotalAcoes(int totalAcoes) {
        this.totalAcoes = totalAcoes;
    }

}
