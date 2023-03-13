import java.time.LocalDate;
import java.util.Hashtable;

interface animal{

    public Integer getHeight();
    private static void setHeight(Integer value){};

    public Integer getWeight();
    private static void setWeight(Integer value){};

    public String getEyeColor();
    private void setEyeColor(String value){};

    public static String[] getGeneral(){
        return new String[100];
    };
    private static void setGeneral(Hashtable<String,Object> WishItBeWiser){};

    public String[] getSpecifics();
    private void setSpecifics(Hashtable <String,String> args){};

    public String SayYourThing();
    private static void WhatDoesItSay(String call_){};

}

interface domestic extends animal{


    public String getName();
    private void reName(){};

    public String getBreed();
    private void setBreed(){};

    public boolean getVaccinated();
    private void setVaccinated(){};

    public String getColor();
    private void setColor(){};

    public LocalDate getBirthDate();
    private void setBirthDate(){};

    public void askAttention();

}

interface wild extends animal{

    public String getInhabitedZone();
    private void setInhabitedZone(){};

    public LocalDate getFirstContactDate();
    private void setFirstContactDate(){};

}