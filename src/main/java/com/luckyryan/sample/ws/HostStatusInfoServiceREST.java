package com.luckyryan.sample.ws;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.luckyryan.sample.dto.HostStatusInfoDTO;

//REST Setup (Follows JAX-RS)
@Path("/hostStatusInfoService")
@Produces("application/json")
public interface HostStatusInfoServiceREST {

	@POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/create")
    public HostStatusInfoDTO saveInfo(HostStatusInfoDTO info);

	@GET
	@Consumes("application/json")
	@Produces("application/json")
	@Path("/hostInfo/{id}")
	public HostStatusInfoDTO getInfo(@PathParam("id") String id);
	
	@GET
	@Consumes("application/json")
	@Produces("application/json")
	@Path("/allHost/{userId}")
	public List<HostStatusInfoDTO> getAll(@PathParam("userId") String userId);
	
	@GET
	@Consumes("application/json")
	@Produces("application/json")
	@Path("/delHost/{hostId}")
	public String deleteHost(@PathParam("hostId") String hostId);
}
