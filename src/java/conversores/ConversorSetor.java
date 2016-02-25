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
import modelo.SetorBO;
import persistencia.SetorDAO;

/**
 *
 * @author rodrigo
 */
@FacesConverter(forClass = SetorBO.class, value = "conversorSetor")
public class ConversorSetor implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String nomeSetor) {

        SetorBO setorBO = null;
        try {
            SetorDAO setorDAO = new SetorDAO();
            setorBO = setorDAO.carregarSetorPorNome(nomeSetor);
            

        } catch (SQLException ex) {
            Logger.getLogger(ConversorSetor.class.getName()).log(Level.SEVERE, null, ex);

        }
        return setorBO;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        SetorBO setorBO = new SetorBO();
        return setorBO.getNomeSetor();

    }
}
