package com.pedsf.cardgame.controller;

import com.pedsf.cardgame.model.Deck;
import com.pedsf.cardgame.model.Player;
import com.pedsf.cardgame.model.PlayingCard;
import com.pedsf.cardgame.view.View;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.exit;


public class GameController {

   enum GameState {
      ADDING_PLAYERS, CARDS_DEALT, WINNER_REVEALED
   }

   Deck deck;
   List<Player> players;
   Player winner;
   View view;
   GameState gameState;

   public GameController(Deck deck, View view) {
      super();
      this.deck = deck;
      this.view = view;
      this.players = new ArrayList<>();
      this.gameState = GameState.ADDING_PLAYERS;
      view.setController(this);
   }

   public void run() {
      while (gameState == GameState.ADDING_PLAYERS) {
         view.promptForPlayerName();
      }

      switch (gameState) {
         case CARDS_DEALT:
            view.promptForFlip();
            break;
         case WINNER_REVEALED:
            view.promptForNewGame();
            break;
         default:
      }
   }

   public void addPlayer(String playerName) {
      if (gameState == GameState.ADDING_PLAYERS) {
         players.add(new Player(playerName));
         view.showPlayerName(players.size(), playerName);
      }
   }

   public void startGame() {
      if (gameState != GameState.CARDS_DEALT) {
         deck.shuffle();
         int playerIndex = 1;
         for (Player player : players) {
            player.addCardToHand(deck.removeTopCard());
            view.showFaceDownCardForPlayer(playerIndex++, player.getName());
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
         view.showCardForPlayer(playerIndex++, player.getName(),
               pc.getRank().toString(), pc.getSuit().toString());
      }

      evaluateWinner();
      displayWinner();
      rebuildDeck();
      gameState = GameState.WINNER_REVEALED;
      this.run();
   }

   void evaluateWinner() {
      Player bestPlayer = null;
      int bestRank = -1;
      int bestSuit = -1;

      for (Player player : players) {
         boolean newBestPlayer = false;

         if (bestPlayer == null) {
            newBestPlayer = true;
         } else {
            PlayingCard pc = player.getCard(0);
            int thisRank = pc.getRank().value();
            if (thisRank >= bestRank) {
               if (thisRank > bestRank) {
                  newBestPlayer = true;
               } else {
                  if (pc.getSuit().value() > bestSuit) {
                     newBestPlayer = true;
                  }
               }
            }
         }

         if (newBestPlayer) {
            bestPlayer = player;
            PlayingCard pc = player.getCard(0);
            bestRank = pc.getRank().value();
            bestSuit = pc.getSuit().value();
         }
      }

      winner = bestPlayer;
   }

   void displayWinner() {
      view.showWinner(winner.getName());
   }

   void rebuildDeck() {
      for (Player player : players) {
         deck.returnCardToDeck(player.removeCard());
      }
   }
}