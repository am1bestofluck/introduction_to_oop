from datetime import date, timedelta, datetime
from pathlib import Path
from random import choice, choices
from string import digits
from os import unlink

import sqlite3

from car import Car
from carSpecs import CarModel, CarColor, Fuel
try:
    from container_wb import Container_wb
except ImportError:
    pass # ...
try:
    from waybill import Waybill
except ImportError:
    pass

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
    
    def createNewWB(self, string_to_parse: str,routine:bool=True) -> "Container_wb":
        args=string_to_parse[6:]
        driver_o= self.getDriver(args.split(' ')[2],args.split(' ')[3])
        car_o = self.getAuto(' '.join([args.split(' ')[0],args.split(' ')[1]]).upper()) 
        qua = int(args.split(' ')[4]) if routine else int(input("Сколько путевых листов?"))
        dates = []
        legit = choice((True,False))# на будущее
        wbnumbers = self.giveNumberWB(qua=qua, strikeThrough=legit)
        if routine:
            walk = date.today()
            for i in range(qua):
                dates.append(walk)
                walk = walk + timedelta(days=1)
        else:
            for i in range(qua):
                dates.append(datetime.strptime(input(f"input waybill date, format {self.__patternDate}"),self.__patternDate).date())
        out = Container_wb(car_o,driver_o,dates,wbnumbers)
        return out
    
    def returnWB(self, string_to_parse: str) -> None:
        raise NotImplementedError # TODO
    
    def addDriver(self, **drivers:Driver) -> None:
        
        base = sqlite3.connect(self.__SOURCE)   
        crs = base.cursor()
        for driver in drivers:
            fullName= " ".join([driver.get()["name"], driver.get()["surname"]])
            crs.execute(f"SELECT ID FROM persons WHERE FULLNAME='{fullName}'")
            code = crs.fetchone()
            buffer = (driver.get()["permitDate"],code)
            crs.execute("INSERT INTO drivers(DRIVEPERMIT_D, PERSONID) VALUES(?, ?)", buffer)
        base.commit()
        base.close()

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

    def addAuto(self, *args:Car):
        # raise NotImplementedError("TODO")
        base = sqlite3.connect(self.__SOURCE)   
        crs = base.cursor()
        crs.executemany("INSERT INTO cars"
                        +" (MODEL, COLOR, PROD_YEAR, FUEL, TECHREVIEW, ASSURANCE"
                        + ", METROLOGY, GOVPL) VALUES(?, ?, ?, ?, ?, ?, ?, ?)",
                        [car_.get_sqlite() for car_ in args])
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

    def addNumberWB(self, rangeNums:range) -> None:
        base = sqlite3.connect(self.__SOURCE)
        crs = base.cursor()
        crs.executemany("INSERT INTO new_wbs(NUMBER) VALUES(?)",
                        [(num,) for num in list(rangeNums)])
        base.commit()
        base.close()
    
    def giveNumberWB(self, qua:int, strikeThrough:bool=False) -> list[int]:
        base = sqlite3.connect(self.__SOURCE)
        crs = base.cursor()
        crs.execute(f"SELECT NUMBER FROM new_wbs WHERE USED IS NULL")
        nums = crs.fetchmany(qua)
        if strikeThrough:
            print("добавляем еденичку в USED")
            crs.executemany("UPDATE new_wbs SET USED=1 WHERE NUMBER=(?)",[num for num in nums])
            
        else:
            print("переносим в конец списка")
            crs.executemany("DELETE FROM new_wbs WHERE NUMBER=(?)",[num for num in nums])
            crs.executemany("INSERT INTO new_wbs(NUMBER) VALUES(?)", [num for num in nums])

        base.commit()
        base.close()
        return nums

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
                    ,  "NUMBER int NOT NULL UNIQUE,"
                    , "USED int"
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
                    , "SHIFTDATE DATE,"
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
    
    def journal_extract(self,date_after:date=None, date_till: date=None, 
                        car:Car = None, driver:Driver = None):
        date_till_m = date(2999,12,12) if date_till is None else date_till
        date_after_m = date(1,1,1) if date_after is None else date_after
        base = sqlite3.connect(self.__SOURCE)
        crs = base.cursor()
        
        idCar = "ALL"
        idDriver = "ALL"
        if car is not None:
            crs.execute(f"""SELECT ID FROM cars WHERE GOVPL='{car.get()["govPl"]}'""")
            idCar=crs.fetchone()
        if driver is not None:
            fullName= " ".join([driver.get()['name'],driver.get()['surname']])
            crs.execute(f"SELECT ID FROM persons WHERE FULLNAME='{fullName}'")
            personId=crs.fetchone()
            idDriver = crs.execute(f"SELECT ID FROM drivers WHERE PERSONID={personId}")

        operator = f"SELECT * FROM journal WHERE AUTO ={idCar} AND DRIVER={idDriver} "\
        + f"AND SHIFTDATE BETWEEN #{date_till_m:%m/%d/%Y}# AND #{date_after_m:%m/%d/%Y}#"
        crs.execute(operator)
        out= crs.fetchall()
        base.close()
        return out

    def journal_log(self,wbs:list['Waybill']):
        base = sqlite3.connect(self.__SOURCE)
        crs = base.cursor()
        crs.executemany("INSERT INTO( RELDATE, SHIFTDATE, WBNUM, AUTO, DRIVER) journal()",[i.get_sqlite()[:-2] for i in wbs])
        base.commit()
        base.close()


    def journal_return(self):
        pass

def dbg():
    a = GateDB()
    a.reinit()
    testCar=Car(model=CarModel.focus,color=CarColor.rgb,prodYear=date(2023,1,1),fuel=Fuel.benz,date_rw=date(2023,2,2),date_as=date(2022,12,12),date_mtr=date(2023,3,3),govpl="ADD 777")
    testCar2=Car(model=CarModel.focus,color=CarColor.rgb,prodYear=date(2023,1,1),fuel=Fuel.benz,date_rw=date(2023,2,2),date_as=date(2022,12,12),date_mtr=date(2023,3,3),govpl="ADD 888")
    a.addAuto(testCar,testCar2,Car())
    st=15000000
    a.addNumberWB(range(st,st+1000))
    # grey = a.giveNumberWB(10)
    # white = a.giveNumberWB(10,True)
    # print(grey)
    # print(white)
    pass
if __name__ =="__main__":
    dbg()