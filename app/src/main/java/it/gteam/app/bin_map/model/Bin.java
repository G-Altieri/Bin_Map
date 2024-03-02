package it.gteam.app.bin_map.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.json.JSONObject;

import java.io.Serializable;

@Entity(tableName = "bins")

//qua ci manca la roba per il database
public class Bin implements Serializable {

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

    //Metodo per creare un'istanza Bin dal file json
    public static Bin parseJson(JSONObject object) {
        if (object == null) return null;

        Bin bin = new Bin();

        bin.setType(object.optString("tipo"));
        bin.setProvincia(object.optString("provincia"));
        bin.setComune(object.optString("comune"));
        bin.setIndirizzo(object.optString("indirizzo"));
        bin.setRegione(object.optString("regione"));
        bin.setRifiuti(object.optString("rifiuti"));

        //Try & Catch per Latitudine
        try {
            String valueLat = object.optString("latitudine",null);
            if (valueLat != null) {
                bin.setLatitudine(Double.parseDouble(valueLat));
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        //Try & Catch per Longitudine
        try {
            String valueLng = object.optString("longitudine" ,null );
            if (valueLng != null) {
                bin.setLongitudine(Double.parseDouble(valueLng));
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        return bin;
    }


    @PrimaryKey
    private Integer id;

    private String type;

    private String provincia;

    private String comune;

    private String indirizzo;

    private String regione;

    private double latitudine;

    private double longitudine;

    private String rifiuti;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getRifiuti() {
        return rifiuti;
    }

    public void setRifiuti(String rifiuti) {
        this.rifiuti = rifiuti;
    }
}
