/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.ipn.escom.tomatoDiseasesClassifier.services;

import mx.ipn.escom.tomatoDiseasesClassifier.pojos.Leaf;

/**
 *
 * @author edgar
 */
public interface ClassifierService {
    public void classify(Leaf leaf);
}
