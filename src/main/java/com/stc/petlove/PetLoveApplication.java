package com.stc.petlove;

import com.stc.petlove.entities.TaiKhoan;
import com.stc.petlove.repositories.UserRepository;
import com.stc.petlove.utils.EnumRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class PetLoveApplication implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(PetLoveApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        if(userRepository.count()==0){
            TaiKhoan user = new TaiKhoan("tuvh","vohungtu2000@gmail.com","123456",
                    Arrays.asList(EnumRole.ROLE_ADMIN.name()));
            userRepository.save(user);
        }
    }

}
