package com.club.jalvara2.common;

/**
 * Created by jalvara2 on 22/02/18.
 */

public class Session {
    public boolean active;
    public int id;
    public String name;
    public String timeS;
    public String cve;

    public Session() {
    }

    public Session(boolean active, int id, String name, String timeS) {
        this.active = active;
        this.id = id;
        this.name = name;
        this.timeS = timeS;
    }

    public Session(boolean active, int id, String name, String timeS, String cve) {
        this.active = active;
        this.id = id;
        this.name = name;
        this.timeS = timeS;
        this.cve = cve;
    }

    public String getCve() {
        return cve;
    }

    public void setCve(String cve) {
        this.cve = cve;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
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

    public String getTimeS() {
        return timeS;
    }

    public void setTimeS(String timeS) {
        this.timeS = timeS;
    }
}
