# DSTI-DataPipeline2
Project for the Data Pipeline of DSTI about “Réseau de Transport d'Électricité ("Electricity Transmission Network").

## How to

### Clone the repository
Go to the parent folder in your computer and execute the command:
- if you use http
```bash
git clone https://github.com/saschaMoccozet/DSTI-DataPipeline2.git
```
- if your rather use SSH key
```bash
git clone git@github.com:saschaMoccozet/DSTI-DataPipeline2.git
```

### Install stb
You can found all the ressorces on this website : https://www.scala-sbt.org/1.x/docs/Installing-sbt-on-Windows.html

It also exists for linux and MacOs by searching on the table of content.

For windows : download and extract https://github.com/coursier/launchers/raw/master/cs-x86_64-pc-win32.zip

then run the exe file and follow the instruction!

### Create the structure of an STB project (if needed)
You can found all the ressorces in this website: https://alvinalexander.com/scala/how-to-create-sbt-project-directory-structure-scala/

From the root of the project run the command
```bash
bash mkdirs4sbt.sh
```
and it will initiate the stb structure inside your root folder

### Compile and run the project
Your can found all the ressorces on this website : https://alvinalexander.com/scala/sbt-how-to-compile-run-package-scala-project/

To compile the project execute the command:
```bash
sbt compile
```
To run the project execute the command:
```bash
sbt run
```

### Package the project
To package the project run the command:
```bash
sbt package
```

### run the batch files generation locally without using spark-submit 

### run the batch using the spark-submit command locally 

### copy the data to AWS s3 so that the AWS spark-submit command executes without error (aws s3 …)
