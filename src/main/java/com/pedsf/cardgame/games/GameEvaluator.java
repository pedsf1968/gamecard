package com.pedsf.cardgame.games;

import com.pedsf.cardgame.model.Player;

import java.util.List;

public interface GameEvaluator {
   Player evaluateWinner(List<Player> playerList);
}
