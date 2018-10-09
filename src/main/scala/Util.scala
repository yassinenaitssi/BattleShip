package jeu
import scala.annotation.tailrec
import scala.util.Random


object Util {
    // function showPrompt2 displays a message that solicits the user to enter the ligne number
    def showPrompt2(): Unit = { print("\nSelect ligne (1) (2) (3) (4) (5) (6) (7) (8) (9) (10) : \n") }
    // function showPrompt3 displays a message that solicits the user to enter the column number
    def showPrompt3(): Unit = { print("\nSelect column (A) (B) (C) (D) (E) (F) (G) (H) (I) (J) : \n") }
    // function showPrompt4 displays a message that solicits the user to enter the direction of the ship
    def showPrompt4(): Unit = { print("\n(V)ertical or (H)orizontal \n") } 

    // function that transforms the input of users to a number
    def columnNumber(posXChoose: String): Int = posXChoose match {
        case "A" => 1
        case "B" => 2
        case "C" => 3
        case "D" => 4
        case "E" => 5
        case "F" => 6
        case "G" => 7
        case "H" => 8
        case "I" => 9
        case "J" => 10
        case _ => 0
    }
    // function that transforms the input of users to a number
    def ligneNumber(posYChoose: String): Int = posYChoose match {
        case "1" => 1
        case "2" => 2
        case "3" => 3
        case "4" => 4
        case "5" => 5
        case "6" => 6
        case "7" => 7
        case "8" => 8
        case "9" => 9
        case "10" => 10
        case _ => 0
    }

    def getUserInput(): String = readLine.trim.toUpperCase

