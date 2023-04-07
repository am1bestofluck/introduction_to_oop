from abc import ABC, abstractmethod

class Sql_able(ABC):
    @abstractmethod
    def get_sqlite(self) -> list:
        pass

    def get(self) -> dict:
        pass
