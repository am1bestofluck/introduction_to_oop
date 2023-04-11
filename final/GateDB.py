from datetime import date, timedelta, datetime
from pathlib import Path
from random import choice, choices
from string import digits
from os import unlink

import sqlite3

from car import Car
from carSpecs import CarModel, CarColor, Fuel
from container_wb import Container_wb
from driver import Driver
from person import Person
from pathL import PAthDB

class GateDB():

    __SOURCE = PAthDB.getPathDB()
    __patternDate="%Y-%m-%d"
    def __new__(cls):
        if not hasattr(cls, 'instance'):
            cls.instance = super(GateDB, cls).__new__(cls)
        return cls.instance
    
    def createNewWB(self, string_to_parse: str) -> Container_wb:
        
        raise NotImplementedError # TODO
    
    def returnWB(self, string_to_parse: str) -> None:
        raise NotImplementedError # TODO
    
    def addDriver(self, driver:Driver) -> None:
        raise NotImplementedError("TODO")    

    def getDriver(self, name:str, surname:str) -> Driver:
        fullname = " ".join([name.upper(),surname.upper()])
        base = sqlite3.connect(self.__SOURCE)   
        crs = base.cursor()
        crs.execute(f"SELECT * FROM persons WHERE FULLNAME='{fullname}'")
        pbase = crs.fetchone()
        id=pbase[0]
        driverBase = Person( name=pbase[1], surname=pbase[2],
                    bday=datetime.strptime(pbase[4],self.__patternDate).date(),
                      sex=pbase[5],perscode=pbase[6])
        crs.execute(f"SELECT * FROM drivers WHERE PERSONID='{id}'")
        tmp = crs.fetchone()
        permitDate=datetime.strptime(tmp[1],self.__patternDate).date()
        base.close()
        return Driver(driverBase,permitDate)
    
    def addPerson(self,**persons:Person) -> None:
        base = sqlite3.connect(self.__SOURCE)
        crs = base.cursor()
        crs.executemany("INSERT INTO persons(NAME, SURNAME, FULLNAME, BDAY, SEX, PERSCODE) VALUES(?,?,?,?,?,?)",[i.get_sqlite() for i in persons])
        base.commit()
        base.close()

    def getPerson(self, name:str, surname: str) -> Person:
        fullname = " ".join([name.upper(),surname.upper()])
        
        base = sqlite3.connect(self.__SOURCE)   
        crs = base.cursor()
        
        crs.execute(f"SELECT * FROM persons WHERE FULLNAME='{fullname}'")
        pbase= crs.fetchall()
        base.close()
        if len(pbase) >1:
            raise NotImplementedError("filter? idno?")
        elif len(pbase) == 0:
            return None
        else :
            return Person( name=pbase[0][1], surname=pbase[0][2],
                    bday=datetime.strptime(pbase[0][4],self.__patternDate).date(),
                      sex=pbase[0][5],perscode=pbase[0][6])

    def addAuto(self, **cars:Car):
        # raise NotImplementedError("TODO")
        base = sqlite3.connect(self.__SOURCE)   
        crs = base.cursor()
        crs.executemany("INSERT INTO cars"
                        +" (MODEL, COLOR, PROD_YEAR, FUEL, TECHREVIEW, ASSURANCE"
                        + ", METROLOGY, GOVPL) VALUES(?, ?, ?, ?, ?, ?, ?, ?)",
                        [car_.get_sqlite() for car_ in cars])
        base.commit()
        base.close()

    def getAuto(self, govpl: str) -> Car:
        base = sqlite3.connect(self.__SOURCE)
        crs = base.cursor()
        crs.execute(f"SELECT * FROM cars WHERE GOVPL=\"{govpl}\"")
        get_= crs.fetchone()
        model = CarModel.parse(get_[1])
        color = CarColor.parse(get_[2])
        prodYear = datetime(get_[3],1,1)
        fuel = Fuel.parse(get_[4])
        date_rw = datetime.strptime(get_[5],self.__patternDate).date()
        date_as = datetime.strptime(get_[6],self.__patternDate).date()
        date_mtr = datetime.strptime(get_[7],self.__patternDate).date()
        govpl= get_[8]
        base.close()
        return Car(model,color,prodYear,fuel,date_rw,date_as,date_mtr,govpl)

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
                    , "SEX int," #0f 1m
                    , "PERSCODE varchar(13) UNIQUE"
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
                    , "DRIVEPERMIT_D DATE NOT NULL,"
                    , "PERSONID int,"
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

    def reinit(cls) -> None:
        cls.__PURGE()
        cls.__DEFAULT_FILL()

    def __DEFAULT_FILL(self) -> None:
        base = sqlite3.connect(self.__SOURCE)
        crs = base.cursor()
        persons=[
            Person("JOHN", "DOE", date(choice(range(1950,2004,1)),choice(range(1,13)),choice(range(1,28))),True,'1231231231231'),
            Person("SMITH", "WESSON", date(choice(range(1950,2004,1)),choice(range(1,13)),choice(range(1,28))),True, ''.join(choices(digits,k=13))),
            Person("LUCY ", "WRY", date(choice(range(1950,2004,1)),choice(range(1,13)),choice(range(1,28))),False, ''.join(choices(digits,k=13))),
            Person("PIT", "SNAKE", date(choice(range(1950,2004,1)),choice(range(1,13)),choice(range(1,28))),True, ''.join(choices(digits,k=13))),
            Person("JANE", "STONE", date(choice(range(1950,2004,1)),choice(range(1,13)),choice(range(1,28))),False, ''.join(choices(digits,k=13)))
            ]
        # for fullname in persons:
        #     fullname[2] = " ".join([fullname[0],fullname[1]])
        crs.executemany("INSERT INTO persons(NAME, SURNAME, FULLNAME, BDAY, SEX, PERSCODE) VALUES(?,?,?,?,?,?)",[i.get_sqlite() for i in persons])
        
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
        
        crs.executemany("INSERT INTO new_wbs(NUMBER) VALUES(?)",
                        [(num,) for num in list(range(23000000,23000000+1000))])
        crs.execute("SELECT ID FROM persons")
        codes = crs.fetchall()
        sortedlist,dates=[],[]
        for i in codes:
            sortedlist.append(i[0])
        sortedlist.sort()
        for i in sortedlist:
            crs.execute(f"SELECT BDAY FROM persons where ID={i}")
            tmp = crs.fetchone()
            dates.append(datetime.strptime(tmp[0],self.__patternDate).date()+timedelta(weeks=16*4*12))
        pair_permit_code = []
        for i in range(len(sortedlist)):
            pair_permit_code.append((dates[i],sortedlist[i]))
        crs.executemany("INSERT INTO drivers(DRIVEPERMIT_D, PERSONID) VALUES(?, ?)",[i for i in pair_permit_code])
        # хочу выразить признательность стаковерфлоу за подсказку по синтаксису!
        base.commit()
        base.close()



def dbg():
    a = GateDB()
    q = a.getPerson("John", "Doe")
    w= a.getPerson( "no", "such")
    print(q)
    print(w)
    e = a.getDriver("pit","snake")
if __name__ =="__main__":
    dbg()