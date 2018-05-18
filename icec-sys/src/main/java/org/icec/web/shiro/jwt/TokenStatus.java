package org.icec.web.shiro.jwt;

public enum TokenStatus {
    EXPIRED("EXPIRED"),
    INVALID("INVALID"),
    VALID("VALID");

    private final String status;
    private TokenStatus(String status){
        this.status = status;
    }
    public String value(){
        return this.status;
    }
}