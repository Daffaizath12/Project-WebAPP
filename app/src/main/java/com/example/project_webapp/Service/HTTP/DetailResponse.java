package com.example.project_webapp.Service.HTTP;

public class DetailResponse {
    private int id;
    private String pondasi;
    private String dinding;
    private String rangkaatap;
    private String kusen;
    private String plafond;
    private String air;
    private String listrik;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getFotocluster() {
        return fotocluster;
    }

    public void setFotocluster(String fotocluster) {
        this.fotocluster = fotocluster;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getNamacluster() {
        return namacluster;
    }

    public void setNamacluster(String namacluster) {
        this.namacluster = namacluster;
    }

    private String jumlahkamar;
    private String luastanah;
    private String fotocluster;
    private String harga;
    private String namacluster;
}
