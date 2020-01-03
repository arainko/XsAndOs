package com.arainko.xno.view.menus;

public class LoadMenu extends Menu<MenuButton> {
    public LoadMenu() {
        super();
        MenuButton button = new MenuButton("TEST", 300, 30);
        addButton(button);
        this.getChildren().add(button);
    }
}
