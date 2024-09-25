import Const.GridSize

private type CordTransformer = Cord => Cord

def transform0(cord: Cord): Cord = cord
def transform90(cord: Cord): Cord = Cord(GridSize - cord.y - 1, cord.x)
def transform180(cord: Cord): Cord = Cord(GridSize - cord.x - 1, GridSize - cord.y - 1)
def transform240(cord: Cord): Cord = Cord(cord.y, GridSize - cord.x - 1)

def transformGrid(grid: Grid, transformer: CordTransformer): Grid = {
  val newGrid = generateGrid()
  for (x <- grid.indices) {
    for (y <- grid.indices) {
      val newCords = transformer(Cord(x, y))
      newGrid(x)(y) = grid(newCords.x)(newCords.y)
    }
  }
  newGrid
}

def partToTransform(part: Int): CordTransformer = {
  part match
    case 1 => transform0
    case 2 => transform90
    case 3 => transform180
    case 4 => transform240
    case _ => throw NumberFormatException()
}

