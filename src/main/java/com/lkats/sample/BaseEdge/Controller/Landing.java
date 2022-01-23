package com.lkats.sample.BaseEdge.Controller;

import com.lkats.sample.BaseEdge.annotation.Execute;
import com.lkats.sample.BaseEdge.annotation.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("landing")
public class Landing {

    @Query
    public ResponseEntity getLanding() {
        return ResponseEntity.ok().body("Hello.");
    }

    @Execute
    public ResponseEntity postLanding(@RequestBody String incomingText) {
        return ResponseEntity.accepted().body(incomingText);
    }
}
