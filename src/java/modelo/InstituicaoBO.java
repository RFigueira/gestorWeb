package modelo;

import java.util.Date;
import java.util.Objects;

public class InstituicaoBO {

    private int idInstituicao;
    private String nome;
    private String fundacao;
    private String telefone;
    private String rua;
    private String bairro;
    private String cep;
    private String cidade;
    private String estado;
    private String pais;
    private String numero;
    private int status;
    private String statusString;
    private String nomeInstituicaoCidadeEmString;

    public InstituicaoBO() {
    }

    public InstituicaoBO(int idInstituicao, String nome, String fundacao, String telefone, String rua, String bairro,
            String cep, String cidade, String estado, String pais, String numero, int Status) {

        this.setIdInstituicao(idInstituicao);
        this.setNome(nome);
        this.setFundacao(fundacao);
        this.setTelefone(telefone);
        this.setRua(rua);
        this.setBairro(bairro);
        this.setCep(cep);
        this.setCidade(cidade);
        this.setEstado(estado);
        this.setPais(pais);
        this.setNumero(numero);
        this.setStatus(status);
        
    }

    public int getIdInstituicao() {
        return idInstituicao;
    }

    public void setIdInstituicao(int idInstituicao) {
        this.idInstituicao = idInstituicao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFundacao() {
        return fundacao;
    }

    public void setFundacao(String fundacao) {
        this.fundacao = fundacao;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusString() {

        if (status == 1) {
          return  statusString = "Ativo";

        } 
        else if(status == 2)
                {
         return   statusString = "Inativo";
        }
        

        return "N/A";
    }

   
    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
    
    
    public String getNomeInstituicaoCidadeEmString() {
        return nome + " / " + cidade;
    }

   

    @Override
    public String toString() {
        return this.nome;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.nome);
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
        final InstituicaoBO other = (InstituicaoBO) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        return true;
    }

}
