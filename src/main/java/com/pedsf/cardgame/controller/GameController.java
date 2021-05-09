package com.pedsf.cardgame.controller;

import com.pedsf.cardgame.games.GameEvaluator;
import com.pedsf.cardgame.model.Deck;
import com.pedsf.cardgame.model.Player;
import com.pedsf.cardgame.model.PlayingCard;
import com.pedsf.cardgame.view.CommandLineView;

import java.util.ArrayList;
import java.util.List;


public class GameController {


   enum GameState {
      ADDING_PLAYERS, CARDS_DEALT, WINNER_REVEALED
   }

   Deck deck;
   CommandLineView commandLineView;
   GameEvaluator gameEvaluator;
   List<Player> players;
   Player winner;
   GameState gameState;

   public GameController(Deck deck, CommandLineView commandLineView, GameEvaluator gameEvaluator) {
      super();
      this.deck = deck;
      this.commandLineView = commandLineView;
      this.gameEvaluator = gameEvaluator;
      this.players = new ArrayList<>();
      this.gameState = GameState.ADDING_PLAYERS;
      commandLineView.setController(this);
   }

   public void run() {
      while (gameState == GameState.ADDING_PLAYERS) {
         commandLineView.promptForPlayerName();
      }

      switch (gameState) {
         case CARDS_DEALT:
            commandLineView.promptForFlip();
            break;
         case WINNER_REVEALED:
            commandLineView.promptForNewGame();
            break;
         default:
      }
   }

   public void addPlayer(String playerName) {
      if (gameState == GameState.ADDING_PLAYERS) {
         players.add(new Player(playerName));
         commandLineView.showPlayerName(players.size(), playerName);
      }
   }

   public void startGame() {
      if (gameState != GameState.CARDS_DEALT) {
         deck.shuffle();
         int playerIndex = 1;
         for (Player player : players) {
            player.addCardToHand(deck.removeTopCard());
            commandLineView.showFaceDownCardForPlayer(playerIndex++, player.getName());
         }
         gameState = GameState.CARDS_DEALT;
      }
      this.run();
   }

   public void flipCards() {
      int playerIndex = 1;
      for (Player player : players) {
         PlayingCard pc = player.getCard(0);
         pc.flip();
         commandLineView.showCardForPlayer(playerIndex++, player.getName(),
               pc.getRank().toString(), pc.getSuit().toString());
      }

      evaluateWinner();
      displayWinner();
      rebuildDeck();
      gameState = GameState.WINNER_REVEALED;
      this.run();
   }

   void evaluateWinner() {
      winner = gameEvaluator.evaluateWinner(players);
   }

   void displayWinner() {
      commandLineView.showWinner(winner.getName());
   }

   void rebuildDeck() {
      for (Player player : players) {
         deck.returnCardToDeck(player.removeCard());
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