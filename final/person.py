from datetime import date, datetime
from interface import Sql_able

class Person(Sql_able):

    def __init__(self,name:str = None,surname:str = None,
                 bday:date = None,sex:bool=None,perscode:str=None):
        try:
            self._name = name
            self._surname = surname
            self._fullname =" ".join([name, surname])
            self._bday = bday
            self._sex = sex
            self._persCode = perscode
        except AttributeError:
            dateNote="YYYY/mm/dd"
            dateFormat="%Y/%m/%d"
            expected_pcode_format= "13 digits"
            expected_sex_format="1#m|0#f"
            masks= ["name","surname","bday","sex",
                    "persCode"]
            tmp =dict()
            for i in masks:
                if i in ["permitDate", "bday"]:
                    note = f"input {i}, {dateNote=}"
                elif i == "persCode":
                    note = f"input {i}, {expected_pcode_format=}"
                elif i == "sex":
                    note = f"input {i},  {expected_sex_format=}"
                else:
                    note = f"input {i}"
                tmp[i] = input(f"{note} ")
            self._name = tmp[masks[0]]
            self._surname = tmp[masks[1]]
            self._fullname =" ".join([name, surname])
            self._bday = datetime.strptime(tmp[masks[2]],dateFormat).date()
            self._sex = True if tmp[masks[3]] =="1" else False
            self._persCode = tmp[masks[4]]

    def get(self) -> dict[str,any]:
        return {"name": self._name, "surname": self._surname,
                "bday":self._bday,"sex": self._sex, 
                "persCode":self._persCode}
    
    def get_sqlite(self) -> list:
        return [self._name, self._surname, self._fullname,
                 self._bday ,self._sex, self._persCode] # NAME, SURNAME, FULLNAME, BDAY, SEX
    
    def __str__(self) -> str:
        return f"Person {self._fullname}."