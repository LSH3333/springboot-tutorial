package com.godcoder.myhome.controller;

import com.godcoder.myhome.model.Board;
import com.godcoder.myhome.repositories.BoardRepository;
import com.godcoder.myhome.validator.BoardValidator;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController
{
    @Autowired // auto injection
    private BoardRepository boardRepository;
    @Autowired
    private BoardValidator boardValidator;

    @GetMapping("/list")
    public String list(Model model)
    {
        List<Board> boards = boardRepository.findAll();
        model.addAttribute("boards", boards);
        return "board/list";
    }

    @GetMapping("/form")
    public String form(Model model, @RequestParam(required = false) Long id)
    {
        if(id == null) // 새로운 아이디
        {
            model.addAttribute("board", new Board());
        }
        else // 이미 존재하는 아이디
        {
            Board board = boardRepository.findById(id).orElse(null);
            model.addAttribute("board", board);
        }

        return "board/form";
    }

    @PostMapping("/form")
    public String greetingSubmit(@Valid Board board, BindingResult bindingResult) // 커맨드 객체
    {
        boardValidator.validate(board, bindingResult);
        if(bindingResult.hasErrors()) return "board/form";

        boardRepository.save(board);
        return "redirect:/board/list";
    }
}
