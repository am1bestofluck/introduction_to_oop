import re
from gateDB import GateDB
from Printer import Printer
class Controller():

    __regex_relwb_r = re.compile(r'^(relwb) [a-zA-Z]+ [0-9]+ [a-zA-Z\-]+ [a-zA-Z\-]+ [0-9]+$',re.IGNORECASE)
    __regex_relwb_c = re.compile(r'^(relwb) [a-zA-Z]+ [0-9]+ [a-zA-Z\-]+ [a-zA-Z\-]+$',re.IGNORECASE)
    __regex_retwb = re.compile("^(rwb) ([0-9]{8}\s{1}\d+\s?)+$", re.IGNORECASE)

    def __new__(cls):
        if not hasattr(cls, 'instance'):
            cls.instance = super(Controller, cls).__new__(cls)
        return cls.instance

    def help(self):
        print("relwb #car #driver #quantity - рутина")
        print("relwb #car #driver - опрашивает даты")
        print("rwb {#number traverse} - возвращает путёвки")

    def repl(self,prompt:str):
        base = GateDB()
        while True:
            readline = input(prompt)
            if self.__regex_relwb_r.match(readline):
                kit = base.createNewWB(readline)
                wbs = kit.unzip()
                Printer.print_wayBills(wbs)
                print("exception?")
                
            elif self.__regex_relwb_c.match(readline):
                kit=base.createNewWB(string_to_parse=readline,routine=False)
                Printer.print_wayBills(kit)
            elif self.__regex_retwb.match(readline):
                print('return wbs') #TODO
            else: 
                self.help()

    
def main():
    a = Controller()
    a.repl("ready")
    print("loop over!")

if __name__=="__main__":
    main()