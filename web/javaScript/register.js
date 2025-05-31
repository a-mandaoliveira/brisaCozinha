$(document).ready(function () {
    $('#register').submit(function (e) {
        e.preventDefault();
        var data = {
            fullName: $('#fullName').val(),
            phone: $('#phone').val(),
            cpf: $('#cpf').val(),
            email: $('#email').val(),
            password: $('#password').val()
        };

        $.ajax({
            url: 'http://localhost:8080/Restaurante/RegisterServerlet',
            method: 'POST',
            data: data,
            dataType: 'json'
        }).done(function (result) {
            if(result && result.success === true){
                alert(result.message);
                
                $('#fullName').val(''),
                $('#phone').val(''),
                $('#cpf').val(''),
                $('#email').val('');
                $('#password').val('');
                
                window.location.href = "/login.html";
            }else if(result && result.success === false){
                alert(result.message);
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