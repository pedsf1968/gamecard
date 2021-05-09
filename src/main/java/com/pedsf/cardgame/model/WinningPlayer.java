package com.pedsf.cardgame.model;

public class WinningPlayer implements IPlayer{

   IPlayer winner;

   public WinningPlayer(IPlayer winner) {
      this.winner = winner;
   }

   @Override
   public void addCardToHand(PlayingCard playingCard) {
      winner.addCardToHand(playingCard);
   }

   @Override
   public PlayingCard removeCard() {
      return winner.removeCard();
   }

   @Override
   public PlayingCard getCard(int index) {
      return winner.getCard(index);
   }

   @Override
   public String getName() {
      return "***** " + winner.getName() + " *****";
   }
}
