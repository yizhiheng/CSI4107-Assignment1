# CSI4107-Assignment1

According to the instructions in the assignment sheet, our program can achieve the
following functions.
First of all, the program reads the file which records the tweet documents, from the
file divides tweet texts and tweet ID's, and stores them separately. The tweet texts
are tokenized and removed stop-words from.
Secondly, the program extracts the query titles from the Query document, and
stores them in a set of lists as the vocabulary.
Thirdly, each query will be compared with all the documents by repetition to
calculate and collect the weight of each document and each query. And then the
program calculates the value of cosine with the document's and the query's weight
as the relevance.
Fourthly, the program ranks the relevance decreasingly in the required format.Finally, the results will be evaluated, comparing them with the relevance feedback
file using trec_eval script.

Since the program is developed in Java, it requires the user's device to have a Java
platform such as JDK or JRE. Also, having eclipse installed will make running the
program much easier. Otherwise, the CMD.exe will suffice. The running instruction
will be introduced separately for both eclipse and DOS users as follows.
The program is in a project folder named "A1Program" in which the contents are
listed as:
A sub-folder: .settings
A .chasspath file
A .project file
Two text file topics_MB1-49.txt and Trec_micoblog.txt: used to load the query and
document into the program to proceed
A .class file: Query.class
A .java file: Query.java
For the users with eclipse, import the project folder "A1Program" into workspace, in
"Package Explorer" find "default package" under " A1Program", and open Query.java
file under "default package" with double click. Afterwards, simply click "Run As"
button in "Quick Access" bar. The results will be displayed in a text file named as
"results.txt" in the project folder.
For the users using CMD.exe to run the program, go to the directory where the
project folder "A1Program" is stored, then type "java Query" and hit "Enter". The
results will be displayed in a text file named as "results.txt" in the project folder.
