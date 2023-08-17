package com.intexsoft.webshop.orderorchestrator.enums;

public enum CommandType {

    CHECK_PICKUP_POINT, CHECK_USER, CHECK_SHOP, FAIL_ORDER;

    public String lowerCaseName(){
        return this.name().toLowerCase();
    }
}