package com.pedsf.cardgame.view;

import com.pedsf.cardgame.controller.GameController;

public interface GameViewable {

   void setController(GameController gameController);
   void promptForPlayerName();
   void promptForFlip();
   void promptForNewGame();
   void showWinner(String playerName);
   void showPlayerName(int playerIndex, String playerName);
   void showFaceDownCardForPlayer(int playerIndex, String playerName);
   void showCardForPlayer(int index, String playerName, String rank, String suit);
}
