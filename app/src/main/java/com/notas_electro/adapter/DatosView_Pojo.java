package com.notas_electro.adapter;

public class DatosView_Pojo {

    private String TitleNota;
    private String DescripcionNota;
    private String FechaNota;
    private String HoraNota;
    private String Id;

    public DatosView_Pojo() {

    }

    public DatosView_Pojo(String titleNota, String descripcionNota, String fechaNota, String horaNota, String id) {
        TitleNota = titleNota;
        DescripcionNota = descripcionNota;
        FechaNota = fechaNota;
        HoraNota = horaNota;
        Id = id;
    }

    public void setTitleNota(String titleNota) {
        TitleNota = titleNota;
    }

    public String getTitleNota() {
        return TitleNota;
    }

    public void setDescripcionNota(String descripcionNota) {
        DescripcionNota = descripcionNota;
    }

    public String getDescripcionNota() {
        return DescripcionNota;
    }

    public void setFechaNota(String fechaNota) {
        FechaNota = fechaNota;
    }

    public String getFechaNota() {
        return FechaNota;
    }

    public void setHoraNota(String horaNota) {
        HoraNota = horaNota;
    }

    public String getHoraNota() {
        return HoraNota;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getId() {
        return Id;
    }
}
