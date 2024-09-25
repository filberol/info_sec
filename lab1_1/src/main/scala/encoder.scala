import java.util.regex.Pattern

def charGridToString(charGrid: CharGrid): String = {
  charGrid.map(row => row.mkString("")).mkString("")
}

def stringToCharGrid(string: String): CharGrid = {
  val charGrid = generateCharGrid()
  val stringIter = string.iterator
  for (x <- charGrid.indices) {
    for (y <- charGrid.indices) {
      charGrid(x)(y) = stringIter.next()
    }
  }
  charGrid
}

def encodeStringByPattern(pattern: Grid, string: String): CharGrid = {
  val stringIter = string.iterator
  val charGrid = generateCharGrid()
  for (part <- 1 to 4) {
    val transform = partToTransform(part)
    val transformedPattern: Grid = transformGrid(pattern, transform)
    for (x <- pattern.indices) {
      for (y <- pattern.indices) {
        if (pattern(x)(y)) {
          val newCords = transform(Cord(x, y))
          charGrid(newCords.x)(newCords.y) = stringIter.next()
        }
      }
    }
  }
  charGrid
}

def decodeGridByPattern(pattern: Grid, charGrid: CharGrid): String = {
  val stringBuilder = StringBuilder()
  for (part <- 1 to 4) {
    val transform = partToTransform(part)
    val transformedPattern: Grid = transformGrid(pattern, transform)
    for (x <- pattern.indices) {
      for (y <- pattern.indices) {
        if (pattern(x)(y)) {
          val newCords = transform(Cord(x, y))
          stringBuilder.append(charGrid(newCords.x)(newCords.y))
        }
      }
    }
  }
  stringBuilder.mkString
}
