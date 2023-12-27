package Application.service;

import Application.gui.ReplyKeyboardMarkupConfiguration;
import Application.model.UsersChat;
import Application.model.Workout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class Bot extends TelegramLongPollingBot {

    @Autowired
    private WorkoutService workoutService;

    @Autowired
    private UsersChatService usersChatService;

    private ReplyKeyboardMarkupConfiguration replyKeyboardMarkup = new ReplyKeyboardMarkupConfiguration();

    final private String BOT_TOKEN = "5772427413:AAG1_QUBw3FaYFpHZQh4wGFe2qmJGX5Szhc";

    final private String BOT_NAME = "KuznetsovMOSm221bot";

    public Bot() throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(this);
    }

    @Override
    public void onUpdateReceived(Update update) {
        try {
            if (update.hasMessage() && update.getMessage().hasText()) {
                Message inMess = update.getMessage();
                String response = "";
                String chatId = inMess.getChatId().toString();

                Pattern p = Pattern.compile("^(\\+\\d{1,3}( )?)?((\\(\\d{1,3}\\))|\\d{1,3})[- .]?\\d{3,4}[- .]?\\d{4}$");
                Matcher m = p.matcher(inMess.getText());
                boolean thisNumberPhone = m.matches();
                if (usersChatService.findChatIdBooleanService(chatId)) {
                    if (Objects.equals(usersChatService.findChatIdUsersChatService(chatId).getPhoneNumber(), "")) {
                        response = parseMessage(inMess.getText());//Если пользователь зарегистрирован и указан номер телефона, то дальше парсим сообщение
                    } else if (thisNumberPhone) {
                        //добавляем номер телефона
                    } else {
                        response = "Для завершения регистрации укажите номер телефона\n";
                    }
                } else {
                    if () {//проверяем запрашивает ли клиент регистрацию

                    } else {//если не запрашивает то кидаем сообщение "сначала зарегайтесь"
                        response = "Сначала зарегистрируйтесь\n";
                    }
                }
                SendMessage outMess = new SendMessage();
                outMess.setChatId(chatId);
                outMess.setText(response);
                outMess.setReplyMarkup(replyKeyboardMarkup.getKeyboard());
                execute(outMess);
            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public String parseMessage(String text) { //...........................................................................
        String returnText;
        try {
            if (text.equals("/start") || text.equals("Привет")) {
                returnText = "Добрый день! Выберите действие из вариантов ниже";
            } else if (text.equals("/get1") || text.equals("Создать тренировку")) {
                returnText = requestText("Создать тренировку");
            } else if (text.equals("/get2") || text.equals("Посмотреть сохраненные тренировки")) {
                returnText = requestText("Посмотреть сохраненные тренировки");
            } else if (text.equals("/get3") || text.equals("Общая статистика тренировок")) {
                returnText = requestText("Общая статистика тренировок");
            } else if (text.equals("/get4") || text.equals("Изменить пароль")) {
                returnText = requestText("Изменить пароль");
            } else if (text.equals("/get5") || text.equals("Назначить тренировку")) {
                returnText = requestText("Назначить тренировку");
            } else {
                throw new IllegalArgumentException("Ошибка, недопустимый аргумент.\nВыберите действие из вариантов ниже");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Ошибка: " + e.getMessage();
        }
        return returnText;
    }

    private String requestText(String muscleName) {
        /*try {
            List<Workout> workouts = workoutService.findMuscleNameService(muscleName);
            String request = "Могу предложить следующие упражнения: \n\n";
            for (int i = 0; i < workouts.size(); i++) {
                request += workouts.get(i).getExerciseName() + "\n" + workouts.get(i).getDescription() + "\n\n";
            }
            return request;
        } catch (Exception e) {
            e.printStackTrace();
            return "Ошибка, повторите запрос позже: " + e.getMessage();
        }*/
        return "";
    }

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    @Scheduled(fixedDelay = 3600000)
    public void timeMessageMorning() throws Exception {
//        List<UsersChat> usersChats = usersChatService.findAll();
//
//        for (int k = 0; k < usersChats.size(); k++) {
//            String CHAT_ID = usersChats.get(k).getChatID();
//            SendMessage outMess = new SendMessage();
//            outMess.setChatId(CHAT_ID);
//            outMess.setReplyMarkup(replyKeyboardMarkup.getKeyboard());
//            String message;
//            Date dateTime = new Date();
//            if (dateTime.getHours() == 8) {
//                message = "Доброе утро! \nПора встать и немного размяться, предлагаю тебе выполнить следующие упражнения в качестве утренней разминки: \n\n";
//                List<Workout> workouts;
//                if (dateTime.getDay() % 2 == 1) {
//                    workouts = workoutService.findMuscleNameService("Разминка/Активная");
//                } else {
//                    workouts = workoutService.findMuscleNameService("Разминка/Пассивная");
//                }
//                for (int i = 0; i < workouts.size(); i++) {
//                    message += workouts.get(i).getExerciseName() + "\n" + workouts.get(i).getDescription() + "\n\n";
//                }
//                outMess.setText(message);
//                execute(outMess);
//            } else if (dateTime.getHours() == 13 && ((dateTime.getDay() == 1) || (dateTime.getDay() == 3) || (dateTime.getDay() == 5))) {
//                message = "Пришло время тренировки! \nПредлагаю тебе выполнить следующие силовые упражнения: \n\n";
//                List<Workout> workouts = new ArrayList<>();
//                List<Workout> workouts2 = new ArrayList<>();
//                if (dateTime.getDay() == 1) {
//                    workouts = workoutService.findMuscleNameService("Бицепс");
//                    workouts2 = workoutService.findMuscleNameService("Спина");
//                } else if (dateTime.getDay() == 3) {
//                    workouts = workoutService.findMuscleNameService("Трицепс");
//                    workouts2 = workoutService.findMuscleNameService("Грудь");
//                } else if (dateTime.getDay() == 5) {
//                    workouts = workoutService.findMuscleNameService("Плечевого пояса");
//                    workouts2 = workoutService.findMuscleNameService("Ноги");
//                }
//                for (int i = 0; i < 2; i++) {
//                    int random = (int) (Math.random() * workouts.size() - 1);
//                    message += workouts.get(random).getExerciseName() + "\n" + workouts.get(random).getDescription() + "\n\n";
//                }
//                for (int i = 0; i < 2; i++) {
//                    int random = (int) (Math.random() * workouts2.size() - 1);
//                    message += workouts2.get(random).getExerciseName() + "\n" + workouts2.get(random).getDescription() + "\n\n";
//                }
//                outMess.setText(message);
//                execute(outMess);
//            } else if (dateTime.getHours() == 19) {
//                message = "Вечерняя зарядка - отличный способ для улучшения самочувствия и сна! \nПредлагаю тебе выполнить следующие упражнения перед тем как принять душ и пойти спать: \n\n";
//                List<Workout> workouts;
//                if (dateTime.getDay() % 2 == 1) {
//                    workouts = workoutService.findMuscleNameService("Разминка/Пассивная");
//                } else {
//                    workouts = workoutService.findMuscleNameService("Заминка");
//                }
//                for (int i = 0; i < workouts.size(); i++) {
//                    message += workouts.get(i).getExerciseName() + "\n" + workouts.get(i).getDescription() + "\n\n";
//                }
//                outMess.setText(message);
//                execute(outMess);
//            }
//        }
    }
}
