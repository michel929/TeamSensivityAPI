package team.sensivity.teamsensivityapi.objects;

import java.time.LocalDate;
import java.util.ArrayList;

public class Konto {
    private int id;
    private String name;
    private int amount;
    private String iban;
    private LocalDate abgerechnet;
    private ArrayList<String> user;

    public Konto(int id, String name, int amount, String iban, LocalDate abgerechnet, ArrayList<String> user) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.iban = iban;
        this.abgerechnet = abgerechnet;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public LocalDate getAbgerechnet() {
        return abgerechnet;
    }

    public void setAbgerechnet(LocalDate abgerechnet) {
        this.abgerechnet = abgerechnet;
    }

    public ArrayList<String> getUser() {
        return user;
    }

    public void setUser(ArrayList<String> user) {
        this.user = user;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }
}
