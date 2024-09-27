def xorWords(word1: Array[Int], word2: Array[Int]): Array[Int] = {
  word1.zip(word2).map((a, b) => a ^ b)
}

def encodeBlocksWithPcbc(plaintext: Array[Byte], S: Array[Int], initVector: Array[Int]): Array[Byte] = {
  val words = bytesToWords(plaintext)
  val wordPacks = words.grouped(Const.BlockWordSize).toArray
  var vector = initVector
  var encryptedData = Array[Byte]()
  for (wordPack <- wordPacks) {
    // XOR the plaintext block with the previous ciphertext (or the IV for the first block).
    val toEncrypt = xorWords(vector, wordPack)
    // Encrypt the result using a block cipher.
    val encrypted = encrypt(toEncrypt, S)
    // The output is the ciphertext block.
    encryptedData = encryptedData ++ encrypted
    // The next block's chaining depends on the XOR of the current plaintext and ciphertext.
    vector = xorWords(bytesToWords(encrypted), wordPack)
  }
  encryptedData
}

def decodeBlocksWithPcbc(plaintext: Array[Byte], S: Array[Int], initVector: Array[Int]): Array[Byte] = {
  val words = bytesToWords(plaintext)
  val wordPacks = words.grouped(Const.BlockWordSize).toArray
  var vector = initVector
  var encryptedData = Array[Byte]()
  for (wordPack <- wordPacks) {
    // Decrypt the ciphertext block using the block cipher.
    val decrypted = decrypt(wordPack, S)
    // XOR the decrypted result with the previous ciphertext (or the IV for the first block).
    val toDecrypted = xorWords(vector, bytesToWords(decrypted))
    // The result is the plaintext block.
    encryptedData = encryptedData ++ wordsToBytes(toDecrypted)
    // As with encryption, propagate chaining using the XOR of the plaintext and ciphertext.
    vector = xorWords(toDecrypted, wordPack)
  }
  encryptedData
}