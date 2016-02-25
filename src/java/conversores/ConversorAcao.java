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
import modelo.AcaoBO;
import persistencia.AcaoDAO;

/**
 *
 * @author rodrigo
 */
@FacesConverter(forClass = AcaoBO.class, value = "conversorAcao")
public class ConversorAcao implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String nome) {

        AcaoBO acaoBO = null;
        try {
            AcaoDAO acaoDAO = new AcaoDAO();
            //acaoDAO = acaoDAO.listarAcaoPorNome(nome);

        } catch (SQLException ex) {
            Logger.getLogger(ConversorAcao.class.getName()).log(Level.SEVERE, null, ex);

        }
        return acaoBO;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        AcaoBO acaoBO = new AcaoBO();
        return acaoBO.getNome();

    }
}
