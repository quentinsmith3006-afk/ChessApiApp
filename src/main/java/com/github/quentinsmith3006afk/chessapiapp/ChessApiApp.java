package com.github.quentinsmith3006afk.chessapiapp;

import javafx.application.Application;
import javafx.stage.Stage;
import qchess.chess.logic.ChessBoard;

public class ChessApiApp extends Application {
    @Override
    public void start(Stage stage) {
        ChessBoard chessBoard = ChessBoard.newBuilder()
                .normalChessBoard()
                .build();

        chessBoard.getChessPositions();
    }
}
