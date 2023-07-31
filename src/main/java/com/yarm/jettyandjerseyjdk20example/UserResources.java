package com.yarm.jettyandjerseyjdk20example;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/users")
public class UserResources {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserResources.class);

    private static final Map<Integer, User> mapUsers = new HashMap<>();

    static {
        User user1 = new User();
        user1.setId(1);
        user1.setFirstName("John");
        user1.setLastName("Smith");
        mapUsers.put(user1.getId(), user1);

        User user2 = new User();
        user2.setId(2);
        user2.setFirstName("Jane");
        user2.setLastName("Doe");
        mapUsers.put(user2.getId(), user2);
    }

    @GET
    @Path("/ping")
    @Produces("text/plain")
    public String ping() {
        LOGGER.info("# UserResource ping");
        return "ping";
    }

    @GET
    @Path("/all")
    @Produces("application/json")
    public Users getAllUsers() {
        LOGGER.debug("# UserResource getAllUsers start");
        Users users = new Users();
        users.setUsers(new ArrayList<>(mapUsers.values()));
        LOGGER.info("# UserResource getAllUsers. users=" + users.toString());
        return users;
    }

    @GET
    @Path("/{param}.txt")
    @Produces("text/plain")
    public String param_txt(@PathParam("param") String strParam) {
        LOGGER.debug("# UserResource param_txt. param=\"" + strParam + "\"");
        return "param:\"" + strParam + "\"";
    }

    @GET
    @Path("/{param}.json")
    @Produces(MediaType.APPLICATION_JSON)
    public String param(@PathParam("param") String strParam) {
        LOGGER.debug("# UserResource param. param=\"" + strParam + "\"");
        JSONObject objJson = new JSONObject();
        try {
            objJson.put("param", strParam);
        } catch (JSONException e) {
            LOGGER.error("# UserResource cannot create JSON object. param=\"" + strParam + "\"" + " Exception==\"" + e.toString() + "\"");
        }
        String strResponse = objJson.toString();
        LOGGER.info("# UserResource param. param=\"" + strParam + "\"" + " Response=\"" + strResponse + "\"");
        return strResponse;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUserById(@PathParam("id") String strID) {
        LOGGER.debug("# UserResource getUserById. id=\"" + strID + "\"");
        strID = strID.trim();
        if (strID == null || strID.isEmpty()) {
            LOGGER.error("# UserResource getUserById UserID is empty. id=" + strID);
            return null;
        }
        int intID;
        try {
            intID = Integer.parseInt(strID);
        } catch (NumberFormatException e) {
            LOGGER.error("# UserResource getUserById Incorrect UserID. id=" + strID + " Exception=" + e.toString());
            return null;
        }
        User user = mapUsers.get(intID);
        if (user == null) {
            LOGGER.error("# UserResource getUserById UserID not found. id=" + strID);
            return null;
            //return Response.status(404).build();
        }

        LOGGER.debug("# UserResource getUserById. id=\"" + strID + "\"" + " user=\"" + user + "\"");
        return user;
    }

    @GET
    @Path("/user{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserByUserId(@PathParam("id") String strID) {
        LOGGER.debug("# UserResource getUserByUserId. id=\"" + strID + "\"");
        strID = strID.trim();
        if (strID == null || strID.isEmpty()) {
            LOGGER.error("# UserResource getUserByUserId UserID is empty. id=" + strID);
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        int intID;
        try {
            intID = Integer.parseInt(strID);
        } catch (NumberFormatException e) {
            LOGGER.error("# UserResource getUserByUserId Incorrect UserID. id=" + strID + " Exception=" + e.toString());
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        User user = mapUsers.get(intID);
        if (user == null) {
            LOGGER.error("# UserResource getUserByUserId UserID not found. id=" + strID);
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        LOGGER.debug("# UserResource getUserByUserId. id=\"" + strID + "\"" + " user=\"" + user + "\"");
        return Response.ok().entity(user).build();
    }

}
