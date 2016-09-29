package webService;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

public class GetStates {
	
	private static String xmlInfo = null;
	
	public static String state[]={"AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY"};
	
	public static String stateFull[]={"Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado", "Connecticut", "Delaware", "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota", "Mississippi", "Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire", "New Jersey", "New Mexico", "New York", "North Carolina", "North Dakota", "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island", "South Carolina", "South Dakota", "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington", "West Virginia", "Wisconsin", "Wyoming"};
	
	public static int zipCount[]={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
	
	public static void getStateInfo(){
		
		
		Client client=ClientBuilder.newClient();
		
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
			
			zipCount[x]= newNum;
			
		}
	}

}