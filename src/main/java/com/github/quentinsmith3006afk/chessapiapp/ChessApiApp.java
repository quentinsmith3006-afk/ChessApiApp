package com.github.quentinsmith3006afk.chessapiapp;

import javafx.application.Application;
import javafx.stage.Stage;
import qchess.chess.create.Team;
import qchess.chess.logic.ChessBoard;
import qchess.chess.logic.ChessPosition;

public class ChessApiApp extends Application {
    private ChessBoard board;
    @Override
    public void start(Stage stage) {
        board = ChessBoard.newBuilder().emptyChessBoard().build();
        
        System.out.println(this.getFen());
        
    }
    public String getFen() {
        ChessPosition[] list = board.getChessPositions();
        String fen = "";
        int space = 0;
        for (ChessPosition square : list) {
            if (square.getChessPiece() == null) {
                space++;
                if (square.getCoordinate().getCol() == 7) {
                    fen = fen + space;
                    space = 0;
                    if (square.getCoordinate().getRow() != 7) {
                        fen = fen + "/";
                    }
                }
                continue;
            } else if (space > 0) {
                fen = fen + space;
                space = 0;
            }

            String add = "";

            switch (square.getChessPiece().getName()) {
                case "Pawn":
                    add = "P";
                    break;
                case "Rook":
                    add = "R";
                    break;
                case "Knight":
                    add = "N";
                    break;
                case "Bishop":
                    add = "B";
                    break;
                case "Queen":
                    add = "Q";
                    break;
                case "King":
                    add = "K";
                    break;
            }

            if (square.getChessPiece().getTeam() == Team.BLACK) {
                add = add.toLowerCase();
            }
            fen = fen + add;

            if (square.getChessPiece().getCoordinate().getCol() == 7 && square.getChessPiece().getCoordinate().getRow() != 7) {
                fen = fen + "/";
            }
            
        }
        return fen;
    }
}
