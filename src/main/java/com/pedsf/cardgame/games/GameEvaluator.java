package com.pedsf.cardgame.games;

import com.pedsf.cardgame.model.IPlayer;

import java.util.List;

public interface GameEvaluator {
   IPlayer evaluateWinner(List<IPlayer> IPlayerList);
}
