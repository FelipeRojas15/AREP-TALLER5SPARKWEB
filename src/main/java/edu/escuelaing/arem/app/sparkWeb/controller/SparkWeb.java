/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.escuelaing.arem.app.sparkWeb;
import edu.escuelaing.arem.app.*;
import java.io.IOException;
import static spark.Spark.*;
import spark.Request;
import spark.Response;
/**
 *
 * @author rojas
 */


public class SparkWeb {
    public static void main(String[] args){

        staticFileLocation("/static");
        DataStub conexion = new DataStub();
        port(getPort());
        post("/result",(request,response)-> {
            System.out.println("Hola perra");
            conexion.insertData((String) request.body());
            return "Dato insertado";

        });
        get("/",(req, res)->{
                res.redirect("index.html");
            return null;
        });
        get("/result", (req, res) -> {
            return conexion.getData();
        });
        
    }
    public String homePage(Request req, Response res){

        return "";
    }
    public static int getPort(){
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567; 
    }
}
