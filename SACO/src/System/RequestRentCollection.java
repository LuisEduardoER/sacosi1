package System;

import java.util.ArrayList;
import java.util.List;

public class RequestRentCollection {
	private List<RequestObject> requestList;
	
	public  RequestRentCollection(){
		requestList = new ArrayList<RequestObject>();
	}
	
	public List<RequestObject> getRequestRentCollection(){
		return this.requestList;
	}
	
	public int size(){
		return this.requestList.size();
	}
	
	public void add (String clientEmail, String plate){
		requestList.add(new RequestObject(clientEmail, plate));
	}
}
