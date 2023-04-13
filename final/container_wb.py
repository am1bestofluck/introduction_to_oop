from datetime import date,datetime

from car import Car
from driver import Driver
from waybill import Waybill

class Container_wb():
    _content = {"car":None,"driver":None,"dates":[]}

    def __init__(self,car:Car, driver:Driver, *args:date):
        self._content["car"] = car.get()["govPl"]
        self._content["driver"] =\
            " ".join([ driver.get()["name"], driver.get()["surname"]])
        self._content["dates"] = args

    def unzip(self) -> list[Waybill]:
        out = []
        for i in self._content["dates"][0]:
            tmp = Waybill(None,date.today(),self._content["driver"],self._content["car"],i)
            out.append(tmp)
        return out

# def main():
