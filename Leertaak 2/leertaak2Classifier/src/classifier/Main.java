package classifier;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Christopher on 24/02/2016.
 */
public class Main {

    private static final String FILE_FEATURES = "src/test/OptiesText.txt";
    private static final String FILE_TRAINING_SET = "../TrainingSets/TrainingSet.txt";

    public static void main(String args[]){
        HashMap<String, FeatureType> hashMapFeatures = getFeaturesMap();
        HashMap<Item, String> hashMapTrainingsSet = getTrainingSet(hashMapFeatures);
        DecisionTree decisionTree = new DecisionTree(hashMapTrainingsSet, hashMapFeatures);
        System.out.println(decisionTree.toString());
        new View("Classifier", getFeaturesString());
    }

    private static HashMap<String, FeatureType> getFeaturesMap(){
        HashMap<String, FeatureType> hashMapFeatures = new HashMap<>();
        String[] stringFeatures = getFeaturesString();
        FeatureType featureType = new FeatureType("janee",new String[]{"1","0"});

        for(String feature : stringFeatures)
        {
            hashMapFeatures.put(feature, featureType);
        }

        return hashMapFeatures;
    }

    private static String[] getFeaturesString(){

        String[] features = new String[8];
        try {
            BufferedReader br = new BufferedReader(new FileReader(FILE_FEATURES));
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                features[i] = line;
                i++;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return features;
    }

    private static HashMap<Item, String> getTrainingSet(HashMap<String, FeatureType> features){
        HashMap<Item, String> hashMapTrainingSet = new HashMap<>();

        try  {
            BufferedReader br = new BufferedReader(new FileReader(FILE_TRAINING_SET));
            String line;

            while ((line = br.readLine()) != null) {
                String name = line.substring(0, line.indexOf(";"));

                Feature[] feature = new Feature[8];
                String[] stringFeatures = getFeaturesString();
                line = line.substring(line.indexOf(";"),line.length());
                for(int i = 0; i < features.size(); i++){
                    String value = line.substring(1, 2);
                    line = line.substring(2,line.length());

                    String featureName = stringFeatures[i];
                    FeatureType type = features.get(featureName);

                    feature[i] = new Feature(featureName, value, type);
                }

                Item item = new Item(name, feature);
                hashMapTrainingSet.put(item,line.substring(1));

            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return hashMapTrainingSet;
    }
}
