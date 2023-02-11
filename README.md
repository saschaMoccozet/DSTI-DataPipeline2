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

### Build and Run the project
Your can found a samble example of a running code at [https://alvinalexander.com/scala/sbt-how-to-compile-run-package-scala-project/](https://github.com/jlcanela/simple-job)

To build a JAR for submission via `spark-submit` use the `assembly` SBT task.
```bash
sbt assembly 
```

To run the Spark job
```
spark-submit target/scala-2.12/sample-job-assembly-1.0.jar data/eCO2mix_RTE_Annuel-Definitif_2020.csv
```


### Package the project
You can found a tutorial about sbt at : https://alvinalexander.com/scala/sbt-how-to-compile-run-package-scala-project/
To package the project run the command:
```bash
sbt package
```

### run the batch files generation locally without using spark-submit 

### run the batch using the spark-submit command locally 

### copy the data to AWS s3 so that the AWS spark-submit command executes without error (aws s3 …)
