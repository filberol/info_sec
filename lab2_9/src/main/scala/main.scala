@main
def main(): Unit = {

  val a = -1  // Curve parameter
  val p = BigInt(751) // Field order
  val G = (BigInt(416), BigInt(55)) // Generator point
  val n = BigInt(13)  // Order of generator point
  val e = BigInt(6) // Hash value
  val d = BigInt(10) // Secret key
  val k = BigInt(7) // Randomly chosen number k

  var R = G
  for (_ <- 1 until k.intValue) {
    R = addPoints(R, G, a, p)
  }

  val r = R._1 % n
  val z = modInverse(k, n).getOrElse {
    println("Could not find modular inverse for k")
    return
  }
  val s = (z * (e + d * r)) % n
  println(s"Generated ECDSA Signature: (r = $r, s = $s)")

  if (r < 1 || r > n - 1 || s < 1 || s > n - 1) {
    println("Invalid signature")
    return
  }
  val v = modInverse(s, n).getOrElse {
    println("Could not find modular inverse for s")
    return
  }

  var Q = G
  for (_ <- 1 until d.intValue) {
    Q = addPoints(Q, G, a, p)
  }

  val u1 = (e * v) % n
  val u2 = (r * v) % n
  var u1_G = G
  for (_ <- 1 until u1.intValue) {
    u1_G = addPoints(u1_G, G, a, p)
  }

  var u2_Q = Q
  for (_ <- 1 until u2.intValue) {
    u2_Q = addPoints(u2_Q, Q, a, p)
  }

  val X = addPoints(u1_G, u2_Q, a, p)

  if (r == X._1 % n) {
    println("Signature is valid")
  } else {
    println("Signature is invalid")
  }
}
