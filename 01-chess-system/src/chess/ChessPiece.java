package chess;

import boardgame.*;

public abstract class ChessPiece extends Piece {
    
    private Color color;
    
    public ChessPiece(Board board, Color color) {
        super(board);
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
    
    protected boolean isThereOpponentPiece(Position pos) {
        ChessPiece p = (ChessPiece)getBoard().piece(pos);
        return p != null && p.getColor() != color;
    }
    
}
