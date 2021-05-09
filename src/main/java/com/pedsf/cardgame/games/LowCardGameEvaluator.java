package com.pedsf.cardgame.games;

import com.pedsf.cardgame.model.IPlayer;
import com.pedsf.cardgame.model.PlayingCard;

import java.util.List;

public class LowCardGameEvaluator implements GameEvaluator {

   public IPlayer evaluateWinner(List<IPlayer> IPlayerList) {
      IPlayer bestIPlayer = null;
      int bestRank = -1;
      int bestSuit = -1;

      for (IPlayer IPlayer : IPlayerList) {
         boolean newBestPlayer = false;

         if (bestIPlayer == null) {
            newBestPlayer = true;
         } else {
            PlayingCard pc = IPlayer.getCard(0);
            int thisRank = pc.getRank().value();
            if (thisRank <= bestRank) {
               if (thisRank < bestRank) {
                  newBestPlayer = true;
               } else {
                  if (pc.getSuit().value() < bestSuit) {
                     newBestPlayer = true;
                  }
               }
            }
         }

         if (newBestPlayer) {
            bestIPlayer = IPlayer;
            PlayingCard pc = IPlayer.getCard(0);
            bestRank = pc.getRank().value();
            bestSuit = pc.getSuit().value();
         }
      }

      return bestIPlayer;
   }
}
