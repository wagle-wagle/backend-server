package com.project.waglewagle.broad;

import com.project.waglewagle.broad.dto.BroadPostRequest;
import com.project.waglewagle.broad.dto.BroadResponse;
import com.project.waglewagle.broad.dto.BroadStyleDTO;
import com.project.waglewagle.broad.dto.BroadUpdateRequest;
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

    @PatchMapping("/broads/{BroadId}")
    public ResponseEntity<BroadResponse> changeTitle(@PathVariable("BroadId") Long broadId, @RequestBody BroadUpdateRequest request){
        BroadResponse response = broadService.changeTitle(broadId,request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/broads/style/{BroadStyleId}")
    public ResponseEntity<BroadStyleDTO> changeStyle(@PathVariable("BroadStyleId") Long broadStyleId, @RequestBody BroadStyleDTO request){
        BroadStyleDTO broadStyle = broadService.changeStyle(broadStyleId,request);
        return new ResponseEntity<>(broadStyle,HttpStatus.OK);
    }

}