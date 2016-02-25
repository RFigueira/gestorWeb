/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seguranca;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import modelo.PessoaBO;

/**
 *
 * @author rodrigo
 */
public class Acesso {

    public Acesso() {

    }

    public void inserirUsuarioNaSession(PessoaBO pessoaBO) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession sessaoHttp = (HttpSession) facesContext.getExternalContext().getSession(true);
        sessaoHttp.setAttribute("usuarioLogado", pessoaBO);

    }

    public PessoaBO carregarUsuarioDaSession() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession sessaoHttp = (HttpSession) facesContext.getExternalContext().getSession(true);
        if (((PessoaBO) sessaoHttp.getAttribute("usuarioLogado")) != null) {
            return (PessoaBO) sessaoHttp.getAttribute("usuarioLogado");
        } else {
            return null;
        }
    }

    public void excluirUsuarioDaSession() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession sessaoHttp = (HttpSession) facesContext.getExternalContext().getSession(true);
        sessaoHttp.removeAttribute("usuarioLogado");

    }

}
