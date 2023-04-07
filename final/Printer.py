from dealSpecs import DealSpecs
from carSpecs import Relation
from container_wb import Container_wb

class Printer():

    def __new__(cls):
        if not hasattr(cls, 'instance'):
            cls.instance = super(Printer, cls).__new__(cls)
        return cls.instance
    
    def print_wayBills(cls, waybillBunch:Container_wb):
        raise NotImplementedError("beyond the scope")
    
    def new_deal(cls, content: DealSpecs):
        match content.fastCheck():
            case Relation.rent_add:
                cls._rent_add(content)
            case Relation.rent_stop:
                cls._rent_stop(content)
            case Relation.proprietary_add:
                cls._propr_start(content)
            case Relation.property_stop:
                cls._propr_stop(content)
            case Relation.lease_add:
                cls._lease_start(content)
            case Relation.lease_stop:
                cls._lease_stop(content)
            case _:
                raise TypeError(f"Have a fun implementing!")
            
    def _rent_add(cls, content:DealSpecs):
        raise  NotImplementedError("beyond the scope")
    
    def _rent_stop(cls, content:DealSpecs):
        raise  NotImplementedError("beyond the scope")
    
    def _propr_start(cls, content:DealSpecs):
        raise  NotImplementedError("beyond the scope")
    
    def _propr_stop(cls, content:DealSpecs):
        raise  NotImplementedError("beyond the scope")
    
    def _lease_start(cls, content:DealSpecs):
        raise  NotImplementedError("beyond the scope")
    
    def _lease_stop(cls, content:DealSpecs):
        raise  NotImplementedError("beyond the scope")
    
