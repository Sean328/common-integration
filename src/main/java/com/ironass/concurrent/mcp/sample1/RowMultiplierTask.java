package com.ironass.concurrent.mcp.sample1;

/**
 * 并发版本2  每行一个线程
 *
 * @author lixin
 * @date 2019-04-25 16:21
 **/
public class RowMultiplierTask implements Runnable{

    private final double[][] result;
    private final double[][] matrix1;
    private final double[][] matrix2;
    private final int row;

    public RowMultiplierTask(double[][] result, double[][] matrix1, double[][] matrix2, int i) {
        this.result = result;
        this.matrix1 = matrix1;
        this.matrix2 = matrix2;
        this.row = i;
    }

    @Override
    public void run() {
        for (int j = 0; j < matrix2[0].length; j++) {
            result[row][j] = 0;
            for (int k = 0; k < matrix1[row].length; k++) {
                result[row][j] += matrix1[row][k] * matrix2[k][j];
            }
        }
    }
}

