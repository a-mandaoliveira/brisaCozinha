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
            $("#mesas").append('<option value="'+result[i].idMesa+'">Mesa '+result[i].idMesa+'</option>');
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
    $("#reserva-form").submit(function (e) {
        e.preventDefault();
        var data = {
            op: "efetuarReserva",
            data: $("#data").val(),
            hora: $("#hora").val(),
            pessoas: $("#pessoas").val(),
            mesas: $("#mesas").val()
        };
        $.ajax({
            url: 'http://localhost:8080/Restaurante/ReservationServerlet',
            method: 'POST',
            data: data,
            dataType: 'json'
        }).done(function (result) {
            alert(result.message);
            
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