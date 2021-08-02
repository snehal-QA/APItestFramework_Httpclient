package com.qa.base;

public enum Resources {
	Get_ListUsers("/api/users"),
	Get_SingleUser("/api/users/"),
	Post_CreateUser("/api/users"),
	;
    private String getenumvalue;
    
	public String getGetenumvalue() {
		return this.getenumvalue;
	}

	private Resources(String getenumvalue) {
		this.getenumvalue=getenumvalue;
		
	}
	
	

}
