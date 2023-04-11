from enum import Enum
from abc import ABC, abstractmethod, abstractclassmethod

class Sql_able(ABC):
    @abstractmethod
    def get_sqlite(self) -> list:
        pass

    @abstractmethod
    def get(self) -> dict:
        pass

    @abstractmethod
    def __str__(self) ->str:
        pass

    @abstractclassmethod
    def keyboard_init(cls) -> any:
        pass


class Property(ABC):
    def parse(cls,read:str) -> Enum:
        pass