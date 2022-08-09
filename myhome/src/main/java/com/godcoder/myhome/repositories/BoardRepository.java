package com.godcoder.myhome.repositories;

import com.godcoder.myhome.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long>
{
    List<Board> findByTitle(String title);
}
