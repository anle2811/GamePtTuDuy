package com.example.anle.gamepttuduy.Models;

public class ManChoi {

    private int IdManChoi;
    private int IdTopic;
    private int imgResource;
    private int diemcuaman;
    private String tiendo;
    private int capdo;

    public ManChoi(int IdManChoi,int IdTopic,int imgResource, int diemcuaman, String tiendo, int capdo) {
        this.IdManChoi=IdManChoi;
        this.IdTopic=IdTopic;
        this.imgResource = imgResource;
        this.diemcuaman = diemcuaman;
        this.tiendo = tiendo;
        this.capdo = capdo;
    }

    public int getIdTopic() {
        return IdTopic;
    }

    public void setIdTopic(int idTopic) {
        IdTopic = idTopic;
    }

    public int getIdManChoi() {
        return IdManChoi;
    }

    public void setIdManChoi(int idManChoi) {
        IdManChoi = idManChoi;
    }

    public int getImgResource() {
        return imgResource;
    }

    public void setImgResource(int imgResource) {
        this.imgResource = imgResource;
    }

    public int getDiemcuaman() {
        return diemcuaman;
    }

    public void setDiemcuaman(int diemcuaman) {
        this.diemcuaman = diemcuaman;
    }

    public String getTiendo() {
        return tiendo;
    }

    public void setTiendo(String tiendo) {
        this.tiendo = tiendo;
    }

    public int getCapdo() {
        return capdo;
    }

    public void setCapdo(int capdo) {
        this.capdo = capdo;
    }
}
