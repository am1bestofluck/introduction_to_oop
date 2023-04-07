from enum import Enum,auto

class CarModel(Enum):
    
    prius20 = "Toyota Prius 20"
    prius30 = "Toyota Prius 30"
    prius50 = "Toyota Prius 50"
    priusV = "Toyota Prius V"
    focus = "Ford Focus"
    logan = "Dacia Logan"
    corolla = "Toyota Corolla"
    zafira = "Opel Zafira"

class CarColor(Enum):
    
    k="red"
    o="orange"
    j="yellow"
    z="green"
    g="blue"
    w="white"
    f="purple"
    b="black"
    rgb="multicolor"

class Fuel(Enum):
    
    benz="benzin"
    bm = "benzin/metan"
    bp = "benzin/propan"
    dt = "diesel"
    hb = "hibrid/benzin"
    el = "electro"

class Relation(Enum):

    rent_add = auto()
    rent_stop =auto()
    proprietary_add = auto()
    property_stop = auto()
    lease_add = auto()
    lease_stop = auto()
