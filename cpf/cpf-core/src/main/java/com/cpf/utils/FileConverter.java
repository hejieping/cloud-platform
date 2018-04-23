package com.cpf.utils;

import com.cpf.constants.CpfCoreConstants;
import com.cpf.constants.ErrorConstants;
import com.cpf.exception.BusinessException;
import com.google.common.base.Joiner;
import org.apache.commons.lang3.StringUtils;
import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;

import java.io.*;

/**
 * Created by jieping on 2018-04-07
 */
public class FileConverter {
    /**
     * txt文件转换为csv格式
     * @param resource txt文件（带路径）
     * @param target   生成的csv文件名（带路径）
     */
    public static void txt2csv(String resource,String target)  {
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(resource));
            bufferedWriter = new BufferedWriter(new FileWriter(target));
            String readStr = bufferedReader.readLine();
            while(readStr != null){
                String writeStr = txtStr2csvStr(readStr);
                writeStr+="\r\n";
                bufferedWriter.write(writeStr);
                readStr = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new BusinessException(ErrorConstants.FILE_NOT_FOUND,e);
        } catch (IOException e) {
            throw new BusinessException(CpfCoreConstants.FILE_CONVERT_ERROR,e);
        }finally {
            if(bufferedReader != null){
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    throw new BusinessException(ErrorConstants.SYSTEM_RRROR,e);
                }
            }
            if(bufferedWriter != null){
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    throw new BusinessException(CpfCoreConstants.FILE_CONVERT_ERROR,e);
                }
            }
        }
    }

    /**
     * csv第一行需要注明属性名称！！！
     * csv文件转arff文件
     * @param resource txt文件（带路径）
     * @param target 生成的arff文件名（带路径）
     */
    public static void csv2arff(String resource,String target){
        // load CSV
        try {
            CSVLoader loader = new CSVLoader();
            loader.setNominalAttributes("9");
            loader.setSource(new File(resource));
            Instances data = loader.getDataSet();
            // save ARFF
            ArffSaver saver = new ArffSaver();
            saver.setInstances(data);
            saver.setFile(new File(target));
            saver.writeBatch();
        } catch (IOException e) {
            throw new BusinessException(CpfCoreConstants.FILE_CONVERT_ERROR,e);
        }
    }
    private static String txtStr2csvStr(String string){
        return Joiner.on(",").join(StringUtils.strip(string).split("\t"));
    }
}
