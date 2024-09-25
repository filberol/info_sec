object Const {
  val GridSize = 4
}

case class Cord(x: Int, y: Int)

type Grid = Array[Array[Boolean]]
type CharGrid = Array[Array[Char]]

def printGrid(grid: Grid): Unit = {
  println(grid.map(array => array.map(
    element => if element then "🟩" else "⬜"
  ).mkString("")).mkString("\n"))
}

def printCharGrid(grid: CharGrid): Unit = {
  println(grid.map(array => array.mkString("")).mkString("\n"))
}
  
