import random
with open('train.txt','r') as source:
    data = [ (random.random(), line) for line in source ]
data.sort()
with open('train.txt','w') as target:
    for _, line in data:
        target.write( line )

with open('test.txt','r') as source:
    data = [ (random.random(), line) for line in source ]
data.sort()
with open('test.txt','w') as target:
    for _, line in data:
        target.write( line )
