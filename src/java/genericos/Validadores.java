/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genericos;

import javax.faces.application.FacesMessage;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author rodrigo
 */
public class Validadores extends ValidatorException{

    public Validadores(FacesMessage message) {
        super(message);
    }
    
    
}
