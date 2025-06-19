package com.example.proyectoari.services.implementations;

import com.example.proyectoari.services.DecryptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DecryptionServiceImpl implements DecryptionService {

    @Override
    public String vigenereDecrypt(String cardNumberEncrypted, String encryptionKey) {
        StringBuilder cardNumberDecrypted = new StringBuilder();
        encryptionKey = encryptionKey.toUpperCase();

        for (int i = 0, j = 0; i < cardNumberEncrypted.length(); i++) {
            char letter = cardNumberEncrypted.charAt(i);

            if (letter >= 'A' && letter <= 'J') {
                int encryptedValue = letter - 'A';
                int offset = (encryptionKey.charAt(j % encryptionKey.length()) - 'A') % 10;
                int decryptedDigit = (encryptedValue - offset + 10) % 10;

                cardNumberDecrypted.append(decryptedDigit);
                j++;
            } else {
                cardNumberDecrypted.append(letter);
            }
        }

        return cardNumberDecrypted.toString();
    }
}
