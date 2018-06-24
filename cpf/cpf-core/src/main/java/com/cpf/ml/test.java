package com.cpf.ml;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * Created by jieping on 2018-04-07
 */
public class test {

    public static void main(String[] args) throws Exception {
        String txt1 = "C:/Users/jieping/Desktop/txt1.txt";
        String txt2 = "C:/Users/jieping/Desktop/txt2.txt";
        String txt3 = "C:/Users/jieping/Desktop/txt3.txt";
        String txt4 = "C:/Users/jieping/Desktop/txt4.txt";
        String line = "";
        int i = 0;
        BufferedReader bufferedReader1 = new BufferedReader(new InputStreamReader(new FileInputStream(new File(txt1)),"gbk") );
        BufferedReader bufferedReader2 = new BufferedReader(new FileReader(txt2));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(txt3));
        BufferedWriter bufferedWriter1 = new BufferedWriter(new FileWriter(txt4));
        Map<String,String> map = Maps.newHashMap();
        List<String> result = Lists.newArrayList();
        int sum = 0;
        int exit = 0;
        int noExist = 0;
        while((line = bufferedReader1.readLine()) != null){
            line = line.trim();
            List<String> list = Lists.newArrayList(line.split("\t"));
            String studentId = list.get(0).trim();
            list.remove(0);
            map.put(studentId,Joiner.on(" ").join(list));
        }
        while((line = bufferedReader2.readLine()) != null){
            line = line.trim();
            sum++;
            if(map.containsKey(line)){
                result.add(line+" "+map.get(line));
            }else {
                noExist++;
                bufferedWriter1.write(line);
                bufferedWriter1.newLine();
            }
        }
        result.forEach((string)->{
            try {
                bufferedWriter.write(string);
                bufferedWriter.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        bufferedReader1.close();
        bufferedReader2.close();
        bufferedWriter.close();
        bufferedWriter1.close();
        System.out.println(sum);
        System.out.println(result.size());
        System.out.println(noExist);
    }
}
