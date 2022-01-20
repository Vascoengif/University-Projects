
package com.mycompany.sensor_module;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringWriter;
import static java.lang.Thread.sleep;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.time.LocalTime;
import java.util.Optional;
import org.apache.http.entity.StringEntity;
import java.sql.Timestamp;

public class Sensor {

    private final CloseableHttpClient httpClient = HttpClients.createDefault();
    
    private String chipID, locationID, time;
    
    public Sensor(){}
    
    public Sensor(String chip, String time, String location){
        this.chipID = chip;
        this.time = time;
        this.locationID = location;
    }
    
    private static void sendPost(String url, JSONObject send) throws IOException{

        HttpPost post = new HttpPost(url);
        
        StringWriter out = new StringWriter();
        send.writeJSONString(out);
        
        String sendObj = out.toString();
        
        StringEntity entity = new StringEntity(sendObj, "UTF-8");
        
        post.setEntity(entity);
        post.setHeader("Accept", "application/json");
        post.setHeader("Content-type", "application/json");
        
        try(CloseableHttpClient client = HttpClients.createDefault(); CloseableHttpResponse response = client.execute(post)){
            
                System.out.println();
                System.out.println(EntityUtils.toString(response.getEntity())); 
        }   
    }
    
     public static void showMenu(){
        System.out.println("\n");
        System.out.println("######################## MENU ##########################");
        System.out.println("1-Transmitir Leitura"); //cada checkpoit tera um sensor, onde estara a ultima posição conhecida de cada atleta
        System.out.println("2-Sair da Aplicação");
        
    }
     
    public static void main(String args[]) {
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        try{
            
            while(true){
                
                Sensor sensor = new Sensor();
                Integer switchVariable;
                String url, eventID, athleteID;
            
                showMenu();
                
                System.out.print("\nType your choice: ");
                switchVariable = Integer.parseInt(reader.readLine());
                
                switch(switchVariable){
                    
                    case 1:
                        
                        System.out.print("Insira o ID do evento: ");
                        eventID = reader.readLine();
                        
                        System.out.print("Insira o dorsal do atleta: ");
                        athleteID = reader.readLine();
                        
                        sensor.chipID = "E".concat(eventID).concat("P").concat(athleteID);
                        
                        System.out.print("Insira o locationID: ");
                        String test = reader.readLine();
                        
                        while(!(test.equals("start")) && !(test.equals("P1")) && !(test.equals("P2")) && !(test.equals("P3")) && !(test.equals("finish"))){
                            
                            System.out.print("Insira um locationID válido: ");
                            test = reader.readLine();
                        }
                        
                        sensor.locationID = test;

                        System.out.print("Insira o ano: ");
                        Integer year = Integer.parseInt(reader.readLine())-1900;
                        
                        System.out.print("Insira o mês: ");
                        Integer month = Integer.parseInt(reader.readLine())-1;
                        
                        System.out.print("Insira o dia: ");
                        Integer day = Integer.parseInt(reader.readLine());
                        
                        System.out.print("Insira as horas: ");
                        Integer hours = Integer.parseInt(reader.readLine());
                        
                        System.out.print("Insira os minutos: ");
                        Integer minutes = Integer.parseInt(reader.readLine());
                        
                        System.out.print("Insira os segundos: ");
                        Integer seconds = Integer.parseInt(reader.readLine());
                        
                        System.out.print("Insira os milisegundos: ");
                        Integer miliseconds = Integer.parseInt(reader.readLine());
                        
                        Timestamp timestamp = new Timestamp(year, month, day, hours, minutes, seconds, miliseconds);
                        
                        String timestampString = timestamp.toString();
                        
                        sensor.time = timestampString;
              
                        url = "http://localhost:8090/participants/addSensorLecture"; 
                        
                        JSONObject send = new JSONObject();
        
                        send.put("chipID", sensor.chipID);
                        send.put("time", sensor.time);
                        send.put("locationID", sensor.locationID);
        
                        sendPost(url, send);
                        
                        break;
                   
                    case 2:
                        System.out.println("Closing Sensor App...");
                        System.out.println("\n");
                        System.exit(0);
                }
                
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
}
