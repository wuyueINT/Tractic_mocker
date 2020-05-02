package com.wuyue.tractic_mocker;

import android.util.Xml;
import org.xmlpull.v1.XmlPullParser;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class XmlParser {

    public static ArrayList<Fetter> instance = null;

    /**
     * 解析英雄信息的xml文件
     *
     * @param is
     * @return
     */
    public static ArrayList<Hero> parseXml(InputStream is){
        ArrayList<Hero> heros = new ArrayList<>();
        Hero hero = new Hero();
        try {
            XmlPullParser xpp = Xml.newPullParser();
            xpp.setInput(is, "utf-8");
            int type = xpp.getEventType();
            while (type!=XmlPullParser.END_DOCUMENT){
                switch (type){
                    case XmlPullParser.START_TAG:
                        if ("name".equals(xpp.getName())){
                            xpp.next();
                            hero.setName(xpp.getText());
                        }
                        if ("race".equals(xpp.getName())){
                            xpp.next();
                            hero.setRace(xpp.getText());
                        }
                        if ("profession".equals(xpp.getName())){
                            xpp.next();
                            hero.setPro(xpp.getText());
                        }
                        if ("cost".equals(xpp.getName())){
                            xpp.next();
                            hero.setCost(xpp.getText());
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if ("Hero".equals(xpp.getName())){
                            heros.add(hero.clone());
                        }
                        break;
                }
                type = xpp.next();
            }

        } catch (Exception e){
            e.printStackTrace();
        }

        return heros;
    }


    /**
     * 解析羁绊信息的xml文件
     *
     * @return
     */
    public static ArrayList<Fetter> parseXmlFetters(InputStream is){

        if (instance==null){
            ArrayList<Fetter> fetters = new ArrayList<>();
            Fetter fetter = new Fetter();
            try {
                XmlPullParser xpp = Xml.newPullParser();
                xpp.setInput(is, "utf-8");
                int type = xpp.getEventType();
                while (type!=XmlPullParser.END_DOCUMENT){
                    switch (type){
                        case XmlPullParser.START_TAG:
                            if ("name".equals(xpp.getName())){
                                xpp.next();
                                fetter.setName(xpp.getText());
                            }
                            if ("herosInFetter".equals(xpp.getName())){
                                xpp.next();
                                fetter.setHerosInFetter(xpp.getText());
                            }
                            if ("effectOfFetter".equals(xpp.getName())){
                                xpp.next();
                                fetter.setEffetOfFetter(xpp.getText());
                            }
                            break;
                        case XmlPullParser.END_TAG:
                            if ("fetter".equals(xpp.getName())){
                                fetters.add(fetter.clone());
                            }
                            break;
                    }
                    type = xpp.next();
                }

            } catch (Exception e){
                e.printStackTrace();
            }
            instance = fetters;
        }
        return instance;
    }
}
