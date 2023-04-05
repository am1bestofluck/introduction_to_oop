from datetime import date
from pathlib import Path
from os import unlink

import sqlite3

from car import Car
from carSpecs import CarModel
from driver import Driver
from person import Person

class GateDB():
    __SOURCE = "./db/db_taxi.sql"
    def __new__(cls):

        if not Path(cls.__SOURCE).exists():
            cls.__PURGE()

        if not hasattr(cls, 'instance'):
            cls.instance = super(GateDB, cls).__new__(cls)
        return cls.instance
    
    def createNewWB(self, string_to_parse: str) -> list[Driver]:
        
        raise NotImplementedError # TODO
    
    def returnWB(string_to_parse: str) -> None:
        raise NotImplementedError # TODO
    
    def addDriver(ppl: Person, 
                  doc1:date) -> None:
        return # TODO
    
    def addPerson(name: str, surname: str, bday: date, code: str):
        return # TODO
    def addAuto( govPlateNumb: str, model: CarModel, factoryYear:date):
        return # TODO
    
    def getAuto(govpl: str) -> Car:
        return # TODO
    
    def __PURGE(self) -> None:
        if Path(self.__SOURCE).exists():
            breakIt= input("YOU ABSOLUTELY SURE? 'Yes I TOTALLY know' or breaking.")
            if breakIt == 'Yes I TOTALLY know':
                unlink(self.__SOURCE)
            else:
                return
        base = sqlite3.connect(self.__SOURCE)
        crs = base.cursor()
        sepr=" "
        crs.execute(sepr.join(["CREATE TABLE new_wbs ("
                    , "ID int NOT NULL AUTO_INCREMENT,"
                    ,  "NUMBER int NOT NULL,"
                    ,  "PRIMARY KEY (ID)"
                    ,  ");"]).strip(sepr))
        crs.execute(sepr.join(["CREATE TABLE persons ("
                    ,"ID int NOT NULL AUTO_INCREMENT,"
                    , "NAME varchar(50) NOT NULL,"
                    , "SURNAME varchar(50) NOT NULL,"
                    , "BDAY DATE NOT NULL," # DATE - format YYYY-MM-DD
                    , "SEX int,"
                    , "PRIMARY KEY (ID)" #0f 1m
                    , ");"]).strip(sepr))
        
        crs.execute(sepr.join(["CREATE TABLE cars ("
                    , "ID int NOT NULL AUTO_INCREMENT,"
                    , "MODEL varchar(100),"
                    , "COLOR varchar(30),"
                    , "PROD_YEAR int NOT NULL,"
                    , "FUEL varchar(50) NOT NULL,"
                    , "TECHREVIEW DATE NOT NULL,"
                    , "ASSURANCE DATE NOT NULL,"
                    , "METROLOGY DATE NOT NULL,"
                    , "GOVPL varchar(20),"
                    , "PRIMARY KEY (GOVPL)"
                    , ");"]).strip(sepr))
        crs. execute(sepr.join(["CREATE TABLE drivers ("
                    , " ID int NOT NULL AUTO_INCREMENT,"
                    , "PERSONID int,"
                    , "DRIVEPERMIT_D DATE NOT NULL,"
                    , "PRIMARY KEY (ID),"
                    , "FOREIGN KEY (PERSONID) REFERENCES persons(ID)"
                    , ");"]).strip(sepr))
        crs.execute(sepr.join(["CREATE TABLE colors ("
                               ,"ID INT NOT NULL A"
            , ");"]).strip(sepr))
        base.commit()
        base.close()