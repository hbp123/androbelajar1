package com.raivstudio.katalogfilm;

import org.json.JSONObject;

class ItemFilm {

    private String jdlfilm, tglfilm, ratefilm, sinopsis, gbrfilm, gbrbackdrop;

    public ItemFilm(JSONObject object) {
        try{
            jdlfilm     = object.getString("title");
            tglfilm     = object.getString("release_date");
            ratefilm    = object.getString("vote_average");
            sinopsis    = object.getString("overview");
            gbrfilm     = object.getString("poster_path");
            gbrbackdrop = object.getString("backdrop_path");
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
    public String getJdlfilm() {
        return jdlfilm;
    }

    public String getTglfilm() {
        return tglfilm;
    }

    public String getRatefilm() {
        return ratefilm;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public String getGbrfilm() {
        return gbrfilm;
    }

    public String getGbrbackdrop() {
        return gbrbackdrop;
    }
}
