package ru.job4j.accidents;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Password {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String pwd = encoder.encode("secret");
        System.out.println(pwd);
    }
}
