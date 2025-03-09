from dataclasses import dataclass


"""

"""
@dataclass
class Time:
    ticks: int
    time_of_day: str
    am_or_pm: str
    day_type: str
    
    
    def increment_time(self, amount: int):
        """
        
        """
        minutes: int = 0
        hours: int = 0
        day: int = 0
        
        for _ in range(0, amount, 1):
            # one tick is 3 mins
            self.ticks += 1
            minutes = self.ticks * 3
            
            if minutes > 60:
                minutes = 0
                hours += 1
                if 0 <= hours <= 6:
                    self.time_of_day = "Early Morning"
                    self.am_or_pm = "AM"
                elif 7 <= hours <= 11:
                    self.time_of_day = "Morning"
                    self.am_or_pm = "AM"
                elif 12 <= hours <= 15:
                    self.time_of_day = "Afternoon"
                    self.am_or_pm = "PM"
                elif 16 <= hours <= 19:
                    self.time_of_day = "Late Afternoon"
                    self.am_or_pm = "PM"
                elif 20 <= hours <= 24:
                    self.time_of_day = "Night"
                    self.am_or_pm = "PM"
                    
                if hours > 24:
                    hours = 0
                    day += 1
            
            self.time_of_day = f"{hours:02d}:{minutes:02d}"
      
        
    def __str__(self) -> str:
        return f"Time is: {self.time_of_day}{self.am_or_pm} | {self.day_type}"


# time when game starts
time = Time(0, 0, "AM", "Early Morning")