# Create class
class Init:
    def __init__(self, init_: bool):
        self.init = init_
# Create Instance of the class
init_ = Init(False)
def run():
    print('Will Run')
    if init_.init:
        print( 'Already initiated' )
    if not init_.init:
        init()
def init():
    init_.init = True
    print('Will Init')
run()
run()


#from https://betterprogramming.pub/alternatives-to-using-globals-in-python-a3b2a7d5411b