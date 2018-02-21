package com.club.jalvara2.polarclub2;

/**
 * Created by Etienne on 12/02/2018.
 */

public class User{
    public String pseudo;
    public long frequence;
    public int id;

    public User() {
    }

    public User(int id, String p, long f){
        this.id = id;
        this.pseudo=p;
        this.frequence=f;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public long getFrequence() {
        return frequence;
    }

    public void setFrequence(long frequence) {
        this.frequence = frequence;
    }

    @Override
    public String toString(){
        return this.pseudo+" a "+this.frequence;
    }
}
