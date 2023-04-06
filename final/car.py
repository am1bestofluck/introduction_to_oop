from datetime import date
from carSpecs import CarModel,CarColor,Fuel

class Car():
    
    def __init__(self, model: CarModel,color:CarColor,prodYear:date,fuel:Fuel
                 , date_rw:date, date_as:date, date_mtr:date,
                 govpl:str):
        self._model = model
        self._color = color
        self._prodYear = prodYear.year
        self._fuel = fuel
        self._techInspection = date_rw
        self._assurance = date_as
        self._metrology = date_mtr
        self._govPL = govpl.upper()

    def get(self) -> dict[str,any]:
        return {"model":self._model, "color":self._color, "prYear":self._prodYear,
                "fuel": self._fuel, 
                "to": self._techInspection, 
                "str": self._assurance, "mtr": self._metrology}
    def get_sqlite(self):
        return [self._model.value, self._color.value, self._prodYear,
                self._fuel.value, self._techInspection,self._assurance,
                self._metrology,self._govPL]
    # "MODEL, COLOR, PROD_YEAR, FUEL, TECHREVIEW, ASSURANCE  METROLOGY, GOVPL"
    def set_to(self,arg:date) -> None:
        self._techInspection = arg

    def set_str(self,arg:date) -> None:
        self._assurance = arg

    def set_mtr(self,arg:date) -> None:
        self._metrology = arg