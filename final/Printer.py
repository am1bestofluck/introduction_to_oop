from car import Car
from driver import Driver

class Printer():

    def __new__(cls):
        if not hasattr(cls, 'instance'):
            cls.instance = super(Printer, cls).__new__(cls)
        return cls.instance
    
    def do(cls,parsed:list[str,dict[Car,Driver]]):
        raise NotImplementedError("beyond the scope")

