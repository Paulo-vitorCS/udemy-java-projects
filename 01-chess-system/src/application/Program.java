package application;

import boardgame.*;
import chess.*;

public class Program {

    public static void main(String[] args) {
        
        ChessMatch chessMatch = new ChessMatch();
        UI.printBoard(chessMatch.getPieces());
        //System.out.println(pos);
        
    }
    
}
