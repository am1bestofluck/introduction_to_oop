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
    private static void setGeneral(Hashtable<String,String> args){};

    public String[] getSpecifics();
    private void setSpecifics(Hashtable <String,String> args){};

    public String SayYourThing();
    private static void WhatDoesItSay(String call_){};

}

interface domestic extends animal{
    
}