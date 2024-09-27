
// Constants for RC6 algorithm (w = 32 bits, r = 20 rounds)
val w: Int = 32
val r: Int = 20
val Pw: Int = 0xB7E15163 // Magic constant derived from the binary expansion of e
val Qw: Int = 0x9E3779B9 // Magic constant derived from the binary expansion of the golden ratio

// Key expansion function
def keyExpansion(key: Array[Byte], S: Array[Int]): Unit = {
  val c = key.length / 4
  val L = bytesToWords(key)

  // Initialize S array
  S(0) = Pw
  for (i <- 1 until S.length) {
    S(i) = S(i - 1) + Qw
  }

  var A = 0
  var B = 0
  var i = 0
  var j = 0
  val v = 3 * Math.max(c, S.length)

  for (s <- 0 until v) {
    A = rotateLeft(S(i) + A + B, 3)
    S(i) = A
    B = rotateLeft(L(j) + A + B, A + B)
    L(j) = B
    i = (i + 1) % S.length
    j = (j + 1) % c
  }
}

// Rotation left operation
def rotateLeft(x: Int, y: Int): Int = {
  (x << (y & (w - 1))) | (x >>> (w - (y & (w - 1))))
}

// Rotation right operation
def rotateRight(x: Int, y: Int): Int = {
  (x >>> (y & (w - 1))) | (x << (w - (y & (w - 1))))
}

// Encryption function
def encrypt(word: Array[Int], S: Array[Int]): Array[Byte] = {
  var A0 = word(0); var B0 = word(1)
  var C0 = word(2); var D0 = word(3)

  B0 += S(0)
  D0 += S(1)

  // Rc6 Algorithm
  for (i <- 1 to r) {
    val t = rotateLeft(B0 * (2 * B0 + 1), 5)
    val u = rotateLeft(D0 * (2 * D0 + 1), 5)
    A0 = rotateLeft(A0 ^ t, u) + S(2 * i)
    C0 = rotateLeft(C0 ^ u, t) + S(2 * i + 1)
    val tmp = A0; A0 = B0; B0 = C0; C0 = D0; D0 = tmp
  }

  A0 += S(2 * r + 2)
  C0 += S(2 * r + 3)

  wordsToBytes(Array(A0, B0, C0, D0))
}

// Decryption function
def decrypt(word: Array[Int], S: Array[Int]): Array[Byte] = {
  var A0 = word(0); var B0 = word(1)
  var C0 = word(2); var D0 = word(3)

  C0 -= S(2 * r + 3)
  A0 -= S(2 * r + 2)

  // Rc6 Algorithm
  for (i <- r to 1 by -1) {
    val tmp = D0; D0 = C0; C0 = B0; B0 = A0; A0 = tmp
    val u = rotateLeft(D0 * (2 * D0 + 1), 5)
    val t = rotateLeft(B0 * (2 * B0 + 1), 5)
    C0 = rotateRight(C0 - S(2 * i + 1), t) ^ u
    A0 = rotateRight(A0 - S(2 * i), u) ^ t
  }

  D0 -= S(1)
  B0 -= S(0)

  wordsToBytes(Array(A0, B0, C0, D0))
}
