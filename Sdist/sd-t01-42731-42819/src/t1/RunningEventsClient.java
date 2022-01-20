package t1;

import java.util.Scanner;

/**
 *
 * @author vasco
 */
public class RunningEventsClient {
    

    public static void execute(Server_Answer server_answer){
        
        if(server_answer.getResponse()== 1){
         
            System.out.println();
            
            if(server_answer.getString_list().size() == 1){
                
                System.out.println(server_answer.getString_list().get(0));
            }
            else{
                
                System.out.println("Registo do participante executado com sucesso, com o dorsal nº" + server_answer.getDorsal_list().get(0));
            }
            
            System.out.println();
        }
        else if(server_answer.getResponse()== 2){
            
            System.out.println();
            System.out.println("Eventos na data escolhida: ");
            System.out.println();
            
            if(server_answer.getString_list().size() == 0){
                System.out.println("Não existem eventos nessa data.");
            }
            else{
                System.out.println("OIII TAMANHO É: " + server_answer.getString_list().size());
                int limit = (server_answer.getString_list().size() / 2); //para usar o size da lista na mesma, e conseguir printar os eventos e os tipos
                System.out.println("limit: " + limit);
                for(int i = 0; i < limit; i++){                              //sem resultar num IndexOutOfBounds
                    System.out.println(server_answer.getString_list().get(i+i) + " - " + server_answer.getString_list().get(i+i+1));
                }
            }
            
            System.out.println();
            
        }
        else if(server_answer.getResponse()== 3){
            
            System.out.println();
            
            if(server_answer.getString_list().size() > 0 && server_answer.getString_list().get(0).equals("F")){
                System.out.println("Não foi possível registar o evento. Já existe um evento com este nome.");
            }
            else{
                System.out.println("O evento foi registado com sucesso.");
            }
            
            System.out.println();
            
        }
        else if(server_answer.getResponse()== 4){
            
            System.out.println();
            
            if(server_answer.getString_list().get(0).equals("That event does not exist")){
                
                System.out.println("O evento em questão não existe.");
            }
            else if(server_answer.getString_list().size() == 1 && !(server_answer.getString_list().get(0).equals("That event does not exist"))){
                
                System.out.println("Não existem participantes registados no evento " + server_answer.getString_list().get(0) + ".");
            }
            else{
                
                System.out.println("Participantes da prova " + server_answer.getString_list().get(0) + ":");
                for(int i = 0; i < server_answer.getDorsal_list().size(); i++){
                    System.out.println("dorsal nº: " + server_answer.getDorsal_list().get(i) + " - " + server_answer.getString_list().get(i+i+i+1) + 
                            " - " + server_answer.getString_list().get(i+i+i+2) + " - " + server_answer.getString_list().get(i+i+i+3));
                }
            }
            
            System.out.println();
            
        }
        else if(server_answer.getResponse()== 5){
            
            System.out.println();
            
            if(server_answer.getString_list().get(0).equals("That event does not exist")){
                
                System.out.println("O evento em questão não existe.");
            }
            else if(server_answer.getString_list().size() > 1 && server_answer.getString_list().get(1).equals("No participants")){
                
                System.out.println("Não existem participantes registados no evento em questão.");
            }
            else if(server_answer.getString_list().size() > 1 && server_answer.getString_list().get(1).equals("That dorsal does not exist")){
                
                System.out.println("O dorsal inserido não existe.");
            }
            else{
                
                System.out.println("Tempo de prova registado.");
            }
            
            System.out.println();
            
        }
        else if(server_answer.getResponse()== 6){
            
            System.out.println();
            
            if(server_answer.getString_list().get(0).equals("That event does not exist")){
                
                System.out.println("O evento em questão não existe.");
            }
            else if(server_answer.getString_list().get(2).equals("No participants")){
                    System.out.println("Não existem participantes com esse género e/ou escalão OU não finalizaram o evento.");
            }
            else{
                
                if(server_answer.getString_list().get(1).equals("A")){
                 
                    System.out.println("Classificação Absoluta do Evento " + server_answer.getString_list().get(0) + ":");

                    int lugar = 0;

                    for (int i = 0; i < server_answer.getDorsal_list().size(); i++) {

                        lugar = i + 1;
                        System.out.print(lugar + "º lugar - " + server_answer.getString_list().get(i+i+2) +" - dorsal nº");
                        System.out.print(server_answer.getDorsal_list().get(i) + " - ");
                        System.out.println(server_answer.getString_list().get(i+i+3));
                    }
                   
                }
                else if(server_answer.getString_list().get(1).equals("Masculino")){
                   
                    System.out.println("Classificação Masculina do Evento " + server_answer.getString_list().get(0) + ":");

                    int lugar = 0;

                    for (int i = 0; i < server_answer.getDorsal_list().size(); i++) {

                        lugar = i + 1;
                        System.out.print(lugar + "º lugar - " + server_answer.getString_list().get(i+i+2) +" - dorsal nº");
                        System.out.print(server_answer.getDorsal_list().get(i) + " - ");
                        System.out.println(server_answer.getString_list().get(i+i+3));
                    }
                }
                else if(server_answer.getString_list().get(1).equals("Feminino")){
                  
                    System.out.println("Classificação Feminina do Evento " + server_answer.getString_list().get(0) + ":");

                    int lugar = 0;

                    for (int i = 0; i < server_answer.getDorsal_list().size(); i++) {

                        lugar = i + 1;
                        System.out.print(lugar + "º lugar - " + server_answer.getString_list().get(i+i+2) +" - dorsal nº");
                        System.out.print(server_answer.getDorsal_list().get(i) + " - ");
                        System.out.println(server_answer.getString_list().get(i+i+3));
                    }
                }
            }
            
            System.out.println();
            
        }
        else if(server_answer.getResponse()== 7){
            
            System.out.println();
            
            if(server_answer.getString_list().get(0).equals("That event does not exist")){
                
                System.out.println("O evento em questão não existe.");
            }
            else if(server_answer.getString_list().get(3).equals("No participants")){
                System.out.println("Não existem participantes com esse género e/ou escalão OU não finalizaram o evento.");
            }
            else{
                
                System.out.println("Top 3 " + server_answer.getString_list().get(1) + " do escalão " + server_answer.getString_list().get(2) + " no evento "
                        + server_answer.getString_list().get(0) + ":");
                
                int lugar = 0;

                for (int i = 0; i < server_answer.getDorsal_list().size(); i++) {

                    lugar = i + 1;
                    System.out.print(lugar + "º lugar - " + server_answer.getString_list().get(i+i+3) +" - dorsal nº");
                    System.out.print(server_answer.getDorsal_list().get(i) + " - ");
                    System.out.println(server_answer.getString_list().get(i+i+4));
                }
            }
        }
        
        System.out.println();
    }
    
    
    
    
    
    
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        
        if (args.length != 2) { // requer 2 argumentos
	    System.out.println
		("You forgot to add the registryHost or the registryPort");
	    System.exit(1);
	}
        
