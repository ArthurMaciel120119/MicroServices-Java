package com.sd.user.Controler;

import com.sd.user.Dto.UserRecordDto;
import com.sd.user.Services.UserService;
import com.sd.user.models.UserModel;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
public class UserController {

    final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<UserModel> saveUser(@RequestBody @Valid UserRecordDto userRecordDto){
        var userModel = new UserModel();
        BeanUtils.copyProperties(userRecordDto,userModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(userModel));
    }
}
