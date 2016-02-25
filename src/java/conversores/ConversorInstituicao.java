package conversores;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import modelo.InstituicaoBO;
import persistencia.InstituicaoDAO;

@FacesConverter(forClass = InstituicaoBO.class, value = "conversorInstituicao")
public class ConversorInstituicao implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String nome) {

        InstituicaoBO instituicaoBO = null;
        try {
            InstituicaoDAO instituicaoDAO = new InstituicaoDAO();
            instituicaoBO = instituicaoDAO.carregarInstituicaoPorNome(nome);

        } catch (SQLException ex) {
            Logger.getLogger(ConversorInstituicao.class.getName()).log(Level.SEVERE, null, ex);

        }
        return instituicaoBO;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        InstituicaoBO instituicaoBO = new InstituicaoBO();
        return instituicaoBO.getNome();

    }

}
