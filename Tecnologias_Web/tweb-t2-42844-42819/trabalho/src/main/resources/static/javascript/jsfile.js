
function validationFunction(validation){
    if(validation == ""){
        return false;
    }
}

 function getCurrentDate(actualDate){
     actualDate = new Date();
     actualDate = actualDate.getFullYear()+'-'+(actualDate.getMonth()+1)+'-'+actualDate.getDate();
     return actualDate;
 }

 function removeAllChildNodes(parent) {
     while(parent.firstChild) {
         parent.removeChild(parent.firstChild);
     }
 }
 
 function toTimeOfRun(timeOfRun){
    
    var hours = timeOfRun / (1000*60*60);
    var Hours = Math.floor(hours);
    var h = Hours > 9 ? Hours : '0' + Hours;

    //Get remainder from hours and convert to minutes
    var minutes = (hours - Hours) * 60;
    var Minutes = Math.floor(minutes);
    var m = Minutes > 9 ? Minutes : '0' +  Minutes;

    //Get remainder from minutes and convert to seconds
    var seconds = (minutes - Minutes) * 60;
    var Seconds = Math.floor(seconds);
    var s = Seconds > 9 ? Seconds : '0' + Seconds;

    return h + ':' + m + ':' + s;
 }
 

 function getEventsOfTheDay(){
     
     var actualDate = getCurrentDate(actualDate);

     var actionUrl = "/searchEvents";

     $.post(actionUrl, {eventName:"hoje", eventDate:"hoje"}, function(data){

         for(let a in data.list){

             $("#eventosADecorrer").show();

             appearanceOfEvents(data.list[a].eventName, data.list[a].eventDate, data.list[a].eventDescription, "eventosADecorrer");
             
         }
     });
 }
 
 
 function showSubscriptionsFunction(eventName){
     
     var New = document.createElement("p");
     New.setAttribute("class", "EventosParagrafoNome");
     New.appendChild(document.createTextNode(eventName));
     document.getElementById("acessoReservado_Athlete").appendChild(New);
 }

 

 function inscreverParticipantesFunction(evento, qualDiv){
    
    event.preventDefault();

        var formValues = $(this).serialize();
        var actionUrl = "/registerParticipant";
        
        var text="Do you really want to register?";
        
        if(confirm(text)==true){
            
            $.post(actionUrl, {eventName:evento}, function(data){

                if(data.result == "ok"){
                    alert("Successfully Registered!");
                }
                else if(data.result == "admin"){
                    alert("Admins can't register!");
                }
                else if(data.result == "exist"){
                    alert("You are already registed in this event!");
                }
                else{
                    alert("You need to be sign in to regist!");
                }
            });
        }    
 }

 function ListaDeParticipantesFunction(nomedoevento, qualDiv){

    var actionUrl = "/getParticipantsList";

    $.get(actionUrl, {eventName: nomedoevento}, function(data){
        
        if(data.resultado == "ok"){

            var container = document.getElementById("ListarParticipanteDiv");
            removeAllChildNodes(container);

            var titleListaParticipante = document.createElement("p");
            titleListaParticipante.setAttribute("class", "EventosParagrafoNome");
            titleListaParticipante.innerHTML = "Lista de Participantes do Evento: " + nomedoevento;
            document.getElementById("ListarParticipanteDiv").appendChild(titleListaParticipante);

            var table = document.createElement("table");
            var thead = document.createElement("thead");
            var tbody = document.createElement("tbody");

            table.appendChild(thead);
            table.appendChild(tbody);

            document.getElementById("ListarParticipanteDiv").appendChild(table);

            var linha1 = document.createElement("tr");
            var nome = document.createElement("th");
            nome.innerHTML = "Nome do Participante";
            var genero = document.createElement("th");
            genero.innerHTML = "Género";
            var escalao = document.createElement("th");
            escalao.innerHTML = "Escalão";

            linha1.appendChild(nome);
            linha1.appendChild(genero);
            linha1.appendChild(escalao);

            thead.appendChild(linha1);

            for(let a in data.list){
                
                var novalinha = document.createElement("tr");

                var nomeRecebido = document.createElement("td");
                nomeRecebido.innerHTML = data.list[a].username;

                var generorecebido = document.createElement("td");
                generorecebido.innerHTML = data.list[a].gender;

                var escalaoRecebido = document.createElement("td");
                escalaoRecebido.innerHTML = data.list[a].echelon;

                novalinha.appendChild(nomeRecebido);
                novalinha.appendChild(generorecebido);
                novalinha.appendChild(escalaoRecebido);

                tbody.appendChild(novalinha);
            }

            var hashtag = "#"; 
            var qualDivNova = hashtag.concat(qualDiv);   //# + qualDiv

            $(qualDivNova).hide();
            $("#ListarParticipanteDiv").show();

        }
        else{
            alert("There are no participants in this Event!");
        }
    });
 }

 function createTablesToClassificacaoGeral(qualDiv, data){

    var titulo;

    if(qualDiv == "ClassificacaoGeralAbsolutaDiv"){
        titulo = "Classificação Absoluta";
    }
    else if(qualDiv == "ClassificacaoGeralMasculinaDiv"){
        titulo = "Classificação Masculina";
    }
    else if(qualDiv == "ClassificacaoGeralFemininaDiv"){
        titulo = "Classificação Feminina";
    }

    var titleClassificacao = document.createElement("div");
    titleClassificacao.setAttribute("id", qualDiv);
    titleClassificacao.setAttribute("class", "tabelasDeClassificacao");
    document.getElementById("ClassificacaoGeralDiv").appendChild(titleClassificacao);

    var titleMesmoTitle = document.createElement("p");
    titleMesmoTitle.setAttribute("class", "TilteMesmoTilte");
    titleMesmoTitle.innerHTML = titulo;
    document.getElementById(qualDiv).appendChild(titleMesmoTitle);

    var table = document.createElement("table");
    var thead = document.createElement("thead");
    var tbody = document.createElement("tbody");

    table.appendChild(thead);
    table.appendChild(tbody);

    document.getElementById(qualDiv).appendChild(table);
    document.getElementById("ClassificacaoGeralDiv").appendChild(document.getElementById(qualDiv));

    var linha1 = document.createElement("tr");
    var posicao = document.createElement("th");
    posicao.innerHTML = "Posição";
    var nome = document.createElement("th");
    nome.innerHTML = "Nome";
    var dorsal = document.createElement("th");
    dorsal.innerHTML = "Dorsal";
    var escalao = document.createElement("th");
    escalao.innerHTML = "Escalão";
    var tempo = document.createElement("th");
    tempo.innerHTML = "Tempo";
    var genero = document.createElement("th");
    genero.innerHTML = "Género";

    linha1.appendChild(posicao);
    linha1.appendChild(nome);
    linha1.appendChild(dorsal);
    linha1.appendChild(escalao);
    linha1.appendChild(tempo);
    linha1.appendChild(genero);

    thead.appendChild(linha1);
    
    var i = 1;
    
    for(let a in data.list){

        var novalinha = document.createElement("tr");

        var posicaoRecebida = document.createElement("td");
        posicaoRecebida.innerHTML = i++;

        var nomeRecebido = document.createElement("td");
        nomeRecebido.innerHTML = data.list[a].username;

        var dorsalRecebido = document.createElement("td");
        dorsalRecebido.innerHTML = data.list[a].dorsal;

        var escalaoRecebido = document.createElement("td");
        escalaoRecebido.innerHTML = data.list[a].echelon;

        var tempoRecebido = document.createElement("td");
        
        tempoRecebido.innerHTML = toTimeOfRun(data.list[a].timeOfRun);

        var generoRecebido = document.createElement("td");
        generoRecebido.innerHTML = data.list[a].gender;

        novalinha.appendChild(posicaoRecebida);
        novalinha.appendChild(nomeRecebido);
        novalinha.appendChild(dorsalRecebido);
        novalinha.appendChild(escalaoRecebido);
        novalinha.appendChild(tempoRecebido);
        novalinha.appendChild(generoRecebido);

        tbody.appendChild(novalinha);
    }
 }

 


 function ClassificacaoGeralFunction(nomeDoEvento, qualDiv){

    var actionUrl = "/getClassif";

    var container = document.getElementById("ClassificacaoGeralDiv");
    removeAllChildNodes(container);

    $.get(actionUrl, {evento: nomeDoEvento, genero: "all"}, function(data){
        console.log("all " + data.list);
        if(data.resultado == "ok"){
            createTablesToClassificacaoGeral("ClassificacaoGeralAbsolutaDiv", data);
        }
        else{
            alert("Erro! Algo correu mal");
        }
    });

    $.get(actionUrl, {evento: nomeDoEvento, genero: "M"}, function(data){
        console.log("M " + data.list);
        if(data.resultado == "ok"){
            createTablesToClassificacaoGeral("ClassificacaoGeralMasculinaDiv", data);
        }
        else{
            alert("Erro! Algo correu mal");
        }
    });

    $.get(actionUrl, {evento: nomeDoEvento, genero: "F"}, function(data){
        console.log("F " + data.list);
        if(data.resultado == "ok"){
            createTablesToClassificacaoGeral("ClassificacaoGeralFemininaDiv", data);
        }
        else{
            alert("Erro! Algo correu mal");
        }
    });

    var hashtag = "#"; 
    var qualDivNova = hashtag.concat(qualDiv);   //# + qualDiv

    $(qualDivNova).hide();
    $("#ClassificacaoGeralDiv").show();
 }



 function appearanceOfEvents(nomeDoEvento, dataDoEvento, descricaoDoEvento, qualDiv){
     
     var pNew = document.createElement("p");
     pNew.setAttribute("class", "EventosParagrafoNome");
     pNew.appendChild(document.createTextNode(nomeDoEvento));
     document.getElementById(qualDiv).appendChild(pNew);

     pNew = document.createElement("p");
     pNew.setAttribute("class", "EventosParagrafoData");
     pNew.appendChild(document.createTextNode("Realiza-se na data: " + dataDoEvento));
     document.getElementById(qualDiv).appendChild(pNew);

     pNew = document.createElement("p");
     pNew.setAttribute("class", "EventosparagrafoDescricao");
     pNew.appendChild(document.createTextNode("Descrição do evento: " + descricaoDoEvento));
     document.getElementById(qualDiv).appendChild(pNew);

     var bNew = document.createElement("button");
     bNew.setAttribute("id", "inscreverParticipantes");
     bNew.onclick = function(){ inscreverParticipantesFunction(nomeDoEvento, qualDiv); };
     bNew.setAttribute("class", "buttonsOfEventsExplore");
     bNew.appendChild(document.createTextNode("Inscrever Participante"));
     document.getElementById(qualDiv).appendChild(bNew);

     bNew = document.createElement("button");
     bNew.setAttribute("id", "ListaDeParticipantes");
     bNew.onclick = function(){ ListaDeParticipantesFunction(nomeDoEvento, qualDiv); };
     bNew.setAttribute("class", "buttonsOfEventsExplore");
     bNew.appendChild(document.createTextNode("Lista de Participantes"));
     document.getElementById(qualDiv).appendChild(bNew);

     bNew = document.createElement("button");
     bNew.setAttribute("id", "ClassificacaoGeral");
     bNew.onclick = function(){ ClassificacaoGeralFunction(nomeDoEvento, qualDiv); };
     bNew.setAttribute("class", "buttonsOfEventsExplore");
     bNew.appendChild(document.createTextNode("Classificação Geral"));
     document.getElementById(qualDiv).appendChild(bNew);

     pNew = document.createElement("br");
     document.getElementById(qualDiv).appendChild(pNew);

     pNew = document.createElement("hr");
     pNew.setAttribute("class", "separateLine");
     document.getElementById(qualDiv).appendChild(pNew);
 }


 $(document).ready(function(){

    getEventsOfTheDay();

    $("#login_registo_div").hide();
    $("#acessoReservado").hide();
    $("#acessoReservado_Admin").hide();

    $("#hideProcurarEventoButton").click(function(){
        $("#procurarEvento").hide();
        $("#eventosADecorrer").show();
    });

    $("#acessoReservado_button").click(function(){
        $("#procurarEvento").hide();
        $("#eventosRealizados").hide();
        $("#eventosADecorrer").hide();
        $("#proximosEventos").hide();
        $("#ListarParticipanteDiv").hide();
        $("#inscreverParticipanteDiv").hide();
        $("#ClassificacaoGeralDiv").hide();
        $("#eventosRecebidos").hide();
        $("#acessoReservado_Athlete").show();
    });

    $("#hideRegistarEventoETempoParticipanteButton").click(function(){
        $("#acessoReservado").hide();
        $("#acessoReservado_Admin").hide();
        $("#eventosADecorrer").show();
    });
    
    $("#hideProximosEventosButton").click(function(){
        $("#proximosEventos").hide();
        $("#eventosADecorrer").show();
    });

    $("#eventos_realizados_button").click(function(){
        $("#procurarEvento").hide();
        $("#acessoReservado").hide();
        $("#eventosADecorrer").hide();
        $("#proximosEventos").hide();
        $("#ListarParticipanteDiv").hide();
        $("#inscreverParticipanteDiv").hide();
        $("#ClassificacaoGeralDiv").hide();
        $("#eventosRecebidos").hide();
        $("#acessoReservado_Admin").hide();
        $("#login_registo_div").hide();

        var container = document.getElementById("eventosRealizados");
        removeAllChildNodes(container);

        var actualDate = getCurrentDate(actualDate);
        var actionUrl = "/searchEvents";

        $.post(actionUrl, {eventName:"realizados", eventDate:"realizados"}, function(data){

            for(let a in data.list){
                
                $("#eventosRealizados").show();

                appearanceOfEvents(data.list[a].eventName, data.list[a].eventDate, data.list[a].eventDescription, "eventosRealizados");
            }

        });

    });

    $("#eventos_a_decorrer_button").click(function(){
        $("#procurarEvento").hide();
        $("#acessoReservado").hide();
        $("#eventosRealizados").hide();
        $("#proximosEventos").hide();
        $("#ListarParticipanteDiv").hide();
        $("#inscreverParticipanteDiv").hide();
        $("#ClassificacaoGeralDiv").hide();
        $("#eventosRecebidos").hide();
        $("#acessoReservado_Admin").hide();
        $("#login_registo_div").hide();

        var container = document.getElementById("eventosADecorrer");
        removeAllChildNodes(container);

        $("#eventosADecorrer").show();

        getEventsOfTheDay();
    });


    $("#proximos_eventos_button").click(function(){
        $("#procurarEvento").hide();
        $("#acessoReservado").hide();
        $("#eventosADecorrer").hide();
        $("#eventosRealizados").hide();
        $("#ListarParticipanteDiv").hide();
        $("#inscreverParticipanteDiv").hide();
        $("#ClassificacaoGeralDiv").hide();
        $("#eventosRecebidos").hide();
        $("#acessoReservado_Admin").hide();
        $("#login_registo_div").hide();

        var container = document.getElementById("proximosEventos");
        removeAllChildNodes(container);

        var actualDate = getCurrentDate(actualDate);
        var actionUrl = "/searchEvents";

        $.post(actionUrl, {eventName:"futuros", eventDate:"futuros"}, function(data){

            for(let a in data.list){

                $("#proximosEventos").show();

                appearanceOfEvents(data.list[a].eventName, data.list[a].eventDate, data.list[a].eventDescription, "proximosEventos");
            }
        });
    });
    
    $("#procurar_evento_button").click(function(){
        $("#acessoReservado").hide();
        $("#eventosRealizados").hide();
        $("#eventosADecorrer").hide();
        $("#proximosEventos").hide();
        $("#ListarParticipanteDiv").hide();
        $("#inscreverParticipanteDiv").hide();
        $("#ClassificacaoGeralDiv").hide();
        $("#eventosRecebidos").hide();
        $("#login_registo_div").hide();
        $("#acessoReservado_Admin").hide();
        $("#procurarEvento").show();
    }); 
    
    $("#login_regist_button").click(function(){
        //$("#acessoReservado").hide();
        $("#eventosRealizados").hide();
        $("#eventosADecorrer").hide();
        $("#proximosEventos").hide();
        $("#ListarParticipanteDiv").hide();
        $("#inscreverParticipanteDiv").hide();
        $("#ClassificacaoGeralDiv").hide();
        $("#eventosRecebidos").hide();
        $("#procurarEvento").hide();
        $("#login_registo_div").show();
    });
    
    $("#acesso_admin_button").click(function(){
        $("#eventosRealizados").hide();
        $("#eventosADecorrer").hide();
        $("#proximosEventos").hide();
        $("#ListarParticipanteDiv").hide();
        $("#inscreverParticipanteDiv").hide();
        $("#ClassificacaoGeralDiv").hide();
        $("#eventosRecebidos").hide();
        $("#procurarEvento").hide();
        $("#acessoReservado_Admin").show();
    });
    
    $("#buttonToGetRegistrations").click(function(){
       
        var actionUrl = "/getSubscriptions";
     
            $.get(actionUrl, function (data) {
                
                console.log(data);
                if(data.message == "ok"){
                    
                    for(let a in data.list){
                        showSubscriptionsFunction(data.list[a]);
                    }
                }
                else{
                    alert("You have no subscriptions!");
                }
            });
    });
    

   $("#procurarEventoForm").on("submit", function (event){

        $(".procurarEventoFormP").remove(); //remove os elementos que foram adicionados resultantes da pesquisa anterior

        var container = document.getElementById("eventosRecebidos");
        removeAllChildNodes(container);

        var eventoValue = document.forms.procurarEventoForm.eventName.value;
        var dataValue = document.forms.procurarEventoForm.eventDate.value;

        if (validationFunction(eventoValue) == false && validationFunction(dataValue) == false){
            alert("The box is not filled");
        }
        else {
            event.preventDefault();

            var formValues = $(this).serialize();
            var actionUrl = "/searchEvents";
            
            console.log("aqui");
            $.post(actionUrl, formValues, function (data) {

                var exist = false;
                console.log(data);

                for(let a in data.list){
          
                    exist = true;

                    $("#procurarEvento").hide();
                    $("#eventosRecebidos").show();

                    appearanceOfEvents(data.list[a].eventName, data.list[a].eventDate, data.list[a].eventDescription, "eventosRecebidos");
             
                }
                if(exist == false){
                    var eventReceived = document.createElement("p");
                    eventReceived.setAttribute("class", "procurarEventoFormP");
                    eventReceived.appendChild(document.createTextNode("Não existem eventos com esse nome ou não se realizam nessa data."));
                    document.getElementById("newMiddleDivProcurarEvento").appendChild(eventReceived);
                }
            });
        }
    });

    $("#registarTempodeProvaDeParticipanteForm").on("submit", function(event){

        event.preventDefault();

        var formValues = $(this).serialize();
        var actionUrl = "/addNewTimeOfRun";

        $.post(actionUrl, formValues, function(data){
            console.log(data);
            if(data.resultado == "1"){
                alert("Time of run added");
            }
            else if(data.resultado == "2"){
                alert("Wrong Location ID or Invalid Time");
            }
            else if(data.resultado == "3"){
                alert("Participant does not exist");
            }
            else{
                alert("Event does not exist");
            }
        });
        
    });

    
      
 });
