package com.pedsf.cardgame.games;

import com.pedsf.cardgame.controller.GameController;
import com.pedsf.cardgame.model.DeckFactory;
import com.pedsf.cardgame.view.GameSwingView;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Games {
   public static void main(String[] args) {
      GameSwingView gsw = new GameSwingView();
      gsw.createAndShowGUI();

      // GameController gc = new GameController(new Deck(), new CommandLineView(), new HighCardGameEvaluator());
      GameController gc = new GameController(DeckFactory.makeDeck(DeckFactory.DeckType.FIFTY_TWO_CARDS), gsw, new LowCardGameEvaluator());

      gc.run();
   }
}
