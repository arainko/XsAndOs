package com.arainko.xno.view.menus;

import javafx.scene.control.ScrollPane;
import javafx.scene.text.Text;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LoadMenu extends Menu<MenuButton> {
    private ScrollPane wrapper;
    private Text infoText;
    private String saveFileDirPath = System.getProperty("user.home")+"/.xnosaves";

    public LoadMenu() {
        setupInfoText();
        setupButtons();
        setupWrapper();
    }

    private void setupInfoText() {
        infoText = new Text("Choose a save file:");
        infoText.setId("menu-text");
        getChildren().add(infoText);
    }

    private void setupButtons() {
        List<MenuButton> fileButtons = Stream.of(new File(saveFileDirPath).listFiles())
                .map(File::getName)
                .filter(string -> string.endsWith(".xno"))
                .map(name -> new MenuButton(name, 300, 30))
                .collect(Collectors.toList());
        if (fileButtons.isEmpty())
            infoText.setText("No save files to load :(");
        addButtons(fileButtons);
    }

    private void setupWrapper() {
        wrapper = new ScrollPane(this);
        wrapper.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        wrapper.setFitToWidth(true);
        wrapper.setFitToHeight(true);
        wrapper.setPannable(true);
    }

    public ScrollPane getWrapper() {
        return wrapper;
    }
}
