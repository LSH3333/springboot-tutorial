package com.godcoder.myhome.repositories;

import com.godcoder.myhome.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long>
{

}