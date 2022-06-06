package com.example.springTest.service;

import com.example.springTest.repository.WorldCupItemRepo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Getter
@RequiredArgsConstructor
public class WorldCupGameItemService {

    private final WorldCupItemRepo repo;
    public byte[] getImageById(long id){
        return repo.findById(id).get().getImage();
    }
}
