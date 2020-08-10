import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.text.PDFTextStripper
import java.io.File
import java.net.URL

fun main() {
    val url = URL("https://kotlinlang.org/docs/kotlin-docs.pdf")
    val path = "G:\\Il mio Drive\\Kotlin projects\\PDF word counter\\words.txt"

    val bytes = url.readBytes()
    val file = File(path)

    var writeOn = File("counter.txt")
    file.forEachLine {
        writeOn.writeText("$it - ${countWords(bytes, it)}")
    }
}

fun countWords(file: ByteArray, word: String): Int {
    val doc = PDDocument.load(file)
    val stripper = PDFTextStripper()
    val text = stripper.getText(doc)

    val allWords = text.trim().split("\\s".toRegex())
    var count = 0
    for (each: String in allWords) {
        val aux = each.filter { it.isLetter() }.toLowerCase()
        if (aux == word)
            count++
    }
    return count
}