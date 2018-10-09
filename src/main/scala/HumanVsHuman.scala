package jeu
import scala.annotation.tailrec

object HumanVsHuman {

var myBoard = Util.createGrid(Nil , 0)
       


    var bateaux = Array(Ship("CARRIER" , 5) , Ship("BATTLESHIP" , 4) ,Ship("CRUISER" , 3), Ship("SUBMARINE" , 3), Ship("DESTROYER" , 2))

// function that manage each round of the game
    def gamePlay(player: Player , player1: Player ,gameState :GameState) : Int =
    {
        var playerBoard = player.board
        if (Util.testGrid(playerBoard) == 0)
        {
            println("=====================")
            
            println("ROUND  Winner is :" + player1.number)
            println("=====================")
            return player1.number
        }
        println("It is to :" + player.number + " to play")
        var targetBoard = player1.board
        println("playerBoard")
        println(" ")
        println(" A" + " B" + " C" + " D" + " E" + " F" +" G" + " H" + " I" + " J")
        Util.showGrid(playerBoard)
        println(" ")
        println("targetBoard")
        println(" A" + " B" + " C" + " D" + " E" + " F" +" G" + " H" + " I" + " J")
        Util.showTargetGrid(targetBoard)
        println(" ")
        println("Au joueur numero: " + gameState.playerActif + " de tirer " )
        Util.showPrompt2
        var posX = BattlePlace.checkValidLigne()
        Util.showPrompt3
        var posY = BattlePlace.checkValidColumn()
        if (gameState.playerActif == 0)
        {
            println("vous avez " + Util.testTir(playerBoard, posX, posY) + "Bateaux")
        gamePlay(Player(1,Util.placeTir(targetBoard, posX, posY)) , player ,GameState(1,0) )    
        }
        else
        {
            println("vous avez " + Util.testTir(playerBoard, posX, posY) + "Bateaux")
            gamePlay(Player(0,Util.placeTir(targetBoard, posX, posY)) , player ,GameState(0,0) )  
        }
    } 

// function that manage the game
    def playerVSplayer(round: Int , totalRound: Int ,gameState :GameState, begin: Int , score0 : Int, score1: Int) : Int =
    {
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
        var player = new Player(0,  myBoard)
        var p2 = new Player(1,  myBoard) 
        var myPlayer = BattlePlace.placeShipLoop(player , bateaux)
        var myPlayer1 = BattlePlace.placeShipLoop(p2 , bateaux)
        println("ROUND IS " + round  + " OF " + tr)
        var winner = gamePlay(myPlayer, myPlayer1 ,GameState(0,0))
        if(winner == 0 )
        {
        playerVSplayer(round + 1 , tr, GameState(0,0) , 1 , score0 + 10 , score1 )            
        }
        else
        {
        
        playerVSplayer(round + 1 , tr, GameState(0,0) , 1 , score0 , score1 + 10 )   
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

