/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.edu.fdb135.expo;

import java.util.Date;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import oracle.kv.KVStore;
import oracle.kv.KVStoreConfig;
import oracle.kv.KVStoreFactory;
import oracle.kv.Key;
import oracle.kv.Value;

/**
 *
 * @author esperanza
 */
@Path("")
public class Conexion {
    @Path("{key}")
    @GET
    public Response get(@PathParam("key") String key) {
        System.out.println("Buscando llave --- " + key);
        String storeName = "kvstore";
        String hostName = "localhost";
        String hostPort = "5000";

        KVStoreConfig config = new KVStoreConfig(storeName, System.getenv("ORANOSQL_DOCKER_HOST") + ":" + hostPort);
   

        KVStore kvstore = KVStoreFactory.getStore(config);
        System.out.println("Conectado :v");
        
        String value = new String(kvstore.get(Key.fromString("/" + key)).getValue().toByteArray());
        System.out.println("Valor ---> " + value);
        kvstore.close();
        System.out.println("Desconectado");
        return Response.ok(value).build();
    }

    @POST
    public void put(String key) {
        System.out.println("Pushing key --- " + key);
        String storeName = "kvstore";
        String hostName = "localhost";
        String hostPort = "5000";

        KVStoreConfig config = new KVStoreConfig(storeName, System.getenv("ORANOSQL_DOCKER_HOST") + ":" + hostPort);

        KVStore kvstore = KVStoreFactory.getStore(config);
        System.out.println("Conectado :v");
        kvstore.put(Key.fromString("/" + key), Value.createValue(new Date().toString().getBytes()));

        System.out.println("POST realizado");
        kvstore.close();
        System.out.println("Desconectado");

    }
}
