

val alphabet = "АБВГДЕЖЗИКЛМНОПРСТУФХЦЧШЩЬЫЪЭЮЯ".toCharArray()
val table = mutableListOf<String>()
val generateTable = mutableListOf<String>()
fun encryption(choiceTable: Int){
    println("Введите слово для шифровки: ")
    var word = readln().uppercase().toCharArray()
    println("Введите вспомогательный символ: ")
    val auxilliarChar = readln().uppercase().toCharArray()
    var newWord = ""
    var wordPair = ""
    var col = 0
    var row = 0
    var ind = 0
    var confirmation = 0
    if (word.size % 2 == 1) word += auxilliarChar
    for (i in word.indices){
        if (i % 2 == 1){
            for (j in alphabet.indices){
                if (word[i-1] == 'Й'){
                    confirmation++
                    row = 8
                    ind += row * alphabet.size
                }
                if (word[i] == 'Й'){
                    confirmation++
                    col = 8
                    ind +=  col
                }
                if (word[i-1] == 'Ё'){
                    confirmation++
                    row = 5
                    ind += row * alphabet.size
                }
                if (word[i] == 'Ё'){
                    confirmation++
                    col = 5
                    ind +=  col
                }
                if (word[i-1] == alphabet[j]){
                    row = j
                    ind += row * alphabet.size
                    confirmation++
                }
                if (word[i] == alphabet[j]) {
                    col = j
                    ind +=  col
                    confirmation++
                }
                if (confirmation == 2){
                    wordPair += alphabet[row].toString() + alphabet[col].toString() + " "
                    if (choiceTable == 1) {newWord += table[ind] + " "}
                    else newWord += generateTable[ind] + " "
                    ind = 0
                    confirmation = 0
                }
            }
        }
    }
    println(wordPair)
    println(newWord)
}
fun decryption(choice: Int){
    println("Введите слово для разшифровки: ")
    val encryptText = readln().uppercase().toCharArray()
    var numberChar: Int
    var row: Int
    var col: Int
    var decryptText = ""
    if (encryptText.size % 3 == 0) {
        if(choice == 1){
            for (i in 2..encryptText.size step 3) {
                numberChar =
                    (encryptText[i - 2].toString() + encryptText[i - 1].toString() + encryptText[i].toString()).toInt() - 1
                row = numberChar / alphabet.size
                col = numberChar % alphabet.size
                decryptText += alphabet[row].toString() + alphabet[col].toString()
            }
        }
        else{
            var number: String
            for (i in 2..encryptText.size step 3){
                for (numberChar in generateTable.indices) {
                    number = encryptText[i - 2].toString() + encryptText[i - 1].toString() + encryptText[i].toString()
                    if (number == generateTable[numberChar]){
                        row = numberChar / alphabet.size
                        col = numberChar % alphabet.size
                        decryptText += alphabet[row].toString() + alphabet[col].toString()
                    }
                }
            }
        }
        println(decryptText)
    }
    else println("Вашем тексте не хватает ${encryptText.size % 3} чисел")
}
fun main() {
    var continued = true
    do {
        var fillingTable = 0
        var number: String
        val oneZero = "0"
        val doubleZero = "00"
        println("Типовая Таблица - 1\nГенерированна таблица - 2")
        val choiceTable = readln().toInt()
        //Создание и заполнение таблицы
        for (i in alphabet.indices){
            for (j in alphabet.indices){
                fillingTable++
                when(fillingTable){
                    in 1 .. 10 -> {
                        number = doubleZero + fillingTable.toString()
                        table.add(number)
                    }
                    in 11 .. 99 -> {
                        number = oneZero + fillingTable.toString()
                        table.add(number)
                    }
                    else -> {
                        number = fillingTable.toString()
                        table.add(number)
                    }
                }
            }
        }
        println("Если вы хотите новую генерированную таблицу - 1")
        val newtable = readln().toInt()
        if (choiceTable == 2 && newtable == 1) {
            for (i in table.indices)
                generateTable.add(table[i])
            generateTable.shuffle()
        }
        println("Если хотите зашифровать - 1\nЕсли хотите расшифровать - 2")
        val choice = readln().toInt()
        when(choice){
            1 -> encryption(choiceTable)
            2 -> decryption(choiceTable)
            else -> println("Вы ввели не то число")
        }
        println("Если хотите выйти нажмите - 1")
        if(readln().toInt() == 1) continued = false
    } while (continued)
}