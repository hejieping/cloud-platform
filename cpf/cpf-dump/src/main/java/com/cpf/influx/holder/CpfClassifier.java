package com.cpf.influx.holder;

import lombok.Data;
import weka.classifiers.Classifier;

/**
 * @author jieping
 * @create 2018-04-27 20:59
 * @desc 分类器
 **/
@Data
public class CpfClassifier {
    private Double weight;
    private Classifier classifier;
}
