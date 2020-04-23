package com.wuyue.tractic_mocker;

import android.os.Parcel;
import android.os.Parcelable;

public class Hero implements Parcelable, Cloneable{

    private String name;
    private String race;
    private String pro;
    private String cost;

    public Hero(){}

    private Hero(Parcel in) {
        name = in.readString();
        race = in.readString();
        pro = in.readString();
        cost = in.readString();
    }

    public static final Creator<Hero> CREATOR = new Creator<Hero>() {
        @Override
        public Hero createFromParcel(Parcel in) {
            return new Hero(in);
        }

        @Override
        public Hero[] newArray(int size) {
            return new Hero[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getPro() {
        return pro;
    }

    public void setPro(String pro) {
        this.pro = pro;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(race);
        dest.writeString(pro);
        dest.writeString(cost);
    }

    @Override
    protected Hero clone() throws CloneNotSupportedException {
        Hero hero = new Hero();
        hero.name = this.name;
        hero.race = this.race;
        hero.pro = this.pro;
        hero.cost = this.cost;
        return hero;
    }
}
