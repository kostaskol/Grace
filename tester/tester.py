import os
from subprocess import call
from sys import argv


def parsecmd():
    if len(argv) < 2:
        print("At least one argument is required. Type --help for a list of valid options")
        return []

    method = ""
    path = ""
    output = ""
    odir = ""
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
        elif argv[i] == '-o':
            if (i + 1) < len(argv):
                output = argv[i+1]
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
        elif argv[i] == '--help':
            print("This script automatically runs all .grace files in a given folder:")
            print("Valid options:")
            print(" -j     : Specifies that the executable is a .jar file (Optional)")
            print(" -o     : Path to the output path (Optional) "
                  "\n\t\tNote: Use is discouraged due to a bug where the name of "
                  "the file will be printed after the program's output.")
            print(" -p     : Path to the main class of the grace language (or the .jar file if used with the -j flag)")
            print("--dir   : Path containing the .grace files to be checked (Optional)"
                  "\n\t\tNote: If not supplied, the current directory will be checked")
            print("--help  : Prints this message")
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

    return [method, path, output, odir]


def run(options):
    method = options[0]
    path = options[1]
    output = options[2]
    gdir = options[3]

    try:
        os.remove(output)
    except OSError:
        pass

    for place, holder, f in os.walk(gdir):
        for file in f:
            file_name = file.split('.')
            extension = file_name[1]
            if extension == 'grace' or extension == 'g':
                print("Found file: ", file)
                print("Execute? [Y/n]")
                ans = input()
                if ans == 'Y' or ans == 'y' or ans == 'yes':
                    if output != "":
                        with open(output, 'a+') as outf:
                            call(['java', method, path, gdir + "/" + file], stdout=outf)
                            f.write("\n\n\n")

                    else:
                        call(['java', method, path, gdir + "/" + file])
                        print()
                else:
                    return

    print("All files have been run. Exiting..")


def main():
    options = parsecmd()
    if len(options) == 0:
        return

    run(options)


main()
