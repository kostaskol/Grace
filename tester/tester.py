import os
from subprocess import call

path = '.'

for r, d, f in os.walk(path):
    for file in f:
        fileName = file.split('.')
        extension = fileName[1]
        if extension == 'grace' or extension == 'g':
            print("Printing file: ", file)
            call(['java', 'target/classes/compiler/main/Main', file])
