/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.ipn.escom.presentation;


import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import mx.ipn.escom.pojos.Leaf;
import mx.ipn.escom.services.ClassifierService;
import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.primefaces.event.FileUploadEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author edgar
 */

@ManagedBean@Scope (value = "session")
@Component (value = "classifierBean")
@ELBeanName(value = "classifierBean")
@Join(path = "/classifier", to = "/index.jsf")
public class ClassifierController {
    
    @Autowired(required = true)
    private ClassifierService classifierService;
    
    private Leaf leaf;
    
    private ClassifierController(){
        this.leaf = new Leaf();
    }
    
    public String foo(){
        System.out.println("foo");
        return "Foo: ";
    }
    
    public void subirImagen(FileUploadEvent event) {
        try {
            byte[] content = new byte[event.getFile().getInputstream().available()];
            event.getFile().getInputstream().read(content);
            leaf.setImage(content);
            String result = classifierService.classify(leaf);
            System.out.println("Clasificacion");
            System.out.println(result);
            FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }        
    }

    /**
     * @return the leaf
     */
    public Leaf getLeaf() {
        return leaf;
    }

    /**
     * @param leaf the leaf to set
     */
    public void setLeaf(Leaf leaf) {
        this.leaf = leaf;
    }
    
}
