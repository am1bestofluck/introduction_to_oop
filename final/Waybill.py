
from datetime import date
from driver import Driver
from car import Car
# from gateDB import GateDB
import gateDB
class Waybill():
    short_name="Waybill"
    def __init__(self,number:int,release:date,
                 user:Driver,car:Car,date_shift:date):
        self._number = number
        self._release = release
        self._driver = user
        self._car = car
        self._shift_date = date_shift
    
    def get(self)-> dict[str,str]:
        return {"number": self._number, "date_rel":self._release,
                "driver": self._driver, "car":self._car,
                "shift_date":self._shift_date}
    def __str__(self):
        return f"{self.short_name} {str(self._number)} from {self._shift_date:%d-%m-%Y}"

def dbg():
    b=gateDB.GateDB()
    a= Waybill(number=140,release=date(5,5,5),user=gateDB.GateDB.getDriver(b,"JANE","STONE"),car=gateDB.GateDB.getAuto(b,"LCK 777"),date_shift=date(5,5,5))
    print(a)

if __name__=="__main__":
    dbg()