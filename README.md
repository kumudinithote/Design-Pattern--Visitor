
Following are the commands and the instructions to run ANT on your project.

Note: build.xml is present in numberPlay/src folder.
Instruction to clean:
```
ant -buildfile numberPlay/src/build.xml clean
```
Description: It cleans up all the .class files that were generated when you compiled your code.

Instruction to compile:
```
ant -buildfile numberPlay/src/build.xml all
```
Description: Compiles your code and generates .class files inside the BUILD folder.

Instruction to run:
Use the below command to run the program.
```
./ant -buildfile numberPlay/src/build.xml -DinputFile=<inputFile> -DacceptableWordsFile=<acceptableWordFileName> -Dk=<topK> -DtopKOutputFile=<topKOutputFileName> -DspellCheckOutputFile=<spellCheckOutputFileName>
```

-----------------------------------------------------------------------
## Description:
Input file contains the sentences with charectar set [a-zA-Z\.]. Wrapper class MyArrayList stores the sentence read from inputfile and encapsulate it in MyElement which represents an element to be visited,in an internal Arraylist.MyArrayList is also an Element.Once all the sentence is stored, following analytics has been ran. Note that period and space character are not considered as word.
KMostFrequentWord: for each sentence, top K most frequent word is stored. word comparision is case insensative.

SpellChecker: Check if their is any spelling mistake in word. It is performed on words having > 2 character. Only signle character of word can be replace by acceptable word.Those words that can be changed to acceptable word are stored in result.
After running the analytics, persist the result into corresponding outputfile.
