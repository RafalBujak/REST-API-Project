package com.crud.tasks.controller;


import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sun.awt.geom.AreaOp;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/trello")
public class TrelloController {
    @Autowired
    private TrelloClient trelloClient;

    @RequestMapping(method = RequestMethod.GET, value = "getTrelloBoards")
    public void getTrelloBoards() {

        Optional<String> nameByBord = Optional.of("Kodilla");

        List<TrelloBoardDto> trelloBoards = trelloClient.getTrelloBoards();

        trelloBoards.stream().filter(trelloBoardDto -> trelloBoardDto.equals("Kodilla"))
                .forEach(trelloBoardDto -> System.out.println(trelloBoardDto.getId()
                        + " " + trelloBoardDto.getName()));

        try {
            System.out.println(nameByBord.filter(g -> g.equals(trelloBoards.iterator().next().getName().equals("Kodilla"))));
        } catch (NullPointerException x) {
            System.out.println("Null");
        }
    }
}
