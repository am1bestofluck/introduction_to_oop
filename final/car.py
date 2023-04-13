from datetime import date,datetime


from carSpecs import CarModel,CarColor,Fuel
from interface import Sql_able

class Car(Sql_able):
    __patternDate="%Y-%m-%d"

    def __init__(self, model: CarModel= None,color:CarColor=None,prodYear:date=None,fuel:Fuel=None
                 , date_rw:date=None, date_as:date=None, date_mtr:date=None,
                 govpl:str=None):
        try:
            self._model = model
            self._color = color
            self._prodYear = prodYear.year
            self._fuel = fuel
            self._techInspection = date_rw
            self._assurance = date_as
            self._metrology = date_mtr
            self._govPL = govpl.upper()
        except AttributeError:
            self._model = CarModel.parse(input(CarModel.inputHint()+
                                              f"\nInput {CarModel.__doc__()}: "))
            self._color = CarColor.parse(input(CarColor.inputHint()+
                                              f"\nInput {CarColor.__doc__()}: "))
            self._prodYear= int(input("input production year: "))
            self._fuel = Fuel.parse(input(Fuel.inputHint()+
                                          f"\nInput {Fuel.__doc__()}: "))
            self._techInspection = datetime.strptime(input(f"input tech review date, format {self.__patternDate}: "),self.__patternDate).date()
            self._assurance = datetime.strptime(input(f"input assurance date, format {self.__patternDate}: "),self.__patternDate).date()
            self._metrology = datetime.strptime(input(f"input metrology date, format {self.__patternDate}: "),self.__patternDate).date()
            self._govPL = input("input goverment plate number: ").upper()

    def get(self) -> dict[str,any]:
        return {"model":self._model, "color":self._color, "prYear":self._prodYear,
                "fuel": self._fuel, 
                "to": self._techInspection, 
                "str": self._assurance, "mtr": self._metrology,
                "govPl":self._govPL}
    
    def get_sqlite(self) -> list[any]:
        return [self._model.value, self._color.value, self._prodYear,
                self._fuel.value, self._techInspection,self._assurance,
                self._metrology,self._govPL]
    
    def set_to(self,arg:date) -> None:
        self._techInspection = arg

    def set_str(self,arg:date) -> None:
        self._assurance = arg

    def set_mtr(self,arg:date) -> None:
        self._metrology = arg

    def __str__(self) -> str:
        return f"Car {self._model.value} {self._govPL}."