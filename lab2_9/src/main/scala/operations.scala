def addPoints(P: (BigInt, BigInt), Q: (BigInt, BigInt), a: BigInt, p: BigInt): (BigInt, BigInt) = {
  val (x1, y1) = P
  val (x2, y2) = Q

  if (P == (0, 0)) return Q
  if (Q == (0, 0)) return P

  val l = if (P != Q) {
    (y2 - y1) * (x2 - x1).modInverse(p)
  } else {
    (3 * x1.pow(2) + a) * (2 * y1).modInverse(p)
  }

  val x3 = (l.pow(2) - x1 - x2) % p
  val y3 = (l * (x1 - x3) - y1) % p

  (x3, y3)
}

// Function to compute modular inverse
def modInverse(k: BigInt, n: BigInt): Option[BigInt] = {
  (1 until n.intValue).find(i => (k * i) % n == 1).map(BigInt(_))
}