/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.ipn.escom.classifier;

/**
 *
 * @author edgar
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.dnn.Dnn;
import org.opencv.dnn.Net;
import org.opencv.imgcodecs.Imgcodecs;

public class Classifier {
    
    private final List<String> classes;
    private final Net cnn;
    private Mat image;
    private Mat predictions;

    public Classifier(String labels, String prototxt, String caffeModel, String image){
        //System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        this.classes    = readLabels(labels);
        this.cnn        = readCaffeModel(prototxt, caffeModel);
        this.image      = Imgcodecs.imread(image);;
    }
    
    public Classifier(String labels, String prototxt, String caffeModel){
        //System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        this.classes    = readLabels(labels);
        this.cnn        = readCaffeModel(prototxt, caffeModel);
        this.image      = null;
    }
    
    public Classifier(){
        //System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        this.classes    = readLabels(getClass().getResource("/synset_words.txt").getPath());
        this.cnn        = readCaffeModel(getClass().getResource("/deploy.prototxt").getPath(), getClass().getResource("/classifier.caffemodel").getPath());
        //this.image      = Imgcodecs.imread(getClass().getResource("/images/Healty_03.JPG").getPath());
    }
    
    private List<String> readLabels(String labelsPath){
        List<String> labels = new ArrayList();
        try{
            Scanner sc = new Scanner(new File(labelsPath));
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                labels.add( line.substring( line.indexOf(" ") + 1 ).split(",")[0] );
            }
        }catch(FileNotFoundException fnfe){
            fnfe.printStackTrace();
        }
        return labels;
    }
    
    private Net readCaffeModel(String prototxt, String caffeModel){
        return Dnn.readNetFromCaffe(prototxt, caffeModel);
    }
    
    public void forward(){
        //Mat rawImage = Imgcodecs.imread(this.image);
        Mat inputBlob = Dnn.blobFromImage(this.image, 1.0, new Size(227, 227), new Scalar(104, 117, 123), false);
        this.cnn.setInput(inputBlob);
        this.predictions = cnn.forward();
    }
    
    public static List<Integer> argsort(final List<Double> a) {
        return argsort(a, true);
    }

    public static List<Integer> argsort(final List<Double> a, final boolean ascending) {
        List<Integer> indexes = new ArrayList();
        for (int i = 0; i < a.size(); i++) {
            indexes.add(i);
        }
        Collections.sort(indexes, (final Integer i1, final Integer i2) -> (ascending ? 1 : -1) * Double.compare(a.get(i1), a.get(i2)));
        return indexes;
    }
    
    public String getResults(int numResults){
        String results = "";
        
        double len = this.predictions.size().width;
        List<Double> probs = new ArrayList();
        
        for(int i = 0; i < len; i++){
            probs.add(this.predictions.get(0, i)[0]);
        }
        List<Integer> idxs = Classifier.argsort(probs, false);
        
        for(int i = 0; i < numResults; i ++){
            results += "[INFO] " + (i + 1) + ". label: " + this.classes.get(idxs.get(i)) + ", probability: " + probs.get(idxs.get(i)) + "\n";
        }
        
        return results;
    }
    
    public void printResults(int numResults){
        System.out.println(getResults(numResults));
    }
    
    public void setImage(byte[] image){
        this.image = Imgcodecs.imdecode(new MatOfByte(image), Imgcodecs.CV_LOAD_IMAGE_UNCHANGED);
    }
    
    /*
    public static void main(String[] args) {
        //Classifier classifier = new Classifier(args[0], args[1], args[2], args[3]);
        Classifier classifier = new Classifier();
        classifier.forward();
        classifier.printResults();
    }
    */
}