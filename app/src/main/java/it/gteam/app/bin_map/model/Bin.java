package it.gteam.app.bin_map.model;

//qua ci manca la roba per il database
public class Bin {

    /* ESEMPIO BIN
    {"id":"1",
    "tipo":"Carta",
    "provincia":"AQ",
    "comune":"L'Aquila",
    "indirizzo":"Viale delle Medaglie d'Oro",
    "regione":"Abruzzo",
    "latitudine":"42.35393398033748",
    "longitudine":"13.402421317559728"},
     */

    private int id;

    private String type;

    private String provincia;

    private String comune;

    private String indirizzo;

    private String regione;

    private double latitudine;

    private double longitudine;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComune() {
        return comune;
    }

    public void setComune(String comune) {
        this.comune = comune;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getRegione() {
        return regione;
    }

    public void setRegione(String regione) {
        this.regione = regione;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public double getLatitudine() {
        return latitudine;
    }

    public void setLatitudine(double latitudine) {
        this.latitudine = latitudine;
    }

    public double getLongitudine() {
        return longitudine;
    }

    public void setLongitudine(double longitudine) {
        this.longitudine = longitudine;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
