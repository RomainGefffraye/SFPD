import java.io._
import java.text.DateFormatSymbols


object main {

  def printToFile(f: java.io.File)(op: java.io.PrintWriter => Unit) {
    val p = new java.io.PrintWriter(f)
    try { op(p) } finally { p.close() }
  }

  def getMonth(month : Int) : String = {
    return new DateFormatSymbols().getMonths()(month-1)
  }


  def mapToJs(map:Seq[(String, Int)], nameOfFile : String){
    var finalOutput = "var "
    finalOutput = finalOutput.concat(nameOfFile.dropRight(3).drop(7)).concat(" = [")
    for(b <- map){
      finalOutput = finalOutput.concat("[\"").concat(b._1).concat("\" ,").concat(b._2.toString()).concat("],")
    }
    finalOutput = finalOutput.dropRight(1)
    finalOutput = finalOutput.concat("]")
    var data = Array(finalOutput)
    printToFile(new File(nameOfFile)) { p =>
      data.foreach(p.println)
      }
  }


  def main(args: Array[String]) {
    val bufferedSource = io.Source.fromFile("data/formated.csv")
    var dayFrequency = scala.collection.mutable.Map[String, Int]()
    var timeFrequency = scala.collection.mutable.Map[String, Int]()
    var monthFrequency = scala.collection.mutable.Map[String, Int]()
    var yearFrequency = scala.collection.mutable.Map[String, Int]()
    var district = scala.collection.mutable.Map[String, Int]()
    var category = scala.collection.mutable.Map[String, Int]()
    var dayRelation = Map("Monday" -> 1, "Tuesday" -> 2,"Wednesday" -> 3, "Thursday" -> 4, "Friday" -> 5, "Saturday" -> 6, "Sunday" -> 7)
    for (line <- bufferedSource.getLines.drop(1)) {
        val cols = line.split("~").map(_.trim)
        district(cols(6)) = district.getOrElse(cols(6), 0) + 1
        category(cols(1)) = category.getOrElse(cols(1), 0) + 1
        dayFrequency(cols(3)) = dayFrequency.getOrElse(cols(3), 0) + 1
        timeFrequency(cols(5).subSequence(0,2).toString()) = timeFrequency.getOrElse(cols(5).subSequence(0,2).toString(), 0) + 1
        monthFrequency(cols(4).subSequence(0,2).toString()) = monthFrequency.getOrElse(cols(4).subSequence(0,2).toString(), 0) + 1
        yearFrequency(cols(4).subSequence(6,10).toString()) = yearFrequency.getOrElse(cols(4).subSequence(6,10).toString(), 0) + 1
    }

    district.foreach{keyVal =>
      println(keyVal._1)
      println(keyVal._2)
    }

    val districtSorted = district.toSeq.sortBy(_._1).map(x => (x._1.toLowerCase() , x._2))
    val yearFrequencySorted = yearFrequency.toSeq.sortBy(_._1).dropRight(1)
    val timeFrequencySorted = timeFrequency.toSeq.sortBy(_._1)
    var monthFrequencySorted = monthFrequency.toSeq.sortBy(_._1).map(x => (getMonth(x._1.toInt), x._2))
    val dayFrequencySorted = dayFrequency.toSeq.map(x => (dayRelation(x._1),x._2)).sortWith(_._1 < _._1).map(x => (dayRelation.find(_._2==x._1).getOrElse("",0)._1,x._2))
    mapToJs(yearFrequencySorted,"static/year.js")
    mapToJs(timeFrequencySorted,"static/time.js")
    mapToJs(dayFrequencySorted,"static/day.js")
    mapToJs(monthFrequencySorted,"static/month.js")
    mapToJs(districtSorted,"static/district.js")
    bufferedSource.close
  }
}
