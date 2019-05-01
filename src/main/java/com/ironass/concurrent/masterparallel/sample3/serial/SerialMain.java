package com.ironass.concurrent.masterparallel.sample3.serial;


import com.ironass.concurrent.masterparallel.sample3.BankMarketingLoader;
import com.ironass.concurrent.masterparallel.sample3.KnnClassifier;
import com.ironass.concurrent.masterparallel.sample3.dmain.BankMarketing;

import java.util.Date;
import java.util.List;

/**
 * @author lixin
 * @date 2019-05-01 11:46
 **/
public class SerialMain {

    public static void main(String[] args) {

        BankMarketingLoader loader = new BankMarketingLoader();
        List<BankMarketing> train = loader.load("data\\bank.data");
        System.out.println("Train: " + train.size());
        List<BankMarketing> test = loader.load("data\\bank.test");
        System.out.println("Test: " + test.size());
        double currentTime = 0d;
        int success = 0, mistakes = 0;

        int k = 10;
        if (args.length==1) {
            k = Integer.parseInt(args[0]);
        }

        success = 0;
        mistakes = 0;
        KnnClassifier classifier = new KnnClassifier(train, k);
        try {
            Date start, end;
            start = new Date();
            for (BankMarketing example : test) {
                String tag = classifier.classify(example);
                if (tag.equals(example.getTag())) {
                    success++;
                } else {
                    mistakes++;
                }
            }
            end = new Date();

            currentTime = end.getTime() - start.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("******************************************");
        System.out.println("Serial Classifier - K: " + k);
        System.out.println("Success: " + success);
        System.out.println("Mistakes: " + mistakes);
        System.out.println("Execution Time: " + (currentTime / 1000)
                + " seconds.");
        System.out.println("******************************************");
    }
}
