package com.pedsf.cardgame.controller;

import com.pedsf.cardgame.games.GameEvaluator;
import com.pedsf.cardgame.model.*;
import com.pedsf.cardgame.view.GameViewable;

import java.util.ArrayList;
import java.util.List;


public class GameController {


   enum GameState {
      ADDING_PLAYERS, CARDS_DEALT, WINNER_REVEALED
   }

   Deck deck;
   GameViewable gameViewable;
   GameEvaluator gameEvaluator;
   List<IPlayer> Players;
   IPlayer winner;
   GameState gameState;

   public GameController(Deck deck, GameViewable gameViewable, GameEvaluator gameEvaluator) {
      super();
      this.deck = deck;
      this.gameViewable = gameViewable;
      this.gameEvaluator = gameEvaluator;
      this.Players = new ArrayList<>();
      this.gameState = GameState.ADDING_PLAYERS;
      gameViewable.setController(this);
   }

   public void run() {
      while (gameState == GameState.ADDING_PLAYERS) {
         gameViewable.promptForPlayerName();
      }

      switch (gameState) {
         case CARDS_DEALT:
            gameViewable.promptForFlip();
            break;
         case WINNER_REVEALED:
            gameViewable.promptForNewGame();
            break;
         default:
      }
   }

   public void addPlayer(String playerName) {
      if (gameState == GameState.ADDING_PLAYERS) {
         Players.add(new Player(playerName));
         gameViewable.showPlayerName(Players.size(), playerName);
      }
   }

   public void startGame() {
      if (gameState != GameState.CARDS_DEALT) {
         deck.shuffle();
         int playerIndex = 1;
         for (IPlayer IPlayer : Players) {
            IPlayer.addCardToHand(deck.removeTopCard());
            gameViewable.showFaceDownCardForPlayer(playerIndex++, IPlayer.getName());
         }
         gameState = GameState.CARDS_DEALT;
      }
      this.run();
   }

   public void flipCards() {
      int playerIndex = 1;
      for (IPlayer IPlayer : Players) {
         PlayingCard pc = IPlayer.getCard(0);
         pc.flip();
         gameViewable.showCardForPlayer(playerIndex++, IPlayer.getName(),
               pc.getRank().toString(), pc.getSuit().toString());
      }

      evaluateWinner();
      displayWinner();
      rebuildDeck();
      gameState = GameState.WINNER_REVEALED;
      this.run();
   }

   void evaluateWinner() {
      winner = new WinningPlayer(gameEvaluator.evaluateWinner(Players));
   }

   void displayWinner() {
      gameViewable.showWinner(winner.getName());
   }

   void rebuildDeck() {
      for (IPlayer IPlayer : Players) {
         deck.returnCardToDeck(IPlayer.removeCard());
      }
   }

   void exitGame() {
      System.exit(0);
   }

   public void nextAction(String choice) {
      if(choice.equals("q")) {
         exitGame();
      } else {
         startGame();
      }
   }

}