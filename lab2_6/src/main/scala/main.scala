import scala.math.pow

//case class Point(
//    x: BigInt,
//    y: BigInt
//)

val points: Seq[(Point, Point)] = List(
  (Point(16, 416),  Point(724, 522)),
  (Point(489, 468), Point(719, 538)),
  (Point(56, 419),  Point(205, 372)),
  (Point(72, 254),  Point(628, 293)),
  (Point(188, 93),  Point(594, 337)),
  (Point(440, 539), Point(588, 707)),
  (Point(568, 355), Point(707, 556)),
  (Point(489, 468), Point(719, 538)),
  (Point(16, 416),  Point(590, 376)),
  (Point(56, 419),  Point(612, 329)),
  (Point(188, 93),  Point(594, 337))
)

val alphabet: Map[Point, Char] = Map(
  Point(238, 175) -> 'м',
  Point(243, 664) -> 'с',
  Point(250, 14) -> 'ц',
  Point(253, 540) -> 'ы',
  Point(238, 576) -> 'н',
  Point(247, 266) -> 'т',
  Point(250, 737) -> 'ч',
  Point(256, 121) -> 'ь',
  Point(240, 309) -> 'о',
  Point(247, 485) -> 'у',
  Point(251, 245) -> 'ш',
  Point(256, 630) -> 'э',
  Point(240, 442) -> 'п',
  Point(249, 183) -> 'ф',
  Point(251, 506) -> 'щ',
  Point(257, 293) -> 'ю',
  Point(243, 87) -> 'р',
  Point(249, 568) -> 'х',
  Point(253, 211) -> 'ъ',
  Point(257, 458) -> 'я',
  Point(67, 84) -> 'B',
  Point(99, 456) -> 'e',
  Point(198, 527) -> 'Й',
  Point(33, 355) -> ' ',
  Point(67, 667) -> 'C',
  Point(100, 364) -> 'f',
  Point(200, 30) -> 'К',
  Point(33, 396) -> '!',
  Point(69, 241) -> 'D',
  Point(100, 387) -> 'g',
  Point(200, 721) -> 'Л',
  Point(34, 74) -> '"',
  Point(69, 510) -> 'E',
  Point(102, 267) -> 'h',
  Point(203, 324) -> 'М',
  Point(34, 677) -> '#',
  Point(70, 195) -> 'F',
  Point(102, 484) -> 'i',
  Point(203, 427) -> 'Н',
  Point(36, 87) -> '$',
  Point(70, 556) -> 'G',
  Point(105, 369) -> 'j',
  Point(205, 372) -> 'О',
  Point(36, 664) -> '%',
  Point(72, 254) -> 'H',
  Point(105, 382) -> 'k',
  Point(205, 379) -> 'П',
  Point(39, 171) -> '&',
  Point(72, 497) -> 'I',
  Point(106, 24) -> 'l',
  Point(206, 106) -> 'Р',
  Point(39, 580) -> '\'',
  Point(73, 72) -> 'J',
  Point(106, 727) -> 'm',
  Point(206, 645) -> 'С',
  Point(43, 224) -> '(',
  Point(73, 679) -> 'K',
  Point(108, 247) -> 'n',
  Point(209, 82) -> 'Т',
  Point(43, 527) -> ')',
  Point(74, 170) -> 'L',
  Point(108, 504) -> 'o',
  Point(209, 669) -> 'У',
  Point(44, 366) -> '*',
  Point(74, 581) -> 'M',
  Point(109, 200) -> 'p',
  Point(210, 31) -> 'Ф',
  Point(44, 385) -> '+',
  Point(75, 318) -> 'N',
  Point(109, 551) -> 'q',
  Point(210, 720) -> 'Х',
  Point(45, 31) -> ',',
  Point(75, 433) -> 'O',
  Point(110, 129) -> 'r',
  Point(215, 247) -> 'Ц',
  Point(45, 720) -> '-',
  Point(78, 271) -> 'P',
  Point(110, 622) -> 's',
  Point(215, 504) -> 'Ч',
  Point(47, 349) -> '.',
  Point(78, 480) -> 'Q',
  Point(114, 144) -> 't',
  Point(218, 150) -> 'Ш',
  Point(47, 402) -> '/',
  Point(79, 111) -> 'R',
  Point(114, 607) -> 'u',
  Point(218, 601) -> 'Щ',
  Point(48, 49) -> '0',
  Point(79, 640) -> 'S',
  Point(115, 242) -> 'v',
  Point(221, 138) -> 'Ъ',
  Point(48, 702) -> '1',
  Point(80, 318) -> 'T',
  Point(115, 509) -> 'w',
  Point(221, 613) -> 'Ы',
  Point(49, 183) -> '2',
  Point(80, 433) -> 'U',
  Point(116, 92) -> 'x',
  Point(226, 9) -> 'Ь',
  Point(49, 568) -> '3',
  Point(82, 270) -> 'V',
  Point(116, 659) -> 'y',
  Point(226, 742) -> 'Э',
  Point(53, 277) -> '4',
  Point(82, 481) -> 'W',
  Point(120, 147) -> 'z',
  Point(227, 299) -> 'Ю',
  Point(53, 474) -> '5',
  Point(83, 373) -> 'X',
  Point(120, 604) -> '{',
  Point(227, 452) -> 'Я',
  Point(56, 332) -> '6',
  Point(83, 378) -> 'Y',
  Point(125, 292) -> '|',
  Point(228, 271) -> 'а',
  Point(56, 419) -> '7',
  Point(85, 35) -> 'Z',
  Point(125, 459) -> '}',
  Point(228, 480) -> 'б',
  Point(58, 139) -> '8',
  Point(85, 716) -> '[',
  Point(126, 33) -> '~',
  Point(229, 151) -> 'в',
  Point(58, 612) -> '9',
  Point(86, 25) -> '\\',
  Point(189, 297) -> 'А',
  Point(229, 600) -> 'г',
  Point(59, 365) -> ':',
  Point(90, 21) -> '^',
  Point(192, 32) -> 'В',
  Point(234, 587) -> 'е',
  Point(61, 129) -> '<',
  Point(90, 730) -> '_',
  Point(192, 719) -> 'Г',
  Point(235, 19) -> 'ж',
  Point(61, 622) -> '=',
  Point(93, 267) -> '`',
  Point(194, 205) -> 'Д',
  Point(235, 732) -> 'з',
  Point(62, 372) -> '>',
  Point(93, 484) -> 'a',
  Point(194, 546) -> 'Е',
  Point(236, 39) -> 'и',
  Point(62, 379) -> '?',
  Point(98, 338) -> 'b',
  Point(197, 145) -> 'Ж',
  Point(236, 712) -> 'й',
  Point(66, 199) -> '@',
  Point(98, 413) -> 'c',
  Point(197, 606) -> 'З',
  Point(237, 297) -> 'к',
  Point(66, 552) -> 'A',
  Point(99, 295) -> 'd',
  Point(198, 224) -> 'И',
  Point(237, 454) -> 'л',
  Point(189, 454) -> 'Б',
  Point(234, 164) -> 'д',
  Point(59, 386) -> ';',
  Point(90, 21) -> '^',
  Point(192, 32) -> 'В',
  Point(234, 587) -> 'е',
  Point(221, 138) -> 'Ъ',
  Point(61, 129) -> '<',
  Point(90, 730) -> '_',
  Point(192, 719) -> 'Г',
  Point(235, 19) -> 'ж'
)

