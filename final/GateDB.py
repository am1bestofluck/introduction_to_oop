from datetime import date, timedelta
from pathlib import Path
from random import choice
from os import unlink

import sqlite3

from car import Car
from carSpecs import CarModel, CarColor, Fuel
from driver import Driver
from person import Person

class GateDB():
    __SOURCE = "./db/db_taxi.sql"
    def __new__(cls):
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
    
    def __PURGE(cls, debug:bool=True) -> None:
        if Path(cls.__SOURCE).exists():
            if debug:
                unlink(cls.__SOURCE)
            else:
                breakIt= input("YOU ABSOLUTELY SURE? 'Yes I'M TOTALLY sure' or breaking.")
                if breakIt == "Yes I'M TOTALLY sure":
                    unlink(cls.__SOURCE)
                else:
                    return
        base = sqlite3.connect(cls.__SOURCE)
        crs = base.cursor()
        sepr=" "
        crs.execute(sepr.join(["CREATE TABLE new_wbs ("
                    , "ID integer primary key AUTOINCREMENT,"
                    ,  "NUMBER int NOT NULL"
                    ,  ");"]).strip(sepr))
        crs.execute(sepr.join(["CREATE TABLE persons ("
                    ,"ID integer primary key AUTOINCREMENT,"
                    , "NAME varchar(50) NOT NULL,"
                    , "SURNAME varchar(50) NOT NULL,"
                    , "FULLNAME varchar (100) NOT NULL,"
                    , "BDAY DATE NOT NULL," # DATE - format YYYY-MM-DD
                    , "SEX int" #0f 1m
                    , ");"]).strip(sepr))
        
        crs.execute(sepr.join(["CREATE TABLE cars ("
                    , "ID integer primary key AUTOINCREMENT,"
                    , "MODEL varchar(100),"
                    , "COLOR varchar(30),"
                    , "PROD_YEAR int NOT NULL,"
                    , "FUEL varchar(50) NOT NULL,"
                    , "TECHREVIEW DATE NOT NULL,"
                    , "ASSURANCE DATE NOT NULL,"
                    , "METROLOGY DATE NOT NULL,"
                    , "GOVPL varchar(20)"
                    , ");"]).strip(sepr))
        crs. execute(sepr.join(["CREATE TABLE drivers ("
                    , "ID integer primary key AUTOINCREMENT,"
                    , "PERSONID int"
                    , "DRIVEPERMIT_D DATE NOT NULL,"
                    , "FOREIGN KEY (PERSONID) REFERENCES persons(ID)"
                    , ");"]).strip(sepr))
        crs.execute(sepr.join(["CREATE TABLE journal ("
                    ,"ID integer primary key AUTOINCREMENT,"
                    , "RELDATE DATE NOT NULL,"
                    , "WBNUM int,"
                    , "AUTO int,"
                    , "DRIVER int,"
                    , "RETURNDATE DATE,"
                    , "TRAVERSE int,"
                    , "FOREIGN KEY (AUTO) REFERENCES cars(ID),"
                    , "FOREIGN KEY (DRIVER) REFERENCES drivers(ID)"
                    , ");"]).strip(sepr))
        base.commit()
        base.close()
    
    def reinit(cls):
        cls.__PURGE()
        cls.__DEFAULT_FILL()

    def __DEFAULT_FILL(self):
        base = sqlite3.connect(self.__SOURCE)
        crs = base.cursor()
        persons=[
            ["JOHN", "DOE", None, date(choice(range(1950,2004,1)),choice(range(1,13)),choice(range(1,28))),1],
            ["SMITH", "WESSON", None, date(choice(range(1950,2004,1)),choice(range(1,13)),choice(range(1,28))),1],
            ["LUCY ", "WRY", None, date(choice(range(1950,2004,1)),choice(range(1,13)),choice(range(1,28))),0],
            ["PIT", "SNAKE", None, date(choice(range(1950,2004,1)),choice(range(1,13)),choice(range(1,28))),1],
            ["JANE", "STONE", None, date(choice(range(1950,2004,1)),choice(range(1,13)),choice(range(1,28))),0]
            ]
        for fullname in persons:
            fullname[2] = " ".join([fullname[0],fullname[1]])
        print()
        crs.executemany("INSERT INTO persons(NAME, SURNAME, FULLNAME, BDAY, SEX) VALUES(?,?,?,?,?)",[i for i in persons])
        cars = [
            Car( CarModel.prius20, CarColor.b , date(choice(range(2008,2018,1)),1,1)
                , Fuel.bm,date.today()-timedelta(days=choice(range(180)))
                , date.today()-timedelta(days=choice(range(365)))
                , date.today()-timedelta(days=choice(range(365)))
                , "ACA 064"),
            Car( CarModel.prius30,CarColor.w, date(choice(range(2008,2018,1)),1,1)
                , Fuel.hb, date.today()-timedelta(days=choice(range(180)))
                , date.today()-timedelta(days=choice(range(365)))
                , date.today()-timedelta(days=choice(range(365)))
                , "ZXE 733"),
            Car( CarModel.zafira,CarColor.rgb, date(choice(range(2008,2018,1)),1,1)
                 ,Fuel.dt, date.today()-timedelta(days=choice(range(180)))
                 , date.today()-timedelta(days=choice(range(365)))
                 , date.today()-timedelta(days=choice(range(365)))
                 , "AWA 058"),
            Car( CarModel.focus,CarColor.k, date(choice(range(2008,2018,1)),1,1)
                , Fuel.bp, date.today()-timedelta(days=choice(range(180)))
                , date.today()-timedelta(days=choice(range(365)))
                , date.today()-timedelta(days=choice(range(365)))
                , "CSA 924"),
            Car( CarModel.logan,CarColor.j, date(choice(range(2008,2018,1)),1,1)
               , Fuel.bp, date.today()-timedelta(days=choice(range(180)))
               , date.today()-timedelta(days=choice(range(365))) 
               , date.today()-timedelta(days=choice(range(365)))
               , "QXV 768")
        ]
        crs.executemany("INSERT INTO cars"
                        +" (MODEL, COLOR, PROD_YEAR, FUEL, TECHREVIEW, ASSURANCE"
                        + ", METROLOGY, GOVPL) VALUES(?, ?, ?, ?, ?, ?, ?, ?)",
                        [car_.get_sqlite() for car_ in cars])
        base.commit()
        base.close()

def main():
    a = GateDB()
    a.reinit()
    
if __name__ =="__main__":
    main()