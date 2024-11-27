case class Point(x: Int, y: Int)

val points: Seq[(Point, Point)] = List(
  (Point(16, 416), Point(724, 522)),
  (Point(489, 468), Point(719, 538)),
  (Point(56, 419), Point(205, 372)),
  (Point(72, 254), Point(628, 293)),
  (Point(188, 93), Point(594, 337)),
  (Point(440, 539), Point(588, 707)),
  (Point(568, 355), Point(707, 556)),
  (Point(489, 468), Point(719, 538)),
  (Point(16, 416), Point(590, 376)),
  (Point(56, 419), Point(612, 329)),
  (Point(188, 93), Point(594, 337))
)

@main
def main(): Unit = {
  val a = -1
  val p = 751
  val nb = 48

  var message = ""
  for (point <- points) {
    var nb_kG = point._1
    for (_ <- 1 until nb) {
      nb_kG = addPoints(nb_kG, point._1, a, p)
    }
    val result = subPoints(point._2, nb_kG, a, p)
    message = message + alphabet(result)
  }

  println("Decrypted message: " + message)
}