    var myBoard = createGrid(Nil , 0)
    // function that create each row on the grid the first row is created in create grid
    // the first element of each row is dedied to the ligne number
    def createRow( l: List[String] , i : Int, ind: Int) : List[String] = 
    {
                if(i != 11)
                {
                    if(i == 0)
                    {
                     var r = ind + 1
                     createRow(l:+r.toString(), i +1, ind)
                    }
                    else
                    {
                    createRow(l:+".", i +1, ind)    
                    }
                        
                    
                }
                else 
                {
                    return l
                }
    }
    // function that create the grid it calls createRow    
    def createGrid(g: List[List[String]] , ind : Int) : List[List[String]] = 
    {
        var alphabet = List(List(" ", "A","B", "C","D", "E","F", "G","H", "I","J"))
      if (ind <= 9)
      {
        if (ind == 0)
        {
        createGrid( alphabet:+createRow(Nil , 0 , ind) , ind + 1 )    
        }
        else
        {
        createGrid( g:+createRow(Nil , 0, ind) , ind + 1 )    
        }
        
      }
      else
      {
        return g
      }
    }
         // function that test the element of the row to check if their is a shîp or not
                 def testRow(r: List[String]) : Int = 
    {
            var e = 0 
            if (r != Nil)
            {
                if(r.head == "|")
                {
                return 1    
                }
                else
                {
                testRow(r.tail)  
                }
                
                
            }
            else
            {
                return e
            }
    }
    //  function that camouflages the opponent board
            def renderTargetRow(r: List[String])
    {
            if (r != Nil)
            {
                if(r.head == "|")
                {
                print(".")    
                }
                else
                {
                print(r.head)    
                }
                
                renderTargetRow(r.tail)
            }
    }
    //  function that displays the opponent board
    def showTargetGrid (l : List[List[String]]){
        if (l != Nil)
        {
            renderTargetRow(l.head)
            println(" ")
            showTargetGrid(l.tail)

        }
    }
        //  function that test the grid to see if the game it is end or not yet
    def testGrid (l : List[List[String]]) : Int = {
        var end = 0
        var e1 = 0
        if (l != Nil)
        {
            end = testRow(l.head)
            e1 = testGrid(l.tail)

        }
        if ( e1 == 1 || end == 1)
        {
            return 1    
        }
        else
        {
            return 0
        }
    }
    //  function that displays each row of the player grid
    def renderRow(r: List[String])
    {

            if (r != Nil)
            {
                print(r.head)
                renderRow(r.tail)
            }
    }
    //  function that displays the player grid
    def showGrid (l : List[List[String]]){

        if (l != Nil)
        {
            renderRow(l.head)
            println(" ")
            showGrid(l.tail)

        }
    }
        //  function that test if the column number is valable to place a ship
    def checkEmptyColumn(playerBoard: List[List[String]] , taille: Int, posXChoose: Int, posYChoose: Int, index: Int) : Int =
    {
       try { 
        if(index <= posXChoose + taille - 1)
        {
            if(playerBoard(index)(posYChoose) == "|")
            {
                return 1
                
            }
            else 
            {
                checkEmptyColumn(playerBoard , taille , posXChoose, posYChoose,index + 1)
            }
        }
        else
        {
        return 0            
        } 
       } catch {     

         case e: Exception =>  1
       }
        

    }
        //  function that test if the ligne number is valable to place a ship
        def checkEmptyLigne(playerBoard: List[List[String]] , taille: Int, posXChoose: Int, posYChoose: Int, index: Int) : Int =
    {
        try {
        
        if(index <= posYChoose + taille - 1)
        {
            if(playerBoard(posXChoose)(index) == "|")
            {
                return 1                
            }
            else 
            {
                checkEmptyLigne(playerBoard , taille , posXChoose, posYChoose ,index + 1)
            }
        }
        else
        {
        return 0    
        }

        }
        catch {
            case e: Exception =>  1
        }
        
    }
            //  function that test if the ship could be placed in the place choose
                def testPlace(playerBoard: List[List[String]] , taille: Int, posXChoose: Int, posYChoose: Int, dirChoose: String): Int =
    {
        var index = posXChoose
        var index1 = posYChoose

            var empty = 0
            if (dirChoose == "H")
            {
                   
                   empty = checkEmptyLigne(playerBoard , taille , posXChoose,posYChoose , index1)
            }
            else {

                   
                   empty = checkEmptyColumn(playerBoard , taille , posXChoose,posYChoose ,index)
                   
            }
            return empty
    }
    //  function that place a ship horizental
    def placeHorizentalShip(playerBoard: List[List[String]] , taille: Int, posXChoose: Int, posYChoose: Int, index: Int) : List[List[String]] =
    {
        if(index <= posYChoose + taille - 1)
        {
           placeHorizentalShip(playerBoard.patch(posXChoose , Seq(playerBoard(posXChoose).patch(index,Seq("|"),1)),1) , taille , posXChoose,posYChoose ,index + 1)
        }
        else
        {
        return playerBoard    
        }
        
    }
    // function that place a ship vertically
        def placeVerticalShip(playerBoard: List[List[String]] , taille: Int, posXChoose: Int, posYChoose: Int, index: Int) : List[List[String]] =
    {
        if(index <= posXChoose + taille - 1)
        {
           placeVerticalShip(playerBoard.patch(index , Seq(playerBoard(index).patch(posYChoose,Seq("|"),1)),1) , taille , posXChoose,posYChoose ,index + 1)
        }
        else
        {
        return playerBoard    
        }
    }
        //    function that place a ship  

            def placeBateau(playerBoard: List[List[String]] , taille: Int, posXChoose: Int, posYChoose: Int, dirChoose: String): List[List[String]] =
    {
            var index = posXChoose
            var index1 = posYChoose

            if (dirChoose == "H")
            {
                   return placeHorizentalShip(playerBoard , taille , posXChoose,posYChoose ,index1)
            }
            else {
                   return placeVerticalShip(playerBoard , taille , posXChoose,posYChoose ,index)
            }
    }
    // function that place a shoot
        def placeTir(playerBoard: List[List[String]] ,posXChoose: Int, posYChoose: Int): List[List[String]] =
    {

                    testTir(playerBoard, posXChoose, posYChoose) match {
                        case "Touche" => playerBoard.patch(posXChoose , Seq(playerBoard(posXChoose).patch(posYChoose,Seq("O"),1)),1)
                        case "Rate" => playerBoard.patch(posXChoose , Seq(playerBoard(posXChoose).patch(posYChoose,Seq("X"),1)),1) 
                        case _ => playerBoard
                    }
                    
                    
    } 
    // function that test if the shoot was successful or not
            def testTir(playerBoard: List[List[String]] , posXChoose: Int, posYChoose: Int): String = playerBoard(posXChoose)(posYChoose) match {
                    case "." => "Rate"
                    case "|" =>  "Touche"
                    case _ => "Already"
                    
            }
}

