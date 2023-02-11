import org.apache.log4j.Logger;
import org.apache.spark.sql.SparkSession

object App {
     
    val log = Logger.getLogger(App.getClass().getName())

    def createSession = {
         val builder = SparkSession.builder.appName("Spark Batch")
        //.config("spark.eventLog.enabled", true)
        //.config("spark.eventLog.dir", "./spark-logs")
        builder.getOrCreate()
    }
    
    def run(in: String) = {
        log.info("launch app -> START")
        log.info("load the file -> START")
        val spark = createSession
        val df = spark.read
            .option("header", true)
            .option("encoding", "ISO-8859-1")
            .csv(in)
        log.info("load the file -> DONE")
        df.printSchema()
        createSession.close
        log.info("launch app -> DONE")
    }

    def main(args: Array[String]) = args match {
        case Array(in) => run(in)
        case _ => println("usage: spark-submit app.jar <in.csv>")
    }
}