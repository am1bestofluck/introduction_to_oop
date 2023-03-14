import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Hashtable;

interface animal{

    public Integer getHeight();

    public Integer getWeight();

    public String getEyeColor();

    public ArrayList<String> getGeneral();
    private static void setGeneral(Hashtable<String,Object> WishItBeWiser){};

    public ArrayList<String> getSpecifics();
    private void setSpecifics(Hashtable <String,String> args){};

    public String SayYourThing();
    private void WhatDoesItSay(String call_){};

    public void getInfo();

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

interface basic_bird {

    public Integer getFlightLevel();
    private void setFlightLevel(){};

    public String iFly();
}

interface domestic_bird extends domestic, basic_bird{}

interface wild_bird extends wild,basic_bird{}