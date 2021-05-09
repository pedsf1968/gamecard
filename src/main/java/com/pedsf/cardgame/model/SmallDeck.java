package com.pedsf.cardgame.model;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

@Slf4j
public class SmallDeck extends Deck{

   public SmallDeck() {
      cards = new ArrayList<>();
      for(Suit s : Suit.values()) {
         for(Rank r : Rank.values()) {
            if(r.value() >= 7) {
               log.debug("Creating card ({},{})", s, r);
               cards.add(new PlayingCard(s, r));
            }
         }
      }
      shuffle();
   }
}
