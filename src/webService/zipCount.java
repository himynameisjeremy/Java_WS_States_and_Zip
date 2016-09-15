package webService;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.mcavallo.opencloud.Cloud;
import org.mcavallo.opencloud.Tag;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import com.google.gson.Gson;
import webService.State;

@Path("/zipCount")
public class zipCount {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getCall(){
		
		String xmlInfo = null;
		String state[]={"AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY"};
		int zipCount[]={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
		String[] WORDS = new String[500];
		String stateFull[]={"Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado", "Connecticut", "Delaware", "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota", "Mississippi", "Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire", "New Jersey", "New Mexico", "New York", "North Carolina", "North Dakota", "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island", "South Carolina", "South Dakota", "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington", "West Virginia", "Wisconsin", "Wyoming"};
		int zipCountDivided[]={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
		
		Client client=ClientBuilder.newClient();
		
		Gson gson = new Gson();
		
		String stateJsonString = "{"
				+ "\"states\" : [";
		
		for(int x=0;x<state.length;x++){
			
			String codeCut = null;
			int newNum = 0;
			
			WebTarget target= client.target("http://api.geonames.org/postalCodeSearch?&country=US&placename="+state[x]+"&startRow=1&maxRows=1&username=himynameisjeremy");
			
			xmlInfo = target.request(MediaType.TEXT_XML).get(String.class);
			
			try{
				codeCut = xmlInfo.substring(85, 89);
				newNum = Integer.parseInt(codeCut);
			} catch (Exception e) {
				codeCut = xmlInfo.substring(85, 88);
				newNum = Integer.parseInt(codeCut);
			}
			
			zipCountDivided[x]= Math.round(newNum/40);
			zipCount[x]= newNum;
			
			State stateObject = new State();
			stateObject.setStateName(stateFull[x]);
			stateObject.setZipCodeCount(zipCount[x]);
			
			
			if(x<state.length-1){
				stateJsonString+=gson.toJson(stateObject);
				stateJsonString+=",";
			}else{
				stateJsonString+=gson.toJson(stateObject);
				stateJsonString+="]}";
			}
			
		}
		
		String response = (stateJsonString);
		
		for(int i = 0; i< zipCountDivided.length;i++){
			WORDS[i]=stateFull[i];
		}
		
		JFrame frame = new JFrame(CreateTagCloud.class.getSimpleName());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		Cloud cloud = new Cloud();
		for(int i=0;i<WORDS.length;i++){
			while(WORDS[i]!=null && zipCountDivided[i]>0){
				cloud.addTag(WORDS[i]);
				zipCountDivided[i]=zipCountDivided[i]-1;
			}
		}
		for (Tag tag : cloud.tags()) {
			final JLabel label = new JLabel(tag.getName());
		 	label.setOpaque(false);
		 	label.setFont(label.getFont().deriveFont((float) tag.getWeight() * 10));
		 	panel.add(label);
		 }
		 frame.add(panel);
		 frame.setSize(800, 600);
		 frame.setVisible(true);
		 
		return response;
	}

}
