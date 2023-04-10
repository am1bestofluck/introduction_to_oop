from datetime import date
from interface import Sql_able

class Person(Sql_able):

    def __init__(self,name:str,surname:str,
                 bday:date,sex:bool,perscode:str):
        self._name = name
        self._surname = surname
        self._fullname =" ".join([name, surname])
        self._bday = bday
        self._sex = sex
        self._persCode = perscode
        return

    def get(self) -> dict[str,any]:
        return {"name": self._name, "surname": self._surname,
                "bday":self._bday,"sex": self._sex, 
                "persCode":self._persCode}
    
    def get_sqlite(self) -> list:
        return [self._name, self._surname, self._fullname,
                 self._bday ,self._sex, self._persCode] # NAME, SURNAME, FULLNAME, BDAY, SEX
    
    def __str__(self) -> str:
        return f"Person {self._fullname}."