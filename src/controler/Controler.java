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
import model.User;
import model.Zanr;

/**
 *
 * @author Korisnik
 */
public class Controler {

    private DBBroker dbb;
    private List<Knjiga> listaKnjiga = new ArrayList<>();
    private List<Autor> listaAutora = new ArrayList<>();
    private List<User> listaUsera = new ArrayList<>();

    private static Controler instance;

    public static Controler getInstance() {
        if (instance == null) {
            instance = new Controler();
        }
        return instance;
    }

    private Controler() {
        dbb = new DBBroker();

        User u1 = new User(1, "User broj 1", "123456");
        User u2 = new User(2, "User broj 2", "654321");
        User u3 = new User(3, "User broj 3", "789456");

        listaUsera.add(u1);
        listaUsera.add(u2);
        listaUsera.add(u3);

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

    public List<User> getListaUsera() {
        return listaUsera;
    }

    public void setListaUsera(List<User> listaUsera) {
        this.listaUsera = listaUsera;
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
        dbb.dodajKnjiguIzBaze(novaKnjiga);
        // listaKnjiga.add(novaKnjiga);
    }

    public List<Knjiga> ucitajListuKnjigaIzBaze() {
        return dbb.ucitajListuKnjigaIzBaze();
    }

    public List<Autor> getListaAutoraIzBaze() {
        return dbb.getListaAutoraIzBaze();
    }

    public void azurirajKnjigu(Knjiga knjigaZaIzmenu) {
        dbb.azurirajKnjiguIzBaze(knjigaZaIzmenu);
    }

    public boolean loginMemorija(String usrname, String password) {
        for (User korisnik : listaUsera) {
            if (korisnik.getKorisnickoIme().equals(usrname) && korisnik.getSifra().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public boolean loginBaza(String usrname, String password) {
        return dbb.loginBaza(usrname, password);
    }

    public List<Knjiga> filtriraj(String naziv, String autor) {
        List<Knjiga> rezultat = new ArrayList<>();
        if (autor != null && naziv == null) {
            for (Knjiga k : listaKnjiga) {
                if ((k.getAutor().getIme() + " " + k.getAutor().getPrezime()).contains(autor)) {
                    rezultat.add(k);
                }
            }
        }
        if (naziv != null && autor == null) {
            for (Knjiga k : listaKnjiga) {
                if (k.getNaslov().contains(naziv)) {
                    rezultat.add(k);
                }
            }
        }
        if (naziv != null && autor != null) {
            for (Knjiga k : listaKnjiga) {
                if ((k.getAutor().getIme() + " " + k.getAutor().getPrezime()).contains(autor)
                        && k.getNaslov().contains(naziv)) {
                    rezultat.add(k);
                }
            }
        }
        return rezultat;
    }

    public List<Knjiga> filtrirajBaza(String naziv, String autor) {
        return dbb.filtriranaListaBaza(naziv, autor);
    }

}
