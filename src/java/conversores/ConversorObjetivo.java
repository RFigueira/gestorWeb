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
import modelo.ObjetivoBO;
import persistencia.ObjetivoDAO;

/**
 *
 * @author rodrigo
 */
@FacesConverter(forClass = ObjetivoBO.class, value = "conversorObjetivo")
public class ConversorObjetivo implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String nome) {

        ObjetivoBO objetivoBO = null;
        try {
            ObjetivoDAO objetivoDAO = new ObjetivoDAO();
            objetivoBO = objetivoDAO.carregarObjetivoPorNome(nome);

        } catch (SQLException ex) {
            Logger.getLogger(ConversorObjetivo.class.getName()).log(Level.SEVERE, null, ex);

        }
        return objetivoBO;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        ObjetivoBO objetivoBO = new ObjetivoBO();
        return objetivoBO.getNome();

    }
}
