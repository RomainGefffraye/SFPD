formatedFile = open("data/formated.csv", 'w')
isInQuote = False
for line in open("data/gros.csv") :
    newLine = ""
    for char in line:
        if (char == '"' and isInQuote == False):
            isInQuote = True
            newLine = newLine + char
        elif(char == '"' and isInQuote):
            isInQuote = False
            newLine = newLine + char
        else:
            if (char == ',' and isInQuote):
                newLine = newLine + char
            elif(char == ',' and isInQuote == False):
                newLine = newLine + '~'
            else:
                newLine = newLine + char
    formatedFile.write(newLine)
formatedFile.close()
