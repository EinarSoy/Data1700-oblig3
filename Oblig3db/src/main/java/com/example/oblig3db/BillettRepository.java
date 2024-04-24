package com.example.oblig3db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BillettRepository {

    @Autowired
    private JdbcTemplate db;

    private Logger logger = LoggerFactory.getLogger(BillettRepository.class);

    public boolean lagreBillett(Billett b){
        String sql = "INSERT INTO Billett (film, antall, fornavn, etternavn, telefon, epost) VALUES (?,?,?,?,?,?)";
        try{
            db.update(sql, b.getFilm(), b.getAntall(), b.getFornavn(), b.getEtternavn(), b.getTelefon(), b.getEpost());
            return true;
        }
        catch(Exception e){
            logger.error("Feil i lagre billett "+e);
            return false;
        }
    }

    public List<Billett> hentAlleBilletter(){
        String sql = "SELECT * FROM Billett";
        try{
            return db.query(sql, new BeanPropertyRowMapper(Billett.class));
        }
        catch (Exception e){
            logger.error("Feil i hent alle billetter "+e);
            return null;
        }
    }

    public Billett henteEnBillet(int id){
        String sql = "SELECT * FROM Billett WHERE id=?";
        try{
            List<Billett> enBillett = db.query(sql, new BeanPropertyRowMapper(Billett.class),id);
            return enBillett.get(0);
        }
        catch (Exception e){
            logger.error("Feil i hent en billett" +e);
            return null;
        }
    }

    public boolean endreBillett(Billett b) {
        String sql = "UPDATE Billett SET film=?, antall=?, fornavn=?, etternavn=?, telefon=?, epost=? WHERE id=?";
        try {
            db.update(sql, b.getFilm(), b.getAntall(), b.getFornavn(), b.getEtternavn(), b.getTelefon(), b.getEpost());
            return true;
        } catch (Exception e) {
            logger.error("Feil i endre en billett " + e);
            return false;
        }
    }

    public boolean slettEnBillett(String bestillingsnr) {
        String sql = "DELETE FROM Billett WHERE bestillingsnr=?";
        try {
            db.update(sql, bestillingsnr);
            return true;
        } catch (Exception e) {
            logger.error("Feil ved sletting av billett " + e);
            return false;
        }
    }

    public boolean slettAlleBilletter (){
        String sql = "DELETE FROM Billett";
        try {
            db.update(sql);
            return true;
        }
        catch (Exception e){
            logger.error("Feil ved sletting av alle billetter "+e);
            return false;
        }
    }
}

