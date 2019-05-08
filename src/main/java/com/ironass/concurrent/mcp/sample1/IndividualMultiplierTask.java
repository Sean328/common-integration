package com.ironass.concurrent.mcp.sample1;

/**
 * 并行版本1,每个元素一个线程 task
 * 测试证明这种开启了线程，导致最终速度还不如串行执行的效率高
 *
 * @author lixin
 * @date 2019-04-25 15:53
 **/
public class IndividualMultiplierTask implements Runnable {
    private final double[][] result;
    private final double[][] matrix1;
    private final double[][] matrix2;
    private final int row;
    private final int column;

    public IndividualMultiplierTask(double[][] result, double[][] matrix1, double[][] matrix2, int i, int j) {
        this.result = result;
        this.matrix1 = matrix1;
        this.matrix2 = matrix2;
        this.row = i;
        this.column = j;
    }

    @Override
    public void run() {
        result[row][column] = 0;
        for (int k = 0; k < matrix1[row].length; k++) {
            result[row][column] += matrix1[row][k] * matrix2[k][column];
        }
    }
}

