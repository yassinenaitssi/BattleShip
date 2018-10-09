package jeu
import scala.annotation.tailrec


object Ia1VSIa3 {

var myBoard = Util.createGrid(Nil , 0)
       


    var bateaux = Array(Ship("CARRIER" , 5) , Ship("BATTLESHIP" , 4) ,Ship("CRUISER" , 3), Ship("SUBMARINE" , 3), Ship("DESTROYER" , 2))
    // function that manage each round of the game
    def gamePlay(player: SIA , player1: SIA ,gameState :GameState) : Int =
    {
        var playerBoard = player.board
        var targetBoard = player1.board
        var gt = player.goodTarget
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
        gt = player1.goodTarget
        posX = BattlePlaceIA.checkValidLigneIA()
        posY = BattlePlaceIA.checkValidColumnIA()
        gamePlay(SIA(1,Util.placeTir(targetBoard, posX, posY), 0,gt) , player ,GameState(1,0) )
        }
        else
        {
        gt = player.goodTarget    
        if(gt == Set())
        {
        posX = BattlePlaceIA.checkValidLigneIA()
        posY = BattlePlaceIA.checkValidColumnIA() 
        if(Util.testTir(targetBoard, posX, posY) == "Touche" )
        {

                gt = player.goodTarget ++ Set((posX-1 , posY),(posX+1 , posY),(posX , posY+1),(posX , posY-1) )
                gamePlay(SIA(0,Util.placeTir(targetBoard, posX, posY), 1, Set()) , SIA(1,playerBoard, 0, gt)  ,GameState(0,0))
        }
        else
        {
        gamePlay(SIA(0,Util.placeTir(targetBoard, posX, posY), 1, Set()) , SIA(1,playerBoard, 0, gt)  ,GameState(0,0))    
        }

        }
        else
        {    
        posX = BattlePlaceIA.checkValidLigneSIA(player.goodTarget.head._1 , player.goodTarget)
        posY = BattlePlaceIA.checkValidColumnSIA(player.goodTarget.head._2, player.goodTarget)
        if(Util.testTir(targetBoard, posX, posY) == "Already" )
        {
                gamePlay(SIA(1,playerBoard, 0, gt.tail) , player1 , gameState)
        }
        else
        {
        if(Util.testTir(targetBoard, posX, posY) == "Touche" )
        {

                gt = player.goodTarget ++ Set((posX-1 , posY),(posX+1 , posY),(posX , posY+1),(posX , posY-1) ) 
                gamePlay(SIA(0,Util.placeTir(targetBoard, posX, posY), 1, Set()) , SIA(1,playerBoard, 0, gt)  ,GameState(0,0))
        }
        else
        {
        gamePlay(SIA(0,Util.placeTir(targetBoard, posX, posY), 1, Set()) , SIA(1,playerBoard, 0, gt.tail)  ,GameState(0,0))    
                    
        }
            
        }
        

        }
        
        
               
         
        
        }



    } 
// function that manage the game
    def ia1VSia3(round: Int, totalRound: Int, gameState: GameState, begin: Int, score0 : Int, score1: Int ) : Int =  {
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
        var player = new SIA(0,  myBoard, 1, Set())
        var p2 = new SIA(1,  myBoard, 0, Set()) 
        var myPlayer = BattlePlaceIA.createGridSIA(player, bateaux)
        var myPlayer1 = BattlePlaceIA.createGridSIA(p2 , bateaux)
        println("ROUND IS " + round  + " OF " + tr)
        var winner = gamePlay(myPlayer, myPlayer1 ,GameState(0,0))
        if(winner == 0 )
        {

        ia1VSia3(round + 1 , tr, GameState(0,0) , 1 , score0 + 10 , score1 )            
        
        }
        else
        {

        ia1VSia3(round + 1 , tr, GameState(0,0) , 1 , score0 , score1 + 10 )   
        
        }
        }
        else
        {
            Proof.recorderIAResult("AI1 ;"  + score0 + "; AI3 ;" + score1)
            Proof.endRecord()
            if(score1 < score0)
            {

                println("======================")
                println("Match winner is number 0")
                println("IA1 score is:" + score0)
                println("IA3 score is:" + score1)
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
                println("IA1 score is:" + score0)
                println("IA3 score is:" + score1)
                println("THE END")
                println("======================")
                return 0
    
                }
                else
                {
                println("======================")
                println("Draw")
                println("IA1 score is:" + score0)
                println("IA3 score is:" + score1)
                println("THE END")
                println("======================")
                return 2    
                }
                
            }
            
        }
        

    }
    
    }

