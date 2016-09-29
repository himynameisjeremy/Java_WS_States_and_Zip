package webService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import webService.State;

@Path("/getZipCount")

public class ZipCount {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String zipCount(){
		
		Gson gson = new Gson();
		
		String stateJsonString = "{"
				+ "\"states\" : [";
		
		GetStates.getStateInfo();
		
		for(int x=0;x<GetStates.stateFull.length;x++){
			State stateObject = new State();
			stateObject.setStateName(GetStates.stateFull[x]);
			stateObject.setZipCodeCount(GetStates.zipCount[x]);
			
			if(x<GetStates.state.length-1){
				stateJsonString+=gson.toJson(stateObject);
				stateJsonString+=",";
			}else{
				stateJsonString+=gson.toJson(stateObject);
				stateJsonString+="]}";
			}
		}
		
		String response = (stateJsonString);
		return response;
	}

}
