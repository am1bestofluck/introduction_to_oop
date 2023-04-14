
from datetime import date
from driver import Driver
from car import Car
from interface import Sql_able
# from gateDB import GateDB
import gateDB


class Waybill(Sql_able):
    short_name="Waybill"
    def __init__(self,number:int,release:date,
                 user:Driver,car:Car,date_shift:date, 
                 returnDate: date =None,traverseOnBegin: int = None):
        self._number = number
        self._release = release
        self._driver = user
        self._car = car
        self._shift_date = date_shift
        self._returnDate=returnDate
        self._traverseOnBegin = traverseOnBegin
    
    def get(self)-> dict[str,str]:
        out ={"number": self._number, "date_rel":self._release,
                "driver": self._driver, "car":self._car,
                "shift_date":self._shift_date,
                "returned_date":self._returnDate,
                "traversedTill":self._traverseOnBegin}
        return 
    # "RELDATE DATE NOT NULL,"
        # , "SHIFTDATE DATE,"
        #             , "WBNUM int,"
        #             , "AUTO int,"
        #             , "DRIVER int,"
        #             , "RETURNDATE DATE,"
    def get_sqlite(self) -> list:
        return [self._release, self._shift_date, self._number, 
                self._car, self._driver,self._returnDate, self._traverseOnBegin]
    
    def __str__(self):
        return f"{self.short_name} {str(self._number)} from {self._shift_date:%d-%m-%Y}"

def dbg():
    b=gateDB.GateDB()
    a= Waybill(number=140,release=date(5,5,5),user=gateDB.GateDB.getDriver(b,"JANE","STONE"),car=gateDB.GateDB.getAuto(b,"LCK 777"),date_shift=date(5,5,5))
    print(a)

if __name__=="__main__":
    dbg()