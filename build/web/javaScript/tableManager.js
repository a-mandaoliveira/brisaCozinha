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
        console.log(result);
        for (var i = 0; i < result.length; i++){
            $("#tabelaDeMesas").append('<tr id="mesa-'+result[i].idMesa+'"> <td id="id-'+result[i].idMesa+'">'+result[i].idMesa+'</td><td id="numero-'+result[i].idMesa+'">'+result[i].numeroMesa+'</td> <td id="lugares-'+result[i].idMesa+'">'+result[i].qntdLugar+'</td> <td id="status-'+result[i].idMesa+'">'+result[i].status+'</td> <td id="preco-'+result[i].idMesa+'">R$'+result[i].valorReserva+'</td> <td> <button class="btn btn-editar" onclick="abrirModal('+result[i].idMesa+')">Editar</button> <button class="btn btn-apagar">Apagar</button> </td> </tr>');
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
});