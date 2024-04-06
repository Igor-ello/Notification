package com.example.notification.utils;

public class BaseBuilder {
    Integer a;
    Integer b;
    Integer c;
    String d;

    private BaseBuilder() {

    }

    public static class Builder{
        BaseBuilder baseBuilder;
        public Builder() {
            baseBuilder = new BaseBuilder();
        }

        public Builder setA(Integer _a){
           baseBuilder.a = _a;
           return this;
        }

        public Builder setB(Integer _b){
            baseBuilder.b = _b;
            return this;
        }

        public Builder setC(Integer _c){
            baseBuilder.c = _c;
            return this;
        }

        public Builder setD(String _d){
            baseBuilder.d = _d;
            return this;
        }

        public BaseBuilder build(){
            return baseBuilder;
        }
    }
}
