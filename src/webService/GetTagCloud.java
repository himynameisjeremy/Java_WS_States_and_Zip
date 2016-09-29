package webService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/getTagCloud")

public class GetTagCloud {
	
	@GET
	@Produces("text/plain")
	public String generateCloud(){
		
		String response = "Creating Cloud";
		CreateTagCloud.createCloud();
		return response;
	}

}
