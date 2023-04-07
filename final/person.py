from datetime import date
from interface import Sql_able

class Person(Sql_able):

    def __init__(self,name:str,surname:str,
                 bday:date,sex:bool):
        self._name = name
        self._surname = surname
        self._fullname =" ".join([name, surname])
        self._bday = bday
        self._sex = sex
        return

    def get(self) -> dict[str,any]:
        return {"name": self._name, "surname": self._surname,
                "bday":self._bday,"sex": self._sex}
    
    def get_sqlite(self) -> list:
        return [self._name, self._surname, self._fullname,
                 self._bday ,self._sex] # NAME, SURNAME, FULLNAME, BDAY, SEX