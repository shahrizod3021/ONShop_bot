package Classes;

import Enums.*;
import Interfaces.Buttons;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.meta.generics.LongPollingBot;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.*;

public class Bot extends TelegramWebhookBot implements LongPollingBot {
    List<Xabarlar> xabarlars = new ArrayList<>();
    List<Long> topilgan = new ArrayList<>();
    List<Long> oqilgan = new ArrayList<>();
    Map<Long, String> isProduct = new HashMap<>();
    Map<Long, String> isAccaunt = new HashMap<>();
    Map<Long, String> name = new HashMap<>();
    Map<Long, String> surname = new HashMap<>();
    Map<Long, String> phoneNumber = new HashMap<>();
    Map<Long, String> ismahsulot = new HashMap<>();
    Map<Long, String> prName = new HashMap<>();
    Map<Long, String> prPrice = new HashMap<>();
    Map<Long, String> prDescription = new HashMap<>();
    Map<Long, String> prYear = new HashMap<>();
    Map<Long, String> prAdress = new HashMap<>();
    Map<Long, String> prPhone = new HashMap<>();
    Map<Long, ProductType> productType = new HashMap<>();
    Map<Long, GenderTyep> genderType = new HashMap<>();
    Map<Long, File> prRasm = new HashMap<>();
    List<Product> products = new ArrayList<>();
    Set<Users> users = new HashSet<>();
    Map<Long, String> isSearch = new HashMap<>();
    Map<Long, ProductType> searchPrType = new HashMap<>();
    Map<Long, GenderTyep> searchGeType = new HashMap<>();
    Map<Long, String> isXabar = new HashMap<>();
    Map<Long, String> textxabar = new HashMap<>();
    Map<Long, String> searchName = new HashMap<>();
    Map<Long, String> xabarTel = new HashMap<>();
    Map<Long, String> searchPrice = new HashMap<>();
    Map<Long, String> searchPrice1 = new HashMap<>();
    Map<Long, String> searchAdress = new HashMap<>();
    Map<Long, String> removedproduct = new HashMap<>();
    Map<Long, String> isUser = new HashMap<>();

