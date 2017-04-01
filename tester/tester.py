#!/bin/python3
import os
from subprocess import call
from sys import argv


def parse_cmd():
    if len(argv) < 2:
        print("At least one argument is required. Type --help for a list of valid options")
        return []

    method = ""
    path = ""
    odir = ""
    suppressed = False
    wait = True

    i = 1
    while i < len(argv):
        if argv[i] == '-j':
            method = "-jar"
        elif argv[i] == '-p':
            if (i + 1) <= len(argv):
                path = argv[i + 1]
                i += 2
                continue
            else:
                print("Error parsing arguments. Type --help for help")
                return []
        elif argv[i] == '--dir':
            if (i + 1) < len(argv):
                odir = argv[i+1]
                i += 2
                continue
            else:
                print("Error parsing argument. Type --help for help")
                return []
        elif argv[i] == '-s':
            suppressed = True
            i += 1
            continue
        elif argv[i] == '--nowait':
            wait = False
            i += 1
            continue
        elif argv[i] == '--help':
            print("This script automatically runs all .grace files in a given folder:")
            print("Valid options:")
            print("\t -p      : Path to the main class of the grace language (or the .jar file "
                  "if used with the -j flag)")
            print("  Optional arguments: ")
            print("\t -j      : Specifies that the executable is a .jar file")
            print("\t -s      : Suppresses the compiler's output (prints only whether the program is "
                  "syntactically correct or not)")
            print("\t--dir    : Path containing the .grace files to be checked"
                  "\n\t\t\t\tNote: If not supplied, the current directory will be checked")
            print("\t--nowait : Checks all files in the given folder without waiting for user input")
            print("\t--help   : Prints this message")
            return []
        else:
            print("Unknown flag: ", argv[i])
            print("Ignore? [Y/n]")
            ans = input()
            if ans == 'Y' or ans == 'y' or ans == 'yes' or ans == 'Yes':
                print("Ignoring")
            else:
                return []
        i += 1

    if path == "":
        print("The java executable path has not been supplied. Type --help for a list of valid options")
    if odir == "":
        print("The directory containing the .grace files has not been supplied. "
              "Using current directory")

    if odir == "":
        odir = "."

    return [method, path, odir, suppressed, wait]


def run(options):
    method = options[0]
    path = options[1]
    grace_dir = options[2]
    sup = options[3]
    wait = options[4]

    for place, holder, f in os.walk(grace_dir):
        for file in f:
            file_name = file.split('.')
            extension = file_name[1]
            if extension == 'grace' or extension == 'g':
                go = True
                print("File: ", file, end='')

                if wait:
                    print()
                    print("Execute? [Y/n]")
                    ans = input()
                    if ans == 'N' or ans == 'n':
                        go = False
                if go:
                    if not sup:
                        print()
                        call(['java', method, path, grace_dir + "/" + file])
                    else:
                        with open(os.devnull, 'w') as nul:
                            result = call(['java', method, path, grace_dir + "/" + file], stdout=nul, stderr=nul)
                            if result == 0:
                                print(" : OK")
                            else:
                                # Print text in red
                                print(" :\033[91m NOT OK \033[0m")
                    print()
                else:
                    return

    print("All files have been run. Exiting..")


def main():
    options = parse_cmd()
    if len(options) == 0:
        return

    run(options)


main()
