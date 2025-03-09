def fancy_border(text: str, surrounding: str):
    text = text.upper
    print(surrounding * (len(text) + 4))
    print(f"{surrounding} {text} {surrounding}")
    print(surrounding * (len(text) + 4))


def main() -> None:
    print("Hello World!")
    fancy_border("Rock paper scissors", '=')