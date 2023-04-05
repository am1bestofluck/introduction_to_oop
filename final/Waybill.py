
from datetime import date
from driver import Driver
from car import Car

class Waybill():
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

