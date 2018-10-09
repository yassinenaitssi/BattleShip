package jeu
import scala.annotation.tailrec
import scala.util.Random





object BattlePlaceIA {

    var myBoard = Util.createGrid(Nil , 0)
//check valid ligne
    def checkValidLigneIA(): Int = {
                val r = new scala.util.Random
                var x = r.nextInt(11)
                if(Util.ligneNumber(x.toString) == 0)
                {   
                    checkValidLigneIA()
                }
                else 
                {
                return x    
    }           }

    def checkValidColumnSIA(num: Int,target: Set[(Int,Int)]): Int = {
                if(Util.ligneNumber(num.toString) == 0)
                {   
                            
                       checkValidColumnSIA(target.head._1, target.tail)
                }
                else{
                return num    
                }
    }  
    def checkValidLigneSIA(num: Int,target: Set[(Int,Int)]): Int = {
                if(Util.ligneNumber(num.toString) == 0)
                {   
                            
                       checkValidLigneSIA(target.head._2, target.tail)
                }
                else{
                return num  
                }
    }
    def checkValidColumnIA(): Int = {
                val r = new scala.util.Random
                var x = r.nextInt(11)
                if(Util.ligneNumber(x.toString) == 0)
                {   
                   
                       checkValidColumnIA()
                }
                else{
                return x    
                }
    }            

    def checkValidDir(): String = {
                val r = new scala.util.Random
                var x = r.nextInt(2)
                if(x == 1)
                {   
                     return "V"
                }
                else{
                return "H"    
                }
    }
    var bateaux = Array(Ship("CARRIER" , 5) , Ship("BATTLESHIP" , 4) ,Ship("CRUISER" , 3), Ship("SUBMARINE" , 3), Ship("DESTROYER" , 2))
   @tailrec
   def createGridIA(ia: IA , bateaux: Array[Ship]) : IA = {
        var b= bateaux.toBuffer
        if(b.isEmpty)
        {
         return ia 
        }
        var playerBoard = ia.board
        var posX = checkValidLigneIA()
        var posY = checkValidColumnIA()
        var taille = bateaux(0).taille
        var dirChoose = checkValidDir()
        b.remove(0)
        if (Util.testPlace(playerBoard, taille, posX, posY, dirChoose) == 1)
        {
            taille = 0
            posX = 0
            posY = 0
            dirChoose = "V"
            b = bateaux.toBuffer
        }
        createGridIA(IA(0,Util.placeBateau(playerBoard, taille, posX, posY, dirChoose), 0), b.toArray) 


   }
      def createGridSIA(ia: SIA , bateaux: Array[Ship]) : SIA = {
        var b= bateaux.toBuffer
        if(b.isEmpty)
        {
         return ia 
        }
        var playerBoard = ia.board
        var posX = checkValidLigneIA()
        var posY = checkValidColumnIA()
        var taille = bateaux(0).taille
        var dirChoose = checkValidDir()
        b.remove(0)
        if (Util.testPlace(playerBoard, taille, posX, posY, dirChoose) == 1)
        {
            taille = 0
            posX = 0
            posY = 0
            dirChoose = "V"
            b = bateaux.toBuffer
        }
        createGridSIA(SIA(0,Util.placeBateau(playerBoard, taille, posX, posY, dirChoose), 0, Set()), b.toArray) 


   }
      def createGridHuman(ia: IA , bateaux: Array[Ship]) : IA = {
        var b= bateaux.toBuffer
        if(b.isEmpty)
        {
         return ia 
        }
        var playerBoard = ia.board
        Util.showGrid(playerBoard)
      println(" ")
        var shipChoose = bateaux(0).name
        var taille = bateaux(0).taille
        println("Place Your " + shipChoose + " of size " + taille + " : " )
        Util.showPrompt2()
        var posX = BattlePlace.checkValidLigne()
        Util.showPrompt3
        var posY = BattlePlace.checkValidColumn()
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
        createGridHuman(IA(0,Util.placeBateau(playerBoard, taille, posX, posY, dirChoose), 1), b.toArray) 


   }

         def createGridHumanS(ia: SIA , bateaux: Array[Ship]) : SIA = {
        var b= bateaux.toBuffer
        if(b.isEmpty)
        {
         return ia 
        }
        var playerBoard = ia.board
        Util.showGrid(playerBoard)
      println(" ")
        var shipChoose = bateaux(0).name
        var taille = bateaux(0).taille
        println("Place Your " + shipChoose + " of size " + taille + " : " )
        Util.showPrompt2()
        var posX = BattlePlace.checkValidLigne()
        Util.showPrompt3
        var posY = BattlePlace.checkValidColumn()
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
        createGridHumanS(SIA(0,Util.placeBateau(playerBoard, taille, posX, posY, dirChoose), 1, Set()), b.toArray) 


   }
}

