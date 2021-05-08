package com.pedsf.cardgame.model;

/**
 * Class to describe a card with rank, color and wich side is up
 */
public class PlayingCard {
   private Suit suit;
   private Rank rank;
   private Boolean faceUp;

   public PlayingCard(Suit suit, Rank rank) {
      this.suit = suit;
      this.rank = rank;
   }

   public Rank getRank() {
      return rank;
   }

   public Suit getSuit() {
      return suit;
   }

   // No setter for no card modification
   public boolean flip() {
      faceUp = !faceUp;
      return faceUp;
   }
}
