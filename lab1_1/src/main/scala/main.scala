import scala.io.Source
import scala.io.Source.fromFile
import Const.GridSize

import java.io.PrintWriter

@main
def main(): Unit = {
  // ENCODE
  val inputFile = "input.txt"
  val encodeFile = "encode.txt"
  val partSize = GridSize * GridSize
  val pattern = generatePattern()
  println("\tGenerated pattern")
  printGrid(pattern)
  println(s"\tReading from file - $inputFile")
  val input = fromFile(inputFile)
  val inputText = input.mkString
  println(inputText.take(100) + "...")
//  println("\tSplit into parts ->")
  val splitText = inputText.grouped(partSize).toArray
  input.close()
  for (part <- splitText.indices) {
    if (splitText(part).length < partSize) {
      splitText(part) = splitText(part).padTo(partSize, ' ')
    }
  }
//  splitText.slice(0, 5).foreach(part => println(part))
//  println("\tPaste into grids")
  var charGrids: Array[CharGrid] = Array()
  splitText.foreach(stringPart => {
    charGrids :+= encodeStringByPattern(pattern, stringPart)
  })
//  charGrids.slice(0, 1).foreach(it => printCharGrid(it))
  println("\tConvert to encoded string")
  val encodedStr = charGrids.map(grid => charGridToString(grid)).mkString
  println(encodedStr.take(100) + "...")

  //DECODE
  println("\tDecode")
  val writer = new PrintWriter(encodeFile)
  writer.write(encodedStr)
  writer.close()
  val decodedString = encodedStr.grouped(partSize).map(
    stringPart => decodeGridByPattern(pattern, stringToCharGrid(stringPart))
  ).mkString
  println(decodedString.take(100) + "...")
}