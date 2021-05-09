package com.pedsf.cardgame.view;

import com.pedsf.cardgame.controller.GameController;

import java.util.ArrayList;
import java.util.List;

public class GameViewables implements GameViewable {

   List<GameViewable> views;

   public GameViewables () {

      views = new ArrayList<GameViewable>();

   }

   public void addViewable (GameViewable view) {

      views.add(view);

   }

   @Override
   public void setController(GameController gameController) {
      for(GameViewable gv : views) {
         gv.setController(gameController);
      }
   }

   @Override
   public void promptForPlayerName() {
      for(GameViewable gv : views) {
         gv.promptForPlayerName();
      }
   }

   @Override
   public void promptForFlip() {
      for(GameViewable gv : views) {
         gv.promptForFlip();
      }
   }

   @Override
   public void promptForNewGame() {
      for(GameViewable gv : views) {
         gv.promptForNewGame();
      }
   }

   @Override
   public void showWinner(String playerName) {
      for(GameViewable gv : views) {
         gv.showWinner(playerName);
      }
   }

   @Override
   public void showPlayerName(int playerIndex, String playerName) {
      for (GameViewable gv : views) {
         gv.showPlayerName(playerIndex, playerName);
      }
   }

   @Override
   public void showFaceDownCardForPlayer(int playerIndex, String playerName) {
      for (GameViewable gv : views) {
         gv.showFaceDownCardForPlayer(playerIndex, playerName);
      }
   }

   @Override
   public void showCardForPlayer(int index, String playerName, String rank, String suit) {
      for (GameViewable gv : views) {
         gv.showCardForPlayer(index, playerName, rank, suit);
      }
   }
}