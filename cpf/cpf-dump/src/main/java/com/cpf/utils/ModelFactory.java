package com.cpf.utils;

import com.cpf.constants.ModelTypeEnum;
import com.cpf.constants.OptionTypeEnum;
import com.cpf.mysql.manager.DO.ModelDO;
import com.cpf.mysql.manager.DO.ModelOptionDO;
import com.cpf.mysql.manager.DO.ModelOptionsDO;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.Collections;
import java.util.List;
import java.util.Map;
/**
 * @author jieping
 * @create 2018-04-08
 * @desc 模型工厂，产生指定模型的类
 **/
public class ModelFactory {
    /**
     * 算法模型的可选参数
     */
    private static Map<String,List<ModelOptionDO>> optionsMap = Maps.newHashMap();

    static {
        //初始化模型的参数
        optionsMap.put(ModelTypeEnum.IBk.toString(),Collections.unmodifiableList(getIBk()));
        optionsMap.put(ModelTypeEnum.J48.toString(),Collections.unmodifiableList(getJ48()));
        optionsMap.put(ModelTypeEnum.LMT.toString(),Collections.unmodifiableList(getLMT()));
        optionsMap.put(ModelTypeEnum.SMO.toString(),Collections.unmodifiableList(getSMO()));
        optionsMap.put(ModelTypeEnum.ADABOOST_M1.toString(),Collections.unmodifiableList(getAdaboostM1()));
        optionsMap.put(ModelTypeEnum.Bagging.toString(),Collections.unmodifiableList(getBagging()));
        optionsMap.put(ModelTypeEnum.LOGIT_BOOST.toString(),Collections.unmodifiableList(getLogitBoost()));
        optionsMap.put(ModelTypeEnum.STACKING.toString(),Collections.unmodifiableList(getSTACKING()));
        optionsMap.put(ModelTypeEnum.LOGISTIC.toString(),Collections.unmodifiableList(getLOGISTIC()));
        optionsMap.put(ModelTypeEnum.JRIP.toString(),Collections.unmodifiableList(getJRIP()));
        optionsMap.put(ModelTypeEnum.PART.toString(),Collections.unmodifiableList(getPART()));
        optionsMap.put(ModelTypeEnum.NAIVE_BAYES.toString(),Collections.unmodifiableList(getBAYES()));
        optionsMap.put(ModelTypeEnum.DECISION_STUMP.toString(),Collections.unmodifiableList(getDecisionStump()));
        optionsMap.put(ModelTypeEnum.ONE_R.toString(),Collections.unmodifiableList(getOneR()));

    }
    private static Map<String,String> getClassifierMap(){
        Map<String,String> classifierMap = Maps.newHashMap();
        classifierMap.put("weka.classifiers.meta.AdaBoostM1","AdaBoostM1");
        classifierMap.put("weka.classifiers.meta.AdditiveRegression","AdditiveRegression");
        classifierMap.put("weka.classifiers.meta.AttributeSelectedClassifier","AttributeSelectedClassifier");
        classifierMap.put("weka.classifiers.bayes.net.BIFReader","BIFReader");
        classifierMap.put("weka.classifiers.meta.Bagging","Bagging");
        classifierMap.put("weka.classifiers.bayes.net.BayesNetGenerator","BayesNetGenerator");
        classifierMap.put("weka.classifiers.meta.CostSensitiveClassifier","CostSensitiveClassifier");
        classifierMap.put("weka.classifiers.trees.DecisionStump","DecisionStump");
        classifierMap.put("weka.classifiers.rules.DecisionTable","DecisionTable");
        classifierMap.put("weka.classifiers.bayes.net.EditableBayesNet","EditableBayesNet");
        classifierMap.put("weka.classifiers.meta.FilteredClassifier","FilteredClassifier");
        classifierMap.put("weka.classifiers.functions.GaussianProcesses","GaussianProcesses");
        classifierMap.put("weka.classifiers.trees.HoeffdingTree","HoeffdingTree");
        classifierMap.put("weka.classifiers.lazy.IBk","IBk");
        classifierMap.put("weka.classifiers.misc.InputMappedClassifier","InputMappedClassifier");
        classifierMap.put("weka.classifiers.meta.IterativeClassifierOptimizer","IterativeClassifierOptimizer");
        classifierMap.put("weka.classifiers.trees.J48","J48");
        classifierMap.put("weka.classifiers.rules.JRip","JRip");
        classifierMap.put("weka.classifiers.lazy.KStar","KStar");
        classifierMap.put("weka.classifiers.trees.LMT"," LMT");
        classifierMap.put("weka.classifiers.lazy.LWL","LWL");
        classifierMap.put("weka.classifiers.functions.LinearRegression","LinearRegression");
        classifierMap.put("weka.classifiers.functions.Logistic","Logistic");
        classifierMap.put("weka.classifiers.trees.lmt.LogisticBase","LogisticBase");
        classifierMap.put("weka.classifiers.meta.LogitBoost","LogitBoost");
        classifierMap.put("weka.classifiers.trees.M5P","M5P");
        classifierMap.put("weka.classifiers.rules.M5Rules","M5Rules");
        classifierMap.put("weka.classifiers.meta.MultiClassClassifier","MultiClassClassifier");
        classifierMap.put("weka.classifiers.meta.MultiClassClassifierUpdateable","MultiClassClassifierUpdateable");
        classifierMap.put("weka.classifiers.meta.MultiScheme","MultiScheme");
        classifierMap.put("weka.classifiers.functions.MultilayerPerceptron","MultilayerPerceptron");
        classifierMap.put("weka.classifiers.bayes.NaiveBayes","NaiveBayes");
        classifierMap.put("weka.classifiers.bayes.NaiveBayesMultinomial","NaiveBayesMultinomial");
        classifierMap.put("weka.classifiers.bayes.NaiveBayesMultinomialText","NaiveBayesMultinomialText");
        classifierMap.put("weka.classifiers.bayes.NaiveBayesMultinomialUpdateable","NaiveBayesMultinomialUpdateable");
        classifierMap.put("weka.classifiers.bayes.NaiveBayesUpdateable","NaiveBayesUpdateable");
        classifierMap.put("weka.classifiers.pmml.consumer.NeuralNetwork","NeuralNetwork");
        classifierMap.put("weka.classifiers.rules.OneR","OneR");
        classifierMap.put("weka.classifiers.rules.PART","PART");
        classifierMap.put("weka.classifiers.trees.REPTree","REPTree");
        classifierMap.put("weka.classifiers.meta.RandomCommittee","RandomCommittee");
        classifierMap.put("weka.classifiers.trees.RandomForest","RandomForest");
        classifierMap.put("weka.classifiers.meta.RandomSubSpace","RandomSubSpace");
        classifierMap.put("weka.classifiers.trees.RandomTree","RandomTree");
        classifierMap.put("weka.classifiers.meta.RandomizableFilteredClassifier","RandomizableFilteredClassifier");
        classifierMap.put("weka.classifiers.meta.RegressionByDiscretization","RegressionByDiscretization");
        classifierMap.put("weka.classifiers.functions.SGD","SGD");
        classifierMap.put("weka.classifiers.functions.SGDText","SGDText");
        classifierMap.put("weka.classifiers.functions.SMO","SMO");
        classifierMap.put("weka.classifiers.functions.SMOreg","SMOreg");
        classifierMap.put("weka.classifiers.misc.SerializedClassifier","SerializedClassifier");
        classifierMap.put("weka.classifiers.functions.SimpleLinearRegression","SimpleLinearRegression");
        classifierMap.put("weka.classifiers.functions.SimpleLogistic","SimpleLogistic");
        classifierMap.put("weka.classifiers.SingleClassifierEnhancer","SingleClassifierEnhancer");
        classifierMap.put("weka.classifiers.meta.Stacking","Stacking");
        classifierMap.put("weka.classifiers.meta.Vote","Vote");
        classifierMap.put("weka.classifiers.functions.VotedPerceptron","VotedPerceptron");
        classifierMap.put("weka.classifiers.meta.WeightedInstancesHandlerWrapper","WeightedInstancesHandlerWrapper");
        classifierMap.put("weka.classifiers.rules.ZeroR","ZeroR");
        return classifierMap;
    }
    /**
     * 根据模型名称和模型类型产生一个模型类，
     * @param name 模型名称
     * @param modelType 模型类型
     * @return
     */
    public static ModelDO getModel(String name,String modelType){
        ModelDO modelDO = new ModelDO();
        modelDO.setWeight(0D);
        modelDO.setName(name);
        ModelTypeEnum modelTypeEnum = ModelTypeEnum.valueOf(modelType);
        ModelOptionsDO modelOptionsDO = new ModelOptionsDO();
        modelOptionsDO.setModelType(modelTypeEnum);
        modelOptionsDO.setOptions(optionsMap.get(modelTypeEnum.toString()));
        modelDO.setConfig(modelOptionsDO);
        return modelDO;
    }

