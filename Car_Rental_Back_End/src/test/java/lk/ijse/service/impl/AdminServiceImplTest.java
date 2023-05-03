package lk.ijse.service.impl;

import lk.ijse.config.WebAppConfig;
import lk.ijse.config.WebRootConfig;
import lk.ijse.entity.Admin;
import lk.ijse.repo.AdminRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.jupiter.api.Assertions.*;


@WebAppConfiguration
@ContextConfiguration(classes = {WebAppConfig.class, WebRootConfig.class})
@ExtendWith(SpringExtension.class)
class AdminServiceImplTest {

    @Autowired
    AdminRepo adminRepo;

    @Autowired
    ModelMapper modelMapper;

    @Test
    void login() {
        Admin login = adminRepo.login("kamal", "1234");
        System.out.println(login.toString());
    }
}