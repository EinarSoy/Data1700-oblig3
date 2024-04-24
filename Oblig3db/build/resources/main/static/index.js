$(function(){  //Ready-funksjon
    hentAlle();
});

function hentAlle() {
    $.get( "/hentAlle", function( billetter ) {
        formaterData(billetter);
    })
        .fail(function(jqXHR) {
            const json = $.parseJSON(jqXHR.responseText);
            $("#feil").html(json.message);
        });
}

function formaterData(billetter) {
    let ut = "<table class='table table-striped'><tr><th>Film</th><th>Antall</th><th>Fornavn</th>" +
        "<th>Etternavn/th><th>Telefon</th><th>Epost</th><th></th><th></th></tr>";
    for (const billett of billetter) {
        ut += "<tr><td>" + billett.film + "</td><td>" + billett.antall + "</td><td>" + billett.fornavn + "</td>" +
            "<td>" + billett.etternavn + "</td><td>" + billett.telefon + "</td><td>" + billett.epost + "</td>" +
            "<td> <button class='btn btn-primary' onclick='idTilEndring("+billett.id+")'>Endre</button></td>"+
            "<td> <button class='btn btn-danger' onclick='slettEnMotorvogn("+billett.etternavn+")'>Slett</button></td>"+
            "</tr>";
    }
    ut += "</table>";
    $("#billettene").html(ut);
}

function idTilEndring(id) {
    window.location.href = "/endre.html?"+id;
}

function slettEnBillett(etternavn) {
    const url = "/slettEnBillett?telefon="+telefon;
    $.get( url, function() {
        window.location.href = "/";
    });
}

function slettAlle() {
    $.get( "/slettAlle", function() {
        hentAlle();
    });
}