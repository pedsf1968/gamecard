package com.pedsf.cardgame.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for the hand of one player
 */
public class Hand {
   private List<PlayingCard> cards;

   public Hand() {
      cards = new ArrayList<PlayingCard>();
   }

   public void addCard(PlayingCard playingCard) {
      cards.add(playingCard);
   }

   public PlayingCard getCard(int index) {
      return cards.get(index);
   }

   public PlayingCard removeCard() {
      return cards.remove(0);
   }
}
