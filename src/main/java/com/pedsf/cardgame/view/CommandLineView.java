package com.pedsf.cardgame.view;

import com.pedsf.cardgame.controller.GameController;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

@Slf4j
public class CommandLineView implements GameViewable{

   GameController gameController;

   Scanner keyboard = new Scanner(System.in);

   public void setController(GameController gameController) {
      this.gameController = gameController;
   }

   public void promptForPlayerName() {
      log.info("Enter player name:");
      String name = keyboard.nextLine();
      if( name.isEmpty()) {
         gameController.startGame();
      } else {
         gameController.addPlayer(name);
      }
   }

   public void promptForFlip() {
      log.info("Press enter to reveal cards");
      keyboard.nextLine();
      gameController.flipCards();
   }

   public void promptForNewGame() {
      log.info("Press enter to deal again or q to exit");
      gameController.nextAction(keyboard.nextLine());

   }

   public void showWinner(String playerName) {
      log.info("Winner is {} !",playerName);
   }

   public void showPlayerName(int playerIndex, String playerName) {
      log.info("[{}][{}]", playerIndex, playerName );
   }

   public void showFaceDownCardForPlayer(int playerIndex, String playerName) {
      log.info("[{}][{}][X][X]", playerIndex, playerName );
   }

   public void showCardForPlayer(int index, String playerName, String rank, String suit) {
      log.info("[{}][{}][{}][{}]", index, playerName, rank, suit );
   }

}
