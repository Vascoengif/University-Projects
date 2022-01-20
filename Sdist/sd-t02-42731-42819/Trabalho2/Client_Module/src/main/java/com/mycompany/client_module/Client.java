
package com.mycompany.client_module;

import com.jayway.jsonpath.JsonPath;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.sql.Timestamp;
import org.apache.http.entity.StringEntity;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

/**
 *
 * @author vasco
 */
public class Client {
    
    private final CloseableHttpClient httpClient = HttpClients.createDefault();
    
    
    
    public static String passToString(Long timeReceived){
        
        long days = TimeUnit.MILLISECONDS.toDays(timeReceived);
        timeReceived -= TimeUnit.DAYS.toMillis(days);
        long hours = TimeUnit.MILLISECONDS.toHours(timeReceived);
        timeReceived -= TimeUnit.HOURS.toMillis(hours);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(timeReceived);
        timeReceived -= TimeUnit.MINUTES.toMillis(minutes);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(timeReceived);
        
        String daysS = String.valueOf(days);
        String hoursS = String.valueOf(hours);
        String minutesS = String.valueOf(minutes);
        String secondsS = String.valueOf(seconds);
        
        String toReturn = daysS.concat("d").concat(hoursS).concat("h").concat(minutesS).concat("m").concat(secondsS).concat("s");
        
        return toReturn;
    }
    
    
    public String sendGet(String url) throws Exception{

        HttpGet request = new HttpGet(url);
        
        String receivedToSend = "";

        try(CloseableHttpResponse response = httpClient.execute(request)) {

            HttpEntity entity = response.getEntity();

            if(entity != null) {
                receivedToSend = EntityUtils.toString(entity);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return receivedToSend;
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
        
        try(CloseableHttpClient client = HttpClients.createDefault();
            CloseableHttpResponse response = client.execute(post)){
            
               System.out.println(EntityUtils.toString(response.getEntity())); 
        }        
    }
    
    
    public static void showMenu(){
        System.out.println();
        System.out.println("######################## MENU ##########################");
        System.out.println("1-Obter lista de eventos numa determinada data");
        System.out.println("2-Registar novo evento");
        System.out.println("3-Lista de inscritos num evento");
        System.out.println("4-Classificação Geral");
        System.out.println("5-Situação de atletas em corrida");
        System.out.println("6-Inscrição de Participante");
        System.out.println("7-Sair da Aplicação"); 
        
    }
    
    public static void main(String[] args) {
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        try{
            
            Client client = new Client();
            Integer switchVariable = 0;
            Integer hour;
            String date, eventName, participantName, genre;
            String echelon, type, url, point;
            String received;
            
            while(true){
                
                showMenu();
                
                
                JSONObject send = new JSONObject();
                
                System.out.print("\nType your choice: ");
                switchVariable = Integer.parseInt(reader.readLine());
                
                switch(switchVariable){
                    
                    case 1:
                        
                        System.out.print("Insira a data em que se realiza o evento: ");
                        date = reader.readLine();
                        
                        url = "http://localhost:8090/event/getEventsOnADate?" + "date=" + date;
                        
                        received = client.sendGet(url);
                        
                        JSONArray arr = JsonPath.read(received, "$.[?(@['eventName'])]['eventName']");
                        JSONArray arr1 = JsonPath.read(received,"$.[?(@['eventType'])]['eventType']");
                        
                        ArrayList<String> NameOfEvents = new ArrayList<>();
                        ArrayList<String> TypeOfEvents = new ArrayList<>();
 
                        for (int i = 0; i < arr.toArray().length; i++) {
                            NameOfEvents.add(arr.get(i).toString());
                            TypeOfEvents.add(arr1.get(i).toString());
                        }

                        if(NameOfEvents.isEmpty()){
                            System.out.println();
                            System.out.println("Não existem eventos nessa data");
                        }
                        else{
                            System.out.println();
                            System.out.println("Eventos no dia " + date + ":");
                            System.out.println();

                            for (int i = 0; i < NameOfEvents.size(); i++) {
                                System.out.print(NameOfEvents.get(i));
                                System.out.print(" - ");
                                System.out.println(TypeOfEvents.get(i));
                            }
                        }

                        break;
                        
                    case 2:
                        
                        System.out.print("Insira o Nome do novo evento: ");
                        eventName = reader.readLine();
                        
                        System.out.print("Insira o Tipo do novo evento (Atletismo de Pista(A), Estrada(E), Trail(T)): ");
                        type = reader.readLine();
                        
                        while(!(type.equals("A")) && !(type.equals("E")) && !(type.equals("T"))){
                            System.out.println();
                            System.out.print("Input incorreto, digite uma das iniciais: ");
                            type = reader.readLine();
                        }
                        
                        if(type.equals("A")){
                            type = "Atletismo de Pista";
                        }
                        else if(type.equals("E")){
                            type = "Atletismo de Estrada";
                        }
                        else if(type.equals("T")){
                            type = "Trail";
                        }
                        
                        System.out.println("Insira os dados sobre a Data de realização do novo evento: ");
                     
                        System.out.print("Insira o ano (YYYY): ");
                        Integer year = Integer.parseInt(reader.readLine());
                        Integer fakeYear = year -1900;
                        
                        System.out.print("Insira o mês (MM): ");
                        Integer month = Integer.parseInt(reader.readLine());
                        Integer fakeMonth = month-1;
                        
                        System.out.print("Insira o dia (DD): ");
                        Integer day = Integer.parseInt(reader.readLine());
                        
                        System.out.print("Insira as horas (HH): ");
                        Integer hours = Integer.parseInt(reader.readLine());
                        
                        System.out.print("Insira os minutos (MM): ");
                        Integer minutes = Integer.parseInt(reader.readLine());
                        
                        System.out.print("Insira os segundos (SS): ");
                        Integer seconds = Integer.parseInt(reader.readLine());
                        
                        System.out.print("Insira os milisegundos (MM): ");
                        Integer miliseconds = Integer.parseInt(reader.readLine());
                        
                        Timestamp dateWithTimestamp = new Timestamp(fakeYear, fakeMonth, day, hours, minutes, seconds, miliseconds);
                        
                        System.out.println(dateWithTimestamp);
                        
                        date = year.toString().concat("-").concat(month.toString()).concat("-").concat(day.toString());
                        
                        System.out.println("date normal: " + date);
        
                        String dateWithTimestampString = dateWithTimestamp.toString();
                        
                        url = "http://localhost:8090/event/newEvent";
                        
                        send.put("name", eventName);
                        send.put("type", type);
                        send.put("date", date);
                        send.put("dateWithTimestamp", dateWithTimestampString);
                        
                        sendPost(url, send);
                        
                        break;
                        
                    case 3:
                        
                        System.out.print("Insira o nome do Evento: ");
                        eventName = reader.readLine();
                        
                        url = "http://localhost:8090/event/getListOfParticipants?" + "eventName=" + eventName;
                        
                        received = client.sendGet(url);
                        
                        if(received.toString().equals("")){
                          
                            System.out.println();
                            System.out.println("Esse evento não existe.");
                        }
                        else{
                      
                            JSONArray arr2 = JsonPath.read(received, "$.[?(@['name'])]['name']");
                            JSONArray arr3 = JsonPath.read(received,"$.[?(@['dorsal'])]['dorsal']");
                            JSONArray arr4 = JsonPath.read(received, "$.[?(@['genre'])]['genre']");
                            JSONArray arr5 = JsonPath.read(received,"$.[?(@['echelon'])]['echelon']");

                            ArrayList<String> names = new ArrayList<>();
                            ArrayList<String> dorsals = new ArrayList<>();
                            ArrayList<String> genres = new ArrayList<>();
                            ArrayList<String> echelons = new ArrayList<>();

                            for (int i = 0; i < arr2.toArray().length; i++) {
                                names.add(arr2.get(i).toString());
                                dorsals.add(arr3.get(i).toString());
                                genres.add(arr4.get(i).toString());
                                echelons.add(arr5.get(i).toString());
                            }

                            if(names.isEmpty()){
                                System.out.println();
                                System.out.println("Não existem participantes registados neste evento.");
                            }
                            else{
                                System.out.println();
                                System.out.println("Lista de Participantes no evento " + eventName + ":");
                                System.out.println();

                                for (int i = 0; i < names.size(); i++) {
                                    System.out.print(names.get(i));
                                    System.out.print(" - dorsal nº");
                                    System.out.print(dorsals.get(i));
                                    System.out.print(" - ");
                                    System.out.print(genres.get(i));
                                    System.out.print(" - ");
                                    System.out.println(echelons.get(i));
                                }
                            }
                        }
                        
                        break;
                        
                    case 4:
                        
                        System.out.print("Que classificação pretende obter?(Geral-G; Num ponto intermédio-P):");
                        String classificationType = reader.readLine();
                        
                        while(!(classificationType.equals("G")) && !(classificationType.equals("P"))){
                            
                            System.out.println();
                            System.out.print("Input incorreto, digite uma das iniciais: ");
                            classificationType = reader.readLine();
                        }
                        
                        System.out.println();
                        System.out.println("Insira o nome do evento");
                        eventName = reader.readLine();
                        String typeOfClassification = "";
                        String pointClass = "";
                            
                        if(classificationType.equals("G")){
                            
                            typeOfClassification ="Geral";
                            pointClass = "finish";
                        }
                        else{
                            
                            System.out.print("Insira o Ponto Intermédio (P1,P2,P3): ");
                            pointClass = reader.readLine();

                            while(!(pointClass.equals("P1")) && !(pointClass.equals("P2")) && !(pointClass.equals("P3"))){

                                System.out.print("Insira um locationID válido: ");
                                pointClass = reader.readLine();
                            }
                            
                            typeOfClassification = "no Ponto ".concat(pointClass);                       
                        }
                            
                        url = "http://localhost:8090/event/getClassificationAtAPoint?" + "eventName=" + eventName + "&locationID=" + pointClass; 
                        
                        received = client.sendGet(url);

                        if(received.toString().equals("")){

                            System.out.println();
                            System.out.println("Esse evento não existe ou ainda não passaram participantes por este Ponto Intermédio.");
                        }
                        else{

                            JSONArray arr6 = JsonPath.read(received, "$.[?(@['name'])]['name']");
                            JSONArray arr7 = JsonPath.read(received,"$.[?(@['dorsal'])]['dorsal']");
                            JSONArray arr8 = JsonPath.read(received, "$.[?(@['genre'])]['genre']");
                            JSONArray arr9 = JsonPath.read(received,"$.[?(@['echelon'])]['echelon']");
                            JSONArray arr10 = new JSONArray();
                            
                            if(pointClass.equals("P1")){
                                arr10 = JsonPath.read(received,"$.[?(@['timeAtP1'])]['timeAtP1']");
                            }
                            else if(pointClass.equals("P2")){
                                arr10 = JsonPath.read(received,"$.[?(@['timeAtP2'])]['timeAtP2']");
                            }
                            else if(pointClass.equals("P3")){
                                arr10 = JsonPath.read(received,"$.[?(@['timeAtP3'])]['timeAtP3']");
                            }
                            else if(pointClass.equals("finish")){
                                arr10 = JsonPath.read(received,"$.[?(@['timeOfRun'])]['timeOfRun']");
                            }
                            
                            ArrayList<String> names = new ArrayList<>();
                            ArrayList<String> dorsals = new ArrayList<>();
                            ArrayList<String> genres = new ArrayList<>();
                            ArrayList<String> echelons = new ArrayList<>();
                            ArrayList<Long> times = new ArrayList<>();

                            for (int i = 0; i < arr6.toArray().length; i++) {
                                names.add(arr6.get(i).toString());
                                dorsals.add(arr7.get(i).toString());
                                genres.add(arr8.get(i).toString());
                                echelons.add(arr9.get(i).toString());
                                times.add(Long.parseLong(arr10.get(i).toString()));
                            }

                            if(names.isEmpty()){
                                System.out.println();
                                System.out.println("Não existem participantes registados neste evento.");
                            }
                            else{
                                System.out.println();
                                System.out.println("Classificação " + typeOfClassification + " do Evento " + eventName + ":");
                                System.out.println();

                                for (int i = 1; i < names.size() + 1; i++) {

                                    System.out.print(i + "º lugar - ");
                                    System.out.print(names.get(i-1) + " - ");
                                    System.out.print("dorsal nº " + dorsals.get(i-1) + " - ");
                                    System.out.print(genres.get(i-1) + " - ");
                                    System.out.print(echelons.get(i-1) + " - ");
                                    String time = passToString(times.get(i-1));
                                    System.out.println(time);
                                }
                            }
                                
                        }
                       
                        break;
                        
                    case 5:
                        
                        System.out.print("Insira o nome do Evento: ");
                        eventName = reader.readLine();
                        
                        System.out.print("Insira o Ponto Intermédio que pretende: ");
                        point = reader.readLine();
                        
                        while(!(point.equals("start")) && !(point.equals("P1")) && !(point.equals("P2")) && !(point.equals("P3")) && !(point.equals("finish"))){
                            
                            System.out.print("Insira um locationID válido: ");
                            point = reader.readLine();
                        }
                        
                        url="http://localhost:8090/participants/howMany?" + "eventName=" + eventName + "&point=" + point;
                        
                        received = client.sendGet(url);
                        
                        //Integer howManyParticipantsPassedBy = Integer.parseInt(received);
                        
                        System.out.println("Passaram pelo ponto " + point + " : " + received + " atletas.");
                        
                        break;
                    case 6:
                        
                        System.out.print("Insira o nome do Evento em que pretende registar um Novo Participante: ");
                        eventName = reader.readLine();
                        
                        System.out.print("Insira o nome do Novo Participante: ");
                        participantName = reader.readLine();
                        
                        System.out.print("Insira o género do Novo Participante (Masculino(M); Feminino(F)): ");
                        genre = reader.readLine();
                        
                        while(!(genre.equals("M")) && !(genre.equals("F"))){
                            
                            System.out.print("Input incorreto. Submeta de novo: ");
                            genre = reader.readLine();
                        }
                        
                        if(genre.equals("M")){
                            genre = "Masculino";
                        }
                        else{
                            genre = "Feminino";
                        }
                        
                        
                        System.out.print("Insira o escalão do Novo Participante: ");
                        System.out.println("Júnior: 18 a 19 anos (Escreva J)");
                        System.out.println("Sénior: 20 a 34 anos (Escreva S)");
                        System.out.println("Veterano 35: dos 35 aos 39 anos (Escreva V35)");
                        System.out.println("Veterano 40: dos 40 aos 44 anos (Escreva V40)");
                        System.out.println("Veterano 45: dos 45 aos 49 anos (Escreva V45)");
                        System.out.println("Veterano 50: dos 50 aos 54 anos (Escreva V50)");
                        System.out.println("Veterano 55: dos 55 aos 59 anos (Escreva V55)");
                        System.out.println("Veterano 60: dos 60 aos 64 anos (Escreva V60)");
                        System.out.println("Veterano 60+: dos 65 em diante (Escreva V65+)");

                        echelon = reader.readLine();

                        while(!(echelon.equals("J")) && !(echelon.equals("S")) && !(echelon.equals("V35")) && !(echelon.equals("V40")) &&
                                !(echelon.equals("V45")) && !(echelon.equals("V50")) && !(echelon.equals("V55")) && !(echelon.equals("V60")) && !(echelon.equals("V65+"))){

                            System.out.print("Input incorreto. Submeta de novo: ");
                            echelon = reader.readLine();
                        }

                        if(echelon.equals("J")){
                            echelon = "Júnior";
                        }
                        else if(echelon.equals("S")){
                            echelon = "Sénior";
                        }
                        else if(echelon.equals("V35")){
                            echelon = "Veterano 35";
                        }
                        else if(echelon.equals("V40")){
                            echelon = "Veterano 40";
                        }
                        else if(echelon.equals("V45")){
                            echelon = "Veterano 45";
                        }
                        else if(echelon.equals("V50")){
                            echelon = "Veterano 50";
                        }
                        else if(echelon.equals("V55")){
                            echelon = "Veterano 55";
                        }
                        else if(echelon.equals("V60")){
                            echelon = "Veterano 60";
                        }
                        else{
                            echelon = "Veterano 65+";
                        }
                        
                        
                        url = "http://localhost:8090/participants/addNewParticipant";
                        
                        send.put("eventName", eventName);
                        send.put("participantName", participantName);
                        send.put("genre", genre);
                        send.put("echelon", echelon);
                        
                        sendPost(url, send);
                        
                        break;
                    case 7:
                        System.out.println("Closing Running Events App...");
                        System.exit(0);
                }
                
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
