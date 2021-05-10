package com.pedsf.cardgame.games;

import com.pedsf.cardgame.controller.GameController;
import com.pedsf.cardgame.model.DeckFactory;
import com.pedsf.cardgame.view.GameSwingPassiveView;
import com.pedsf.cardgame.view.GameSwingView;
import com.pedsf.cardgame.view.GameViewables;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class Games {
   public static void main(String[] args) {
      GameViewables gameViewables = new GameViewables();

      // Active view creation
      GameSwingView gsw = new GameSwingView();
      gsw.createAndShowGUI();
      gameViewables.addViewable(gsw);

      for (int i=0; i<3; i++) {
         // view instantation
         GameSwingPassiveView gameSwingPassiveView = new GameSwingPassiveView();
         // Frame creation
         gameSwingPassiveView.createAndShowGUI();
         // add frame to view list
         gameViewables.addViewable(gameSwingPassiveView);

         // waiting for having time to move frame on screen
         try {
            Thread.sleep(2500);
         } catch (InterruptedException e) {
            log.error("Error creating new Swing frame on windows :" ,e);
         }
      }

      // GameController gc = new GameController(new Deck(), new CommandLineView(), new HighCardGameEvaluator());
      GameController gc = new GameController(DeckFactory.makeDeck(DeckFactory.DeckType.FIFTY_TWO_CARDS), gameViewables, new LowCardGameEvaluator());

      gc.run();
   }
}
