fun main() {
    var continiu = true
    var choice: String
    do {
        game()
        println("Хотите повторить игру? (y/n)")
        choice = readln()
        if (choice == "n") continiu = false
    }
    while (continiu)
}

fun game(){
    println("Ваш ход: \n Камень - 1 \n Ножницы - 2 \n Бумага - 3")
    val choiceGamer = readln().toInt()
    val choiceComputer = (1..3).random()
    when(choiceComputer){
        1 -> println("Камень - выбрал компьютер")
        2 -> println("Ножницы - выбрал компьютер")
        3 -> println("Бумага - выбрал компьютер")
    }
    when(choiceGamer){
        1 ->{
            when(choiceComputer) {
                1 -> {
                    println("Ничья")
                    game()
                }
                2 -> println("Камень затупляет или ломает ножницы. Победил игрок")
                3 -> println("Бумага обёртывает камень. Победил компьтер")
            }
        }
        2 ->{
            when(choiceComputer) {
                1 -> println("Камень затупляет или ломает ножницы. Победил компьтер")
                2 -> {
                    println("Ничья")
                    game()
                }
                3 -> println("Ножницы разрезают бумагу. Победил игрок")
            }
        }
        3 ->{
            when(choiceComputer) {
                1 -> println("Бумага обёртывает камень. Победил игрок")
                2 -> println("Ножницы разрезают бумагу. Победил компьтер")
                3 -> {
                    println("Ничья")
                    game()
                }
            }
        }
    }
}