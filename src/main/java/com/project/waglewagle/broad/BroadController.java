package com.project.waglewagle.broad;

import com.project.waglewagle.broad.dto.BroadPostRequest;
import com.project.waglewagle.broad.dto.BroadResponse;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1")
@NoArgsConstructor
public class BroadController {

    @Autowired
    BroadService broadService;

    @GetMapping("/broads/{BroadId}")
    public ResponseEntity<BroadResponse> getBroad(@PathVariable("BroadId") Long broadId){
        BroadResponse response = broadService.getBroad(broadId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/broads")
    public ResponseEntity<String> postBroad(@RequestBody BroadPostRequest request){
        broadService.postBroad(request);
        return new ResponseEntity<>("good",HttpStatus.OK);
    }


}
