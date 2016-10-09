
package com.JonesRandom.crudmysql.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelData {

    @SerializedName("id_barang")
    @Expose
    private String idBarang;
    @SerializedName("Nama")
    @Expose
    private String nama;
    @SerializedName("Jenis")
    @Expose
    private String jenis;
    @SerializedName("Keterangan")
    @Expose
    private String keterangan;

    public static final String id_barang = "ID_BARANG";
    public static final String nama_barang = "ID_BARANG";
    public static final String jenis_barang = "ID_BARANG";
    public static final String keterangan_barang = "ID_BARANG";

    public ModelData(String id,String nama, String jenis, String keterangan) {
        this.idBarang = id;
        this.nama = nama;
        this.jenis = jenis;
        this.keterangan = keterangan;
    }

    /**
     * 
     * @return
     *     The idBarang
     */
    public String getIdBarang() {
        return idBarang;
    }

    /**
     * 
     * @param idBarang
     *     The id_barang
     */
    public void setIdBarang(String idBarang) {
        this.idBarang = idBarang;
    }

    /**
     * 
     * @return
     *     The nama
     */
    public String getNama() {
        return nama;
    }

    /**
     * 
     * @param nama
     *     The Nama
     */
    public void setNama(String nama) {
        this.nama = nama;
    }

    /**
     * 
     * @return
     *     The jenis
     */
    public String getJenis() {
        return jenis;
    }

    /**
     * 
     * @param jenis
     *     The Jenis
     */
    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    /**
     * 
     * @return
     *     The keterangan
     */
    public String getKeterangan() {
        return keterangan;
    }

    /**
     * 
     * @param keterangan
     *     The Keterangan
     */
    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

}
