package Application.gui;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;

public class ReplyKeyboardMarkupConfiguration {
    public ReplyKeyboardMarkup getKeyboard() {
        try {
            ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
            replyKeyboardMarkup.setResizeKeyboard(true);
            replyKeyboardMarkup.setOneTimeKeyboard(false);
            ArrayList<KeyboardRow> keyboardRows = new ArrayList<>();
            KeyboardRow keyboardRow = new KeyboardRow();
            keyboardRow.add(new KeyboardButton("Создать тренировку"));
            keyboardRows.add(keyboardRow);
            KeyboardRow keyboardRow2 = new KeyboardRow();
            keyboardRow2.add(new KeyboardButton("Посмотреть сохраненные тренировки"));
            keyboardRows.add(keyboardRow2);
            KeyboardRow keyboardRow3 = new KeyboardRow();
            keyboardRow3.add(new KeyboardButton("Общая статистика тренировок"));
            keyboardRows.add(keyboardRow3);
            KeyboardRow keyboardRow4 = new KeyboardRow();
            keyboardRow4.add(new KeyboardButton("Изменить пароль"));
            keyboardRows.add(keyboardRow4);
            return replyKeyboardMarkup;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
