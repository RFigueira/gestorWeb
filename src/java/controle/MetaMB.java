/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import modelo.AcaoBO;
import modelo.MetaBO;

/**
 *
 * @author rodrigo
 */
//@Named(value = "metaMB")
@ManagedBean

@RequestScoped
public class MetaMB {

    private MetaBO metaBO;
    private List<AcaoBO> acoes;
    private List<MetaBO> mestas;

    public MetaMB() {
    }

    public MetaBO getMetaBO() {
        return metaBO;
    }

    public void setMetaBO(MetaBO metaBO) {
        this.metaBO = metaBO;
    }

    public List<AcaoBO> getAcoes() {
        return acoes;
    }

    public void setAcoes(List<AcaoBO> acoes) {
        this.acoes = acoes;
    }

    public List<MetaBO> getMestas() {
        return mestas;
    }

    public void setMestas(List<MetaBO> mestas) {
        this.mestas = mestas;
    }

}
