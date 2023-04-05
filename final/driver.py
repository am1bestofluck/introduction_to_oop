from datetime import date
from person import Person

class Driver(Person):
    
    def __init__(self,name:str,surname:str,
                 bday:date,sex:bool ,permit:date):
        super().__init__( name, surname, bday, sex)
        self._permitDate = permit
    
    def get(self) -> dict[str,any]:

        out = super().get()
        out["drivePermit"] = self._permitDate
        return out

