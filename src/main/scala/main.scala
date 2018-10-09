package jeu
import scala.annotation.tailrec
// the class that helps to change the actif player
case class GameState(playerActif: Int, fin: Int)

object main extends App {

    var gameState = new GameState(0,0)
    mainLoop(gameState)

    def mainLoop(gameState :GameState) {
        
        
        println("1 - Player VS Player:")
        println("2 - Player VS IA1:")
        println("3 - Player VS IA2:")
        println("4 - Player VS IA3:")
        println("5 - IA1 VS IA2:")
        println("6 - IA1 VS IA3:")
        println("7 - IA2 VS IA3:")
        println(" ")
        println("Select game mode:")
        var mode = Util.getUserInput()
        mode match {
            case "1" => var game = HumanVsHuman.playerVSplayer(1, 0 , gameState, 0, 0, 0)
            case "2" => var game = HumanVSIA1.playerVSIA(1, 0 , gameState, 0, 0, 0)
            case "3" => var game = HumanVSIA2.playerVSIA(1, 0 , gameState, 0, 0, 0)
            case "4" => var game = HumanVSIA3.playerVSIA(1, 0 , gameState, 0, 0, 0) 
            case "5" => var game = Ia1VSIa2.ia1VSia2(1, 0 , gameState, 0, 0, 0)
            case "6" => var game = Ia1VSIa3.ia1VSia3(1, 0 , gameState, 0, 0, 0)
            case "7" => var game = Ia2VSIa3.ia2VSia3(1, 0 , gameState, 0, 0, 0)
              

        }
        }
    }

