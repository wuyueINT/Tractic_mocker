package com.wuyue.tractic_mocker;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Hero implements Parcelable {

    private String name;
    private String race;
    private String pro;
    private int cost;

    public Hero(String name, Race race, Profession pro, int cost) {
        this.name = name;
        this.race = race.toString();
        this.pro = pro.toString();
        this.cost = cost;
    }

    private Hero(Parcel in) {
        name = in.readString();
        race = in.readString();
        pro = in.readString();
        cost = in.readInt();
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

    public void setRace(Race race) {
        this.race = race.toString();
    }

    public String getPro() {
        return pro;
    }

    public void setPro(Profession pro) {
        this.pro = pro.toString();
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
        dest.writeInt(cost);
    }
}
