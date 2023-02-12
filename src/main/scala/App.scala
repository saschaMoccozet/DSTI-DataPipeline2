import org.apache.log4j.Logger;
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types.IntegerType


object App {
     
    val log = Logger.getLogger(App.getClass().getName())

    /**
     * Create the session
     * */
    def createSession = {
        val builder = SparkSession.builder.appName("Spark Batch")
        .master("local")
        //.config("spark.eventLog.enabled", true)
        //.config("spark.eventLog.dir", "./spark-logs")
        builder.getOrCreate()

    }
    
    /**
     * The running program
     * */
    def run(in: String) = {
        // define the path for the output
        val pathOuptup_CoalOilGas = "./data/CO2_emission.csv"
        val pathOuptup_15minCoalOil = "./data/CO2_emission.csv"
        val pathOuptup_DayCoalOil= "./data/CO2_emission.csv"

        log.info("launch app -> START")
        log.info("load the file -> START")
        // create the session and load the dataframe
        val spark = createSession
        var df = spark.read
            .option("header", true)
            .option("encoding", "ISO-8859-1")
            .option("delimiter", ";")
            .csv(in)
        //renaming columns name
        df = df.withColumnRenamed("Heures", "Time")
            .withColumnRenamed("Gaz", "Gas")
            .withColumnRenamed("Charbon", "Coal")
            .withColumnRenamed("Fioul","Oil")
        // casting columns type
        df = df.withColumn("Oil",col("Oil").cast(IntegerType))
            .withColumn("Coal",col("Coal").cast(IntegerType))
        log.info("load the file -> DONE")

        //select the needed header on the dataframe
        log.info("computeCO2_CoalOilGas -> START")
        val df_chalenge0 = df.select("Date", "Time","Oil", "Coal", "Gas")
    // Save as csv does not works because of write acces on the folder ...
        // df_chalenge0.coalesce(1).write.mode("Overwrite").csv(pathOuptup_CoalOilGas)
        df_chalenge0.show()
        log.info("computeCO2_CoalOilGas -> DONE")

        // select the needed header of the dataframe and compute the comparaison
        log.info("compute15min_CoalOil -> START")
        var df_chalenge1 = df.select("Date", "Time","Oil", "Coal")
            .withColumn("Coal_CO2_greater_than_Oil_CO2", col("Coal") > col("Oil"))
    // Save as csv does not works because of write acces on the folder ...
        // df_chalenge1.coalesce(1).write.mode("Overwrite").csv(pathOuptup_15minCoalOil)
        df_chalenge1.select("Date","Coal_CO2_greater_than_Oil_CO2").show()
        log.info("compute15min_CoalOil -> DONE")

        // select the neede header of the dataframe and group the row be summing up
        log.info("computeDay_CoalOil -> START")
        var df_chalenge2 = df.select("Date","Oil", "Coal")
            .groupBy("Date")
            .sum("Coal","Oil")
        // Compute the comparaison and select only the needed header
        df_chalenge2 = df_chalenge2.withColumn("Coal_CO2_greater_than_Oil_CO2", col("sum(Coal)") > col("sum(Oil)"))
        df_chalenge2.orderBy(col("Date")).select("Date","Coal_CO2_greater_than_Oil_CO2").show()
    // Save as csv does not works because of write acces on the folder ...
        // df_chalenge2.coalesce(1).write.mode("Overwrite").csv(pathOuptup_DayCoalOil)
        log.info("computeDay_CoalOil -> DONE")



        createSession.close
        log.info("launch app -> DONE")

    }

    /**
    * The main trhead that is run when executing
    * */
    def main(args: Array[String]) = args match {

        case Array(in) => run(in)
        case _ => println("usage: spark-submit app.jar <in.csv>")
    }
}