object Const {
  val BlockByteSize: Int = 16
  val BlockWordSize: Int = BlockByteSize / 4
}

type Block = Array[Char]

def splitStringIntoBlocks(string: String, partSize: Int): Array[String] = {
  val splitText = string.grouped(partSize).toArray
  for (part <- splitText.indices) {
    if (splitText(part).length < partSize) {
      splitText(part) = splitText(part).padTo(partSize, ' ')
    }
  }
  splitText
}

def unpackWordsToVariables(words: Array[Int]): (Int, Int, Int, Int) = {
  (words(0), words(1), words(2), words(3))
}

// Convert a byte array to an Int array
def bytesToWords(data: Array[Byte]): Array[Int] = {
  var padData: Array[Byte] = null
  val size = Const.BlockByteSize
  if (data.length % size != 0) {
    padData = data ++ Array.fill(size - data.length % size) { ' '.toByte }
  } else {
    padData = data
  }
  val words = new Array[Int](padData.length / 4)
  for (i <- words.indices) {
    words(i) =
      (padData(i * 4) & 0xff) |
        ((padData(i * 4 + 1) & 0xff) << 8) |
        ((padData(i * 4 + 2) & 0xff) << 16) |
        ((padData(i * 4 + 3) & 0xff) << 24)
  }
  words
}

// Convert an Int array to a byte array
def wordsToBytes(words: Array[Int]): Array[Byte] = {
  val bytes = new Array[Byte](words.length * 4)
  for (i <- words.indices) {
    bytes(i * 4) = (words(i) & 0xff).toByte
    bytes(i * 4 + 1) = ((words(i) >> 8) & 0xff).toByte
    bytes(i * 4 + 2) = ((words(i) >> 16) & 0xff).toByte
    bytes(i * 4 + 3) = ((words(i) >> 24) & 0xff).toByte
  }
  bytes
}