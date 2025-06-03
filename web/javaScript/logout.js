$(document).ready(function () {
    $('#logout-button').click(function (e) {
        e.preventDefault();

        $.ajax({
            url: 'http://localhost:8080/Restaurante/LogoutServerlet',
            method: 'POST',
            dataType: 'json'
        }).done(function (result) {
            if(result && result.success === true){                
                window.alert(result.message)
                window.location.href = "index.html";
            }else{
                if(result && result.message){
                    alert(result.message);
                }
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