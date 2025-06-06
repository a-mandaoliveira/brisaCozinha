$(document).ready(function () {
    $('#login').submit(function (e) {
        e.preventDefault();
        var data = {
            email: $('#email').val(),
            password: $('#password').val()
        };

        $.ajax({
            url: 'http://localhost:8080/Restaurante/LoginServerlet',
            method: 'POST',
            data: data,
            dataType: 'json'
        }).done(function (result) {
            if(result && result.success === true){                
                $('#email').val('');
                $('#password').val('');
                
                window.location.href = "reserva.jsp";
            }else{
                if(result && result.message){
                    alert(result.message);
                }
                $('#email').val('');
                $('#password').val('');
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
});
    


