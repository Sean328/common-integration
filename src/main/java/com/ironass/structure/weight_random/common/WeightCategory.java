package com.ironass.structure.weight_random.common;

/**
 * @author lixin
 * @date 2019-04-09 15:06
 **/
public class WeightCategory {
    private String categroy;
    private Integer weight;

    public WeightCategory(String categroy,Integer weight){
        this.categroy = categroy;
        this.weight = weight;
    }

    public String getCategroy() {
        return categroy;
    }

    public void setCategroy(String categroy) {
        this.categroy = categroy;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }
}
