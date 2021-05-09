package com.pedsf.cardgame.model;

/**
 * Class for the player
 */
public interface IPlayer {
   void addCardToHand(PlayingCard playingCard);
   PlayingCard removeCard();
   PlayingCard getCard(int index);
   String getName();
}
