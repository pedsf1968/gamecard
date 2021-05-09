package com.pedsf.cardgame.model;

public class Player implements IPlayer{
   private String name;
   private Hand hand;

   public Player(String name) {
      super();
      this.name = name;
      hand = new Hand();
   }

   public void addCardToHand(PlayingCard playingCard) {
      hand.addCard(playingCard);
   }

   public PlayingCard removeCard() {
      return hand.removeCard();
   }

   public PlayingCard getCard(int index) {
      return hand.getCard(index);
   }

   public String getName() {
      return name;
   }
}
