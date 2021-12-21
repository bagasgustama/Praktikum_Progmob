package id.bagas.modul4recyclerview.model;

public class Data {

    private String id, nama_barang, nama_penerima, dropshiper, jumlah, daerah;

    public Data(){

    }

    public Data(String id, String nama_barang, String nama_penerima, String dropshiper, String jumlah, String daerah){
        this.id = id;
        this.nama_barang = nama_barang;
        this.nama_penerima = nama_penerima;
        this.dropshiper = dropshiper;
        this.jumlah = jumlah;
        this.daerah = daerah;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getnama_barang() {
        return nama_barang;
    }

    public void setnama_barang(String nama_barang) {
        this.nama_barang = nama_barang;
    }

    public String getnama_penerima() {
        return nama_penerima;
    }

    public void setnama_penerima(String nama_penerima) {
        this.nama_penerima = nama_penerima;
    }

    public String getdropshiper() {
        return dropshiper;
    }

    public void setdropshiper(String dropshiper) {
        this.dropshiper = dropshiper;
    }

    public String getjumlah() {
        return jumlah;
    }

    public void setjumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public String getdaerah() {
        return daerah;
    }

    public void setdaerah(String daerah) {
        this.daerah = daerah;
    }

}
