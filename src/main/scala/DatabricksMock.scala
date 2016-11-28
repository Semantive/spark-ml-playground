/**
  * @author Maciej Migacz <mmigacz@semantive.com>
  * @since 28.11.2016
  */


import org.apache.spark.sql.{DataFrame, SparkSession}


object DatabricksMock {
  val spark = SparkSession
    .builder()
    .appName("SparkSessionZipsExample")
    .getOrCreate()

  val sqlContext = spark.sqlContext
  val sc = spark.sparkContext

  def display(df: DataFrame) {}
  def display(df: String*) {}
}
