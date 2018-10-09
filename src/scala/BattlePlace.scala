package jeu
import scala.annotation.tailrec
import scala.util.Random

case class Ship(name: String , taille: Int)
case class Player(number: Int , board:List[List[String]]){

}

object BattlePlace {



    var myBoard = Util.createGrid(Nil , 0)

    def checkValidLigne(): Int = {
                var x = Util.getUserInput()
                if(Util.ligneNumber(x) == 0)
                {   
                    println("Ligne number invalid give a new number please :")
                    checkValidLigne()
                }
                else 
                {
                Util.ligneNumber(x)    
                }
                

    }

        def checkValidColumn(): Int = {
                var x = Util.getUserInput()
                if(Util.columnNumber(x) == 0)
                {   
                     println("Column number invalid give a new number please :")
                       checkValidColumn()
                }
                else{
                Util.columnNumber(x)    
                }
    }

    var bateaux = Array(Ship("CARRIER" , 5) , Ship("BATTLESHIP" , 4) ,Ship("CRUISER" , 3), Ship("SUBMARINE" , 3), Ship("DESTROYER" , 2))
    var player = new Player(0, myBoard)

    @tailrec
    def placeShipLoop(player: Player , bateaux: Array[Ship]) : Player = {
        var b= bateaux.toBuffer
        if(b.isEmpty)
        {
         return player 
        }
        var playerBoard = player.board
        println(" A" + " B" + " C" + " D" + " E" + " F" +" G" + " H" + " I" + " J")
        Util.showGrid(playerBoard)
      println(" ")
        var shipChoose = bateaux(0).name
        var taille = bateaux(0).taille
        println("Place Your " + shipChoose + " of size " + taille + " : " )
        Util.showPrompt2()
        var posX = checkValidLigne()
        Util.showPrompt3
        var posY = checkValidColumn()
        Util.showPrompt4
        var dirChoose = Util.getUserInput()
        b.remove(0)
        if (Util.testPlace(playerBoard, taille, posX, posY, dirChoose) == 1)
        {
            taille = 0
            posX = 0
            posY = 0
            dirChoose = "V"
            b = bateaux.toBuffer
            println("Place already taken")
        } 
        placeShipLoop(Player(0,Util.placeBateau(playerBoard, taille, posX, posY, dirChoose)), b.toArray)


        
    }


}

