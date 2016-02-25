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
public class MetaBO {

    private int idMeta;
    private String nome;
    private String descricao;
    private Date dataInicio;
    private Date dataFim;
    private int status;
    private int tipoMeta;
    private AcaoBO acaoBO;
    

    public MetaBO() {
    }

    public MetaBO(int idMeta, String nome, String descricao, Date dataInicio, Date dataFim, int status, int tipoMeta, AcaoBO acaoBO) {
        this.setIdMeta(idMeta);
        this.setNome(nome);
        this.setDataInicio(dataInicio);
        this.setDataFim(dataFim);
        this.setStatus(status);
        this.setDescricao(descricao);
        this.setTipoMeta(tipoMeta);
        this.setAcaoBO(acaoBO);

    }

    public int getIdMeta() {
        return idMeta;
    }

    public void setIdMeta(int idMeta) {
        this.idMeta = idMeta;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTipoMeta() {
        return tipoMeta;
    }

    public void setTipoMeta(int tipoMeta) {
        this.tipoMeta = tipoMeta;
    }

    public AcaoBO getAcaoBO() {
        return acaoBO;
    }

    public void setAcaoBO(AcaoBO acaoBO) {
        this.acaoBO = acaoBO;
    }

    @Override
    public String toString() {
        return "MetaBO{" + "nome=" + nome + '}';
    }

    // @Override                      => Perguntar qual a diferença entre esses métodos
    //  public String toString() {
    //     return this.nome;}
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.nome);
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
        final MetaBO other = (MetaBO) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        return true;
    }

}
