package com.example.springTest.repository;

import com.example.springTest.entity.WorldCupGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface WorldCupGameRepo extends JpaRepository<WorldCupGame,Long> {
    @Query("Select this_ from WorldCupGame this_ join fetch this_.makeMember order by this_.playCount desc")
    public ArrayList<WorldCupGame> getAllGames();

    @Query("Select this_ from WorldCupGame this_ join this_.games as t where t.id=:id")
    public WorldCupGame findByItemId(@Param("id")long itemId);
}
