import java.io.PrintWriter
import scala.io.Source.fromFile
import scala.sys.exit

@main
def main(): Unit = {
  val inputFile = "resources/input.txt"
  val encodeFile = "encode.txt"
  val key = "aboba".getBytes("UTF-8")
  
  val input = fromFile(inputFile)
  val plaintext = input.mkString
  input.close()

  if (key.length < 4 ) {
    println("Key length should be at least 4")
    exit
  }
  val S = new Array[Int](2 * r + 4)

  // Key expansion
  keyExpansion(key, S)

  // Encryption
  println(s"Converting $plaintext".take(100) + "...")
  val encrypted = encodeBlocksWithPcbc(plaintext.getBytes("UTF-8"), S, S)
  val encryptedStr = encrypted.map("%02x".format(_)).mkString
  println("Encrypted: " + encryptedStr.take(100) + "...")

  val writer = new PrintWriter(encodeFile)
  writer.write(encryptedStr)
  writer.close()

  // Decryption
  val decrypted = decodeBlocksWithPcbc(encrypted, S, S)
  println("Decrypted: " + new String(decrypted, "UTF-8").take(100) + "...")
}