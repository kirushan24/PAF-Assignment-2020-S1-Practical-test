package com;

/**
* @author  Kirushan_Sivanandam_IT18088588
* created: April 26th, 2020 10:00PM
* modified: May 3rd, 2020 04.00PM
*/

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

@Path("/patient")
public class PatientController {
	PatientModel model = new PatientModel();
	Map<String, Object> data = new HashMap<String, Object>();
	Gson gson = new Gson();

	//INSERT
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String insertPatient(String json) {
		Patient p = gson.fromJson(json, Patient.class);
		return gson.toJson(model.insertPatient(p));
	}
	
	//UPDATE
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public String updatePatient(String json) {
		Patient p = gson.fromJson(json, Patient.class);
		return gson.toJson(model.updatePatient(p));
	}
	
	//DELETE
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String deletePatient(@PathParam("id") String id) {
		return gson.toJson(model.deletePatient(id));
	}


	//SEARCH
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getPatient(@PathParam("id") int id) {
		return gson.toJson(model.searchPatient(id));
	}
	
	//SEARCH
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getPatients() {
		return gson.toJson(model.searchAllPatient());
	}

}
