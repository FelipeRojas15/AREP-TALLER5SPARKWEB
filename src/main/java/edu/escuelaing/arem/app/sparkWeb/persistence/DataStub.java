package edu.escuelaing.arem.app.sparkWeb.persistence;

import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Conexion a la base de datos y sus respectivas acciones como insertar y seleccionar los datos
 */
public class DataStub {

    private MongoClientURI uri;
    private MongoClient mongoClient;
    private MongoDatabase mongodb;

    /**
     * Conexion a la base de datos
     */
    public DataStub() {
        uri = new MongoClientURI(
                "mongodb://admin:admin123@database-arep:27017/?serverSelectionTimeoutMS=5000&connectTimeoutMS=10000&authSource=database&authMechanism=SCRAM-SHA-256&3t.uriVersion=3&3t.connection.name=database");
        mongoClient = new MongoClient(uri);
        mongodb = mongoClient.getDatabase("database");

    }

    /**
     * Insertar Mensaje a la base de datos por medio de documentos
     * @param mensaje, mensaje a insertar
     */
    public void insertData(String mensaje){
        System.out.println("Aca voy");
        Date fecha = new Date();
        Map<String,Object> dataMap = new HashMap<String, Object>();
        dataMap.put("dato", mensaje);
        dataMap.put("fecha",fecha.toString());
        Document documento= new Document(dataMap);
        getMongodb().getCollection("datosArep").insertOne(documento);
    }

    /**
     * Trae los datos de la base de datos como string
     * @return String con los datos
     */
    public String getData(){

        MongoCollection<Document> coleccion =  getMongodb().getCollection("datosArep");
        String data = "";
        FindIterable<Document> iterDoc = coleccion.find();
        for(Document d : iterDoc){
            data += "{dato: "+d.get("dato").toString() +", fecha: " +d.get("fecha").toString()+"}";
        }

        return data;
    }
    /**
     * trae la base de datos
     * @return MongoDatabase, base de datos
     */
    public MongoDatabase getMongodb() {
        return mongodb;
    }
}
