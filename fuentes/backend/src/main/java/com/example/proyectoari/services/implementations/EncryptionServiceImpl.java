package com.example.proyectoari.services.implementations;

import com.example.proyectoari.services.EncryptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EncryptionServiceImpl implements EncryptionService {

    @Override
    public String vigenereEncrypt(String cardNumber, String encryptionKey) {
        StringBuilder cardNumberEncrypted = new StringBuilder();
        encryptionKey = encryptionKey.toUpperCase();

        for (int i = 0, j = 0; i < cardNumber.length(); i++) {
            char number = cardNumber.charAt(i);

            if (Character.isDigit(number)) {
                int digit = Character.getNumericValue(number);
                int offset = (encryptionKey.charAt(j % encryptionKey.length()) - 'A') % 10;
                int encryptedDigit = (digit + offset) % 10;

                cardNumberEncrypted.append((char) ('A' + encryptedDigit));
                j++;
            } else {
                cardNumberEncrypted.append(number);
            }
        }

        return cardNumberEncrypted.toString();
    }
}
