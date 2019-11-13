package com.example.anle.gamepttuduy.Models;

public class CauHoi {

    private int IdCauHoi;
    private int IdManChoi;
    private int imgResource;
    private int score;
    private String tuta;

    public String getTuta() {
        return tuta;
    }

    public void setTuta(String tuta) {
        this.tuta = tuta;
    }

    public CauHoi(int idCauHoi, int idManChoi, int imgResource, int score,String tuta) {
        IdCauHoi = idCauHoi;
        IdManChoi = idManChoi;
        this.imgResource = imgResource;
        this.score = score;
        this.tuta=tuta;
    }

    public int getIdCauHoi() {
        return IdCauHoi;
    }

    public void setIdCauHoi(int idCauHoi) {
        IdCauHoi = idCauHoi;
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
