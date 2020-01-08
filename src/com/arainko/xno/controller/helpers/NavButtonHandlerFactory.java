package com.arainko.xno.controller.helpers;

import com.arainko.xno.controller.game.GameController;
import com.arainko.xno.controller.interfaces.NavButtonHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;

public class NavButtonHandlerFactory {
    public static NavButtonHandler getLeftButtonHandler
            (GameController gameController, Node screenFrom, NavButtonHandler handlerFrom) {
        return new NavButtonHandler() {
            @Override
            public EventHandler<ActionEvent> getLeftButtonActionEvent() {
                return event -> {
                    gameController.getUIWrapper().changeMainView(screenFrom);
                    gameController.setCurrentNavButtonHandler(handlerFrom);
                };
            }
        };
    }

    public static NavButtonHandler getRightButtonHandler
            (GameController gameController, Node screenTo, NavButtonHandler handlerTo) {
        return new NavButtonHandler() {
            @Override
            public EventHandler<ActionEvent> getRightButtonActionEvent() {
                return event -> {
                    gameController.getUIWrapper().changeMainView(screenTo);
                    gameController.setCurrentNavButtonHandler(handlerTo);
                };
            }
        };
    }

    public static NavButtonHandler getLeftRightButtonHandler
            (GameController gameController, Node screenFrom, NavButtonHandler handlerFrom,
             Node screenTo, NavButtonHandler handlerTo) {
        return new NavButtonHandler() {
            @Override
            public EventHandler<ActionEvent> getLeftButtonActionEvent() {
                return event -> {
                    gameController.getUIWrapper().changeMainView(screenFrom);
                    gameController.setCurrentNavButtonHandler(handlerFrom);
                };
            }

            @Override
            public EventHandler<ActionEvent> getRightButtonActionEvent() {
                return event -> {
                    gameController.getUIWrapper().changeMainView(screenTo);
                    gameController.setCurrentNavButtonHandler(handlerTo);
                };
            }
        };
    }


}
