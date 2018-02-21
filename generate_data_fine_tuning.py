import os

TRAIN_FILE = "./data/train.txt"
TEST_FILE = "./data/test.txt"

TRAIN_PERCENT = 0.8

label = 0

trf = open(TRAIN_FILE, 'w')
tef = open(TEST_FILE, 'w')
for root, directories, filenames in os.walk('/home/edgar/git/PlantDiseaseDetection/data/color/'):
    for directory in directories:
        theDir = os.path.join(root, directory)
        images = [f for f in os.listdir(theDir)]
        
        print('Writing from dir', theDir, '...')
        print('Writing train images', '...')
        for image in images[ : int(TRAIN_PERCENT * len(images))]:
            trf.write(os.path.join(theDir, image) + ' ' + str(label) + '\n')

        print('Writing test images', '...')
        for image in images[int(TRAIN_PERCENT * len(images)) : ]:
            tef.write(os.path.join(theDir, image) + ' ' + str(label) + '\n')
        
        label = label + 1

trf.close()
tef.close()
