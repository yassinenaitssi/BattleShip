package jeu
import scala.annotation.tailrec


object Ia1VSIa2 {

var myBoard = Util.createGrid(Nil , 0)
       


    var bateaux = Array(Ship("CARRIER" , 5) , Ship("BATTLESHIP" , 4) ,Ship("CRUISER" , 3), Ship("SUBMARINE" , 3), Ship("DESTROYER" , 2))
// function that manage each round of the game
    def gamePlay(player: IA , player1: IA ,gameState :GameState) : Int =
    {
        var playerBoard = player.board
        var targetBoard = player1.board
        var replay = 0
        var posX = 0
        var posY = 0
        if (Util.testGrid(playerBoard) == 0)
        {
            return player1.number
        }
        else{
        if(gameState.playerActif == 1)
        {
        posX = BattlePlaceIA.checkValidLigneIA()
        posY = BattlePlaceIA.checkValidColumnIA()
        if(Util.testTir(targetBoard, posX, posY) == "Already" )
        {
            posX = 0
            posY = 0
            replay = 1
        }
        }
        else
        {
        posX = BattlePlaceIA.checkValidLigneIA()
        posY = BattlePlaceIA.checkValidColumnIA()
        }
        if(replay == 1)
        {
          gamePlay(IA(1,Util.placeTir(targetBoard, posX, posY), 0) , player1 ,GameState(1,0) )            
        }
        else
        {
        if (gameState.playerActif == 0)
        {

        gamePlay(IA(1,Util.placeTir(targetBoard, posX, posY), 0) , player ,GameState(1,0) )    
        }
        else
        {
        gamePlay(IA(0,Util.placeTir(targetBoard, posX, posY), 0) , player ,GameState(0,0) )  
        }            
        }
            
        }


    } 
// function that manage the game
    def ia1VSia2(round: Int, totalRound: Int, gameState: GameState, begin: Int, score0 : Int, score1: Int ) : Int =  {
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
        var myPlayer = BattlePlaceIA.createGridIA(player, bateaux)
        var myPlayer1 = BattlePlaceIA.createGridIA(p2 , bateaux)
        var winner = gamePlay(myPlayer, myPlayer1 ,GameState(0,0))
        if(winner == 0 )
        {

        ia1VSia2(round + 1 , tr, GameState(0,0) , 1 , score0 + 10 , score1 )            
        
        }
        else
        {

        ia1VSia2(round + 1 , tr, GameState(0,0) , 1 , score0 , score1 + 10 )   
        
        }
        }
        else
        {
            Proof.recorderIAResult("AI1 ;"  + score0 + "; AI2 ;" + score1)
            Proof.endRecord()
            if(score1 < score0)
            {
                println("======================")
                println("Match winner is IA1")
                println("IA1 score is:" + score0)
                println("IA2 score is:" + score1)
                println("THE END")
                println("======================")
                return 1
            }
            else
            {
                if(score1 > score0)
                {
                println("======================")
                println("Match winner is IA2")
                println("IA1 score is:" + score0)
                println("IA2 score is:" + score1)
                println("THE END")
                println("======================")
                return 0
    
                }
                else
                {
                println("======================")
                println("Draw")
                println("IA1 score is:" + score0)
                println("IA2 score is:" + score1)
                println("THE END")
                println("======================")
                return 2    
                }
                
            }
            
        }
        


    }
    
    }

