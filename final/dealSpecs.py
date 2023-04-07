from car import Car
from carSpecs import Relation
from person import Person
from datetime import date

class DealSpecs():

    def __init__(self,car:Car,person:Person, type:Relation):
        self._car = car
        self._person = person
        self._type = type
    
    def get(self):
        return {"car":self._car, "person":self._person, "type":self._type}
    
    def fastCheck(self):
        return self._type
    
def dbg():
    a = Car("None","None",date(2,2,2),"None",date(1,1,1),date(2,3,4),date(2,3,4),"None")
    b = Person("None","None",date(2,3,5),"None")

    c = DealSpecs(a,b,Relation.lease_add)

    print(c.fastCheck() == Relation.lease_add)
