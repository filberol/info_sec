import scala.io.Source
import scala.io.Source.fromFile

import Const.GridSize

@main
def main(): Unit = {
  val inputFile = "input.txt"
  val grid = generatePattern()
  println("\tGenerated pattern")
  printGrid(grid)
  println(s"\tReading from file - $inputFile")
  val input = fromFile(inputFile)
  val inputText = input.mkString
  println(inputText.take(50) + "...")
  println("\tSplit into parts ->")
  val partSize = GridSize * GridSize
  val splitText = inputText.grouped(partSize).toArray
  input.close()
  for (part <- splitText.indices) {
    if (splitText(part).length < partSize) {
      splitText(part) = splitText(part).padTo(partSize, ' ')
    }
  }
  splitText.slice(0, 5).foreach(part => println(part))
  println("Paste into grids")
}