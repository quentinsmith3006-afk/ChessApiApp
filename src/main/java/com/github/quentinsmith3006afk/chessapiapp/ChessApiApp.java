package com.github.quentinsmith3006afk.chessapiapp;

import java.util.HashMap;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import qchess.chess.create.EnpassantInfo;
import qchess.chess.create.Team;
import qchess.chess.logic.ChessBoard;
import qchess.chess.logic.ChessPosition;
import qchess.chess.logic.MoveLogic;

public class ChessApiApp extends Application {
    private Stage stage;
    private Scene scene;

    private ChessBoard board;

    @Override
    public void init() {
        board = ChessBoard.newBuilder().normalChessBoard().build();

        board.setOnPieceMovement((event) -> System.out.println(this.boardToFen() + " " + this.getChessMetaData()));

        board.launchGame();
    }

    @Override
    public void start(Stage stage) {
        this.stage = stage;

        this.scene = new Scene(board);

        stage.setScene(scene);
        stage.show();
    }

    public String getChessMetaData() {
        StringBuilder endFen = new StringBuilder();
        boolean aTeamCanCastle = false;

        MoveLogic logic = board.getMoveLogic();

        endFen.append(board.getPlayerTeam() == Team.WHITE ? "w" : "b");
        endFen.append(" ");

        HashMap<String, Boolean> castleInformation = logic.getCastleInformation();

        if (castleInformation.containsKey("KINGSIDE WHITE")) {
            endFen.append("K");
            aTeamCanCastle = true;
        }
        if (castleInformation.containsKey("QUEENSIDE WHITE")) {
            endFen.append("Q");
            aTeamCanCastle = true;
        }
        if (castleInformation.containsKey("KINGSIDE BLACK")) {
            endFen.append("k");
            aTeamCanCastle = true;
        }
        if (castleInformation.containsKey("QUEENSIDE BLACK")) {
            endFen.append("q");
            aTeamCanCastle = true;
        }

        if (!aTeamCanCastle) {
            endFen.append("-");
        }

        endFen.append(" ");

        EnpassantInfo enpassantInfo = logic.getEnpassantInfo();
        if (enpassantInfo != null) {
            endFen.append(enpassantInfo.coordinate().getAlgebraicName());
        } else {
            endFen.append("-");
        }

        endFen.append(" ");

        endFen.append(logic.getTotalNumFullMoves() + 1);

        endFen.append(" ");



        return endFen.toString();
    }

    public String boardToFen() {
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

            // idk bro I think I led you down the wrong path
            // String add = square.getChessPiece().getName().substring(0, 1);

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