    public static void main(String[] args) {
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(new Bot());
        } catch (TelegramApiException e) {
            System.err.println("not api");
        }
    }

    @Override
    public String getBotUsername() {
        return "easy_online_shop_uz_bot";
    }

    @Override
    public String getBotToken() {
        return "5920791367:AAEjMx1Wh3NAeNV7UxHW8p1bOlUuh_jt7_4";
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        return null;
    }

    @Override
    public String getBotPath() {
        return null;
    }

    @Override
    public void clearWebhook() throws TelegramApiRequestException {

    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        String text = message.getText();
        Long chatId = message.getChatId();
        List<PhotoSize> photo = message.getPhoto();
        PhotoSize photoSize = new PhotoSize();
        String fileUniqueId = photoSize.getFileUniqueId();
        String filePath = photoSize.getFilePath();
        String fileId = photoSize.getFileId();
        Integer fileSize = photoSize.getFileSize();
        Integer width = photoSize.getWidth();
        Integer height = photoSize.getHeight();
        User from = message.getFrom();
        SendMessage sendMessage = new SendMessage();
        System.out.println(chatId);
        System.out.println(from);
        SendPhoto sendPhoto = new SendPhoto();
        users.add(new Users(chatId));
        if (message.hasText()) {
            if (text.equals("/start")) {
                System.out.println(users);
                System.out.println(users.size());
                if (chatId == 1681505177) {
                    sendMsg(chatId, "Salom admin", sendMessage);
                    if (oqilgan.size() > 0) {
                        sendMsg(chatId, "sizda " + oqilgan.size() + "ta yangi xabar mavjud", sendMessage);
                    }
                    sendMsg(chatId, buttonYasash(sendMessage, chatId.toString(), Buttons.adminButton), sendMessage);
                } else {
                    sendMsg(chatId, "Assalomu alekum botimizga hush kelibisiz", sendMessage);
                    sendMsg(chatId, buttonYasash(sendMessage, chatId.toString(), Buttons.loguserButtons), sendMessage);
                }
            } else if (text.equals("Mening mahsulotlarim")) {
                sendMsg(chatId, buttonYasash(sendMessage, chatId.toString(), Buttons.mahsulotlar), sendMessage);
                ismahsulot.put(chatId, "tanlash");
            } else if (ismahsulot.size() > 0) {
                mahsulot(chatId, text, sendMessage, sendPhoto);
            } else if (text.equals("/menu")) {
                if (chatId == 1681505177) {
                    sendMsg(chatId, buttonYasash(sendMessage, chatId.toString(), Buttons.adminButton), sendMessage);
                } else {
                    sendMsg(chatId, buttonYasash(sendMessage, chatId.toString(), Buttons.loguserButtons), sendMessage);
                }
            } else if (text.equals("Accaunt")) {
                for (Users user : users) {
                    if (user.getChatId().equals(chatId)) {
                        sendMsg(chatId, buttonYasash(sendMessage, chatId.toString(), Buttons.accauntButton), sendMessage);
                        isAccaunt.put(chatId, "acc");
                    }
                }
            } else if (isAccaunt.size() > 0) {
                acc(chatId, text, sendMessage);
            } else if (text.equals("Userlar🙍‍♂")) {
                if (users.size() > 1) {
                    sendMsg(chatId, users(chatId, "Mana userlar", sendMessage), sendMessage);
                    isUser.put(chatId, "users1");
                } else {
                    sendMsg(chatId, "Hali userlar mavjud emas", sendMessage);
                }
            } else if (isUser.size() > 0) {
                getUser(chatId, text, sendMessage);
            } else if (text.equals("Mahsulot joylashtirish")) {
                sendMsg(chatId, "Mahsulotning nomini kiriting", sendMessage);
                isProduct.put(chatId, "prName");
            } else if (isProduct.size() > 0) {
                mahsulotqoshish(chatId, text, sendMessage);
            } else if (text.equals("Mahsulot qidirish🔍")) {
                sendMsg(chatId, buttonYasash(sendMessage, chatId.toString(), Buttons.productButtons), sendMessage);
                isSearch.put(chatId, "productse");
            } else if (isSearch.size() > 0) {
                serach(chatId, text, sendMessage, sendPhoto);
            } else if (text.equals("So'rovnoma")) {
                sendMsg(chatId, "Assalomu alekum, bizning botimizga qanday savolingiz bor", sendMessage);
                isXabar.put(chatId, "text");
            } else if (isXabar.size() > 0) {
                xabaryuborish(chatId, text, sendMessage, from);
            } else if (chatId == 1681505177 && text.equals("Xabarlar✉")) {
                if (xabarlars.isEmpty()) {
                    sendMsg(chatId, "hali habarlar mavjud emas", sendMessage);
                } else {
                    for (Xabarlar xabarlar : xabarlars) {
                        sendMsg(chatId, "From -> " + xabarlar.getKimdan() + "\n" + "Phone Number: " + xabarlar.getPhoneNumber() + "\n" + "Savol -> " + xabarlar.getText(), sendMessage);
                        oqilgan.clear();
                    }
                }
            }
        } else if (message.hasPhoto()) {
            photo.add(new PhotoSize(fileId, fileUniqueId, width, height, fileSize, filePath));
            for (PhotoSize size : photo) {
                String s = isProduct.get(chatId);
                if ("rasm".equals(s)) {
                    sendMsg(chatId, "Mahsulot qo'shildi", sendMessage);
                    if (chatId == 1681505177) {
                        sendMsg(chatId, buttonYasash(sendMessage, chatId.toString(), Buttons.adminButton), sendMessage);
                    } else {
                        sendMsg(chatId, buttonYasash(sendMessage, chatId.toString(), Buttons.loguserButtons), sendMessage);
                    }
                    for (Users user : users) {
                        if (user.getChatId().equals(chatId)) {
                            File file = new File(size.getFileId(), size.getFileUniqueId(), size.getFileSize().longValue(), size.getFilePath());
                            prRasm.put(chatId, file);
                            ProducType producType = new ProducType(productType.get(chatId));
                            Date date = new Date();
                            products.add(new Product(prName.get(chatId), prPrice.get(chatId), prDescription.get(chatId), prYear.get(chatId), prAdress.get(chatId), true, prPhone.get(chatId), producType, date, file, 0));
                            user.getProducts().add(new Product(prName.get(chatId), prPrice.get(chatId), prDescription.get(chatId), prYear.get(chatId), prAdress.get(chatId), true, prPhone.get(chatId), producType, date, file, 0));
                            System.out.println(user.getProducts());
                            System.out.println(user.getProducts().size());
                            isProduct.clear();
                        }
                    }
                } else if ("rasm1".equals(s)) {
                    sendMsg(chatId, "Mahsulot qo'shildi rahmat", sendMessage);
                    if (chatId == 1681505177) {
                        sendMsg(chatId, buttonYasash(sendMessage, chatId.toString(), Buttons.adminButton), sendMessage);
                    } else {
                        sendMsg(chatId, buttonYasash(sendMessage, chatId.toString(), Buttons.loguserButtons), sendMessage);
                    }
                    for (Users user : users) {
                        if (user.getChatId().equals(chatId)) {
                            File file = new File(size.getFileId(), size.getFileUniqueId(), size.getFileSize().longValue(), size.getFilePath());
                            prRasm.put(chatId, file);
                            ProducType producType = new ProducType(productType.get(chatId), genderType.get(chatId));
                            Date date = new Date();
                            products.add(new Product(prName.get(chatId), prPrice.get(chatId), prDescription.get(chatId), prYear.get(chatId), prAdress.get(chatId), true, prPhone.get(chatId), producType, date, file, 0));
                            user.getProducts().add(new Product(prName.get(chatId), prPrice.get(chatId), prDescription.get(chatId), prYear.get(chatId), prAdress.get(chatId), true, prPhone.get(chatId), producType, date, file, 0));
                            System.out.println(user.getProducts());
                            System.out.println(user.getProducts().size());
                            isProduct.clear();
                        }
                    }
                } else if ("rasm2".equals(s)) {
                    sendMsg(chatId, "Mahsulot qo'shildi.😊😊", sendMessage);
                    if (chatId == 1681505177) {
                        sendMsg(chatId, buttonYasash(sendMessage, chatId.toString(), Buttons.adminButton), sendMessage);
                    } else {
                        sendMsg(chatId, buttonYasash(sendMessage, chatId.toString(), Buttons.loguserButtons), sendMessage);
                    }
                    for (Users user : users) {
                        if (user.getChatId().equals(chatId)) {
                            File file = new File(size.getFileId(), size.getFileUniqueId(), size.getFileSize().longValue(), size.getFilePath());
                            prRasm.put(chatId, file);
                            ProducType producType = new ProducType(productType.get(chatId), genderType.get(chatId));
                            Date date = new Date();
                            products.add(new Product(prName.get(chatId), prPrice.get(chatId), prDescription.get(chatId), prYear.get(chatId), prAdress.get(chatId), true, prPhone.get(chatId), producType, date, prRasm.get(chatId), 0));
                            user.getProducts().add(new Product(prName.get(chatId), prPrice.get(chatId), prDescription.get(chatId), prYear.get(chatId), prAdress.get(chatId), true, prPhone.get(chatId), producType, date, prRasm.get(chatId), 0));
                            System.out.println(user.getProducts());
                            System.out.println(user.getProducts().size());
                            isProduct.clear();
                        }
                    }
                }
                break;
            }
        }

    }


    public void sendMsg(Long chatId, String text, SendMessage sendMessage) {
        sendMessage.setChatId(chatId);
        sendMessage.setText(text);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            System.err.println("not execute");
        }
    }

    public void mahsulotqoshish(Long chatId, String text, SendMessage sendMessage) {
        if (isProduct.get(chatId).equals("prName")) {
            sendMsg(chatId, "Mahsulotning narxini kiriting. (Amerika (USD) valyutasida)", sendMessage);
            isProduct.remove(chatId);
            prName.put(chatId, text.toUpperCase());
            isProduct.put(chatId, "prnarx");
        } else if (isProduct.get(chatId).equals("prnarx")) {
            sendMsg(chatId, "Mahsulotning malumotlarini kiriting", sendMessage);
            isProduct.remove(chatId);
            prPrice.put(chatId, text.toUpperCase());
            isProduct.put(chatId, "desc");
        } else if (isProduct.get(chatId).equals("desc")) {
            sendMsg(chatId, "mahsulotning chiqqan yilini kiriting", sendMessage);
            isProduct.remove(chatId);
            prDescription.put(chatId, text.toUpperCase());
            isProduct.put(chatId, "yil");
        } else if (isProduct.get(chatId).equals("yil")) {
            sendMsg(chatId, "Manzilingizni kiriting", sendMessage);
            isProduct.remove(chatId);
            prYear.put(chatId, text.toUpperCase());
            isProduct.put(chatId, "address");
        } else if (isProduct.get(chatId).equals("address")) {
            sendMsg(chatId, "Telefon raqamingizni kiriting, bu ma'lumot siz bilan bog'lanish uchun kerak bo'ladi", sendMessage);
            isProduct.remove(chatId);
            prAdress.put(chatId, text.toUpperCase());
            isProduct.put(chatId, "tel");
        } else if (isProduct.get(chatId).equals("tel")) {
            if (text.length() == 9 || text.length() == 12) {
                try {
                    int a = Integer.parseInt(text);
                    sendMsg(chatId, buttonYasash(sendMessage, chatId.toString(), Buttons.productButtons), sendMessage);
                    isProduct.remove(chatId);
                    prPhone.put(chatId, text.toUpperCase());
                    isProduct.put(chatId, "productType");
                    System.out.println(a);
                } catch (NumberFormatException e) {
                    sendMsg(chatId, "Telefon raqamda harf kiritib yubordingiz, qaytadan tekshirib kiriting", sendMessage);
                }
            } else {
                sendMsg(chatId, "Telefon raqam uzunligida hatolik mavjud", sendMessage);
            }
        } else if (isProduct.get(chatId).equals("productType") && text.equals("SPORT⚽")) {
            sendMsg(chatId, "Mahsulotning rasmini kiriting", sendMessage);
            isProduct.remove(chatId);
            productType.put(chatId, ProductType.SPORT);
            isProduct.put(chatId, "rasm");
        } else if (isProduct.get(chatId).equals("productType") && text.equals("MEXANIKA🚗")) {
            sendMsg(chatId, "Mahsulotning rasmini kiriting", sendMessage);
            isProduct.remove(chatId);
            productType.put(chatId, ProductType.MEXANIKA);
            isProduct.put(chatId, "rasm");
        } else if (isProduct.get(chatId).equals("productType") && text.equals("MAISHIY TEXNIKA💻")) {
            sendMsg(chatId, "Mahsulotning rasmini kiriting", sendMessage);
            isProduct.remove(chatId);
            productType.put(chatId, ProductType.MAISHIYTEXNIKA);
            isProduct.put(chatId, "rasm");
        } else if (isProduct.get(chatId).equals("productType") && text.equals("TELEFONLAR📱")) {
            sendMsg(chatId, "Mahsulotning rasmini kiriting", sendMessage);
            isProduct.remove(chatId);
            productType.put(chatId, ProductType.TELEFONLAR);
            isProduct.put(chatId, "rasm");
        } else if (isProduct.get(chatId).equals("productType") && text.equals("ASBOB ANJOMLAR🛠")) {
            sendMsg(chatId, "Mahsulotning rasmini kiriting", sendMessage);
            isProduct.remove(chatId);
            productType.put(chatId, ProductType.ASBOBANJOMLAR);
            isProduct.put(chatId, "rasm");
        } else if (isProduct.get(chatId).equals("productType") && text.equals("O'QUV QUROLLARI📏")) {
            sendMsg(chatId, "Mahsulotning rasmini kiriting", sendMessage);
            isProduct.remove(chatId);
            productType.put(chatId, ProductType.QURILISHMOLLARI);
            isProduct.put(chatId, "rasm");
        } else if (isProduct.get(chatId).equals("productType") && text.equals("O'YINCHOQLAR🎈")) {
            sendMsg(chatId, "Mahsulotning rasmini kiriting", sendMessage);
            isProduct.remove(chatId);
            productType.put(chatId, ProductType.OYINCHOQLAR);
            isProduct.put(chatId, "rasm");
        } else if (isProduct.get(chatId).equals("productType") && text.equals("OSHXONA BUYUMLARI")) {
            sendMsg(chatId, "Mahsulotning rasmini kiriting", sendMessage);
            isProduct.remove(chatId);
            productType.put(chatId, ProductType.OSHXONABUYUMLARI);
            isProduct.put(chatId, "rasm");
        } else if (isProduct.get(chatId).equals("productType") && text.equals("AKSESSUAR⌚")) {
            sendMsg(chatId, "Mahsulotning rasmini kiriting", sendMessage);
            isProduct.remove(chatId);
            productType.put(chatId, ProductType.AKSESSUAR);
            isProduct.put(chatId, "rasm");
        } else if (isProduct.get(chatId).equals("productType") && text.equals("QURILISH MOLLARI🧱")) {
            sendMsg(chatId, "Mahsulotning rasmini kiriting", sendMessage);
            isProduct.remove(chatId);
            productType.put(chatId, ProductType.QURILISHMOLLARI);
            isProduct.put(chatId, "rasm");
        } else if (isProduct.get(chatId).equals("productType") && text.equals("KIYIMLAR")) {
            sendMsg(chatId, buttonYasash(sendMessage, chatId.toString(), Buttons.genderBUttons), sendMessage);
            isProduct.remove(chatId);
            productType.put(chatId, ProductType.KIYIMLAR);
            isProduct.put(chatId, "gender");
        } else if (isProduct.get(chatId).equals("productType") && text.equals("OYOQ KIYIMLAR👟")) {
            sendMsg(chatId, buttonYasash(sendMessage, chatId.toString(), Buttons.genderBUttons), sendMessage);
            isProduct.remove(chatId);
            productType.put(chatId, ProductType.OYOQKIYIMLAR);
            isProduct.put(chatId, "gender");
        } else if (isProduct.get(chatId).equals("gender") && text.equals("Ayol👗")) {
            if (productType.get(chatId).equals(ProductType.KIYIMLAR)) {
                sendMsg(chatId, "Mahsulotning rasmini kiriting", sendMessage);
                isProduct.remove(chatId);
                genderType.put(chatId, GenderTyep.AYOL);
                isProduct.put(chatId, "rasm1");
            } else if (productType.get(chatId).equals(ProductType.OYOQKIYIMLAR)) {
                sendMsg(chatId, "Mahsulotning rasmini kiriting", sendMessage);
                isProduct.remove(chatId);
                genderType.put(chatId, GenderTyep.AYOL);
                isProduct.put(chatId, "rasm2");
            }
        } else if (isProduct.get(chatId).equals("gender") && text.equals("Erkak👔")) {
            if (productType.get(chatId).equals(ProductType.KIYIMLAR)) {
                sendMsg(chatId, "Mahsulotning rasmini kiriting", sendMessage);
                isProduct.remove(chatId);
                genderType.put(chatId, GenderTyep.ERKAK);
                isProduct.put(chatId, "rasm1");
            } else if (productType.get(chatId).equals(ProductType.OYOQKIYIMLAR)) {
                sendMsg(chatId, "Mahsulotning rasmini kiriting", sendMessage);
                isProduct.remove(chatId);
                genderType.put(chatId, GenderTyep.ERKAK);
                isProduct.put(chatId, "rasm2");
            }
        }
    }


    public void serach(Long chatId, String text, SendMessage sendMessage, SendPhoto sendPhoto) {
        for (Users user : users) {
            if (isSearch.get(chatId).equals("productse") && text.equals("SPORT⚽")) {
                sendMsg(chatId, "mahsulotning nomini kiriting", sendMessage);
                isSearch.remove(chatId);
                searchPrType.put(chatId, ProductType.SPORT);
                isSearch.put(chatId, "seName");
            } else if (isSearch.get(chatId).equals("productse") && text.equals("MEXANIKA🚗")) {
                sendMsg(chatId, "mahsulotning nomini kiriting", sendMessage);
                isSearch.remove(chatId);
                searchPrType.put(chatId, ProductType.MEXANIKA);
                isSearch.put(chatId, "seName");
            } else if (isSearch.get(chatId).equals("productse") && text.equals("MAISHIY TEXNIKA💻")) {
                sendMsg(chatId, "mahsulotning nomini kiriting", sendMessage);
                isSearch.remove(chatId);
                searchPrType.put(chatId, ProductType.MAISHIYTEXNIKA);
                isSearch.put(chatId, "seName");
            } else if (isSearch.get(chatId).equals("productse") && text.equals("TELEFONLAR📱")) {
                sendMsg(chatId, "mahsulotning nomini kiriting", sendMessage);
                isSearch.remove(chatId);
                searchPrType.put(chatId, ProductType.TELEFONLAR);
                isSearch.put(chatId, "seName");
            } else if (isSearch.get(chatId).equals("productse") && text.equals("ASBOB ANJOMLAR🛠")) {
                sendMsg(chatId, "mahsulotning nomini kiriting", sendMessage);
                isSearch.remove(chatId);
                searchPrType.put(chatId, ProductType.ASBOBANJOMLAR);
                isSearch.put(chatId, "seName");
            } else if (isSearch.get(chatId).equals("productse") && text.equals("O'QUV QUROLLARI📏")) {
                sendMsg(chatId, "mahsulotning nomini kiriting", sendMessage);
                isSearch.remove(chatId);
                searchPrType.put(chatId, ProductType.OQUVQUROLLARI);
                isSearch.put(chatId, "seName");
            } else if (isSearch.get(chatId).equals("productse") && text.equals("O'YINCHOQLAR🎈")) {
                sendMsg(chatId, "mahsulotning nomini kiriting", sendMessage);
                isSearch.remove(chatId);
                searchPrType.put(chatId, ProductType.OYINCHOQLAR);
                isSearch.put(chatId, "seName");
            } else if (isSearch.get(chatId).equals("productse") && text.equals("OSHXONA BUYUMLARI")) {
                sendMsg(chatId, "mahsulotning nomini kiriting", sendMessage);
                isSearch.remove(chatId);
                searchPrType.put(chatId, ProductType.OSHXONABUYUMLARI);
                isSearch.put(chatId, "seName");
            } else if (isSearch.get(chatId).equals("productse") && text.equals("AKSESSUAR⌚")) {
                sendMsg(chatId, "mahsulotning nomini kiriting", sendMessage);
                isSearch.remove(chatId);
                searchPrType.put(chatId, ProductType.AKSESSUAR);
                isSearch.put(chatId, "seName");
            } else if (isSearch.get(chatId).equals("productse") && text.equals("QURILISH MOLLARI🧱")) {
                sendMsg(chatId, "mahsulotning nomini kiriting", sendMessage);
                isSearch.remove(chatId);
                searchPrType.put(chatId, ProductType.QURILISHMOLLARI);
                isSearch.put(chatId, "seName");
            } else if (isSearch.get(chatId).equals("seName")) {
                sendMsg(chatId, "O'zingizga yaqin bo'lgan manzilni kiriting", sendMessage);
                isSearch.remove(chatId);
                searchName.put(chatId, text.toUpperCase());
                isSearch.put(chatId, "adress");
            } else if (isSearch.get(chatId).equals("adress")) {
                sendMsg(chatId, "Qidirayotgan mahsulotingizni narxini kiriting", sendMessage);
                isSearch.remove(chatId);
                searchAdress.put(chatId, text.toUpperCase());
                isSearch.put(chatId, "prices");
            } else if (isSearch.get(chatId).equals("productse") && text.equals("KIYIMLAR")) {
                sendMsg(chatId, buttonYasash(sendMessage, chatId.toString(), Buttons.genderBUttons), sendMessage);
                isSearch.remove(chatId);
                searchPrType.put(chatId, ProductType.KIYIMLAR);
                isSearch.put(chatId, "gender");
            } else if (isSearch.get(chatId).equals("productse") && text.equals("OYOQ KIYIMLAR👟")) {
                sendMsg(chatId, buttonYasash(sendMessage, chatId.toString(), Buttons.genderBUttons), sendMessage);
                isSearch.remove(chatId);
                searchPrType.put(chatId, ProductType.OYOQKIYIMLAR);
                isSearch.put(chatId, "gender");
            } else if (isSearch.get(chatId).equals("gender") && text.equals("Ayol👗")) {
                if (searchPrType.get(chatId).equals(ProductType.KIYIMLAR)) {
                    sendMsg(chatId, "O'zingizga yaqin bo'lgan manzilni kiriting", sendMessage);
                    isSearch.remove(chatId);
                    searchGeType.put(chatId, GenderTyep.AYOL);
                    isSearch.put(chatId, "adress1");
                } else if (searchPrType.get(chatId).equals(ProductType.OYOQKIYIMLAR)) {
                    sendMsg(chatId, "O'zingizga yaqin bo'lgan manzilni kiriting", sendMessage);
                    isSearch.remove(chatId);
                    searchGeType.put(chatId, GenderTyep.AYOL);
                    isSearch.put(chatId, "adress2");
                }
            } else if (isSearch.get(chatId).equals("gender") && text.equals("Erkak👔")) {
                if (searchPrType.get(chatId).equals(ProductType.KIYIMLAR)) {
                    sendMsg(chatId, "O'zingizga yaqin bo'lgan manzilni kiriting", sendMessage);
                    isSearch.remove(chatId);
                    searchGeType.put(chatId, GenderTyep.ERKAK);
                    isSearch.put(chatId, "adress1");
                } else if (searchPrType.get(chatId).equals(ProductType.OYOQKIYIMLAR)) {
                    sendMsg(chatId, "O'zingizga yaqin bo'lgan manzilni kiriting", sendMessage);
                    isSearch.remove(chatId);
                    searchGeType.put(chatId, GenderTyep.ERKAK);
                    isSearch.put(chatId, "adress2");
                }
            } else if (isSearch.get(chatId).equals("adress1")) {
                sendMsg(chatId, "O'zingizga maqul bo'lgan narxni kiriting (Amerika (USD) valyutasida)", sendMessage);
                isSearch.remove(chatId);
                searchAdress.put(chatId, text.toUpperCase());
                isSearch.put(chatId, "narx1");
            } else if (isSearch.get(chatId).equals("adress2")) {
                sendMsg(chatId, "O'zingizga maqul bo'lgan narxni kiriting (Amerika (USD) valyutasida)", sendMessage);
                isSearch.remove(chatId);
                searchAdress.put(chatId, text.toUpperCase());
                isSearch.put(chatId, "narx2");
            } else if (isSearch.get(chatId).equals("narx1")) {
                searchPrice.put(chatId, text.toUpperCase());
                int son = 0;
                for (Product product : user.getProducts()) {
                    if (searchPrType.get(chatId).equals(product.getProducType().getProductType()) && product.getProducType().getGenderTyep().equals(searchGeType.get(chatId)) && searchAdress.get(chatId).equals(product.getAddress()) && Integer.parseInt(text) >= Integer.parseInt(product.getNarx())) {
                        sendPh(chatId, product.getRasm(), "Mahsulotning nomi: " + product.getName() + "\n" + "Mahsulotning Tavsifi: " + product.getDescription() + "\n" + "Ishlab chiqarilgan yili:" + product.getYili() + "\n" + "Mahsulotning joylangan sanasi: " + product.getSana() + "\n" + "Mahsulotning narxi: " + product.getNarx() + "$" + "\n" + "Ko'rilganlar soni: " + product.getKorilganlarSoni() + "\n" + "Manzil: " + product.getAddress() + "\n" + "Telefon raqami: " + product.getPhoneNumber() + "\n" + "MAHSULOTNING HOLATI: " + product.getIsActive(), sendPhoto);
                        product.setKorilganlarSoni(product.getKorilganlarSoni() + 1);
                        topilgan.add(chatId);
                        son = 1;
                    } else {
                        son = 2;
                    }
                }
                if (topilgan.size() > 0) {
                    if (son == 1) {
                        if (chatId == 1681505177) {
                            sendMsg(chatId, buttonYasash(sendMessage, chatId.toString(), Buttons.adminButton), sendMessage);
                        } else {
                            sendMsg(chatId, buttonYasash(sendMessage, chatId.toString(), Buttons.loguserButtons), sendMessage);

                        }
                    } else if (son == 2) {
                        if (chatId == 1681505177) {
                            sendMsg(chatId, buttonYasash(sendMessage, chatId.toString(), Buttons.adminButton), sendMessage);
                        } else {
                            sendMsg(chatId, buttonYasash(sendMessage, chatId.toString(), Buttons.loguserButtons), sendMessage);
                        }
                    }
                } else {
                    sendMsg(chatId, "uzur malumot topilmadi", sendMessage);
                    if (chatId == 1681505177) {
                        sendMsg(chatId, buttonYasash(sendMessage, chatId.toString(), Buttons.adminButton), sendMessage);
                    } else {
                        sendMsg(chatId, buttonYasash(sendMessage, chatId.toString(), Buttons.loguserButtons), sendMessage);

                    }
                }
                isSearch.clear();
            } else if (isSearch.get(chatId).equals("narx2")) {
                searchPrice.put(chatId, text.toUpperCase());
                int son = 0;
                for (Product product : user.getProducts()) {
                    if (searchPrType.get(chatId).equals(product.getProducType().getProductType()) && product.getProducType().getGenderTyep().equals(searchGeType.get(chatId)) && searchAdress.get(chatId).equals(product.getAddress()) && Integer.parseInt(text) >= Integer.parseInt(product.getNarx())) {
                        sendPh(chatId, product.getRasm(), "Mahsulotning nomi: " + product.getName() + "\n" + "Mahsulotning Tavsifi: " + product.getDescription() + "\n" + "Ishlab chiqarilgan yili:" + product.getYili() + "\n" + "Mahsulotning joylangan sanasi: " + product.getSana() + "\n" + "Mahsulotning narxi: " + product.getNarx() + "$" + "\n" + "Ko'rilganlar soni: " + product.getKorilganlarSoni() + "\n" + "Manzil: " + product.getAddress() + "\n" + "Telefon raqami: " + product.getPhoneNumber() + "\n" + "MAHSULOTNING HOLATI: " + product.getIsActive(), sendPhoto);
                        product.setKorilganlarSoni(product.getKorilganlarSoni() + 1);
                        topilgan.add(chatId);
                        son = 1;
                    } else {
                        son = 2;
                    }
                }
                if (topilgan.size() > 0) {
                    if (son == 1) {
                        if (chatId == 1681505177) {
                            sendMsg(chatId, buttonYasash(sendMessage, chatId.toString(), Buttons.adminButton), sendMessage);
                        } else {
                            sendMsg(chatId, buttonYasash(sendMessage, chatId.toString(), Buttons.loguserButtons), sendMessage);
                        }
                        topilgan.clear();
                    } else if (son == 2) {
                        if (chatId == 1681505177) {
                            sendMsg(chatId, buttonYasash(sendMessage, chatId.toString(), Buttons.adminButton), sendMessage);
                        } else {
                            sendMsg(chatId, buttonYasash(sendMessage, chatId.toString(), Buttons.loguserButtons), sendMessage);

                        }
                        topilgan.clear();
                    }
                } else {
                    sendMsg(chatId, "uzur malumot topilmadi", sendMessage);
                    if (chatId == 1681505177) {
                        sendMsg(chatId, buttonYasash(sendMessage, chatId.toString(), Buttons.adminButton), sendMessage);
                    } else {
                        sendMsg(chatId, buttonYasash(sendMessage, chatId.toString(), Buttons.loguserButtons), sendMessage);
                    }
                    topilgan.clear();
                }
                isSearch.clear();
            } else if (isSearch.get(chatId).equals("prices")) {
                searchPrice1.put(chatId, text.toUpperCase());
                int son = 0;
                for (Product product : user.getProducts()) {
                    if (searchPrType.get(chatId).equals(product.getProducType().getProductType()) && searchName.get(chatId).equals(product.getName()) && searchAdress.get(chatId).equals(product.getAddress()) && Integer.parseInt(text) >= Integer.parseInt(product.getNarx())) {
                        sendPh(chatId, product.getRasm(), "Mahsulotning nomi: " + product.getName() + "\n" + "Mahsulotning Tavsifi: " + product.getDescription() + "\n" + "Ishlab chiqarilgan yili:" + product.getYili() + "\n" + "Mahsulotning joylangan sanasi: " + product.getSana() + "\n" + "Mahsulotning narxi: " + product.getNarx() + "$" + "\n" + "Ko'rilganlar soni: " + product.getKorilganlarSoni() + "\n" + "Manzil: " + product.getAddress() + "\n" + "Telefon raqami: " + product.getPhoneNumber() + "\n" + "MAHSULOTNING HOLATI: " + product.getIsActive(), sendPhoto);
                        product.setKorilganlarSoni(product.getKorilganlarSoni() + 1);
                        topilgan.add(chatId);
                        son = 1;
                    } else {
                        son = 2;
                    }
                }
                if (topilgan.size() > 0) {
                    if (son == 1) {
                        if (chatId == 1681505177) {
                            sendMsg(chatId, buttonYasash(sendMessage, chatId.toString(), Buttons.adminButton), sendMessage);
                        } else {
                            sendMsg(chatId, buttonYasash(sendMessage, chatId.toString(), Buttons.loguserButtons), sendMessage);

                        }
                        topilgan.clear();
                    } else if (son == 2) {
                        if (chatId == 1681505177) {
                            sendMsg(chatId, buttonYasash(sendMessage, chatId.toString(), Buttons.adminButton), sendMessage);
                        } else {
                            sendMsg(chatId, buttonYasash(sendMessage, chatId.toString(), Buttons.loguserButtons), sendMessage);
                        }
                        topilgan.clear();
                    }
                } else {
                    sendMsg(chatId, "uzur malumot topilmadi", sendMessage);
                    if (chatId == 1681505177) {
                        sendMsg(chatId, buttonYasash(sendMessage, chatId.toString(), Buttons.adminButton), sendMessage);
                    } else {
                        sendMsg(chatId, buttonYasash(sendMessage, chatId.toString(), Buttons.loguserButtons), sendMessage);
                    }
                    topilgan.clear();
                }
                isSearch.clear();
            }
        }
    }


    public String buttonYasash(SendMessage sendMessage, String chatId, List<String> buttons) {
        sendMessage.setChatId(chatId);
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboard = new ArrayList<>();
        int size = buttons.size();
        KeyboardRow row = new KeyboardRow();
        for (int i = 0; i < size; i++) {
            if (i % 3 == 0) {
                row = new KeyboardRow();
                keyboard.add(row);
            }
            row.add(buttons.get(i));
        }
        keyboardMarkup.setKeyboard(keyboard);
        keyboardMarkup.setResizeKeyboard(true);
        sendMessage.setText("Tanlang");
        sendMessage.setReplyMarkup(keyboardMarkup);

        return "Tanlang";
    }


    public void sendPh(Long chatId, File file, String caption, SendPhoto sendPhoto) {
        sendPhoto.setChatId(chatId);
        sendPhoto.setCaption(caption);
        InputFile inputFile = new InputFile(file.getFileId());
        sendPhoto.setPhoto(inputFile);
        try {
            execute(sendPhoto);
        } catch (TelegramApiException e) {
            System.err.println("not rasm");
        }
    }


    public void xabaryuborish(Long chatId, String text, SendMessage sendMessage, User from) {
        if (isXabar.get(chatId).equals("text")) {
            sendMsg(chatId, "Iltimos biz siz bilan bo'glanishimiz uchun telefon raqaminingzni kiriting", sendMessage);
            isXabar.remove(chatId);
            textxabar.put(chatId, text);
            isXabar.put(chatId, "phone");
        } else if (isXabar.get(chatId).equals("phone")) {
            if (text.length() == 9) {
                try {
                    sendMsg(chatId, "sizning savolingiz qabul qilindi, Adminlarimiz siz bilan bog'lanishadi", sendMessage);
                    xabarTel.put(chatId, text);
                    xabarlars.add(new Xabarlar(from.getFirstName(), xabarTel.get(chatId), textxabar.get(chatId)));
                    System.out.println(xabarlars);
                    oqilgan.add(chatId);
                    isXabar.clear();
                } catch (NumberFormatException e) {
                    sendMsg(chatId, "Telefon raqam sonlardan iborat bo'lishi lozim", sendMessage);

                }
            } else {
                sendMsg(chatId, "Telefon raqam uzunligi 9 taga teng bo'lishi shart", sendMessage);
            }
        }
    }


    public void acc(Long chatId, String text, SendMessage sendMessage) {
        if (isAccaunt.get(chatId).equals("acc") && text.equals("Accauntni Taxrirlash")) {
            sendMsg(chatId, "Assalomu alekum, Ismingizni taxrirlang", sendMessage);
            isAccaunt.remove(chatId);
            isAccaunt.put(chatId, "name");
        } else if (isAccaunt.get(chatId).equals("name")) {
            sendMsg(chatId, "Juda yaxshi, endi familyangizni taxrirlang", sendMessage);
            isAccaunt.remove(chatId);
            name.put(chatId, text);
            isAccaunt.put(chatId, "surname");
        } else if (isAccaunt.get(chatId).equals("surname")) {
            sendMsg(chatId, "Yanada yaxshi, endi telefon raqamingizni kiriting. MISOL UCHUN: 98 XXX xx xx", sendMessage);
            isAccaunt.remove(chatId);
            surname.put(chatId, text);
            isAccaunt.put(chatId, "phoneNumber");
        } else if (isAccaunt.get(chatId).equals("phoneNumber")) {
            if (text.length() == 9 || text.length() == 12) {
                try {
                    int a = Integer.parseInt(text);
                    phoneNumber.put(chatId, text);
                    sendMsg(chatId, "Accauntingiz muvaffaqiyatli taxrirlandi", sendMessage);
                    for (Users user : users) {
                        if (chatId == 1681505177) {
                            user.setName(name.get(chatId));
                            user.setSurname(surname.get(chatId));
                            user.setPhoneNumber(phoneNumber.get(chatId));
                            user.setRole(Role.ADMIN);
                            break;
                        } else if (user.getChatId().equals(chatId)) {
                            user.setName(name.get(chatId));
                            user.setSurname(surname.get(chatId));
                            user.setPhoneNumber(phoneNumber.get(chatId));
                            user.setRole(Role.USER);
                            break;
                        }
                    }
                    sendMsg(chatId, buttonYasash(sendMessage, chatId.toString(), Buttons.accauntButton), sendMessage);
                    isAccaunt.put(chatId, "acc");
                    System.out.println(a);
                } catch (NumberFormatException e) {
                    sendMsg(chatId, "Hatolikga yo'l qo'ydingiz, Telefon raqamda harf kiritib yubordingiz", sendMessage);
                }
            } else {
                sendMsg(chatId, "Telefon raqamda hatolik❗❗", sendMessage);
            }
        } else if (isAccaunt.get(chatId).equals("acc") && text.equals("My accaunt")) {
            for (Users user : users) {
                if (user.getChatId().equals(chatId)) {
                    sendMsg(chatId, "Ism: " + user.getName() + "\n" + "Familya: " + user.getSurname() + "\n" + "Telefon raqam: " + user.getPhoneNumber(), sendMessage);
                    sendMsg(chatId, buttonYasash(sendMessage, chatId.toString(), Buttons.accauntButton), sendMessage);
                }
            }
        } else if (isAccaunt.get(chatId).equals("acc") && text.equals("Orqaga")) {
            if (chatId == 1681505177) {
                sendMsg(chatId, buttonYasash(sendMessage, chatId.toString(), Buttons.adminButton), sendMessage);
            } else {
                sendMsg(chatId, buttonYasash(sendMessage, chatId.toString(), Buttons.loguserButtons), sendMessage);
            }
            isAccaunt.clear();
        }
    }

    public void mahsulot(Long chatId, String text, SendMessage sendMessage, SendPhoto sendPhoto) {
        if (ismahsulot.get(chatId).equals("tanlash") && text.equals("Mahsulotlarim")) {
            for (Users user : users) {
                if (user.getChatId().equals(chatId)) {
                    if (user.getProducts().size() > 0) {
                        for (Product product : user.getProducts()) {
                            sendPh(chatId, product.getRasm(), "Nomi: " + product.getName() + "\n" + "Yili: " + product.getYili() + "\n" + "Narx: " + product.getNarx() + "\n" + "Telefon raqam: " + product.getPhoneNumber(), sendPhoto);
                            sendMsg(chatId, buttonYasash(sendMessage, chatId.toString(), Buttons.mahsulotlar), sendMessage);
                        }
                    } else {
                        sendMsg(chatId, "Hali bizning botga mahsulotingizni joylashtirmagansiz", sendMessage);
                    }
                }
            }
        } else if (ismahsulot.get(chatId).equals("tanlash") && text.equals("Mahsulotni o'chirish")) {
            for (Users user : users) {
                if (user.getChatId().equals(chatId)) {
                    if (user.getProducts().size() > 0) {
                        sendMsg(chatId, removeproduct(chatId, "Tanlang", sendMessage), sendMessage);
                        ismahsulot.remove(chatId);
                        removedproduct.put(chatId, text);
                        ismahsulot.put(chatId, "removed");
                    } else {
                        sendMsg(chatId, "Hali bizning botga mahsulot qo'shmagansiz", sendMessage);
                        ismahsulot.put(chatId, "tanlash");
                    }
                }
            }
        } else if (ismahsulot.get(chatId).equals("removed")) {
            for (Users user : users) {
                if (chatId.equals(user.getChatId())) {
                    for (Product product : user.getProducts()) {
                        if (text.equals(product.getName())) {
                            product.setIsActive(false);
                            user.getProducts().remove(product);
                            sendMsg(chatId, "Mahsulotingiz muvaffaqiyatli olib tashlandi", sendMessage);
                            sendMsg(chatId, buttonYasash(sendMessage, chatId.toString(), Buttons.mahsulotlar), sendMessage);
                            break;
                        }
                    }
                }
            }
            ismahsulot.put(chatId, "tanlash");
        } else if (ismahsulot.get(chatId).equals("tanlash") && text.equals("Orqaga")) {
            if (chatId == 1681505177) {
                sendMsg(chatId, buttonYasash(sendMessage, chatId.toString(), Buttons.adminButton), sendMessage);
            } else {
                sendMsg(chatId, buttonYasash(sendMessage, chatId.toString(), Buttons.loguserButtons), sendMessage);
            }
            ismahsulot.clear();
        }
    }


    public String removeproduct(Long chatId, String text, SendMessage sendMessage) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        for (Users user : users) {
            if (user.getChatId().equals(chatId)) {
                for (Product product : user.getProducts()) {
                    row.add(product.getName());
                }
            }
        }
        keyboardRows.add(row);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setKeyboard(keyboardRows);
        sendMessage.setChatId(chatId);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        return text;
    }

    public String users(Long chatId, String text, SendMessage sendMessage) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> rows = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        for (Users user : users) {
            if (user.getChatId() != 1681505177) {
                row.add(user.getName());
            }
        }
        rows.add(row);
        replyKeyboardMarkup.setKeyboard(rows);
        replyKeyboardMarkup.setResizeKeyboard(true);
        sendMessage.setText(text);
        sendMessage.setChatId(chatId);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        return text;
    }

    public void getUser(Long chatId, String text, SendMessage sendMessage) {
        if (isUser.get(chatId).equals("users1")) {
            for (Users user : users) {
                if (text.equals(user.getName())) {
                    isUser.remove(chatId);
                    sendMsg(chatId, "Name: " + user.getName() + "\n" + "Surname: " + user.getSurname() + "\n" + "Phone Number: " + user.getPhoneNumber(), sendMessage);
                    sendMsg(chatId, buttonYasash(sendMessage, chatId.toString(), Buttons.orqagabuttons), sendMessage);
                    isUser.put(chatId, "orqagab");
                }
            }
        } else if (isUser.get(chatId).equals("orqagab")) {
            sendMsg(chatId, buttonYasash(sendMessage, chatId.toString(), Buttons.adminButton), sendMessage);
            isUser.clear();
        }
    }


//    public void foy(Long chatId, String text, SendMessage sendMessage, Map<Long, String> foy, Map<Long, String> malumot, String change){
//        sendMsg(chatId, text, sendMessage);
//        foy.remove(chatId);
//        malumot.put(chatId, text);
//        foy.put(chatId, change);
//    }
}
