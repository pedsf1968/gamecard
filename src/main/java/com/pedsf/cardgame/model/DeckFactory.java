package com.pedsf.cardgame.model;

public class DeckFactory {
   public enum DeckType {FIFTY_TWO_CARDS, THIRDTY_TWO_CARDS, TEST};

   public static Deck makeDeck(DeckType type) {
      switch (type) {
         case FIFTY_TWO_CARDS:
            return new NormalDeck();
         case THIRDTY_TWO_CARDS:
            return new SmallDeck();
         case TEST:
            return new TestDeck();
         default:
      }

      return new NormalDeck();
   }
}
