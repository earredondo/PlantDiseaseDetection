import os

for root, directories, filenames in os.walk('./data/color/'):
    for directory in directories:
        oldDir = os.path.join(root, directory)
        newDir = oldDir.replace(' ', '_')
        os.rename(oldDir, newDir)
    for filename in filenames:
        oldFile = os.path.join(root, filename)
        newFile = oldFile.replace(' ', '_')
        os.rename(oldFile, newFile)
