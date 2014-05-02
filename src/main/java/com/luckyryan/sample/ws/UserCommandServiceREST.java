package com.luckyryan.sample.ws;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.luckyryan.sample.dto.UserCommandDTO;

//REST Setup (Follows JAX-RS)
@Path("/userCommandService")
@Produces("application/json")
public interface UserCommandServiceREST {
	
	@POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/create")
	public UserCommandDTO saveCommand(UserCommandDTO command);
	
	@GET
	@Consumes("application/json")
	@Produces("application/json")
	@Path("/getcommand/{id}")
	public UserCommandDTO getCommand(@PathParam("id")Long id);
	
	@GET
	@Consumes("application/json")
	@Produces("application/json")
	@Path("/lastcommand/{macAddress}")
	public UserCommandDTO getLastUnProcessCommand(@PathParam("macAddress")String macAddress);
	
	@GET
	@Consumes("application/json")
	@Produces("application/json")
	@Path("/allcommand/{macAddress}")
	public List<UserCommandDTO> getAll(@PathParam("macAddress")String macAddress);

	@GET
	@Consumes("application/json")
	@Produces("application/json")
	@Path("/findByIds/{ids}")
	public List<UserCommandDTO> findByIds(@PathParam("ids")String ids);

}
