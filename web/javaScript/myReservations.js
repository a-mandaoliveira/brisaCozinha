$(document).ready(function () {
    var data = {
        op: "listMyReservations"
    };
    console.log('oi');
    $.ajax({
        url: 'http://localhost:8080/Restaurante/ReservationServerlet',
        method: 'POST',
        data: data,
        dataType: 'json'
    }).done(function (result) {
        console.log(result);
        for (var i = 0; i < result.length; i++){
            $("#tabelaDeReservas").append('<tr id="reserva-'+result[i].id+'"> <td id="data-'+result[i].id+'">'+result[i].dtReserva+'</td> <td id="hora-'+result[i].id+'">'+result[i].horaReserva+'</td> <td id="pessoas-'+result[i].id+'">'+result[i].numeroPessoas+'</td> <td> <button class="btn btn-cancelar" onclick="abrirExcluir('+result[i].id+')">Cancelar</button> </td> </tr>');
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
    $("#form-excluir").submit (function (e) {
        e.preventDefault();
        var data = {
            op: "deleteReservation",
            id: $("#idExcluir").val()
        };
        $.ajax({
            url: 'http://localhost:8080/Restaurante/ReservationServerlet',
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
});

