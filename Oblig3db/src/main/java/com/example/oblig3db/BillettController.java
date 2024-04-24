package com.example.oblig3db;

import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@RestController
public class BillettController {

    @Autowired
    private BillettRepository rep;

    private Logger logger = LoggerFactory.getLogger(BillettController.class);

    @GetMapping("/hentBilleter")
    public List<Billett> hentBilletter(HttpServletResponse response) throws IOException {
        List<Billett> alleBilletter = rep.hentAlleBilletter();
        if (alleBilletter==null){
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Feil i DB - prøv igjen senere");
        }
        return alleBilletter;
    }

    @GetMapping("/hentAlle")
    public List<Billett> hentAlleBilletter(HttpServletResponse response) throws IOException {
        List<Billett> alleBilletter = rep.hentAlleBilletter();
        if(alleBilletter==null){
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Feil i DB - Prøv igjen senere");
        }
        return alleBilletter;
    }

    @GetMapping("/henteEnBillett")
    public Billett henteEnBillett(int id, HttpServletResponse response) throws IOException{
        Billett enBillett = rep.henteEnBillet(id);
        if(enBillett == null){
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Feil i DB - Prøv igjen senere");
        }
        return enBillett;
    }

    @PostMapping("/endre")
    public void endre(Billett billett, HttpServletResponse response) throws IOException{

        if(validerBillettOK(billett)){
            if(!rep.endreBillett(billett)){
                response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Feil i DB - prøv igjen senere");
            }
        }
        else{
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Valideringsfeil - prøv igjen senere");
        }
    }

     @GetMapping("/slettEnBillett")
    public void slettEnBillett(String etternavn, HttpServletResponse response) throws IOException{
        if(!rep.slettEnBillett(etternavn)){
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Feil i DB - prøv igjen senere");

        }
     }

    @GetMapping("/slettAlle")
    public void slettAlleBilletter(HttpServletResponse response) throws IOException{
        if(!rep.slettAlleBilletter()){
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Feil i DB - prøv igjen senere");
        }
    }

     private boolean validerBillettOK(Billett billett){
        String regexFilm = "[Michael Clayton (2007), American Psycho (2000), Shutter Island (2010), Identity (2003), Goodfellas (1990)]";
        String regexAntall = "[0-9]{1,3}";
        String regexFornavn = "[0-9a-zA-ZæøåÆØÅ .\\-]{2,30}";
        String regexEtternavn = "[0-9a-zA-ZæøåÆØÅ .\\-]{2,30}";
        String regexTelefon = "/^\\d{8,}$/";
        String regexEpost = "/\\S+@\\S+\\.\\S+/";

        boolean filmOK = billett.getFilm().matches(regexFilm);
        boolean antallOK = billett.getAntall().matches(regexAntall);
        boolean fornavnOK = billett.getFornavn().matches(regexFornavn);
        boolean etternavnOK = billett.getEtternavn().matches(regexEtternavn);
        boolean telefonOK = billett.getTelefon().matches(regexTelefon);
        boolean epostOK = billett.getEpost().matches(regexEpost);
        if (filmOK && antallOK && fornavnOK && etternavnOK && telefonOK && epostOK) {
            return true;
        }
        logger.error("Valideringsfeil");
        return false;
     }



}