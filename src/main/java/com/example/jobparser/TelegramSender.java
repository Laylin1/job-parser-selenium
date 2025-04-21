
package com.example.jobparser;

import io.github.cdimascio.dotenv.Dotenv;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class TelegramSender {

    private static final Dotenv dotenv = Dotenv.load();

    private static final String TOKEN = dotenv.get("TELEGRAM_TOKEN");
    private static final String CHAT_ID = dotenv.get("TELEGRAM_CHAT_ID");
    private static final String CHAT_ID2 = dotenv.get("TELEGRAM_CHAT_ID2");


    public static void sendToTelegram(String message) {
        try {
            String text = URLEncoder.encode(message, "UTF-8");
            String urlString = "https://api.telegram.org/bot" + TOKEN + "/sendMessage?chat_id=" + CHAT_ID + "&text=" + text;

            System.out.println("➡️ Sending to Telegram:");
            System.out.println("URL: " + urlString);

            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
            System.out.println("✅ Response code: " + responseCode); // 200 = OK

            conn.getInputStream().close();

        } catch (Exception e) {
            System.out.println("❌ Telegram error:");
            e.printStackTrace();
        }
    }
}

