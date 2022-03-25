import kotlin.random.Random

fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")


    val dices= listOf<Dice>(Dice(1),Dice(2),Dice(3),Dice(4),Dice(5),Dice(6))
    val player = Player("Player", dices)
    val jamb=Game(player)

    jamb.playGame()



}

class Dice(var ID:Int,var number:Int = 6,var locked:Boolean=false){
    fun roll(){
        number=Random.nextInt(1,6)
    }
}

class Player(var Name:String,var dices:List<Dice>){

    fun throwDice() {
        var unlockedDices=dices.filter { !it.locked }
        for(dice in dices){
            if(!dice.locked) {
                dice.roll();
            }
        }


    }


}
class Game(var player:Player){
    private fun showResult(){
        for(dice in player.dices){
            print("${dice.ID}-->${dice.number}\n")
        }
    }
    private fun lockDices(){
        println("Enter dices values u want to lock,separate with notch,or enter nothing to roll all again")
        //todo
        var input:List<Int?> = readInts()
        //println(input)

        //var unlockedDices=dices.filter { it.number==input }

        for(dice in player.dices.filter { isEqual(it.ID,input) }){
            dice.locked=true;
        }

        //var unlockedDices=dices.filter { !it.locked }


    }
    private fun readInts() = readLine()!!.split(',').map { it.toIntOrNull() }
    private fun isEqual(first: Int, second: List<Int?> ): Boolean {

        for(number in second){
            if(first==number){
                return true;
            }
        }
        return false
    }
    private fun unlockEveryDice(){
        for(dice in player.dices){
            dice.locked=false;
        }
    }
    private fun checkResults() {
        var sortedDices: MutableList<Int> = mutableListOf()
        for (dice in player.dices) {
            sortedDices.add(dice.number)

        }
        sortedDices.sort();

        println("\n");
        println(sortedDices);

        if((sortedDices.contains(1)&& sortedDices.contains(2) && sortedDices.contains(3) && sortedDices.contains(4) && sortedDices.contains(5)) || (sortedDices.contains(6)&& sortedDices.contains(2) && sortedDices.contains(3) && sortedDices.contains(4) && sortedDices.contains(5) )){
            println("SKALA!") // 5 po redu
            return;
        }
        if(sortedDices[0]==sortedDices[4] || sortedDices[1]==sortedDices[5]){
            println("JAMB!") // 5 istih
            return;
        }
        if(sortedDices[0]==sortedDices[3] || sortedDices[1]==sortedDices[4] || sortedDices[2]==sortedDices[5]){
            println("POKER!") //4 istih
            return;
        }
        if(sortedDices[0]==sortedDices[2] || sortedDices[1]==sortedDices[3] || sortedDices[2]==sortedDices[4] || sortedDices[3]==sortedDices[5]){
            println("TRIS!") //3 istih
            return;
        }


    }
    fun playGame(){
        val rounds=3;
       for(i in 1..rounds){
        player.throwDice()
        showResult()
        unlockEveryDice()
        lockDices()
        }

        checkResults()
    }


}