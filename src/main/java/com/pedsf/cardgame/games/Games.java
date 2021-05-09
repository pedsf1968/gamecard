package com.pedsf.cardgame.games;

import com.pedsf.cardgame.controller.GameController;
import com.pedsf.cardgame.model.Deck;
import com.pedsf.cardgame.view.View;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Games {
   public static void main(String[] args) {
      GameController gc = new GameController(new Deck(), new View(), new GameEvaluator());

      gc.run();
   }
}
