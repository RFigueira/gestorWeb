/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Objects;

public class PessoaBO {

    private int idPessoa;
    private String nome;
    private String telefone;
    private String email;
    private String usuario;
    private String senha;
    private int status;
    private String statusString;
    private SetorBO setorBO;

    public PessoaBO() {
    }

    public PessoaBO(int idPessoa, String nome, String telefone, String email, String usuario, String senha, int status,  SetorBO setorBO) {
        this.setIdPessoa(idPessoa);
        this.setNome(nome);
        this.setTelefone(telefone);
        this.setEmail(email);
        this.setUsuario(usuario);
        this.setSenha(senha);
        this.setStatus(status);
        this.setSetorBO(setorBO);

    }

    public int getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(int idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
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

        } else {
            statusString = "Inativo";
        }

        return statusString;
    }


    public SetorBO getSetorBO() {
        return setorBO;
    }

    public void setSetorBO(SetorBO setorBO) {
        this.setorBO = setorBO;
    }

    @Override
    public String toString() {
        return this.getNome();
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.nome);
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
        final PessoaBO other = (PessoaBO) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        return true;
    }

}
