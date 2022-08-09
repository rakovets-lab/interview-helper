package by.rakovets.interview.telegram_bot;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class InterviewTelegramBotApplication {
    public static void main(String[] args) {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new Bot("rakovets_interview_bot", "1875619212:AAEP96eSsmqOwLAo9NYaXuvu7XMjOaCyy0U"));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
