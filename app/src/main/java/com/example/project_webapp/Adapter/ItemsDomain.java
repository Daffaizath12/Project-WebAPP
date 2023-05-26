package com.example.project_webapp.Adapter;

import java.io.Serializable;

public class ItemsDomain implements Serializable {
    private String title;
    private String pondasi;
    private String dinding;
    private String rangkaatap;
    private String kusen;
    private String plafond;
    private String air;

    public String getPondasi() {
        return pondasi;
    }

    public void setPondasi(String pondasi) {
        this.pondasi = pondasi;
    }

    public String getDinding() {
        return dinding;
    }

    public void setDinding(String dinding) {
        this.dinding = dinding;
    }

    public String getRangkaatap() {
        return rangkaatap;
    }

    public void setRangkaatap(String rangkaatap) {
        this.rangkaatap = rangkaatap;
    }

    public String getKusen() {
        return kusen;
    }

    public void setKusen(String kusen) {
        this.kusen = kusen;
    }

    public String getPlafond() {
        return plafond;
    }

    public void setPlafond(String plafond) {
        this.plafond = plafond;
    }

    public String getAir() {
        return air;
    }

    public void setAir(String air) {
        this.air = air;
    }

    public String getListrik() {
        return listrik;
    }

    public void setListrik(String listrik) {
        this.listrik = listrik;
    }

    public String getJumlahkamar() {
        return jumlahkamar;
    }

    public void setJumlahkamar(String jumlahkamar) {
        this.jumlahkamar = jumlahkamar;
    }

    public String getLuastanah() {
        return luastanah;
    }

    public void setLuastanah(String luastanah) {
        this.luastanah = luastanah;
    }

    private String listrik;
    private String jumlahkamar;
    private String luastanah;
    private int price;
    private String pic;

    public ItemsDomain(String title, String pondasi, String dinding, String rangkaatap, String kusen, String plafond, String air, String listrik, String jumlahkamar, String luastanah, int price, String pic) {
        this.title = title;
        this.pondasi = pondasi;
        this.dinding = dinding;
        this.rangkaatap = rangkaatap;
        this.kusen = kusen;
        this.plafond = plafond;
        this.air = air;
        this.listrik = listrik;
        this.jumlahkamar = jumlahkamar;
        this.luastanah = luastanah;
        this.price = price;
        this.pic = pic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}