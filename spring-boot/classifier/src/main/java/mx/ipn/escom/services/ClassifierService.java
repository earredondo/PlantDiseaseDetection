/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.ipn.escom.services;

import mx.ipn.escom.pojos.Leaf;

/**
 *
 * @author edgar
 */
public interface ClassifierService {
    public String classify(Leaf leaf, int numResults);
}
