/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app;

import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author bartk
 */
public class Main {

    public static void main(String[] args) {
        Client client = ClientBuilder.newClient();

        System.out.println("Count: " + Count(client));
        System.out.println("All: " + All(client).toString());

        Complaint single = Single(client, 1);
        System.out.println("Single: " + single.toString());

        CloseComplaint(client, single);

        System.out.println("OpenedComplaints: " + OpenedComplaints(client).toString());

        client.close();

    }

    private static String Count(Client client) {
        return client.target("http://localhost:8080/Complaints/"
                + "resources/complaints/count")
                .request(MediaType.TEXT_PLAIN)
                .get(String.class);
    }

    private static List<Complaint> All(Client client) {
        return client.target("http://localhost:8080/Complaints/"
                + "resources/complaints/")
                .request(MediaType.APPLICATION_JSON)
                .get(Response.class)
                .readEntity(new GenericType<List<Complaint>>() {
                });
    }

    private static Complaint Single(Client client, int no) {
        return client.target("http://localhost:8080/Complaints/"
                + "resources/complaints/" + no)
                .request(MediaType.APPLICATION_JSON)
                .get(Complaint.class);
    }

    private static void CloseComplaint(Client client, Complaint complaint) {
        complaint.setStatus("closed");
        client.target("http://localhost:8080/Complaints/resources/complaints/"
                + complaint.getId().toString())
                .request()
                .put(Entity.entity(complaint, MediaType.APPLICATION_JSON));
    }

    private static List<Complaint> OpenedComplaints(Client client) {
        return client.target("http://localhost:8080/Complaints/"
                + "resources/complaints?status=open")
                .request(MediaType.APPLICATION_JSON)
                .get(Response.class)
                .readEntity(new GenericType<List<Complaint>>() {
                });
    }

}
