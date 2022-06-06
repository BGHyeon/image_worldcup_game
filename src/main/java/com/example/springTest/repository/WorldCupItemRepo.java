package com.example.springTest.repository;

import com.example.springTest.entity.WorldCupItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorldCupItemRepo extends JpaRepository<WorldCupItem,Long> {
    @Query("Select this_.games from WorldCupGame this_ join this_.games as t where this_.id=:id order by t.winCount desc")
    public List<WorldCupItem> getWorldCupItemsResultByItemsId(@Param("id")long itemId);
}
