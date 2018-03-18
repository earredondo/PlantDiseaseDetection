/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.ipn.escom.services;

import mx.ipn.escom.classifier.Classifier;
import mx.ipn.escom.pojos.Leaf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author edgar
 */
@Service
public class ClassifierServiceImpl implements ClassifierService{
    
    @Autowired(required = true)
    private Classifier classifier;
    
    @Override
    public String classify(Leaf leaf){
        classifier.setImage(leaf.getImage());
        classifier.forward();
        return classifier.getResults();
    }
    
}
