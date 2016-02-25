package genericos;

import java.io.IOException;
import java.util.Map;
import javax.faces.component.UIComponent;
import javax.faces.component.UIData;
import javax.faces.component.UIForm;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;
import javax.faces.render.Renderer;

@FacesRenderer(componentFamily = "javax.faces.Command", rendererType = "com.genericos.Paginador")

public class Paginador extends Renderer {

    public void encodeInicial(FacesContext context, UIComponent component) throws IOException {

        String id = component.getClientId(context);
        UIComponent parent = component;
        while (!(parent instanceof UIForm)) {
            parent = parent.getParent();
        }
        String formId = parent.getClientId(context);
        ResponseWriter escrever = context.getResponseWriter();

        String styleClass = (String) component.getAttributes().get("styleClass");
        String selectedStyleClass = (String) component.getAttributes().get("selectedStyleClass");
        String dataTableId = (String) component.getAttributes().get("dataTableId");
        int mostrarPagina = toInt(component.getAttributes().get("mostrarPagina"));

        UIData data = (UIData) component.findComponent(dataTableId);

        //propiedade para contar
        int primeiraPagina = data.getFirst(); //first
        int totalDeItens = data.getRowCount(); // itemcout
        int itensPorPagina = data.getRows(); // pagesize
        int totalDePaginas = totalDeItens / itensPorPagina; //pages
        int paginaInicial = 0; //statrpage
        int paginaFinal = totalDePaginas; //endPage
        int paginaAtual = primeiraPagina / itensPorPagina; //currentPage

        if (itensPorPagina <= 0) {
            itensPorPagina = totalDeItens;
        }
        if (totalDeItens % itensPorPagina != 0) {
            totalDePaginas++;
        }
        if (primeiraPagina >= totalDeItens - itensPorPagina) {
            paginaAtual = totalDePaginas - 1;
        }
        if (mostrarPagina > 0) {
            paginaInicial = (paginaAtual / mostrarPagina) * mostrarPagina;
        }
        if (paginaAtual > 0) {
            ecreverLink(escrever, component, formId, id, "<", styleClass);
        }
        if (paginaInicial > 0) {
            ecreverLink(escrever, component, formId, id, "<<", styleClass);
        }
        for (int i = paginaInicial; i < paginaFinal; i++) {

            ecreverLink(escrever, component, formId, id, "" + (i + 1), i == paginaAtual ? selectedStyleClass : styleClass);

        }
        if (paginaFinal < totalDePaginas) {
            ecreverLink(escrever, component, formId, id, ">>", styleClass);

        }
        if (primeiraPagina < totalDeItens - itensPorPagina) {
            ecreverLink(escrever, component, formId, id, ">", styleClass);

        }

        //coloca o resultado no campo escondigo
        ecreverHf(escrever, component, id);

    }

    private void ecreverLink(ResponseWriter writer, UIComponent component, String formId, String Id, String value, String styleClass) throws IOException {
        writer.writeText(" ", null);
        writer.startElement("a", component);
        writer.writeAttribute("href", "#", null);
        writer.writeAttribute("onclick", "onclicCode(formId, id, value)", null);
        if (styleClass != null) {
            writer.writeAttribute("class", styleClass, "styleClass");
        }
        writer.writeText(value, null);
        writer.endElement("a");
    }

    private String onclickCode(String formId, String id, String value) {
        return new StringBuilder().append("document.forms['")
                .append(formId).append("']['")
                .append(id).append("'].value='").append(value).append("'; document.forms['")
                .append(formId).append("'].submit(); return false;").toString();
    }

    private void ecreverHf(ResponseWriter writer, UIComponent component,
            String id) throws IOException {
        writer.startElement("input", component);
        writer.writeAttribute("type", "hidden", null);
        writer.writeAttribute("name", id, null);
        writer.endElement("input");
    }

    public void decode(FacesContext context, UIComponent component) {
        String id = component.getClientId(context);
        Map<String, String> parameters  = context.getExternalContext().getRequestParameterMap();
        String response = parameters.get(id); //(String) tinha um cast.
        if (response == null || response.equals("")) {
            return;
        }
        String dataTableId = (String) component.getAttributes().get("dataTableId");
        int mostrarPagina = toInt(component.getAttributes().get("mostrarPagina"));

        UIData data = (UIData) component.findComponent(dataTableId);

        //propiedade para contar
        int primeiraPagina = data.getFirst(); //first
        int totalDeItens = data.getRowCount(); // itemcout
        int itensPorPagina = data.getRows(); // pagesize
        if (itensPorPagina <= 0) {
            itensPorPagina = totalDeItens;
        }

        if (response.equals("<")) {
            primeiraPagina -= itensPorPagina;
        } else if (response.equals(">")) {
            primeiraPagina += itensPorPagina;
        } else if (response.equals("<<")) {
            primeiraPagina -= itensPorPagina * mostrarPagina;
        } else if (response.equals(">>")) {
            primeiraPagina += itensPorPagina * mostrarPagina;
        } else {
            int pagina = Integer.parseInt(response);
            primeiraPagina = (pagina - 1) * itensPorPagina;

        }
        if (primeiraPagina + itensPorPagina > totalDeItens) {
            primeiraPagina = totalDeItens - itensPorPagina;
        }
        if (primeiraPagina < 0) {
            primeiraPagina = 0;
        }
        data.setFirst(primeiraPagina);

    }

    private int toInt(Object value) {

        if (value == null) {
            return 0;
        }

        if (value instanceof Number) {
            return ((Number) value).intValue();
        }
        if (value instanceof String) {
            return Integer.parseInt((String) value);
        }
        throw new IllegalArgumentException("Cannot convert " + value);
    }

}
