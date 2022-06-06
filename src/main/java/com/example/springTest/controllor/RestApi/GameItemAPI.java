package com.example.springTest.controllor.RestApi;

import com.example.springTest.service.WorldCupGameItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GameItemAPI {

    private final WorldCupGameItemService service;
    @GetMapping(value = "/image/{id}",produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getImage(@PathVariable("id") String gameItemId){
        return service.getImageById(Long.parseLong(gameItemId));
    }
}
