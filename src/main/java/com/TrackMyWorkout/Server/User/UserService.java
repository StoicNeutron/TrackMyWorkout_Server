package com.TrackMyWorkout.Server.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User saveNewUser(String userName, String password, String email) {
        // safe check
        if(userName == null || password == null || email == null){
            return null;
        }
        if(userName.isBlank() || password.isBlank() || email.isBlank()){
            return null;
        }
        String salt = generateSalt();
        String hashedPassword = null;
        try {
            hashedPassword = sha256Hash(password + salt);
        }catch (NoSuchAlgorithmException e){
            //
        }
        return userRepository.save(new User(userName, hashedPassword, salt, email));
    }

    /**
     * Helper Function.
     * Using random ASCII value between 32 and 126 (inclusive).
     * @return random_string of length 5.
     */
    private static String generateSalt() {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            int asciiValue = random.nextInt(95) + 32;
            char randomChar = (char) asciiValue;
            stringBuilder.append(randomChar);
        }
        return stringBuilder.toString();
    }

    private static String sha256Hash(String input) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] hashedBytes = messageDigest.digest(input.getBytes());
        StringBuilder hexStringBuilder = new StringBuilder();
        for (byte hashedByte : hashedBytes) {
            String hex = Integer.toHexString(0xff & hashedByte);
            if (hex.length() == 1) {
                hexStringBuilder.append('0');
            }
            hexStringBuilder.append(hex);
        }
        return hexStringBuilder.toString();
    }
}
