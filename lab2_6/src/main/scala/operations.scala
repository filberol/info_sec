def addPoints(P: Point, Q: Point, a: Int, p: Int): Point = {
  val (x1, y1) = (P.x, P.y)
  val (x2, y2) = (Q.x, Q.y)

  if (P == Point(0, 0)) return Q
  if (Q == Point(0, 0)) return P

  val lambda = if (P != Q) {
    (y2 - y1) * modInverse(x2 - x1, p) % p
  } else {
    (3 * x1 * x1 + a) * modInverse(2 * y1, p) % p
  }

  val x3 = (lambda * lambda - x1 - x2) % p
  val y3 = (lambda * (x1 - x3) - y1) % p
  Point((x3 + p) % p, (y3 + p) % p)
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
