from enum import Enum,auto, EnumMeta

# from abc import ABCMeta
from interface import Property 

# class MetaCarModel(type(Enum),type(Property)):
#     pass

# пробовал делать интерфейс для этих классов но метакласс ломает enum =\
# а без метакласса не парсится

# class CarModelMeta(Enum, type(Property)):
#     pass

class CarModel(Enum):
    
    @classmethod
    def __doc__(cls):
        return "Car Model"

    prius20 = "Toyota Prius 20"
    prius30 = "Toyota Prius 30"
    prius50 = "Toyota Prius 50"
    priusV = "Toyota Prius V"
    focus = "Ford Focus"
    logan = "Dacia Logan"
    corolla = "Toyota Corolla"
    zafira = "Opel Zafira"

    @classmethod
    def parse(cls, read: str) -> Enum:
        iterator = [cls.prius20, cls.prius30, cls.prius50, cls.priusV,
                    cls.focus, cls.logan, cls.corolla, cls.zafira]
        for i  in iterator:
            if i.value == read:
                return i
        raise ValueError("откуда такая машина в базе?")
    
    @classmethod
    def inputHint(cls):
        return f"Possible args for {cls.__doc__()}: ["+", ".join([i.value for  i in cls])+"]"
    

class CarColor(Enum):

    @classmethod
    def __doc__(cls):
        return "Car Color"
    
    k="red"
    o="orange"
    j="yellow"
    z="green"
    g="blue"
    w="white"
    f="purple"
    b="black"
    rgb="multicolor"

    @classmethod
    def parse(cls, read:str) ->Enum:
        iterator = [cls.k,cls.o,cls.j,cls.z, cls.g, cls.w, cls.f,
            cls.b,cls.rgb]
        for i in iterator:
            if i.value == read:
                return i
        raise ValueError("откуда такой цвет в базе?")
    
    @classmethod
    def inputHint(cls):
        return f"Possible args for {cls.__doc__()}: ["+", ".join([i.value for i in cls])+"]"

class Fuel(Enum):
    
    @classmethod
    def __doc__(cls):
        return "Car Fuel"
    
    benz="benzin"
    bm = "benzin/metan"
    bp = "benzin/propan"
    dt = "diesel"
    hb = "hibrid/benzin"
    el = "electro"

    @classmethod
    def parse(cls,read:str) -> Enum | None:
        iterator = [cls.benz , cls.bm, cls.bp, cls.dt, cls.hb, cls.el]
        for i in iterator:
            if i.value == read:
                return i
        raise ValueError("откуда такое топливо в базе?")
    
    @classmethod
    def inputHint(cls):
        return f"Possible args for {cls.__doc__()}: ["+", ".join([i.value for  i in cls])+"]"

class Relation(Enum):

    @classmethod
    def __doc__(cls):
        return "Relation model"
    
    rent_add = auto()
    rent_stop =auto()
    proprietary_add = auto()
    property_stop = auto()
    lease_add = auto()
    lease_stop = auto()

    def parse() -> Enum | None:
        raise NotImplementedError("#beyond the scope")
    
    @classmethod
    def inputHint(cls):
        return f"Possible args for {cls.__doc__()}: ["+", ".join([i for  i in dir(cls) if not i.startswith('__')])+"]"

def dbg():
    modl = CarModel.parse("Opel Zafira")
    print(modl)
    clr = CarColor.parse("multicolor")
    print(clr)
    f = Fuel.parse("electro")
    print(CarModel.inputHint())
    print(CarColor.inputHint())
    print(Fuel.inputHint())
    
    print(f)
    
if __name__ == "__main__": dbg()