    private static List<ModelOptionDO> getIBk(){
        List<ModelOptionDO> list = Lists.newArrayList();
        ModelOptionDO modelOptionDO = new ModelOptionDO();
        modelOptionDO.setKey("-I");
        modelOptionDO.setDesc("通过距离的倒数来衡量邻居(衡量邻居的方法不能两个全开)");
        modelOptionDO.setValueType(OptionTypeEnum.BOOLEAN);
        ModelOptionDO modelOptionDO7= new ModelOptionDO();
        modelOptionDO7.setKey("-F");
        modelOptionDO7.setDesc("通过 1-距离 来衡量邻居(衡量邻居的方法不能两个全开)");
        modelOptionDO7.setValueType(OptionTypeEnum.BOOLEAN);
        ModelOptionDO modelOptionDO8=new ModelOptionDO();
        modelOptionDO8.setKey("-K");
        modelOptionDO8.setDesc("分类中使用的最近邻居（k）的数量(默认 = 1)");
        modelOptionDO8.setValueType(OptionTypeEnum.INTEGER);
        ModelOptionDO modelOptionDO9=new ModelOptionDO();
        modelOptionDO9.setKey("-E");
        modelOptionDO9.setDesc("使用带数字预测的-X选项时，使用最小化均方误差而不是平均绝对误差");
        modelOptionDO9.setValueType(OptionTypeEnum.BOOLEAN);
        ModelOptionDO modelOptionDO10 = new ModelOptionDO();
        modelOptionDO10.setKey("-W");
        modelOptionDO10.setDesc("训练实例的最大数量。训练实例按照FIFO被丢弃.");
        modelOptionDO10.setValueType(OptionTypeEnum.INTEGER);
        ModelOptionDO modelOptionDO11 = new ModelOptionDO();
        modelOptionDO11.setKey("-X");
        modelOptionDO11.setDesc("在训练数据上选择1和使用hold-one-out评估指定的k值之间的最近邻居数");
        modelOptionDO11.setValueType(OptionTypeEnum.BOOLEAN);
        ModelOptionDO modelOptionDO12 = new ModelOptionDO();
        modelOptionDO12.setKey("-A");
        modelOptionDO12.setDesc("最近邻居搜索算法 (默认:LinearNNSearch)");
        modelOptionDO12.setValueType(OptionTypeEnum.ENUM);
        Map<String,String> extensionMap = Maps.newHashMap();
        extensionMap.put("weka.core.neighboursearch.LinearNNSearch","LinearNNSearc");
        extensionMap.put("weka.core.neighboursearch.BallTree","BallTree");
        extensionMap.put("weka.core.neighboursearch.CoverTree","CoverTree");
        extensionMap.put("weka.core.neighboursearch.KDTree","KDTree");
        extensionMap.put("weka.core.neighboursearch.FilteredNeighbourSearch","FilteredNeighbourSearch");
        modelOptionDO12.setExtension(extensionMap);
        list.add(modelOptionDO);
        list.add(modelOptionDO7);
        list.add(modelOptionDO8);
        list.add(modelOptionDO9);
        list.add(modelOptionDO10);
        list.add(modelOptionDO11);
        list.add(modelOptionDO12);
        return list;
    }
    private static List<ModelOptionDO> getJ48(){
        List<ModelOptionDO> list = Lists.newArrayList();
        ModelOptionDO modelOptionDO13 = new ModelOptionDO();
        modelOptionDO13.setKey("-U");
        modelOptionDO13.setDesc("使用未修剪的树.");
        modelOptionDO13.setValueType(OptionTypeEnum.BOOLEAN);
        ModelOptionDO modelOptionDO14 = new ModelOptionDO();
        modelOptionDO14.setKey("-O");
        modelOptionDO14.setDesc("树不折叠");
        modelOptionDO14.setValueType(OptionTypeEnum.BOOLEAN);
        ModelOptionDO modelOptionDO17 = new ModelOptionDO();
        modelOptionDO17.setKey("-R");
        modelOptionDO17.setDesc("使用减少的错误修剪（使用未修剪的树 关闭时使用）");
        modelOptionDO17.setValueType(OptionTypeEnum.BOOLEAN);
        ModelOptionDO modelOptionDO15 = new ModelOptionDO();
        modelOptionDO15.setKey("-C");
        modelOptionDO15.setDesc("设置修剪的置信度阈值(默认 0.25)（使用未修剪的树 关闭时使用）");
        modelOptionDO15.setValueType(OptionTypeEnum.DOUBLE);
        ModelOptionDO modelOptionDO16 = new ModelOptionDO();
        modelOptionDO16.setKey("-M");
        modelOptionDO16.setDesc("设置每个叶子的最小实例数(默认 2)");
        modelOptionDO16.setValueType(OptionTypeEnum.INTEGER);

        ModelOptionDO modelOptionDO18 = new ModelOptionDO();
        modelOptionDO18.setKey("-N");
        modelOptionDO18.setDesc("树折叠次数（用于减少错误修剪）（使用未修剪的树 关闭时使用）");
        modelOptionDO18.setValueType(OptionTypeEnum.INTEGER);
        ModelOptionDO modelOptionDO19 = new ModelOptionDO();
        modelOptionDO19.setKey("-B");
        modelOptionDO19.setDesc("仅使用二分法分割.");
        modelOptionDO19.setValueType(OptionTypeEnum.BOOLEAN);
        ModelOptionDO modelOptionDO20 = new ModelOptionDO();
        modelOptionDO20.setKey("-S");
        modelOptionDO20.setDesc("不执行子树升级.");
        modelOptionDO20.setValueType(OptionTypeEnum.BOOLEAN);
        ModelOptionDO modelOptionDO21 = new ModelOptionDO();
        modelOptionDO21.setKey("-L");
        modelOptionDO21.setDesc("树木建成后不清理.");
        modelOptionDO21.setValueType(OptionTypeEnum.BOOLEAN);
        ModelOptionDO modelOptionDO22 = new ModelOptionDO();
        modelOptionDO22.setKey("-A");
        modelOptionDO22.setDesc("使用拉普拉斯平滑预测概率.");
        modelOptionDO22.setValueType(OptionTypeEnum.BOOLEAN);
        ModelOptionDO modelOptionDO23 = new ModelOptionDO();
        modelOptionDO23.setKey("-J");
        modelOptionDO23.setDesc("不使用MDL更正数字属性上的信息增益.");
        modelOptionDO23.setValueType(OptionTypeEnum.BOOLEAN);
        ModelOptionDO modelOptionDO24 = new ModelOptionDO();
        modelOptionDO24.setKey("-Q");
        modelOptionDO24.setDesc("随机数据混洗的种子（默认1）");
        modelOptionDO24.setValueType(OptionTypeEnum.INTEGER);
        list.add(modelOptionDO13);
        list.add(modelOptionDO14);
        list.add(modelOptionDO15);
        list.add(modelOptionDO16);
        list.add(modelOptionDO17);
        list.add(modelOptionDO18);
        list.add(modelOptionDO19);
        list.add(modelOptionDO20);
        list.add(modelOptionDO21);
        list.add(modelOptionDO22);
        list.add(modelOptionDO23);
        list.add(modelOptionDO24);
        return list;
    }
    private static List<ModelOptionDO> getLMT(){
        List<ModelOptionDO> list = Lists.newArrayList();
        ModelOptionDO modelOptionDO25 = new ModelOptionDO();
        modelOptionDO25.setKey("-B");
        modelOptionDO25.setDesc("二进制分割（将标称属性转换为二进制分割）");
        modelOptionDO25.setValueType(OptionTypeEnum.BOOLEAN);
        ModelOptionDO modelOptionDO26 = new ModelOptionDO();
        modelOptionDO26.setKey("-R");
        modelOptionDO26.setDesc("分割残差而不是类值");
        modelOptionDO26.setValueType(OptionTypeEnum.BOOLEAN);
        ModelOptionDO modelOptionDO27 = new ModelOptionDO();
        modelOptionDO27.setKey("-C");
        modelOptionDO27.setDesc("为所有节点提升交叉验证");
        modelOptionDO27.setValueType(OptionTypeEnum.BOOLEAN);
        ModelOptionDO modelOptionDO28 = new ModelOptionDO();
        modelOptionDO28.setKey("-P");
        modelOptionDO28.setDesc("使用概率上的错误作为LogitBoost的停止条件");
        modelOptionDO28.setValueType(OptionTypeEnum.BOOLEAN);
        ModelOptionDO modelOptionDO29 = new ModelOptionDO();
        modelOptionDO29.setKey("-I");
        modelOptionDO29.setDesc("为LogitBoost设置固定的迭代次数（而不是使用交叉验证）");
        modelOptionDO29.setValueType(OptionTypeEnum.INTEGER);
        ModelOptionDO modelOptionDO30 = new ModelOptionDO();
        modelOptionDO30.setKey("-M");
        modelOptionDO30.setDesc("设置节点可以分割的最小实例数（默认值为15）");
        modelOptionDO30.setValueType(OptionTypeEnum.INTEGER);
        ModelOptionDO modelOptionDO31 = new ModelOptionDO();
        modelOptionDO31.setKey("-W");
        modelOptionDO31.setDesc("为LogitBoost设置重量修剪测试版。 设置为0（默认）不进行重量修整。");
        modelOptionDO31.setValueType(OptionTypeEnum.DOUBLE);
        ModelOptionDO modelOptionDO32 = new ModelOptionDO();
        modelOptionDO32.setKey("-A");
        modelOptionDO32.setDesc("选择最佳迭代使用AIC");
        modelOptionDO32.setValueType(OptionTypeEnum.BOOLEAN);
        list.add(modelOptionDO25);
        list.add(modelOptionDO26);
        list.add(modelOptionDO27);
        list.add(modelOptionDO28);
        list.add(modelOptionDO29);
        list.add(modelOptionDO30);
        list.add(modelOptionDO31);
        list.add(modelOptionDO32);
        return list;
    }
    private static List<ModelOptionDO> getSMO(){
        List<ModelOptionDO> list = Lists.newArrayList();
        ModelOptionDO modelOptionDO33 = new ModelOptionDO();
        modelOptionDO33.setKey("-N");
        modelOptionDO33.setDesc("训练数据处理");
        modelOptionDO33.setValueType(OptionTypeEnum.ENUM);
        Map<String,String> extesionsMap = Maps.newHashMap();
        extesionsMap.put("0","规范化");
        extesionsMap.put("1","标准化");
        extesionsMap.put("2","不采用");
        modelOptionDO33.setExtension(extesionsMap);
        ModelOptionDO modelOptionDO34 = new ModelOptionDO();
        modelOptionDO34.setKey("-L");
        modelOptionDO34.setDesc(" 公差参数(默认 0.001)");
        modelOptionDO34.setValueType(OptionTypeEnum.DOUBLE);
        ModelOptionDO modelOptionDO35 = new ModelOptionDO();
        modelOptionDO35.setKey("-P");
        modelOptionDO35.setDesc("舍入误差的小数点");
        modelOptionDO35.setValueType(OptionTypeEnum.DOUBLE);
        ModelOptionDO modelOptionDO36 = new ModelOptionDO();
        modelOptionDO36.setKey("-M");
        modelOptionDO36.setDesc("将校准模型拟合到SVM输出.");
        modelOptionDO36.setValueType(OptionTypeEnum.BOOLEAN);
        ModelOptionDO modelOptionDO37 = new ModelOptionDO();
        modelOptionDO37.setKey("-V");
        modelOptionDO37.setDesc("内部交叉验证的折叠次数");
        modelOptionDO37.setValueType(OptionTypeEnum.INTEGER);
        ModelOptionDO modelOptionDO38 = new ModelOptionDO();
        modelOptionDO38.setKey("-W");
        modelOptionDO38.setDesc("随机数种子（默认1）");
        modelOptionDO38.setValueType(OptionTypeEnum.INTEGER);
        ModelOptionDO modelOptionDO39 = new ModelOptionDO();
        modelOptionDO39.setKey("-K");
        modelOptionDO39.setDesc("核函数(默认:PolyKernel)");
        Map<String,String> extesionsMap2 = Maps.newHashMap();
        extesionsMap2.put("weka.classifiers.functions.supportVector.PolyKernel","PolyKernel");
        extesionsMap2.put("weka.classifiers.functions.supportVector.NormalizedPolyKernel","NormalizedPolyKernel");
        extesionsMap2.put("weka.classifiers.functions.supportVector.Puk","Puk");
        extesionsMap2.put("weka.classifiers.functions.supportVector.RBFKernel","RBFKernel");
        modelOptionDO39.setExtension(extesionsMap2);
        modelOptionDO39.setValueType(OptionTypeEnum.ENUM);
        ModelOptionDO modelOptionDO40= new ModelOptionDO();
        modelOptionDO40.setKey("-C");
        modelOptionDO40.setDesc("复杂度常数C（默认1）");
        modelOptionDO40.setValueType(OptionTypeEnum.DOUBLE);
        list.add(modelOptionDO33);
        list.add(modelOptionDO34);
        list.add(modelOptionDO35);
        list.add(modelOptionDO36);
        list.add(modelOptionDO37);
        list.add(modelOptionDO38);
        list.add(modelOptionDO39);
        list.add(modelOptionDO40);
        return list;
    }
    private static List<ModelOptionDO> getAdaboostM1(){
        List<ModelOptionDO> list = Lists.newArrayList();
        ModelOptionDO modelOptionDO42 = new ModelOptionDO();
        modelOptionDO42.setKey("-P");
        modelOptionDO42.setDesc(" 权重训练的百分比（默认为100，减至约90加速）");
        modelOptionDO42.setValueType(OptionTypeEnum.INTEGER);
        ModelOptionDO modelOptionDO43 = new ModelOptionDO();
        modelOptionDO43.setKey("-Q");
        modelOptionDO43.setDesc("使用重新采样进行提升");
        modelOptionDO43.setValueType(OptionTypeEnum.BOOLEAN);
        ModelOptionDO modelOptionDO44 = new ModelOptionDO();
        modelOptionDO44.setKey("-S");
        modelOptionDO44.setDesc("随机数种子（默认为1）");
        modelOptionDO44.setValueType(OptionTypeEnum.INTEGER);
        ModelOptionDO modelOptionDO45 = new ModelOptionDO();
        modelOptionDO45.setKey("-I");
        modelOptionDO45.setDesc("迭代次数");
        modelOptionDO45.setValueType(OptionTypeEnum.INTEGER);
        ModelOptionDO modelOptionDO47 = new ModelOptionDO();
        modelOptionDO47.setKey("-W");
        modelOptionDO47.setDesc(" 基分类器(默认:DecisionStump)");
        modelOptionDO47.setValueType(OptionTypeEnum.ENUM);
        modelOptionDO47.setExtension(getClassifierMap());
        list.add(modelOptionDO42);
        list.add(modelOptionDO43);
        list.add(modelOptionDO44);
        list.add(modelOptionDO45);
        list.add(modelOptionDO47);
        return list;
    }
    private static List<ModelOptionDO> getBagging(){
        List<ModelOptionDO> list = Lists.newArrayList();
        ModelOptionDO modelOptionDO48 = new ModelOptionDO();
        modelOptionDO48.setKey("-P");
        modelOptionDO48.setDesc("每个包的大小，以训练集大小的百分比表示");
        modelOptionDO48.setValueType(OptionTypeEnum.INTEGER);
        ModelOptionDO modelOptionDO49 = new ModelOptionDO();
        modelOptionDO49.setKey("-O");
        modelOptionDO49.setDesc("计算出包错误");
        modelOptionDO49.setValueType(OptionTypeEnum.BOOLEAN);
        ModelOptionDO modelOptionDO50 = new ModelOptionDO();
        modelOptionDO50.setKey("-S");
        modelOptionDO50.setDesc("随机数种子（默认1）");
        modelOptionDO50.setValueType(OptionTypeEnum.INTEGER);
        ModelOptionDO modelOptionDO51 = new ModelOptionDO();
        modelOptionDO51.setKey("-I");
        modelOptionDO51.setDesc("迭代次数（默认值10）");
        modelOptionDO51.setValueType(OptionTypeEnum.INTEGER);
        ModelOptionDO modelOptionDO53 = new ModelOptionDO();
        modelOptionDO53.setKey("-W");
        modelOptionDO53.setDesc("基本分类器 (默认:REPTree）");
        modelOptionDO53.setValueType(OptionTypeEnum.ENUM);
        modelOptionDO53.setExtension(getClassifierMap());
        ModelOptionDO modelOptionDO54 = new ModelOptionDO();
        modelOptionDO54.setKey("-M");
        modelOptionDO54.setDesc(" 设置每个叶子的最小实例数（默认值为2）");
        modelOptionDO54.setValueType(OptionTypeEnum.INTEGER);
        ModelOptionDO modelOptionDO55 = new ModelOptionDO();
        modelOptionDO55.setKey("-V");
        modelOptionDO55.setDesc("设置拆分的训练方差的最小数值类别方差比例（默认 0.001）");
        modelOptionDO55.setValueType(OptionTypeEnum.DOUBLE);
        ModelOptionDO modelOptionDO56 = new ModelOptionDO();
        modelOptionDO56.setKey("-N");
        modelOptionDO56.setDesc("减少错误修剪的折叠数量（默认3）。");
        modelOptionDO56.setValueType(OptionTypeEnum.INTEGER);
        ModelOptionDO modelOptionDO58 = new ModelOptionDO();
        modelOptionDO58.setKey("-L");
        modelOptionDO58.setDesc("最大树深度（默认-1，无最大值）");
        modelOptionDO58.setValueType(OptionTypeEnum.INTEGER);
        list.add(modelOptionDO48);
        list.add(modelOptionDO49);
        list.add(modelOptionDO50);
        list.add(modelOptionDO51);
        list.add(modelOptionDO53);
        list.add(modelOptionDO54);
        list.add(modelOptionDO55);
        list.add(modelOptionDO56);
        list.add(modelOptionDO58);
        return list;
    }
    private static List<ModelOptionDO> getLogitBoost(){
        List<ModelOptionDO> list = Lists.newArrayList();
        ModelOptionDO modelOptionDO60 = new ModelOptionDO();
        modelOptionDO60.setKey("-Q");
        modelOptionDO60.setDesc("使用重新采样");
        modelOptionDO60.setValueType(OptionTypeEnum.BOOLEAN);
        ModelOptionDO modelOptionDO61 = new ModelOptionDO();
        modelOptionDO61.setKey("-P");
        modelOptionDO61.setDesc("在基础训练上的权重百分比。 （默认100，减少到90左右加速）");
        modelOptionDO61.setValueType(OptionTypeEnum.INTEGER);
        ModelOptionDO modelOptionDO62 = new ModelOptionDO();
        modelOptionDO62.setKey("-L");
        modelOptionDO62.setDesc("相似度提高的阈值。 （默认-Double.MAX_VALUE）");
        modelOptionDO62.setValueType(OptionTypeEnum.DOUBLE);
        ModelOptionDO modelOptionDO63 = new ModelOptionDO();
        modelOptionDO63.setKey("-H");
        modelOptionDO63.setDesc("收缩参数（默认1）");
        modelOptionDO63.setValueType(OptionTypeEnum.DOUBLE);
        ModelOptionDO modelOptionDO64 = new ModelOptionDO();
        modelOptionDO64.setKey("-Z");
        modelOptionDO64.setDesc("最大响应阈值（默认值3）");
        modelOptionDO64.setValueType(OptionTypeEnum.DOUBLE);
        ModelOptionDO modelOptionDO65 = new ModelOptionDO();
        modelOptionDO65.setKey("-O");
        modelOptionDO65.setDesc("线程池的大小（默认1）");
        modelOptionDO65.setValueType(OptionTypeEnum.INTEGER);
        ModelOptionDO modelOptionDO66 = new ModelOptionDO();
        modelOptionDO66.setKey("-E");
        modelOptionDO66.setDesc("用于批量预测的线程数量，应该大于线程池的大小（默认值为1）");
        modelOptionDO66.setValueType(OptionTypeEnum.INTEGER);
        ModelOptionDO modelOptionDO67 = new ModelOptionDO();
        modelOptionDO67.setKey("-S");
        modelOptionDO67.setDesc("随机数种子（默认1）");
        modelOptionDO67.setValueType(OptionTypeEnum.INTEGER);
        ModelOptionDO modelOptionDO68 = new ModelOptionDO();
        modelOptionDO68.setKey("-I");
        modelOptionDO68.setDesc("迭代次数（默认值10）");
        modelOptionDO68.setValueType(OptionTypeEnum.INTEGER);
        ModelOptionDO modelOptionDO69 = new ModelOptionDO();
        modelOptionDO69.setKey("-W");
        modelOptionDO69.setDesc("基分类器(默认: DecisionStump)");
        modelOptionDO69.setValueType(OptionTypeEnum.ENUM);
        modelOptionDO69.setExtension(getClassifierMap());
        list.add(modelOptionDO60);
        list.add(modelOptionDO61);
        list.add(modelOptionDO62);
        list.add(modelOptionDO63);
        list.add(modelOptionDO64);
        list.add(modelOptionDO65);
        list.add(modelOptionDO66);
        list.add(modelOptionDO67);
        list.add(modelOptionDO68);
        list.add(modelOptionDO69);
        return list;
    }
    private static List<ModelOptionDO> getSTACKING(){
        List<ModelOptionDO> list = Lists.newArrayList();
        ModelOptionDO modelOptionDO70 = new ModelOptionDO();
        modelOptionDO70.setKey("-M");
        modelOptionDO70.setDesc("元分类器(默认: ZeroR");
        modelOptionDO70.setValueType(OptionTypeEnum.ENUM);
        modelOptionDO70.setExtension(getClassifierMap());
        ModelOptionDO modelOptionDO71 = new ModelOptionDO();
        modelOptionDO71.setKey("-X");
        modelOptionDO71.setDesc("设置交叉验证折叠的数量");
        modelOptionDO71.setValueType(OptionTypeEnum.INTEGER);
        ModelOptionDO modelOptionDO72 = new ModelOptionDO();
        modelOptionDO72.setKey("-S");
        modelOptionDO72.setDesc("随机数种子（默认1）");
        modelOptionDO72.setValueType(OptionTypeEnum.INTEGER);
        ModelOptionDO modelOptionDO73 = new ModelOptionDO();
        modelOptionDO73.setKey("-B");
        modelOptionDO73.setDesc("分类器,（默认：ZeroR）");
        modelOptionDO73.setValueType(OptionTypeEnum.ENUM);
        modelOptionDO73.setExtension(getClassifierMap());
        list.add(modelOptionDO71);
        list.add(modelOptionDO72);
        list.add(modelOptionDO70);
        list.add(modelOptionDO73);
        return list;
    }
    private static List<ModelOptionDO> getLOGISTIC(){
        //Logistic
        List<ModelOptionDO> list = Lists.newArrayList();
        ModelOptionDO modelOptionDO76 = new ModelOptionDO();
        modelOptionDO76.setKey("-R");
        modelOptionDO76.setDesc("对数近似的岭值。");
        modelOptionDO76.setValueType(OptionTypeEnum.DOUBLE);
        ModelOptionDO modelOptionDO77 = new ModelOptionDO();
        modelOptionDO77.setKey("-M");
        modelOptionDO77.setDesc("最大迭代次数（默认-1，直到收敛）。");
        modelOptionDO77.setValueType(OptionTypeEnum.INTEGER);
        list.add(modelOptionDO76);
        list.add(modelOptionDO77);
        return list;
    }
    private static List<ModelOptionDO> getJRIP(){
        //JRip()
        List<ModelOptionDO> list = Lists.newArrayList();
        ModelOptionDO modelOptionDO78= new ModelOptionDO();
        modelOptionDO78.setKey("-F");
        modelOptionDO78.setDesc("设置REPOne fold的折叠次数用作剪枝集（默认3）");
        modelOptionDO78.setValueType(OptionTypeEnum.INTEGER);
        ModelOptionDO modelOptionDO79= new ModelOptionDO();
        modelOptionDO79.setKey("-N");
        modelOptionDO79.setDesc("设置split内的实例的最小权重（默认2.0）");
        modelOptionDO79.setValueType(OptionTypeEnum.DOUBLE);
        ModelOptionDO modelOptionDO80= new ModelOptionDO();
        modelOptionDO80.setKey("-O");
        modelOptionDO80.setDesc("设置优化运行次数。 （默认：2）");
        modelOptionDO80.setValueType(OptionTypeEnum.INTEGER);
        ModelOptionDO modelOptionDO82= new ModelOptionDO();
        modelOptionDO82.setKey("-S");
        modelOptionDO82.setDesc("随机种子（默认值：1）");
        modelOptionDO82.setValueType(OptionTypeEnum.INTEGER);
        ModelOptionDO modelOptionDO83= new ModelOptionDO();
        modelOptionDO83.setKey("-E");
        modelOptionDO83.setDesc("在停止条件中检查错误率> = 0.5（默认值：检查）");
        modelOptionDO83.setValueType(OptionTypeEnum.BOOLEAN);
        ModelOptionDO modelOptionDO84= new ModelOptionDO();
        modelOptionDO84.setKey("-P");
        modelOptionDO84.setDesc("使用修剪");
        modelOptionDO84.setValueType(OptionTypeEnum.BOOLEAN);
        list.add(modelOptionDO78);
        list.add(modelOptionDO79);
        list.add(modelOptionDO80);
        list.add(modelOptionDO82);
        list.add(modelOptionDO83);
        list.add(modelOptionDO84);
        return list;
    }
    private static List<ModelOptionDO> getOneR(){
        //OneR()
        List<ModelOptionDO> list = Lists.newArrayList();
        ModelOptionDO modelOptionDO85= new ModelOptionDO();
        modelOptionDO85.setKey("-B");
        modelOptionDO85.setDesc("存储桶中的最小对象数（默认值：6）");
        modelOptionDO85.setValueType(OptionTypeEnum.INTEGER);
        list.add(modelOptionDO85);
        return list;
    }
    private static List<ModelOptionDO> getPART(){
        List<ModelOptionDO> list = Lists.newArrayList();
        ModelOptionDO modelOptionDO86= new ModelOptionDO();
        modelOptionDO86.setKey("-C");
        modelOptionDO86.setDesc("修剪的置信度阈值（默认值为0.25）");
        modelOptionDO86.setValueType(OptionTypeEnum.DOUBLE);
        ModelOptionDO modelOptionDO87= new ModelOptionDO();
        modelOptionDO87.setKey("-M");
        modelOptionDO87.setDesc("设置每个叶子的最小对象数量（默认2）");
        modelOptionDO87.setValueType(OptionTypeEnum.INTEGER);
        ModelOptionDO modelOptionDO88= new ModelOptionDO();
        modelOptionDO88.setKey("-R");
        modelOptionDO88.setDesc("使用减少的错误修剪");
        modelOptionDO88.setValueType(OptionTypeEnum.BOOLEAN);
        ModelOptionDO modelOptionDO89= new ModelOptionDO();
        modelOptionDO89.setKey("-N");
        modelOptionDO89.setDesc("设置折叠次数以减少错误修剪（默认3）");
        modelOptionDO89.setValueType(OptionTypeEnum.INTEGER);
        ModelOptionDO modelOptionDO90= new ModelOptionDO();
        modelOptionDO90.setKey("-B");
        modelOptionDO90.setDesc("仅使用二分割。");
        modelOptionDO90.setValueType(OptionTypeEnum.BOOLEAN);
        ModelOptionDO modelOptionDO91= new ModelOptionDO();
        modelOptionDO91.setKey("-U");
        modelOptionDO91.setDesc("生成未修改的决策列表");
        modelOptionDO91.setValueType(OptionTypeEnum.BOOLEAN);
        ModelOptionDO modelOptionDO92= new ModelOptionDO();
        modelOptionDO92.setKey("-J");
        modelOptionDO92.setDesc("不使用MDL更正数字属性上的信息增益");
        modelOptionDO92.setValueType(OptionTypeEnum.BOOLEAN);
        ModelOptionDO modelOptionDO93= new ModelOptionDO();
        modelOptionDO93.setKey("-Q");
        modelOptionDO93.setDesc("随机数据混洗的种子（默认1）。");
        modelOptionDO93.setValueType(OptionTypeEnum.INTEGER);
        list.add(modelOptionDO86);
        list.add(modelOptionDO87);
        list.add(modelOptionDO88);
        list.add(modelOptionDO89);
        list.add(modelOptionDO90);
        list.add(modelOptionDO91);
        list.add(modelOptionDO92);
        list.add(modelOptionDO93);
        return list;
    }
    private static List<ModelOptionDO> getBAYES(){
        List<ModelOptionDO> list = Lists.newArrayList();
        ModelOptionDO modelOptionDO= new ModelOptionDO();
        modelOptionDO.setKey("-K");
        modelOptionDO.setDesc("使用内核密度估计器");
        modelOptionDO.setValueType(OptionTypeEnum.BOOLEAN);
        ModelOptionDO modelOptionDO1= new ModelOptionDO();
        modelOptionDO1.setKey("-D");
        modelOptionDO1.setDesc("使用受监督的离散化来处理数字属性");
        modelOptionDO1.setValueType(OptionTypeEnum.BOOLEAN);
        ModelOptionDO modelOptionDO2 = new ModelOptionDO();
        modelOptionDO2.setKey("-O");
        modelOptionDO2.setDesc("以旧格式显示模型（当有很多类时很好）");
        modelOptionDO2.setValueType(OptionTypeEnum.BOOLEAN);
        list.add(modelOptionDO);
        list.add(modelOptionDO1);
        list.add(modelOptionDO2);
        return list;
    }
    private static List<ModelOptionDO> getDecisionStump(){
        List<ModelOptionDO> list = Lists.newArrayList();
        ModelOptionDO modelOptionDO= new ModelOptionDO();
        modelOptionDO.setKey("-do-not-check-capabilities");
        modelOptionDO.setDesc("在构建分类器之前不会检查分类器容量");
        modelOptionDO.setValueType(OptionTypeEnum.BOOLEAN);
        ModelOptionDO modelOptionDO1= new ModelOptionDO();
        modelOptionDO1.setKey("-num-decimal-places");
        modelOptionDO1.setDesc("模型中输出数字的小数位数。");
        modelOptionDO1.setValueType(OptionTypeEnum.INTEGER);
        ModelOptionDO modelOptionDO2 = new ModelOptionDO();
        modelOptionDO2.setKey("-batch-size");
        modelOptionDO2.setDesc("批量预测所需的批量大小。");
        modelOptionDO2.setValueType(OptionTypeEnum.INTEGER);
        list.add(modelOptionDO);
        list.add(modelOptionDO1);
        list.add(modelOptionDO2);
        return list;
    }
    public static void main(String[] args){
        Map<String,List<ModelOptionDO>> map = ModelFactory.optionsMap;
        map.forEach((key,value)->{
            System.out.println(key);
            value.forEach(item->{
                System.out.println(item.getKey()+"  "+item.getDesc());
            });
            System.out.println("=================");
        });
    }
}
