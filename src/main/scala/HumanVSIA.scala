package jeu
import scala.annotation.tailrec

case class IA(number: Int , board:List[List[String]] , human: Int)

object HumanVSIA1 {

var myBoard = Util.createGrid(Nil , 0)
       


    var bateaux = Array(Ship("CARRIER" , 5) , Ship("BATTLESHIP" , 4) ,Ship("CRUISER" , 3), Ship("SUBMARINE" , 3), Ship("DESTROYER" , 2))
// function that manage each round of the game
    def gamePlay(player: IA , player1: IA ,gameState :GameState) : Int =
    {
        var playerBoard = player.board
        var targetBoard = player1.board
        var posX = 0
        var posY = 0
        if (Util.testGrid(playerBoard) == 0)
        {
            println("=====================")
            
            println("ROUND  Winner is :" + player1.number)
            println("=====================")
            return player1.number
        }

        if(player.human == 1)
        {
        println("It is to player number :" + player.number + " to play")
        println("playerBoard")
        Util.showGrid(playerBoard)
        println(" ")
        println("targetBoard")
        Util.showTargetGrid(targetBoard)
        println(" ")
        Util.showPrompt2
        posX = BattlePlace.checkValidLigne()
        Util.showPrompt3
        posY = BattlePlace.checkValidColumn()
        }
        else
        {
        posX = BattlePlaceIA.checkValidLigneIA()
        posY = BattlePlaceIA.checkValidColumnIA()
        }

        if (gameState.playerActif == 0)
        {

        gamePlay(IA(1,Util.placeTir(targetBoard, posX, posY), 0) , player ,GameState(1,0) )    
        }
        else
        {
            gamePlay(IA(0,Util.placeTir(targetBoard, posX, posY), 1) , player ,GameState(0,0) )  
        }
    } 
// function that manage the game

    def playerVSIA(round: Int, totalRound: Int, gameState: GameState, begin: Int, score0 : Int, score1: Int ) : Int =  {
var tr = totalRound
        if(begin == 0)
        {
        println("How much turn you want to play:")
        println(" ")
        tr = Util.getUserInput().toInt    
        }
                if(round <= tr)
        {
        var myBoard = Util.createGrid(Nil , 0)
        var player = new IA(0,  myBoard, 1)
        var p2 = new IA(1,  myBoard, 0) 
        var myPlayer = BattlePlaceIA.createGridHuman(player, bateaux)
        var myPlayer1 = BattlePlaceIA.createGridIA(p2 , bateaux)
        println("ROUND IS " + round  + " OF " + tr)
        var winner = gamePlay(myPlayer, myPlayer1 ,GameState(0,0))
        if(winner == 0 )
        {

        playerVSIA(round + 1 , tr, GameState(0,0) , 1 , score0 + 10 , score1 )            
        
        }
        else
        {

        playerVSIA(round + 1 , tr, GameState(0,0) , 1 , score0 , score1 + 10 )   
        
        }
        }
        else
        {
            if(score1 < score0)
            {
                println("======================")
                println("Match winner is number 0")
                println("THE END")
                println("======================")
                return 1
            }
            else
            {
                if(score1 > score0)
                {
                println("======================")
                println("Match winner is number 1")
                println("THE END")
                println("======================")
                return 0
    
                }
                else
                {
                println("======================")
                println("Draw")
                println("THE END")
                println("======================")
                return 2    
                }
                
            }
            
        }
        


    }
    
    }

