package ml;

import weka.classifiers.bayes.NaiveBayes;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils;

/**
 * Created by jieping on 2018-04-07
 */
public class test {
    public static void main(String[] args) throws Exception {
        String readFile = "cpf-core/src/main/resources/abalone.txt";
        String writeFile = "cpf-core/src/main/resources/abalone.csv";
        String writeFile2 = "cpf-core/src/main/resources/abalone.arff";
        //FileConverter.txt2csv(readFile,writeFile);
        //FileConverter.csv2arff(writeFile,writeFile2);
        //load dataset
        ConverterUtils.DataSource source = new ConverterUtils.DataSource(writeFile2);
        Instances dataset = source.getDataSet();
        //set class index to the last attribute
        dataset.setClassIndex(dataset.numAttributes()-1);
        //create and build the classifier!
        NaiveBayes svm = new NaiveBayes();
        svm.buildClassifier(dataset);
        Instance instance = new DenseInstance(dataset.numAttributes());
        for(int i = 0; i < dataset.numAttributes();i++){
            instance.setValue(i++,1);
        }
        instance.setDataset(dataset);
        System.out.println(svm.classifyInstance(instance));

        //System.out.println(svm.classifyInstance();

//        Evaluation evaluation = new Evaluation(dataset);
//        evaluation.crossValidateModel(svm,dataset,10,new Random(1));
//        System.out.println(evaluation.toSummaryString());
//        SerializationHelper.write("cpf-core/src/main/resources/ibk.model",svm);
//        IBk classifier = (IBk)SerializationHelper.read("cpf-core/src/main/resources/ibk.model");
//        System.out.println(JSON.toJSONString(classifier.getOptions()));

    }
}
