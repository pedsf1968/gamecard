package com.pedsf.cardgame.model;

import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 *
 */
@Slf4j
public class Deck {
   private List<PlayingCard> cards;

   public Deck() {
      for(Suit s : Suit.values()) {
         for(Rank r : Rank.values()) {
            log.debug("Creating card ({},{})",s,r);
            cards.add(new PlayingCard(s, r));
         }
      }
      shuffle();
   }

   private void shuffle() {
      Random random = new Random();
      for(int i = 0; i<cards.size(); i++){
         Collections.swap(cards, i, random.nextInt(cards.size()));
      }
   }

   public PlayingCard removeTopCard() {
      return cards.remove(0);
   }

   public void returnCardToDeck(PlayingCard playingCard) {
      cards.add(playingCard);
   }

   public List<PlayingCard> getCards() {
      return cards;
   }
}
