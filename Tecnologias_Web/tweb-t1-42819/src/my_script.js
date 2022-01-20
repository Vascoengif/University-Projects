


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

 function getEventsOfTheDay(){
     
     var actualDate = getCurrentDate(actualDate);

     var actionUrl = "http://alunos.di.uevora.pt/tweb/t1/eventsearch";

     $.post(actionUrl, {data: actualDate}, function(data){

         for(let a in data.eventos){

             $("#eventosADecorrer").show();

             appearanceOfEvents(data.eventos[a].nome, data.eventos[a].data, data.eventos[a].descricao, "eventosADecorrer");
             
         }
     });
 }

 

 function inscreverParticipantesFunction(evento, qualDiv){
     
    document.getElementById("responseInscreverParticipante").innerHTML = "";

    var hashtag = "#"; 
    var qualDivNova = hashtag.concat(qualDiv);   //# + qualDiv

    $(qualDivNova).hide();
    $("#inscreverParticipanteDiv").show();

    document.getElementById("inputInscreverParticipanteEvento").value = evento;
 }

 function ListaDeParticipantesFunction(nomedoevento, qualDiv){

    var actionUrl = "http://alunos.di.uevora.pt/tweb/t1/inscritos";

    //nomedoevento = "20 Km de Almeirim 2021";

    $.post(actionUrl, {evento: nomedoevento}, function(data){
        
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

            for(let a in data.inscritos){
                
                var novalinha = document.createElement("tr");

                var nomeRecebido = document.createElement("td");
                nomeRecebido.innerHTML = data.inscritos[a].nome;

                var generorecebido = document.createElement("td");
                generorecebido.innerHTML = data.inscritos[a].genero;

                var escalaoRecebido = document.createElement("td");
                escalaoRecebido.innerHTML = data.inscritos[a].escalao;

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
            alert("Erro! Algo correu mal");
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

    for(let a in data.classif){

        var novalinha = document.createElement("tr");

        var posicaoRecebida = document.createElement("td");
        posicaoRecebida.innerHTML = data.classif[a].pos;

        var nomeRecebido = document.createElement("td");
        nomeRecebido.innerHTML = data.classif[a].nome;

        var dorsalRecebido = document.createElement("td");
        dorsalRecebido.innerHTML = data.classif[a].dorsal;

        var escalaoRecebido = document.createElement("td");
        escalaoRecebido.innerHTML = data.classif[a].escalao;

        var tempoRecebido = document.createElement("td");
        tempoRecebido.innerHTML = data.classif[a].tempo;

        var generoRecebido = document.createElement("td");
        generoRecebido.innerHTML = data.classif[a].genero;

        novalinha.appendChild(posicaoRecebida);
        novalinha.appendChild(nomeRecebido);
        novalinha.appendChild(dorsalRecebido);
        novalinha.appendChild(escalaoRecebido);
        novalinha.appendChild(tempoRecebido);
        novalinha.appendChild(generoRecebido);

        tbody.appendChild(novalinha);
    }
 }

 function mostrarTop3(divQueMostra, data){

    var table = document.createElement("table");
    var thead = document.createElement("thead");
    var tbody = document.createElement("tbody");

    table.appendChild(thead);
    table.appendChild(tbody);

    document.getElementById(divQueMostra).appendChild(table);
    
    var linha1 = document.createElement("tr");
    var posicao = document.createElement("th");
    posicao.innerHTML = "Posição";
    var nome = document.createElement("th");
    nome.innerHTML = "Nome";

    linha1.appendChild(posicao);
    linha1.appendChild(nome);

    thead.appendChild(linha1);

    for(let a in data.top3){

        var novalinha = document.createElement("tr");

        var posicaoRecebida = document.createElement("td");
        posicaoRecebida.innerHTML = data.top3[a].pos;

        var nomeRecebido = document.createElement("td");
        nomeRecebido.innerHTML = data.top3[a].nome;

        novalinha.appendChild(posicaoRecebida);
        novalinha.appendChild(nomeRecebido);

        tbody.appendChild(novalinha);
    }

 }


 function ClassificacaoGeralFunction(nomeDoEvento, qualDiv){

    var actionUrl = "http://alunos.di.uevora.pt/tweb/t1/classif";

    var container = document.getElementById("ClassificacaoGeralDiv");
    removeAllChildNodes(container);

    $.post(actionUrl, {evento: nomeDoEvento}, function(data){

        if(data.resultado == "ok"){
            createTablesToClassificacaoGeral("ClassificacaoGeralAbsolutaDiv", data);
        }
        else{
            alert("Erro! Algo correu mal");
        }
    });

    $.post(actionUrl, {evento: nomeDoEvento, genero: "m"}, function(data){

        if(data.resultado == "ok"){
            createTablesToClassificacaoGeral("ClassificacaoGeralMasculinaDiv", data);
        }
        else{
            alert("Erro! Algo correu mal");
        }
    });

    $.post(actionUrl, {evento: nomeDoEvento, genero: "f"}, function(data){

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


 function Top3Function(evento, qualDiv){

    console.log(qualDiv);
    var hashtag = "#"; 
    var qualDivNova = hashtag.concat(qualDiv);   //# + qualDiv

    $(qualDivNova).hide();
    $("#Top3Div").show();

    document.getElementById("inputPedirTop3Eventos").value = evento;
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

     bNew = document.createElement("button");
     bNew.setAttribute("id", "Top3");
     bNew.onclick = function(){ Top3Function(nomeDoEvento, qualDiv); };
     bNew.setAttribute("class", "buttonsOfEventsExplore");
     bNew.appendChild(document.createTextNode("Top 3 do Evento"));
     document.getElementById(qualDiv).appendChild(bNew);

     pNew = document.createElement("br");
     document.getElementById(qualDiv).appendChild(pNew);

     pNew = document.createElement("hr");
     pNew.setAttribute("class", "separateLine");
     document.getElementById(qualDiv).appendChild(pNew);
 }


 $(document).ready(function(){

    getEventsOfTheDay();

    $("#procurar_evento_button").click(function(){
        $("#acessoReservado").hide();
        $("#eventosRealizados").hide();
        $("#eventosADecorrer").hide();
        $("#proximosEventos").hide();
        $("#ListarParticipanteDiv").hide();
        $("#inscreverParticipanteDiv").hide();
        $("#ClassificacaoGeralDiv").hide();
        $("#eventosRecebidos").hide();
        $("#Top3Div").hide();
        $("#procurarEvento").show();
    }); 

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
        $("#Top3Div").hide();
        $("#acessoReservado").show();
    });

    $("#hideRegistarEventoETempoParticipanteButton").click(function(){
        $("#acessoReservado").hide();
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
        $("#Top3Div").hide();

        var container = document.getElementById("eventosRealizados");
        removeAllChildNodes(container);

        var actualDate = getCurrentDate(actualDate);
        var actionUrl = "http://alunos.di.uevora.pt/tweb/t1/eventsearch";

        $.post(actionUrl, {data: ""}, function(data){

            for(let a in data.eventos){
                
                if(data.eventos[a].data < actualDate){

                    $("#eventosRealizados").show();

                    appearanceOfEvents(data.eventos[a].nome, data.eventos[a].data, data.eventos[a].descricao, "eventosRealizados");
                    
                }
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
        $("#Top3Div").hide();

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
        $("#Top3Div").hide();

        var container = document.getElementById("proximosEventos");
        removeAllChildNodes(container);

        var actualDate = getCurrentDate(actualDate);
        var actionUrl = "http://alunos.di.uevora.pt/tweb/t1/eventsearch";

        $.post(actionUrl, {data: ""}, function(data){

            for(let a in data.eventos){

                if(data.eventos[a].data > actualDate){

                    $("#proximosEventos").show();

                    appearanceOfEvents(data.eventos[a].nome, data.eventos[a].data, data.eventos[a].descricao, "proximosEventos");

                }
            }
        });
    });


    $("#procurarEventoForm").on("submit", function (event){

        $(".procurarEventoFormP").remove(); //remove os elementos que foram adicionados resultantes da pesquisa anterior

        var container = document.getElementById("eventosRecebidos");
        removeAllChildNodes(container);

        var eventoValue = document.forms.procurarEventoForm.evento.value;
        var dataValue = document.forms.procurarEventoForm.data.value;

        if (validationFunction(eventoValue) == false && validationFunction(dataValue) == false){
            alert("The box is not filled");
        }
        else {
            event.preventDefault();

            var formValues = $(this).serialize();
            var actionUrl = "http://alunos.di.uevora.pt/tweb/t1/eventsearch";

            $.post(actionUrl, formValues, function (data) {

                var exist = false;

                for(let a in data.eventos){

                    if(data.eventos[a].nome == eventoValue || data.eventos[a].data == dataValue){

                        exist = true;
            
                        $("#procurarEvento").hide();
                        $("#eventosRecebidos").show();

                        appearanceOfEvents(data.eventos[a].nome, data.eventos[a].data, data.eventos[a].descricao, "eventosRecebidos");
                    }
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

    $("#inscreverParticipanteForm").on("submit", function(event){

        var nomeParticipanteValue = document.forms.inscreverParticipanteForm.nome.value;

        if(validationFunction(nomeParticipanteValue) == false){
            alert("The \"Nome\" box is not filled");
        }
        else{
            event.preventDefault();

            var formValues = $(this).serialize();
            var actionUrl = "http://alunos.di.uevora.pt/tweb/t1/inscricao";

            $.post(actionUrl, formValues, function(data){
            
                if(data.resultado == "ok"){
                    document.getElementById("responseInscreverParticipante").innerHTML = "O participante foi inscrito com sucesso.";
                }
                else{
                    document.getElementById("responseInscreverParticipante").innerHTML = "Erro! Participante não foi inscrito.";
                }
            });
        }
    });

    $("#registarNovoEventoForm").on("submit", function(event){

        var nome = document.forms.registarNovoEventoForm.nome.value;
        var data = document.forms.registarNovoEventoForm.data.value;
        var descricao = document.forms.registarNovoEventoForm.descricao.value;

        if(validationFunction(nome) == false || validationFunction(data) == false || validationFunction(descricao) == false){
            alert("The box(s) is(are) not filled");
        } 
        else{
            event.preventDefault();

            var formValues = $(this).serialize();
            var actionUrl = "http://alunos.di.uevora.pt/tweb/t1/addevent";

            $.post(actionUrl, formValues, function(data){

                if(data.resultado == "ok"){
                    document.getElementById("responseRegistarEvento").innerHTML = "Evento registado com sucesso.";
                }
                else{
                    document.getElementById("responseRegistarEvento").innerHTML = "Erro! Não foi possível registar o Evento.";
                }
            });
        }
    });

    $("#registarTempodeProvaDeParticipanteForm").on("submit", function(event){

        var nome = document.forms.registarTempodeProvaDeParticipanteForm.evento.value;
        var dorsal = document.forms.registarTempodeProvaDeParticipanteForm.dorsal.value;
        var tempo = document.forms.registarTempodeProvaDeParticipanteForm.tempo.value;

        if(validationFunction(nome) == false || validationFunction(dorsal) == false  || validationFunction(tempo) == false){
            alert("The box(s) is(are) not filled");
        }
        else{
            event.preventDefault();

            var formValues = $(this).serialize();
            var actionUrl = "http://alunos.di.uevora.pt/tweb/t1/savetime";

            $.post(actionUrl, formValues, function(data){

                if(data.resultado == "ok"){
                    document.getElementById("responseRegistarTempoDeProva").innerHTML = "Tempo de Prova registado com sucesso.";
                }
                else{
                    document.getElementById("responseRegistarTempoDeProva").innerHTML = "Erro! Não foi possível registar o Evento.";
                }
            });
        }
    });

    $("#top3Form").on("submit", function(event){

        var nome = document.forms.top3Form.evento.value;

        var container = document.getElementById("mostraTabelaTop3");
        removeAllChildNodes(container);

        event.preventDefault();

        var formValues = $(this).serialize();
        var actionUrl = "http://alunos.di.uevora.pt/tweb/t1/top3";

        $.post(actionUrl, formValues, function(data){

            if(data.resultado = "ok"){
                mostrarTop3("mostraTabelaTop3", data);
                $("#mostraTabelaTop3").show();
            }
            else{
                alert("Erro! Algo correu mal.")
            }
        });
    });
      
 });