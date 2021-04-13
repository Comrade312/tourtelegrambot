package tourbot.bot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import tourbot.service.CityCrudService;

@Component
public class TelegramBot extends TelegramLongPollingBot {
    @Value("${telegram.username}")
    private String username;

    @Value("${telegram.token}")
    private String token;

    @Autowired
    private CityCrudService cityCrudService;

    @Override
    public void onUpdateReceived(Update update) {
        try {
            if (update.hasMessage()) {
                messageParser(update.getMessage().getChatId(), update.getMessage().getText());
            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void messageParser(Long chatId, String text) throws TelegramApiException {
        StringBuilder response = new StringBuilder();

        if (text.toLowerCase().equals("/start")) {
            response.append("Введите название города:");
        } else {
            cityCrudService
                    .getCityByName(text)
                    .map(city -> response.append(city.getComment()))
                    .orElseGet(() -> response.append(text).append(" - данный город не найден"));
        }

        execute(sendMessage(chatId, response.toString()));
    }

    private SendMessage sendMessage(Long chatId, String text) {
        return new SendMessage()
                .setChatId(chatId)
                .setText(text);
    }

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }
}