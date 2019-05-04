package vu.lt.rest;

import lombok.*;
import vu.lt.entities.Shop;
import vu.lt.interceptors.RestLogInvocation;
import vu.lt.persistence.ShopsDAO;
import vu.lt.rest.contracts.ShopDTO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@ApplicationScoped
@Path("/rest")
public class ShopsController {

    @Inject
    @Setter @Getter
    private ShopsDAO shopsDAO;

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RestLogInvocation
    public Response getById(@PathParam("id") final Integer id) {
        //-- GET http://localhost:8080/psk1_war/api/rest/2

        Shop shop = shopsDAO.findOne(id);
        if (shop == null)
        {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        else
        {
            ShopDTO shopObj = new ShopDTO();
            shopObj.setName(shop.getName());
            shopObj.setAddress(shop.getAddress());
            shopObj.setOpt_Lock_Version(shop.getVersion());

            return Response.ok(shopObj).build();
        }

    }

    @Path("/{id}")
    @PUT  // create/update existing
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    @RestLogInvocation
    public Response update( @PathParam("id") final Integer shopId, String newAddress) {
        //-- http://localhost:8080/psk1_war/api/rest/257
        //-- {  "newAddress": "new+new+new"  }

        Shop existingShop = shopsDAO.findOne(shopId);
        if (existingShop == null)
        {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        else
        {
            JsonObject newData = new JsonParser().parse(newAddress).getAsJsonObject();
            existingShop.setAddress(newData.get("newAddress").getAsString());

            shopsDAO.update(existingShop);
            return Response.ok().build();
        }
    }

    @Path("/{id}")
    @POST  // create new
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    @RestLogInvocation
    public Response create( @PathParam("id") final Integer shopId, String newData) {
        //-- http://localhost:8080/psk1_war/api/rest/500
        //-- { "name": "newwwNAME", "address": "newwwwwADRESS" }

        Shop existingShop = shopsDAO.findOne(shopId);

        if (existingShop != null)
        {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        else
        {
            Shop newShop = new Shop();

            JsonObject newInfo = new JsonParser().parse(newData).getAsJsonObject();
            newShop.setName(newInfo.get("name").getAsString());
            newShop.setAddress(newInfo.get("address").getAsString());

            shopsDAO.persist(newShop);

            return Response.ok().build();
        }
    }

}
