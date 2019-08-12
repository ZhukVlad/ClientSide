/*package comarch.mediation.ClientSide.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("clientendpoint")
public class ClientEndpoint {
	@POST
	@Path("alarms/notify ")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response sendNotification(String json) {
		System.out.println(json);
		return Response.ok().build();
	}
}
*/