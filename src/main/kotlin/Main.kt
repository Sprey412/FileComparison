import java.io.File

fun main() {
    println("📂 Введите путь к первому текстовому файлу:")
    val file1Path = readln().trim()

    println("📂 Введите путь ко второму текстовому файлу:")
    val file2Path = readln().trim()

    val file1 = File(file1Path)
    val file2 = File(file2Path)

    if (!file1.exists() || !file2.exists()) {
        println("❌ Ошибка: Один или оба файла не найдены!")
        return
    }

    val comparator = FileComparator(file1, file2)

    while (true) {
        println("\n🔍 Выберите действие:")
        println("1 - Найти уникальные строки")
        println("2 - Найти различающиеся строки")
        println("3 - Сохранить результаты в файл")
        println("0 - Выйти")

        when (readln().trim()) {
            "1" -> {
                val (unique1, unique2) = comparator.findUniqueLines()
                println("\n🔹 Уникальные строки в первом файле:")
                unique1.forEach { println(it) }
                println("\n🔹 Уникальные строки во втором файле:")
                unique2.forEach { println(it) }
            }
            "2" -> {
                val differences = comparator.findDifferentLines()
                println("\n🔹 Различающиеся строки:")
                differences.forEach { println(it) }
            }
            "3" -> {
                println("📁 Введите каталог для сохранения результата:")
                val outputDir = readln().trim()
                val outputFolder = File(outputDir)
                if (!outputFolder.exists()) outputFolder.mkdirs()
                comparator.saveResults(outputDir)
            }
            "0" -> {
                println("👋 Выход из программы.")
                return
            }
            else -> println("⚠ Некорректный ввод, попробуйте снова.")
        }
    }
}
