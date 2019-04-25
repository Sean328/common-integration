package com.ironass.dsl.builder;


import com.ironass.base.BaseDomain;

/**
 * @author lixin
 * @date 2019-01-17 17:22
 **/
public class Order extends BaseDomain {

    private static final long serialVersionUID = -740265745770856205L;

    public static class Builder{
        private String security;
        private int quantity;
        private int limitPrice;
        private boolean allOrNone;
        private String boughtOrSold;
        private int value;

        public Builder(){}

        public Builder buy(int quantity,String security){
            this.boughtOrSold = "Bought";
            this.quantity = quantity;
            this.security = security;
            return this;
        }

        public Builder sell(int quantity,String security){
            this.boughtOrSold = "Sold";
            this.quantity = quantity;
            this.security = security;
            return this;
        }

        public Builder atLimitPrice(int price){
            this.limitPrice = price;
            return this;
        }

        public Builder getAllOrNone(){
            this.allOrNone = true;
            return this;
        }

        public Builder valueAs(int value){
            this.value = value;
            return this;
        }

        public Order build(){
            return new Order(this);
        }

    }

    private final String security;
    private final int quantity;
    private final int limitPrice;
    private final boolean allOrNone;
    private final String boughtOrSold;
    private int value;

    /**
     * 构造方法私有，禁止不经过builder模式实例化对象
     * @param builder
     */
    private Order(Builder builder){
        this.security = builder.security;
        this.quantity = builder.quantity;
        this.limitPrice = builder.limitPrice;
        this.allOrNone = builder.allOrNone;
        this.value = builder.value;
        this.boughtOrSold = builder.boughtOrSold;
    }


    public String getSecurity() {
        return security;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getLimitPrice() {
        return limitPrice;
    }

    public boolean isAllOrNone() {
        return allOrNone;
    }

    public String getBoughtOrSold() {
        return boughtOrSold;
    }

    public int getValue() {
        return value;
    }

    public Order setValue(int value) {
        this.value = value;
        return this;
    }
}
