package com.ibm.cds.spark.samples

import org.apache.spark._

object HelloSpark {
	//main method invoked when running a standalone Spark Application
	def main(args: Array[String]) {
		val conf = new SparkConf().setAppName("Hello Spark")
		val spark = new SparkContext(conf)

		println("Hello Spark Demo. Compute the mean and Variance of  collection")
		val stats = computeStatsForCollection(spark);
		println(">>> Results: ")
		println(">>>>>>>Mean: " + stats._1);
		println(">>>>>>>Variance: " + stats._2);
		spark.stop()

	}

	def computeStatsForCollection( spark: SparkContext, countperPartitions: Int = 100000, partitions: Int=5): (Double, Double) = {

		val totalNumber = math.min(countperPartitions * partitions, Long.MaxValue).toInt;
		val rdd = spark.parallelize(1 until totalNumber, partitions);
		(rdd.mean(), rdd.variance())
	}
}