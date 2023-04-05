from datetime import date

class Person():

    def __init__(self,name:str,surname:str,
                 bday:date,sex:bool):
        self._name = name
        self._surname = surname
        self._bday = bday
        self._sex = sex
        return

    def get(self) -> dict[str,any]:
        return {"name": self._name, "surname": self._surname,
                "bday":self._bday,"sex": self._sex}

