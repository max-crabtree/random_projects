import rpgtime


def general_prompt() -> str | int:
    """
    
    """
    decision: str | int
    rpgtime.time.increment_time(amount=1)
    print(f"Time is: {rpgtime.time}")
    print("What do you do?")
    decision = input(">> ")
    return decision