package ru.rt.year2015;

import ru.infotecs.crypto.ViPNetProvider;
import ru.infotecs.crypto.gost28147.Gost28147SecretKey;
import ru.infotecs.crypto.keys.ViPNetKeyProtectionParameter;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.*;
import java.security.cert.CertificateException;

/**
 * Created by Павел on 23.11.2015.
 */
public class Soap {
    public static void main(String[] args) throws NoSuchProviderException, NoSuchAlgorithmException {
        // регистрируем провайдер, предоставляющий реализации ГОСТ алгоритмов
        Security.addProvider(new ViPNetProvider());
        MessageDigest digestDriver = MessageDigest.getInstance("GOST3411-94","ViPNet");
        digestDriver.update("".getBytes());
        byte[] digestValue = digestDriver.digest();
        System.out.println(org.apache.commons.codec.binary.Base64.encodeBase64URLSafeString(digestValue));
    }

    public static void containerKeyStore() throws NoSuchProviderException, KeyStoreException, CertificateException, NoSuchAlgorithmException, IOException, UnrecoverableEntryException {
        // получение хранилища для работы с одним контейнером в формате ViPNet
        KeyStore keyStore = KeyStore.getInstance(
                "ViPNetContainer", // тип хранилища
                "ViPNet"           // название провайдера
        );

        // создание пустого хранилища
        keyStore.load(null, null);

        // подготовка потока ввода с данными ViPNet контейнераs
        InputStream inputStream = null;
        // загрузка хранилища
        keyStore.load(inputStream, null);

        // подготовка потока вывода для получениях данных ViPNet контейнера
        OutputStream outputStream = null;
        // сохранение хранилища
        keyStore.store(outputStream, null);


        // подготовка названия записи
        String alias = "key";
        // подготовка пароля
        char[] password = "password".toCharArray();


        // извлечение записи с использованием пароля
        PrivateKey pkey = (PrivateKey) keyStore.getKey(alias, password);
        System.out.print(pkey);
        // добавление записи с использованием пароля
        keyStore.setKeyEntry(alias, pkey, password, null);

        // подготовка ключа защиты (key encryption  key)
        Gost28147SecretKey kek = null;
        // подготовка параметра защиты
        KeyStore.ProtectionParameter protectionParameter = new ViPNetKeyProtectionParameter(kek);
        // извлечение записи с использованием параметра защиты
        KeyStore.Entry entry = keyStore.getEntry(alias, protectionParameter);
        // добавление записи с использованием параметра защиты
        keyStore.setEntry(alias, entry, protectionParameter);

    }
}
