import java.io.File

class FileComparator(private val file1: File, private val file2: File) {

    private val linesFile1 = file1.readLines()
    private val linesFile2 = file2.readLines()

    fun findUniqueLines(): Pair<List<String>, List<String>> {
        val uniqueInFile1 = linesFile1.filter { it !in linesFile2 }
        val uniqueInFile2 = linesFile2.filter { it !in linesFile1 }
        return Pair(uniqueInFile1, uniqueInFile2)
    }

    fun findDifferentLines(): List<String> {
        val differences = mutableListOf<String>()
        val maxLines = maxOf(linesFile1.size, linesFile2.size)

        for (i in 0 until maxLines) {
            val line1 = linesFile1.getOrNull(i) ?: "<нет строки>"
            val line2 = linesFile2.getOrNull(i) ?: "<нет строки>"

            if (line1 != line2) {
                differences.add("Строка ${i + 1}: Файл1 → \"$line1\", Файл2 → \"$line2\"")
            }
        }
        return differences
    }

    fun saveResults(outputDir: String) {
        val outputFile = File(outputDir, "comparison_result.txt")
        val (unique1, unique2) = findUniqueLines()
        val differentLines = findDifferentLines()

        outputFile.writeText("🔹 Уникальные строки в первом файле:\n${unique1.joinToString("\n")}\n\n")
        outputFile.appendText("🔹 Уникальные строки во втором файле:\n${unique2.joinToString("\n")}\n\n")
        outputFile.appendText("🔹 Различия по строкам:\n${differentLines.joinToString("\n")}\n")

        println("✅ Результаты сохранены в: ${outputFile.absolutePath}")
    }
}
