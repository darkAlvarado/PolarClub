package com.club.jalvara2.common;

/**
 * Created by Etienne on 12/02/2018.
 */

public class User{
    public String pseudo;
    public String frequence;
    public int id;
    public int media;

    public User() {
    }

    public User(int id, String p, int media, String f){
        this.id = id;
        this.pseudo=p;
        this.media = media;
        this.frequence=f;
    }

    public User(String pseudo){
        this.pseudo = pseudo;
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

    public String getFrequence() {
        return frequence;
    }

    public void setFrequence(String frequence) {
        this.frequence = frequence;
    }

    public int getMedia() {
        return media;
    }

    public void setMedia(int media) {
        this.media = media;
    }

    @Override
    public String toString(){
        return this.pseudo+" a "+this.frequence;
    }
}
