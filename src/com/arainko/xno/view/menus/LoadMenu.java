package com.arainko.xno.view.menus;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LoadMenu extends Menu<MenuButton> {
    String saveFileDirPath = System.getProperty("user.home")+"/.xnosaves";
    public LoadMenu() {
        setupButtons();
    }

    public void setupButtons() {
        List<String> fileNames = Stream.of(new File(saveFileDirPath).listFiles())
                .map(File::getName)
                .filter(string -> string.endsWith(".xno"))
                .collect(Collectors.toList());
        fileNames.forEach(name -> {
            MenuButton button = new MenuButton(name, 300, 30);
            addButton(button);
            this.getChildren().add(button);
        });
    }
}
