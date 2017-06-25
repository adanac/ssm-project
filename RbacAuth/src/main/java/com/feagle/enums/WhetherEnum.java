package com.feagle.enums;

/**
 * Created by Feagle on 2017/6/5.
 */
public enum WhetherEnum {
    YES(1),NO(0);
    private int value;
    WhetherEnum(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
