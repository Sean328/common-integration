package com.ironass.concurrent.mcp.sample3.dmain;

/**
 * knn算法的抽象实体基类
 * @author lixin
 * @date 2019-04-30 19:07
 **/
public abstract class Sample {

    /**
     * Method that returns the tag or class of the example
     * @return The tag or class of the examples
     */
    public abstract String getTag();

    /**
     * Method that return the values of the attributes of the example as an array of doubles
     * @return The values of the attributes of the example
     */
    public abstract double[] getExample();
}
