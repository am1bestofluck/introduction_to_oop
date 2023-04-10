from datetime import date
from person import Person
from interface import Sql_able

class Driver(Person):
    
    def __init__(self,smb:Person ,permit:date):
        super().__init__(smb.get()["name"],
                         smb.get()["surname"],
                         smb.get()["bday"],
                         smb.get()["sex"])
        self._permitDate = permit
    
    def get(self) -> dict[str,any]:
        out = super().get()
        out["drivePermit"] = self._permitDate
        return out
    
    def get_sqlite(self) -> list:
        out = super().get_sqlite()
        out.append(self._permitDate)
        return out
    
    def __str__(self) -> str:
        return f"Driver {super().get()['name']} {super().get()['surname']}."

def dbg():
    a = Driver(Person("a","b",date(1,2,3),True),date(1,1,1),"1231231231232")
    print(a.get())
    print(a.get_sqlite())
    print(a)