//@main
//def main(): Unit = {
//  val a = -1
//  val p = 751
//  val nb = 48
//
//  var message = ""
//  for (i <- points.indices) {
//    val kG = points(i)(0)
//    val Pm_add_kPb = points(i)(1)
//    var nb_kG = kG
//
//    // Repeat addition of kG `nb - 1` times
//    for (_ <- 0 until (nb - 1)) {
//      nb_kG = add_points(nb_kG, kG, a, p)
//    }
//
//    // Subtract nb_kG from Pm_add_kPb
//    val result = sub_points(Pm_add_kPb, nb_kG, a, p)
//
//    // Append the character to the message based on the result
//    message += alphabet(result)
//  }
//
//  println("Открытый текст: " + message)
//}
//
//def add_points(P: Point, Q: Point, a: Int, p: Int): Point = {
//  val (x1, y1) = (P.x, P.y)
//  val (x2, y2) = (Q.x, Q.y)
//
//  // Check if P is the identity element
//  if (P == Point(0,0)) return Q
//  // Check if Q is the identity element
//  if (Q == Point(0,0)) return P
//
//  // Calculate the slope λ (l) for different cases
//  val l = if (P != Q) {
//    (y2 - y1) * (x2 - x1).modInverse(p) % p
//  } else {
//    (3 * pow(x1.toDouble, 2).toInt + a) * (2 * y1).modInverse(p) % p
//  }
//
//  // Calculate the resulting point (x3, y3)
//  val x3 = (l.pow(2) - x1 - x2) % p
//  val y3 = (l * (x1 - x3) - y1) % p
//
//  Point(x3, y3)
//}
//
//
////def add_points(P: Point, Q: Point, a: Int, p: Int): Point = {
////  var l: Int = 0
////  if (P == Point(0, 0)) return Q
////  if (Q == Point(0, 0)) return P
////  if (P != Q) {
////    l = (Q.y - P.y) * (pow(Q.x - P.x, -1).toInt % p)
////  } else {
////    l = (pow(3 * P.x, 2).toInt + a) * (pow(2 * P.y, -1).toInt % p)
////  }
////  val x3 = (pow(l, 2).toInt - P.x - Q.x) % p
////  val y3 = (l * (P.x - x3) - P.y) % p
////  Point(x3, y3)
////}
//
//def sub_points(P: Point, Q: Point, a: Int, p: Int): Point =
//  add_points(P, negate_point(Q), a, p)
//
//def negate_point(P: Point): Point = Point(P.x, -P.y)

