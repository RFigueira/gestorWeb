/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conversores;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import modelo.PessoaBO;
import persistencia.PessoaDAO;

/**
 *
 * @author rodrigo
 */
@FacesConverter(forClass = PessoaBO.class, value = "conversorPessoa")
public class ConversorPessoa implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String nome) {

        PessoaBO pessoaBO = null;
        try {
            PessoaDAO pessoaDAO = new PessoaDAO();
            pessoaBO = pessoaDAO.carregarPessoaPorNome(nome);

        } catch (SQLException ex) {
            Logger.getLogger(ConversorObjetivo.class.getName()).log(Level.SEVERE, null, ex);

        }
        return pessoaBO;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        PessoaBO pessoaBO = new PessoaBO();
        return pessoaBO.getNome();

    }
}
