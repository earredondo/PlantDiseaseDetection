/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.ipn.escom.presentation;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import mx.ipn.escom.pojos.Leaf;
import mx.ipn.escom.services.ClassifierService;
import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author edgar
 */

@Scope (value = "session")
@Component (value = "classifierBean")
@ELBeanName(value = "classifierBean")
@Join(path = "/classifier", to = "/index.jsf")
public class ClassifierController {
    
    @Autowired(required = true)
    private ClassifierService classifierService;
    
    private Leaf leaf;
    private String results;
    private StreamedContent image;
    
    private List<String> diseases;
    
    private ClassifierController(){
        this.leaf = new Leaf();
        this.diseases = new ArrayList();
        this.diseases.add("Virus del rizado amarillo del tomate (Tomato yellow leaf curl virus).");
        this.diseases.add("Virus del mosaico del tomate (Tomato mosaic virus).");
        this.diseases.add("Corynespora cassiicola. Mancha en forma de blanco. (Target spot).");
        this.diseases.add("Araña roja (Spider mites).");
        this.diseases.add("Septoriosis (Septoria spot).");
        this.diseases.add("Passalora fulva. Moho en la hoja (Leaf mold).");
        this.diseases.add("Tizón tardío (Lateblight).");
        this.diseases.add("Tizón temprano (Earlyblight).");
        this.diseases.add("Mancha bacteriana (Bacterial spot).");
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
            this.results = "A continuación se muestran las dos enfermedades más probables.\n\n" + classifierService.classify(leaf, 2);
            
            this.image = new DefaultStreamedContent(event.getFile().getInputstream(), "image/png");
            
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

    /**
     * @return the results
     */
    public String getResults() {
        return results;
    }

    /**
     * @param results the results to set
     */
    public void setResults(String results) {
        this.results = results;
    }

    /**
     * @return the diseases
     */
    public List<String> getDiseases() {
        return diseases;
    }

    /**
     * @param diseases the diseases to set
     */
    public void setDiseases(List<String> diseases) {
        this.diseases = diseases;
    }

    /**
     * @return the image
     */
    public StreamedContent getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(StreamedContent image) {
        this.image = image;
    }
    
}
