from datetime import date,datetime

from car import Car
from driver import Driver
from waybill import Waybill

class Container_wb():
    _content = {"car":None,"driver":None,"dates":[],"numbers":[]}

    def __init__(self,car:Car, driver:Driver, *args:date):
        self._content["car"] = car.get()["govPl"]
        self._content["driver"] =\
            " ".join([ driver.get()["name"], driver.get()["surname"]])
        self._content["dates"] = args[0]
        self._content["numbers"] = args[1]

    def unzip(self) -> list[Waybill]:
        out = []
        for i in range(len(self._content["dates"])):
            tmp = Waybill(self._content["numbers"][i][0], date.today(), 
                          self._content["driver"], self._content["car"], 
                          self._content["dates"][i])
            out.append(tmp)
        return out

# def main():