case class Point(x: Int, y: Int)

object EllipticCurveOperations {

  def addPoints(P: Point, Q: Point, a: Int, p: Int): Point = {
    val (x1, y1) = (P.x, P.y)
    val (x2, y2) = (Q.x, Q.y)

    if (P == Point(0, 0)) return Q
    if (Q == Point(0, 0)) return P

    val λ = if (P != Q) {
      (y2 - y1) * modInverse(x2 - x1, p) % p
    } else {
      (3 * x1 * x1 + a) * modInverse(2 * y1, p) % p
    }

    val x3 = (λ * λ - x1 - x2) % p
    val y3 = (λ * (x1 - x3) - y1) % p
    Point((x3 + p) % p, (y3 + p) % p) // Ensure positive modulo
  }

  def subPoints(P: Point, Q: Point, a: Int, p: Int): Point = {
    addPoints(P, negatePoint(Q, p), a, p)
  }

  def negatePoint(P: Point, p: Int): Point = {
    Point(P.x, (-P.y + p) % p)
  }

  def modInverse(value: Int, mod: Int): Int = {
    BigInt(value).modInverse(mod).toInt
  }

  def main(args: Array[String]): Unit = {
    val a = -1 // Elliptic curve parameter
    val p = 751 // Field order
    val nb = 48 // Secret key

    var message = ""
    for ((kG, Pm_add_kPb) <- points) {
      var nb_kG = kG
      for (_ <- 1 until nb) {
        nb_kG = addPoints(nb_kG, kG, a, p)
      }
      val result = subPoints(Pm_add_kPb, nb_kG, a, p)
      message += alphabet(result.x % alphabet.length) // Assuming x-coordinate as index
    }

    println("Decrypted message: " + message)
  }
}