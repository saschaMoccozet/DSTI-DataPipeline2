# DSTI-DataPipeline2
Project for the Data Pipeline of DSTI about “Réseau de Transport d'Électricité ("Electricity Transmission Network").

## HOW TO

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
###  Run the batch using the mill or sbt command locally 
First of all we need to install stb
#### Install stb
You can found all the ressorces on this website : https://www.scala-sbt.org/1.x/docs/Installing-sbt-on-Windows.html

It also exists for linux and MacOs by searching on the table of content.

For windows : download and extract https://github.com/coursier/launchers/raw/master/cs-x86_64-pc-win32.zip

then run the exe file and follow the instruction!

#### Compile and run the project
You can found a tutorial about sbt at : https://alvinalexander.com/scala/sbt-how-to-compile-run-package-scala-project/

To compile the project use 
```bash
sbt compile
```

To run the project use 
```bash
sbt run
```

### Package automatically the jar file 
To build a JAR for submission via `spark-submit` use the `assembly` SBT task.
```bash
sbt assembly 
```

### Run the batch files generation locally without using spark-submit 
I do not know ...


### Run the batch using the spark-submit command locally 
Your can found a samble example of a running code at [https://alvinalexander.com/scala/sbt-how-to-compile-run-package-scala-project/](https://github.com/jlcanela/simple-job)

To run the Spark job
```
spark-submit target/scala-2.12/sample-job-assembly-1.0.jar data/eCO2mix_RTE_Annuel-Definitif_2020.csv
```

### Copy the data to AWS s3 so that the AWS spark-submit command executes without error (aws s3 …)
Do not know ...


## Files input output

## Input
The electricity production data for 2020 is published at https://eco2mix.rte-france.com/download/eco2mix/eCO2mix_RTE_Annuel-Definitif_2020.zip

## Output
### CO2_emission.csv
with columns : “Date,Time,Oil_CO2,Coal_CO2” for each data point.

It compute the CO2 emissions for the coal / oil / gas using the provided data

INFO : The columns asked does not match the problem asked, we want the CO2 emissions for the coal / oil / gas so in the output, the "Gaz_CO2" columns is missing. I added it in the output

### CO2_emission_report1.csv 
with columns : “Date,Time,Coal_CO2_greater_than_Oil_CO2” for each data point

It report for each 15mn period if the CO2_emission_coal > CO2_emission_oil.

### CO2_emission_report2.csv
with columns: “Date,Coal_CO2_greater_than_Oil_CO2” for each day.

It report for each day if the CO2_emission_coal > CO2_emission_oil

## ISSUE
### Open directly the xsl file 
I could not found a way to directly take the data as given in the xsl format so I manualy change it to a csv file.

### Saving the CSV
I got issue when saving the CSV ... 
From my investigation, the folder that scala creates to store all the csv do not have the Write acces for the programm to execute and even if I change it manualy or not, when the programm execute it change the acces back to how it was ... The same happend with the program at https://github.com/jlcanela/simple-job
I do not found a way to change that ... (maybe an issue with windows...)

So I created the csv files manualy but the programm print the good result in the console as dataframe.
And the code to do so is commented on the project

Thank you for your understanding.
