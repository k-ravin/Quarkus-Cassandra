package org.acme;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.datastax.oss.driver.api.core.cql.Row;
import com.datastax.oss.quarkus.runtime.api.session.QuarkusCqlSession;


@Path("/hello-resteasy")
public class GreetingResource {

        @Inject QuarkusCqlSession cqlSession;
        
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello()throws Exception {
        
        String response ="";
        //Here comes our main thing, a real query to Cassandra
        java.util.List<Row> rs=   cqlSession.execute("SELECT id,name,description from k1.test_table").all();

        //as every database query returns a resultset we are going to extract data from the resultSet and return as api response : 
        
        for(com.datastax.oss.driver.api.core.cql.Row rw:rs) {

            System.out.println(rw.getFormattedContents());
            response+=rw.getFormattedContents()+"\n";
        }

        return response;
    }

    @GET
    @Path("/retrievedata")
    public String retrievedata()throws Exception{

        String response ="";
        //Here comes our main thing, a real query to Cassandra
        java.util.List<Row> rs=   cqlSession.execute("SELECT id,name,description from k1.test_table").all();

        //as every database query returns a resultset we are going to extract data from the resultSet and return as api response : 
        
        for(com.datastax.oss.driver.api.core.cql.Row rw:rs) {

            System.out.println(rw.getFormattedContents());
            response+=rw.getFormattedContents()+"\n";
        }

        return response;

    }

}