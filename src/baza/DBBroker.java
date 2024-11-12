/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baza;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Knjiga;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Statement;
import java.sql.ResultSet;
import model.Autor;
import model.Zanr;
import java.sql.PreparedStatement;

/**
 *
 * @author Korisnik
 */
public class DBBroker {

    public List<Knjiga> ucitajListuKnjigaIzBaze() {
        List<Knjiga> lista = new ArrayList<>();

        try {
            String upit = "SELECT * FROM KNJIGA k JOIN AUTOR a ON k.autorId = a.id";
            Statement st = Konekcija.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit);

            while (rs.next()) {
                int id = rs.getInt("k.id");
                String naslov = rs.getString("k.naslov");
                int godinaIzdanja = rs.getInt("k.godinaIzdanja");
                String ISBN = rs.getString("k.ISBN");
                String zanr = rs.getString("k.zanr");
                Zanr z = Zanr.valueOf(zanr);

                int autorId = rs.getInt("a.id");
                String ime = rs.getString("a.ime");
                String prezime = rs.getString("a.prezime");
                int godinaRodjenja = rs.getInt("a.godinaRodjenja");
                String biografija = rs.getString("a.biografija");

                Autor a = new Autor(autorId, ime, prezime, godinaRodjenja, biografija);

                Knjiga k = new Knjiga(id, naslov, a, ISBN, godinaIzdanja, z);
                lista.add(k);

            }

        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

    public List<Autor> getListaAutoraIzBaze() {
        List<Autor> autoriLista = new ArrayList<>();

        try {
            String upit = "SELECT * FROM autor";
            Statement st = Konekcija.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit);

            while (rs.next()) {

                int autorId = rs.getInt("id");
                String ime = rs.getString("ime");
                String prezime = rs.getString("prezime");
                int godinaRodjenja = rs.getInt("godinaRodjenja");
                String biografija = rs.getString("biografija");

                Autor a = new Autor(autorId, ime, prezime, godinaRodjenja, biografija);

                autoriLista.add(a);

            }

        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

        return autoriLista;
    }

    public void obrisiIzBaze(int id) {
        try {
            String upit = "DELETE FROM knjiga WHERE id=?";
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(upit);
            ps.setInt(1, id);
            ps.executeUpdate();

            Konekcija.getInstance().getConnection().commit();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void dodajKnjiguIzBaze(Knjiga novaKnjiga) {
        try {
            String upit = "INSERT INTO knjiga (id, naslov, autorId, godinaIzdanja, ISBN, zanr) "
                    + "VALUES(?,?,?,?,?,?)";
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(upit);
            ps.setInt(1, novaKnjiga.getId());
            ps.setString(2, novaKnjiga.getNaslov());
            ps.setInt(3, novaKnjiga.getAutor().getId());
            ps.setInt(4, novaKnjiga.getGodinaIzdanja());
            ps.setString(5, novaKnjiga.getISBN());
            ps.setString(6, String.valueOf(novaKnjiga.getZanr()));

            ps.executeUpdate();
            Konekcija.getInstance().getConnection().commit();

        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void azurirajKnjiguIzBaze(Knjiga knjigaZaIzmenu) {
        try {
            String upit = "UPDATE knjiga SET naslov=?, autorId=?, godinaIzdanja=?, ISBN=?, zanr=? WHERE id=?";
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(upit);

            ps.setString(1, knjigaZaIzmenu.getNaslov());
            ps.setInt(2, knjigaZaIzmenu.getAutor().getId());
            ps.setInt(3, knjigaZaIzmenu.getGodinaIzdanja());
            ps.setString(4, knjigaZaIzmenu.getISBN());
            ps.setString(5, String.valueOf(knjigaZaIzmenu.getZanr()));
            ps.setInt(6, knjigaZaIzmenu.getId());

            ps.executeUpdate();
            Konekcija.getInstance().getConnection().commit();

        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
