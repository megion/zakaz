package org.megion.system1.rest;

import org.megion.system1.api.System1Service;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ejb.EJB;
import javax.ws.rs.core.MediaType;

@Path("/system1")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
@Stateless
@TransactionAttribute(TransactionAttributeType.NEVER)
public class HelloWorld {
	@Inject
	HelloService helloService;

    @EJB(lookup = "ejb:system-1-ear.ear/system-1-ejb.jar/System1Service!org.megion.system1.api.System1Service")
    private System1Service system1Service;

	@GET
	@Path("/json")
	@Produces({ "application/json" })
	public String getHelloWorldJSON() {
		return "{\"result\":\"" + system1Service.doWork1() + "\"}";
	}

	@GET
	@Path("/xml")
	@Produces({ "application/xml" })
	public String getHelloWorldXML() {
		return "<xml><result>" + helloService.createHelloMessage("My world")
				+ "</result></xml>";
	}

}
