import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class TelegramBot extends TelegramLongPollingBot {
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()){
            if (update.getMessage().getText().equals("/start")){
                SendMessage message=new SendMessage();
                message.setText("click buttons");
                InlineKeyboardMarkup markup=new InlineKeyboardMarkup();
                InlineKeyboardButton button1=new InlineKeyboardButton();
                button1.setText("menu");
                button1.setCallbackData("main1");
                InlineKeyboardButton button2=new InlineKeyboardButton();
                button2.setText("previous menu");
                button2.setCallbackData("main2");
                InlineKeyboardButton button3=new InlineKeyboardButton();
                button3.setText("next menu");
                button3.setCallbackData("main3");


                List<InlineKeyboardButton> buttonList1=new ArrayList<>();
                buttonList1.add(button1);
                List<InlineKeyboardButton> buttonList2=new ArrayList<>();
                buttonList2.add(button2);
                buttonList2.add(button3);
                List<  List<InlineKeyboardButton>  > inlineList=new ArrayList<>();
                inlineList.add(buttonList1);
                inlineList.add(buttonList2);
                markup.setKeyboard(inlineList);
                message.setReplyMarkup(markup);
                message.setChatId(String.valueOf(update.getMessage().getChatId()));
                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        if (update.hasCallbackQuery()){
            Long chat = update.getCallbackQuery().getMessage().getChatId();
            String data = update.getCallbackQuery().getData();
            SendMessage sendMessage=new SendMessage();

            if (data.equals("main1")){
                sendMessage.setText("Bu asosiy menu");
            }
            if (data.equals("main2")){
                sendMessage.setText("Bu oldingi menu");
            }
            if (data.equals("main3")){
                sendMessage.setText("Bu oxirgi menu");
            }
            sendMessage.setChatId(chat.toString());
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }

        }
    }
    @Override
    public String getBotUsername() {
        return "http://t.me/b19groupbot";
    }

    @Override
    public String getBotToken() {
        return "5310235917:AAGWUuvymByQ1p3ksOEX7y1m0kIteTDr_V8";
    }

}
