package com.project.waglewagle.broad;

import com.project.waglewagle.broad.dto.BroadPostRequest;
import com.project.waglewagle.broad.dto.BroadResponse;
import com.project.waglewagle.broad.dto.BroadStyleDTO;
import com.project.waglewagle.broad.dto.BroadUpdateRequest;
import com.project.waglewagle.global.config.security.PrincipalDetail;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping(value = "/api/v1")
@NoArgsConstructor
public class BroadController {

    @Autowired
    BroadService broadService;

    @GetMapping("/broads/{Broad}")
    public ResponseEntity<BroadResponse> getBroad(@PathVariable("Broad") String broad){
        try{
            BroadResponse response = broadService.getBroad(Long.parseLong(broad));
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch (NumberFormatException e)
        {
            BroadResponse response = broadService.getBroadByUrl(broad);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

    }

    @PostMapping("/broads")
    public ResponseEntity<String> postBroad(@RequestBody BroadPostRequest request, @AuthenticationPrincipal PrincipalDetail principalDetail){
        broadService.postBroad(request,principalDetail.getUser());
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
