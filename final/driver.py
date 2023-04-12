from datetime import date, datetime
from person import Person
from interface import Sql_able


class Driver(Person):
    
    def __init__(self,smb:Person = None ,permit:date = None):
        try:
            super().__init__(smb.get()["name"],
                            smb.get()["surname"],
                            smb.get()["bday"],
                            smb.get()["sex"],
                            smb.get()["persCode"])
            self._permitDate = permit
        except AttributeError:
            dateNote="YYYY/mm/dd"
            dateFormat="%Y/%m/%d"
            expected_pcode_format= "13 digits"
            expected_sex_format="1#m|0#f"
            masks= ["name","surname","bday","sex",
                    "persCode","permitDate"]
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
            super().__init__(tmp[masks[0]].upper(),tmp[masks[1]].upper(),
                datetime.strptime(tmp[masks[2]],dateFormat).date(),
                True if tmp[masks[3]] =="1" else False, tmp[masks[4]])
            self._permintDate = datetime.strptime(tmp[masks[5]],dateFormat).date()
    
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
    a = Driver()
    print(a)

if __name__ == "__main__":
    dbg()