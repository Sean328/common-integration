package com.ironass.concurrent.mcp.sample3;


import com.ironass.concurrent.mcp.sample3.dmain.BankMarketing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * 辅助类，加载数据使用
 * @author lixin
 * @date 2019-05-01 11:46
 **/
public class BankMarketingLoader {

    /**
     * Method that loads the examples of the Bank Marketing data set from a file
     *
     * @param dataPath Path to the file where the data items are stored
     * @return List of BankMarketing examples
     */
    public List<BankMarketing> load(String dataPath) {
        Path file = Paths.get(dataPath);
        List<BankMarketing> dataSet = new ArrayList<>();
        try (InputStream in = Files.newInputStream(file);
             BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                String data[] = line.split("[;]");
                BankMarketing dataObject = new BankMarketing();
                dataObject.setData(data);
                dataSet.add(dataObject);
            }
        } catch (IOException x) {
            x.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataSet;
    }

}
