package com.crud.tasks.trello.client;

import com.crud.tasks.domain.TrelloBoardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class TrelloClient {

    @Value("${trello.api.endpoint.prod}")
    private String trelloApiEndpoint;

    @Value("${trello.app.key}")
    private String trelloAppKey;

    @Value("${trello.app.token}")
    private String trelloToken;

    @Value("${trello.app.username}")
    private String userName;

    @Autowired
    private RestTemplate restTemplate;

    private URI makeUrl() {
        URI url = UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint + "/members/" + userName + "/boards")
                .queryParam("key", trelloAppKey)
                .queryParam("token", trelloToken).build().encode().toUri();

        return url;
    }

public List<TrelloBoardDto> getTrelloBoards() {

        TrelloBoardDto[] boardsresponse = restTemplate.getForObject(makeUrl(), TrelloBoardDto[].class);

        Optional response = Optional.of(boardsresponse);

        response.isPresent();

            if (response!= null) {
                 return Arrays.asList(boardsresponse);
            }
        return new ArrayList<>();
    }
}
