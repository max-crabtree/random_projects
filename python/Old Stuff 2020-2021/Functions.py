dict = {
    "first":{
        "one":"one",
        "two":"two"
    }
}

def info(x):
    if dict["first"][x] == "one":
        print("Correct")

def other():
    info("one")

other()

