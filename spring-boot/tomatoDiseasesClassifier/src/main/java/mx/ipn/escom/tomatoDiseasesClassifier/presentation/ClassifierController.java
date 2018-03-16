/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.ipn.escom.tomatoDiseasesClassifier.presentation;


import javax.faces.bean.ManagedBean;
import mx.ipn.escom.tomatoDiseasesClassifier.pojos.Leaf;

/**
 *
 * @author edgar
 */
@ManagedBean
public class ClassifierController {
    
    private Leaf leaf;
    
    public String foo(){
        System.out.println("foo");
        return "Foo: " + this.leaf.getImage();
    }
    
}
