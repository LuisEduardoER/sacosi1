package System;

public class RequestObject {
	private String clientEmail;
	private String plateOfVehicle;
	
	public RequestObject(String clientEmail, String plate){
		this.clientEmail = clientEmail;
		this.plateOfVehicle = plate;
	}
	
	public String getEmail(){
		return this.clientEmail;
	}
	
	public String getPlate(){
		return this.plateOfVehicle;
	}
}
