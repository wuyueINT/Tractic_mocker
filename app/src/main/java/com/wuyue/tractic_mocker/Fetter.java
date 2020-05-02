package com.wuyue.tractic_mocker;

import java.util.List;

public class Fetter implements Cloneable {

    String name;
    String herosInFetter;
    String effetOfFetter;

    public Fetter() {
    }

    public Fetter(String name, String herosInFetter, String effetOfFetter) {
        this.name = name;
        this.herosInFetter = herosInFetter;
        this.effetOfFetter = effetOfFetter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHerosInFetter() {
        return herosInFetter;
    }

    public void setHerosInFetter(String herosInFetter) {
        this.herosInFetter = herosInFetter;
    }

    public String getEffetOfFetter() {
        return effetOfFetter;
    }

    public void setEffetOfFetter(String effetOfFetter) {
        this.effetOfFetter = effetOfFetter;
    }

    @Override
    protected Fetter clone() throws CloneNotSupportedException {
        Fetter fetter = new Fetter();
        fetter.setName(this.name);
        fetter.setHerosInFetter(this.herosInFetter);
        fetter.setEffetOfFetter(this.effetOfFetter);
        return fetter;
    }
}
