package com.lkats.sample.BaseEdge.Controller;

import com.lkats.sample.BaseEdge.annotation.Execute;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login")
public class Login {

    @Execute
    public ResponseEntity postLogin(@RequestBody String loginCredentials) {
        return ResponseEntity.accepted().body(loginCredentials);
    }
}
