$(document).ready(function () {
    var data = {
        op: "listTable"
    };
    $.ajax({
        url: 'http://localhost:8080/Restaurante/TableServerlet',
        method: 'POST',
        data: data,
        dataType: 'json'
    }).done(function (result) {
        for (var i = 0; i < result.length; i++){
            $("#tabelaDeMesas").append('<tr id="mesa-'+result[i].idMesa+'"> <td id="id-'+result[i].idMesa+'">'+result[i].idMesa+'</td><td id="numero-'+result[i].idMesa+'">'+result[i].numeroMesa+'</td> <td id="lugares-'+result[i].idMesa+'">'+result[i].qntdLugar+'</td> <td id="status-'+result[i].idMesa+'">'+result[i].status+'</td> <td id="preco-'+result[i].idMesa+'">'+result[i].valorReserva+'</td> <td> <button class="btn btn-editar" onclick="abrirModal('+result[i].idMesa+')">Editar</button> <button class="btn btn-apagar" onclick="abrirExcluir('+result[i].idMesa+')">Apagar</button> </td> </tr>');
        }
    }).fail(function (jqXHR, textStatus, errorThrown) {
        console.error("Erro AJAX: ", textStatus, errorThrown);
        console.error("Detalhes: ", jqXHR.responseText);

        var serverErrorMessage = "Erro ao conectar com o servidor. Tente novamente mais tarde.";
        if (jqXHR.responseJSON && jqXHR.responseJSON.message) {
            serverErrorMessage = jqXHR.responseJSON.message;
        } else if (jqXHR.responseText) {
             serverErrorMessage = "Erro do servidor: " + jqXHR.status + " - " + (jqXHR.responseText.substring(0,100) || errorThrown) ;
        }
        alert(serverErrorMessage);
    });
    $('#form-editar').submit(function (e) {
        e.preventDefault();
        var data = {
            op: "updateTable",
            id: $("#idMesa").val(),
            numero: $('#numero').val(),
            lugares: $('#lugares').val(),
            status: $('#status').val(),
            preco: $('#preco').val()
        };
        $.ajax({
            url: 'http://localhost:8080/Restaurante/TableServerlet',
            method: 'POST',
            data: data,
            dataType: 'json'
        }).done(function (result) {
            console.log(result.message);
            
        }).fail(function (jqXHR, textStatus, errorThrown) {
            console.error("Erro AJAX: ", textStatus, errorThrown);
            console.error("Detalhes: ", jqXHR.responseText);

            var serverErrorMessage = "Erro ao conectar com o servidor. Tente novamente mais tarde.";
            if (jqXHR.responseJSON && jqXHR.responseJSON.message) {
                serverErrorMessage = jqXHR.responseJSON.message;
            } else if (jqXHR.responseText) {
                 serverErrorMessage = "Erro do servidor: " + jqXHR.status + " - " + (jqXHR.responseText.substring(0,100) || errorThrown) ;
            }
            alert(serverErrorMessage);
        });
    });
    $("#form-excluir").submit (function (e) {
        e.preventDefault();
        var data = {
            op: "deleteTable",
            id: $("#idExcluir").val()
        };
        $.ajax({
            url: 'http://localhost:8080/Restaurante/TableServerlet',
            method: 'POST',
            data: data,
            dataType: 'json'
        }).done(function (result) {
            console.log(result);
            
            fecharModal();
            window.location.reload(true);
        }).fail(function (jqXHR, textStatus, errorThrown) {
            console.error("Erro AJAX: ", textStatus, errorThrown);
            console.error("Detalhes: ", jqXHR.responseText);

            var serverErrorMessage = "Erro ao conectar com o servidor. Tente novamente mais tarde.";
            if (jqXHR.responseJSON && jqXHR.responseJSON.message) {
                serverErrorMessage = jqXHR.responseJSON.message;
            } else if (jqXHR.responseText) {
                 serverErrorMessage = "Erro do servidor: " + jqXHR.status + " - " + (jqXHR.responseText.substring(0,100) || errorThrown) ;
            }
            alert(serverErrorMessage);
        });
    });
    $("#form-adicionar").submit (function (e) {
        e.preventDefault();
        var data = {
            op: "addTable",
            numero: $('#numeroADD').val(),
            lugares: $('#lugaresADD').val(),
            status: $('#statusADD').val(),
            preco: $('#precoADD').val()
        };
        $.ajax({
            url: 'http://localhost:8080/Restaurante/TableServerlet',
            method: 'POST',
            data: data,
            dataType: 'json'
        }).done(function (result) {
            console.log(result);
            $('#numeroADD').val("");
            $('#lugaresADD').val("");
            $('#statusADD').val("");
            $('#precoADD').val("");
            fecharModal();
            window.location.reload(true);
        }).fail(function (jqXHR, textStatus, errorThrown) {
            console.error("Erro AJAX: ", textStatus, errorThrown);
            console.error("Detalhes: ", jqXHR.responseText);

            var serverErrorMessage = "Erro ao conectar com o servidor. Tente novamente mais tarde.";
            if (jqXHR.responseJSON && jqXHR.responseJSON.message) {
                serverErrorMessage = jqXHR.responseJSON.message;
            } else if (jqXHR.responseText) {
                 serverErrorMessage = "Erro do servidor: " + jqXHR.status + " - " + (jqXHR.responseText.substring(0,100) || errorThrown) ;
            }
            alert(serverErrorMessage);
        });
    });
});