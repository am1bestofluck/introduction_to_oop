from datetime import date


from car import Car
from driver import Driver

class Container_wb():
    _content = {"car":None,"driver":None,"dates":[]}
    def __init__(self,car:Car, driver:Driver, *args:date):
        self._content["car"] = car.get()["govPl"]
        self._content["driver"] =\
            " ".join([ driver.get()["name"], driver.get()["surname"]])
        self._content["dates"] = args


# def main():
