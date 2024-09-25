import Const.GridSize

import scala.util.Random

def generateGrid(): Grid = {
  Array.fill(GridSize) { Array.fill(GridSize) { false } }
}

def generateCharGrid(): CharGrid = {
  Array.fill(GridSize) { Array.fill(GridSize) { ' ' } }
}

def generatePattern(): Grid = {
  val grid = generateGrid()
  val rand = Random()
  for (x <- 0 until GridSize/2) {
    for (y <- 0 until GridSize/2) {
      val randomPart = rand.between(1, 5)
      val transCords = partToTransform(randomPart)(Cord(x, y))
      grid(transCords.x)(transCords.y) = true
    }
  }
  grid
}

def printGrid(grid: Grid): Unit = {
  println(grid.map(array => array.map(
    element => if element then "🟩" else "⬜"
  ).mkString("")).mkString("\n"))
}
