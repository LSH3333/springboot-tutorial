package com.godcoder.myhome.controller;

import java.util.List;

import com.godcoder.myhome.model.Board;
import com.godcoder.myhome.repositories.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
class BoardApiController
{
    @Autowired
    private BoardRepository repository;

    @GetMapping("/boards")
    List<Board> all(@RequestParam(required = false) String title)
    {
        if(StringUtils.isEmpty(title))
        {
            return repository.findAll();
        }
        else
        {
            return repository.findByTitle(title);
        }

    }
    // end::get-aggregate-root[]

    @PostMapping("/boards")
    Board newboard(@RequestBody Board newboard) {
        return repository.save(newboard);
    }

    // Single item

    @GetMapping("/boards/{id}")
    Board one(@PathVariable Long id) {

        return repository.findById(id).orElseGet(null);
    }

    @PutMapping("/boards/{id}")
    Board replaceboard(@RequestBody Board newboard, @PathVariable Long id) {

        return repository.findById(id)
                .map(board -> {
                    board.setTitle(newboard.getTitle());
                    board.setTitle(newboard.getContent());
                    return repository.save(board);
                })
                .orElseGet(() -> {
                    newboard.setId(id);
                    return repository.save(newboard);
                });
    }

    @DeleteMapping("/boards/{id}")
    void deleteBoard(@PathVariable Long id) {
        repository.deleteById(id);
    }
}