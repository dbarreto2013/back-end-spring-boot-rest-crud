package com.srm.asset.document;

public enum RiscoEnum {
	
	A(null),
    B(10),
    C(20);

    private Integer risco;

    RiscoEnum(Integer risco) {
        this.risco = risco;
    }
    
    public Integer getRisco() {
    	return this.risco;
    }
    
}
