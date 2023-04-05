
class Printer():

    def __new__(cls):
        if not hasattr(cls, 'instance'):
            cls.instance = super(Printer, cls).__new__(cls)
        return cls.instance
    
    def do(self,parsed:list[dict[car,driver]]):
        raise NotImplementedError("beyond the scope")
    
