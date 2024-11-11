/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controler;

import baza.DBBroker;
import java.util.ArrayList;
import java.util.List;
import model.Autor;
import model.Knjiga;
import model.Zanr;

/**
 *
 * @author Korisnik
 */
public class Controler {

    private DBBroker dbb;
    private List<Knjiga> listaKnjiga = new ArrayList<>();
    private List<Autor> listaAutora = new ArrayList<>();

    private static Controler instance;

    public static Controler getInstance() {
        if (instance == null) {
            instance = new Controler();
        }
        return instance;
    }

    private Controler() {
        dbb = new DBBroker();
        /*Autor autor1 = new Autor("Ivo", "Andric", 1892, "Bigrafija Iva Andrica");
        Autor autor2 = new Autor("Danilo", "Kis", 1935, "Bigrafija Danila Kisa");
        Autor autor3 = new Autor("Mesa", "Selimovic", 1910, "Bigrafija Mese Selimovica");

        Knjiga knjiga1 = new Knjiga("Na Drini cuprija", autor1, "1234567890", 1945, Zanr.ISTORIJSKI);
        Knjiga knjiga2 = new Knjiga("Basta, pepeo", autor2, "0123456789", 1982, Zanr.POEZIJA);
        Knjiga knjiga3 = new Knjiga("Tvrdjava", autor3, "9012345678", 1970, Zanr.DETEKTIVSKI);

        listaKnjiga.add(knjiga1);
        listaKnjiga.add(knjiga2);
        listaKnjiga.add(knjiga3);

        listaAutora.add(autor1);
        listaAutora.add(autor2);
        listaAutora.add(autor3);*/

    }

    public List<Knjiga> getListaKnjiga() {
        return listaKnjiga;
    }

    public void setListaKnjiga(List<Knjiga> listaKnjiga) {
        this.listaKnjiga = listaKnjiga;
    }

    public List<Autor> getListaAutora() {
        return listaAutora;
    }

    public void setListaAutora(List<Autor> listaAutora) {
        this.listaAutora = listaAutora;
    }

    public void obrisiKnjigu(int id) {

        dbb.obrisiIzBaze(id);

        // listaKnjiga.remove(id);
    }

    public void dodajKnjigu(Knjiga novaKnjiga) {
        listaKnjiga.add(novaKnjiga);
    }

    public List<Knjiga> ucitajListuKnjigaIzBaze() {
        return dbb.ucitajListuKnjigaIzBaze();
    }

    public List<Autor> getListaAutoraIzBaze() {
        return dbb.getListaAutoraIzBaze();
    }

}