        String regHost = args[0];
        String regPort = args[1];
        
        try{
            
            RunningEvents obj =
	      (RunningEvents) java.rmi.Naming.lookup("rmi://" 
                      + regHost +":"+regPort +"/runningevents"); 
            
            Scanner s = new Scanner(System.in);
            
            int inputDeEnvio;
            byte[] byteDeEnvio = new byte[256];
            
            int option = 0;
            
            while(true){
                System.out.println("############### MENU PRINCIPAL ################");
                System.out.println("1 - Registar Participante"); //num determinado evento
                System.out.println("2 - Consultar Eventos");  //numa data específica
                System.out.println("3 - Registar Evento");   
                System.out.println("4 - Listar participantes inscritos num evento");
                System.out.println("5 - Registar tempo de prova de um participante");
                System.out.println("6 - Classificação geral de um evento");
                System.out.println("7 - Top3 de cada evento");
                System.out.println("8 - Sair da aplicação");
                
                
                System.out.println("Insira a opção do menu:");
                
                option = s.nextInt(); 
               
                Client_Request new_request = new Client_Request();
                
                if(option == 1){                   //registar participante
                    
                    System.out.println("Preencha os campos relativos ao participante e ao evento em que pretende inscrever:");
                    
                    System.out.print("Evento: ");
                    inputDeEnvio = System.in.read(byteDeEnvio, 0, 256);
                    String evento = new String(byteDeEnvio, 0, inputDeEnvio-1);
                    
                    System.out.println();
                    
                    System.out.print("Nome do Participante: ");
                    inputDeEnvio = System.in.read(byteDeEnvio, 0, 256);
                    String nome = new String(byteDeEnvio, 0, inputDeEnvio-1);
                    
                    System.out.println();
                    
                    System.out.print("Género: ");
                    System.out.print("Masculino (Pressione M) ou Feminino (Pressione F): ");
                    inputDeEnvio = System.in.read(byteDeEnvio, 0, 256);
                    String genero = new String(byteDeEnvio, 0, inputDeEnvio-1);
                    
                    while(!(genero.equals("M")) && !(genero.equals("F"))){
                        
                        System.out.print("Incorrect input. Type again: ");
                        inputDeEnvio = System.in.read(byteDeEnvio, 0, 256);
                        genero = new String(byteDeEnvio, 0, inputDeEnvio-1);
                    }
                    
                    if(genero.equals("M")){
                        
                        genero = "Masculino";
                    }
                    else{
                        
                        genero = "Feminino";
                    }
                    
                    System.out.println();
                    
                    System.out.println("Selecione o escalão:");
                    System.out.println("Júnior: 18 a 19 anos (Escreva J)");
                    System.out.println("Sénior: 20 a 34 anos (Escreva S)");
                    System.out.println("Veterano 35: dos 35 aos 39 anos (Escreva V35)");
                    System.out.println("Veterano 40: dos 40 aos 44 anos (Escreva V40)");
                    System.out.println("Veterano 45: dos 45 aos 49 anos (Escreva V45)");
                    System.out.println("Veterano 50: dos 50 aos 54 anos (Escreva V50)");
                    System.out.println("Veterano 55: dos 55 aos 59 anos (Escreva V55)");
                    System.out.println("Veterano 60: dos 60 aos 64 anos (Escreva V60)");
                    System.out.println("Veterano 60+: dos 65 em diante (Escreva V65+)");
                    
                    inputDeEnvio = System.in.read(byteDeEnvio, 0, 256);
                    String escalao = new String(byteDeEnvio, 0, inputDeEnvio-1);
                    
                    while(!(escalao.equals("J")) && !(escalao.equals("S")) && !(escalao.equals("V35")) && !(escalao.equals("V40")) &&
                            !(escalao.equals("V45")) && !(escalao.equals("V50")) && !(escalao.equals("V55")) && !(escalao.equals("V60")) && !(escalao.equals("V65+"))){
                        
                        System.out.print("Input incorreto. Submeta de novo: ");
                        inputDeEnvio = System.in.read(byteDeEnvio, 0, 256);
                        escalao = new String(byteDeEnvio, 0, inputDeEnvio-1);
                    }
                    
                    if(escalao.equals("J")){
                        escalao = "Júnior";
                    }
                    else if(escalao.equals("S")){
                        escalao = "Sénior";
                    }
                    else if(escalao.equals("V35")){
                        escalao = "Veterano 35";
                    }
                    else if(escalao.equals("V40")){
                        escalao = "Veterano 40";
                    }
                    else if(escalao.equals("V45")){
                        escalao = "Veterano 45";
                    }
                    else if(escalao.equals("V50")){
                        escalao = "Veterano 50";
                    }
                    else if(escalao.equals("V55")){
                        escalao = "Veterano 55";
                    }
                    else if(escalao.equals("V60")){
                        escalao = "Veterano 60";
                    }
                    else{
                        escalao = "Veterano 65+";
                    }
                    
                    new_request.getString_List().add(evento);
                    new_request.getString_List().add(nome);
                    new_request.getString_List().add(genero);
                    new_request.getString_List().add(escalao);
                    
                    RunningEventsClient.execute(obj.registryPlayer(new_request));
                    
                }
                
                else if(option == 2){              //consultar eventos
                    
                    System.out.print("Insira (no formato AAAA-MM-DD) a data em que pretende consultar os eventos: ");
                    
                    inputDeEnvio = System.in.read(byteDeEnvio, 0, 256);
                    String date = new String(byteDeEnvio, 0, inputDeEnvio-1);
                    
                    new_request.getString_List().add(date);
                    
                    RunningEventsClient.execute(obj.getEventsOfTheDay(new_request));
                }
                
                else if(option == 3){         //registar eventos
                    
                    System.out.print("Insira o nome do novo evento: ");
                    inputDeEnvio = System.in.read(byteDeEnvio, 0, 256);
                    String evento = new String(byteDeEnvio, 0, inputDeEnvio-1);
                    
                    System.out.println();
                    
                    System.out.println("Selecione o tipo do novo evento: Atletismo de Pista(P) | Corrida de Estrada(E) | Trail(T)");
                    inputDeEnvio = System.in.read(byteDeEnvio, 0, 256);
                    String tipo = new String(byteDeEnvio, 0, inputDeEnvio-1);
                    
                    while(!(tipo.equals("P")) && !(tipo.equals("E")) && !(tipo.equals("T"))){
                        
                        System.out.print("Input incorreto. Submeta de novo: ");
                        inputDeEnvio = System.in.read(byteDeEnvio, 0, 256);
                        tipo = new String(byteDeEnvio, 0, inputDeEnvio-1);
                    }
                    
                    if(tipo.equals("P")){
                        tipo = "Atletismo de Pista";
                    }
                    else if(tipo.equals("E")){
                        tipo = "Corrida de Estrada";
                    }
                    else{
                        tipo = "Trail";
                    }
                    
                    System.out.println();
                    
                    System.out.print("Insira (no formato AAAA-MM-DD) a data em que pretende registar o evento: ");
                    inputDeEnvio = System.in.read(byteDeEnvio, 0, 256);
                    String date = new String(byteDeEnvio, 0, inputDeEnvio-1);
                    
                    new_request.getString_List().add(evento);
                    new_request.getString_List().add(tipo);
                    new_request.getString_List().add(date);
                    
                    RunningEventsClient.execute(obj.registryEvent(new_request));
                }
                
                else if(option == 4){         //listar participantes inscritos num determinado evento
                    
                    System.out.print("Insira o evento onde pretende aceder aos participantes registados: ");
                    inputDeEnvio = System.in.read(byteDeEnvio, 0, 256);
                    String evento = new String(byteDeEnvio, 0, inputDeEnvio-1);
                    
                    new_request.getString_List().add(evento);
                    
                    RunningEventsClient.execute(obj.printPlayersInOneEvent(new_request));
                }
                
                else if(option == 5){         //registar tempo de prova de um participante
                    
                    System.out.print("Insira o evento onde o participante está inscrito: ");
                    inputDeEnvio = System.in.read(byteDeEnvio, 0, 256);
                    String evento = new String(byteDeEnvio, 0, inputDeEnvio-1);
                    
                    System.out.println();
                    
                    System.out.print("Insira o nº do dorsal do participante no evento: ");
                    s = new Scanner(System.in);
                    int dorsal = s.nextInt();
                    
                    System.out.println();
                    
                    System.out.println("Insira o tempo de prova do participante (no formato horas:minutos:segundos.milisegundos)");
                    System.out.println("Exemplo: 02:51:12.124");
                    inputDeEnvio = System.in.read(byteDeEnvio, 0, 256);
                    String tempodeprova = new String(byteDeEnvio, 0, inputDeEnvio-1);
                    
                    new_request.getString_List().add(evento);
                    new_request.getString_List().add(tempodeprova);
                    new_request.setDorsal(dorsal);
                    
                    System.out.println("tempo de prova inserido: " + tempodeprova);
                    
                    RunningEventsClient.execute(obj.registryTimeOfRace(new_request));
                }
                
                else if(option == 6){         //classificação geral de um evento
                    
                    System.out.print("Insira o evento: ");
                    inputDeEnvio = System.in.read(byteDeEnvio, 0, 256);
                    String evento = new String(byteDeEnvio, 0, inputDeEnvio-1);
                    
                    System.out.println();
                    
                    System.out.print("Pretende obter a classificação absoluta(A) ou por género?(G) :");
                    inputDeEnvio = System.in.read(byteDeEnvio, 0, 256);
                    String tipo = new String(byteDeEnvio, 0, inputDeEnvio-1);
                    
                    while(!(tipo.equals("A")) && !(tipo.equals("G"))){
                        
                        System.out.print("Input incorreto. Submeta de novo: ");
                        inputDeEnvio = System.in.read(byteDeEnvio, 0, 256);
                        tipo = new String(byteDeEnvio, 0, inputDeEnvio-1);
                    }
                    
                    if(tipo.equals("G")){
                        
                        System.out.print("Pretende obter a classificação Masculina?(M) ou Feminina(F): ");
                        inputDeEnvio = System.in.read(byteDeEnvio, 0, 256);
                        tipo = new String(byteDeEnvio, 0, inputDeEnvio - 1);

                        while (!(tipo.equals("M")) && !(tipo.equals("F"))) {

                            System.out.print("Input incorreto. Submeta de novo: ");
                            inputDeEnvio = System.in.read(byteDeEnvio, 0, 256);
                            tipo = new String(byteDeEnvio, 0, inputDeEnvio - 1);
                        }
                        
                        if(tipo.equals("M")){
                            tipo = "Masculino";
                        }
                        else{
                            tipo = "Feminino";
                        }
                    }
                    
                    
                    new_request.getString_List().add(evento);
                    new_request.getString_List().add(tipo);
                    
                    RunningEventsClient.execute(obj.printEventClassification(new_request));
                }
                
                else if(option == 7){         //top3 de cada evento
                 
                    System.out.print("Insira o evento: ");
                    inputDeEnvio = System.in.read(byteDeEnvio, 0, 256);
                    String evento = new String(byteDeEnvio, 0, inputDeEnvio-1);
                    
                    System.out.println();
                    
                    System.out.print("Género: ");
                    System.out.print("Masculino (Pressione M) ou Feminino (Pressione F): ");
                    inputDeEnvio = System.in.read(byteDeEnvio, 0, 256);
                    String genero = new String(byteDeEnvio, 0, inputDeEnvio-1);
                    
                    while(!(genero.equals("M")) && !(genero.equals("F"))){
                        
                        System.out.print("Input incorreto. Submeta de novo: ");
                        inputDeEnvio = System.in.read(byteDeEnvio, 0, 256);
                        genero = new String(byteDeEnvio, 0, inputDeEnvio-1);
                    }
                    
                    if(genero.equals("M")){
                        
                        genero = "Masculino";
                    }
                    else{
                        
                        genero = "Feminino";
                    }
                    
                    System.out.println();
                    
                    System.out.println("Selecione o escalão:");
                    System.out.println("Júnior: 18 a 19 anos (Escreva J)");
                    System.out.println("Sénior: 20 a 34 anos (Escreva S)");
                    System.out.println("Veterano 35: dos 35 aos 39 anos (Escreva V35)");
                    System.out.println("Veterano 40: dos 40 aos 44 anos (Escreva V40)");
                    System.out.println("Veterano 45: dos 45 aos 49 anos (Escreva V45)");
                    System.out.println("Veterano 50: dos 50 aos 54 anos (Escreva V50)");
                    System.out.println("Veterano 55: dos 55 aos 59 anos (Escreva V55)");
                    System.out.println("Veterano 60: dos 60 aos 64 anos (Escreva V60)");
                    System.out.println("Veterano 60+: dos 65 em diante (Escreva V65+)");
                    
                    inputDeEnvio = System.in.read(byteDeEnvio, 0, 256);
                    String escalao = new String(byteDeEnvio, 0, inputDeEnvio-1);
                    
                    while(!(escalao.equals("J")) && !(escalao.equals("S")) && !(escalao.equals("V35")) && !(escalao.equals("V40")) &&
                            !(escalao.equals("V45")) && !(escalao.equals("V50")) && !(escalao.equals("V55")) && !(escalao.equals("V60")) && !(escalao.equals("V65+"))){
                        
                        System.out.print("Input incorreto. Submeta de novo: ");
                        inputDeEnvio = System.in.read(byteDeEnvio, 0, 256);
                        escalao = new String(byteDeEnvio, 0, inputDeEnvio-1);
                    }
                    
                    if(escalao.equals("J")){
                        escalao = "Júnior";
                    }
                    else if(escalao.equals("S")){
                        escalao = "Sénior";
                    }
                    else if(escalao.equals("V35")){
                        escalao = "Veterano 35";
                    }
                    else if(escalao.equals("V40")){
                        escalao = "Veterano 40";
                    }
                    else if(escalao.equals("V45")){
                        escalao = "Veterano 45";
                    }
                    else if(escalao.equals("V50")){
                        escalao = "Veterano 50";
                    }
                    else if(escalao.equals("V55")){
                        escalao = "Veterano 55";
                    }
                    else if(escalao.equals("V60")){
                        escalao = "Veterano 60";
                    }
                    else{
                        escalao = "Veterano 65+";
                    }
                    
                    new_request.getString_List().add(evento);
                    new_request.getString_List().add(genero);
                    new_request.getString_List().add(escalao);
                    
                    RunningEventsClient.execute(obj.top3OfEachEvent(new_request));
                }
                
                else if(option == 8){         //sair da aplicação
                    System.out.println("Saiu da aplicação");
                    s.close();
                    System.exit(0);
                }
                
                else{
                    System.out.println("Opção inválida");
                }
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
