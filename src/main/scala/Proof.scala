package jeu

import java.io.PrintWriter
import java.io.File

object Proof{
	
	var writer = new PrintWriter(new File("proof.csv"))
	def recorderIAResult(s: String): Unit = writer.write(s) 
	def endRecord(): Unit = writer.close() 
}