package com.pedsf.cardgame.model;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

@Slf4j
public class TestDeck extends Deck {

   public TestDeck() {
      cards = new ArrayList<>();
      for(int i=0; i<20; i++) {
         log.debug("Creating card [{}]({},{})", i, Suit.CLUBS, Rank.ACE);
         cards.add(new PlayingCard(Suit.CLUBS, Rank.ACE));
      }
      shuffle();
   }
}
