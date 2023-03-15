import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Hashtable;

interface animal_i{

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

interface domestic_i extends animal_i{


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

interface wild_i extends animal_i{

    public String getInhabitedZone();
    private void setInhabitedZone(){};

    public LocalDate getFirstContactDate();
    private void setFirstContactDate(){};

}

interface bird_i {

    public Integer getFlightLevel();
    private void setFlightLevel(){};

    public String iFly();
}
