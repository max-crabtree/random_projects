import sys
import time

def countdown(t, step=1, msg='sleeping'):  # in seconds
    pad_str = ' ' * len('%d' % step)
    for i in range(t, 0, -step):
        print('%s for the next %d seconds %s\r' % (msg, i, pad_str)),
        sys.stdout.flush()
        time.sleep(step)
    print('Done %s for %d seconds!  %s' % (msg, t, pad_str))

countdown(t, step=1, msg='sleeping')