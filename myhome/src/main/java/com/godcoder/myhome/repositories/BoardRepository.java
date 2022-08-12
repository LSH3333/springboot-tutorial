package com.godcoder.myhome.repositories;

import com.godcoder.myhome.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long>
{
    // JpaRepository의 메소드 명명 규칙에 따라야함
    List<Board> findByTitle(String title);
    List<Board> findByTitleOrContent(String title, String content);

}
