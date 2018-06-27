package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class FacadeMapperTestSuite {
    @InjectMocks
    private TrelloMapper trelloMapper;

    @Test
    public void testMapToCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("exampleCard", "Card", "1", "11");
        TrelloCard trelloCard = new TrelloCard("exampleCard", "Card", "1", "11");

        //When
        TrelloCard mappedCard = trelloMapper.mapToCard(trelloCardDto);

        //Then
        assertEquals("exampleCard", mappedCard.getName());
        assertEquals("Card", mappedCard.getDescription());
        assertEquals("11", trelloMapper.mapToCardDto(trelloCard).getListId());
    }

    @Test
    public void testMapToList() {
        //Given
        List<TrelloList> trelloList = new ArrayList<>();
        trelloList.add(new TrelloList("1", "Card", false));

        List<TrelloListDto> trelloListDto = new ArrayList<>();
        trelloListDto.add(new TrelloListDto("1", "Card", false));

        //When
        List<TrelloListDto> theList = trelloMapper.mapToListDto(trelloList);

        //Then
        assertEquals("Card", theList.get(0).getName());
        assertEquals("1", theList.get(0).getId());
    }
    @Test
    public void testMapToBoard() {
        //Given
        List<TrelloList> trelloList = new ArrayList<>();
        trelloList.add(new TrelloList("1", "Card", false));

        List<TrelloListDto> trelloListDto = new ArrayList<>();
        trelloListDto.add(new TrelloListDto("1", "Card", false));

        List<TrelloBoard> trelloBoardList = new ArrayList<>();
        trelloBoardList.add(new TrelloBoard("1", "board1", trelloList));

        List<TrelloBoardDto> trelloBoardDtos = new ArrayList<>();
        trelloBoardDtos.add(new TrelloBoardDto("1", "board1", trelloListDto));

        //When
        List<TrelloBoard> mappedBoardList = trelloMapper.mapToBoards(trelloBoardDtos);
        List<TrelloBoardDto> theBoardList = trelloMapper.mapToBoardsDto(trelloBoardList);

        //Then
        assertEquals(trelloBoardList.get(0).getName(), mappedBoardList.get(0).getName());
        assertEquals(trelloBoardDtos.get(0).getName(), theBoardList.get(0).getName());
    }

    @Test
    public void mapToBoardsDtoTest() {
        //Given

        List<TrelloListDto> trelloListDto = new ArrayList<>();
        trelloListDto.add(new TrelloListDto("1", "Card", false));


        List<TrelloBoardDto> trelloBoardDtos = new ArrayList<>();
        trelloBoardDtos.add(new TrelloBoardDto("1", "board1", trelloListDto));
        trelloBoardDtos.add(new TrelloBoardDto("2", "board2", trelloListDto));

        //when
        List<TrelloBoard> mappedBoardList = trelloMapper.mapToBoards(trelloBoardDtos);


        //then
        assertEquals(2, mappedBoardList.size());
        assertEquals("board1", trelloMapper.mapToBoardsDto(mappedBoardList).get(0).getName());
        assertEquals("board2", trelloMapper.mapToBoardsDto(mappedBoardList).get(1).getName());
        assertEquals("2", trelloMapper.mapToBoardsDto(mappedBoardList).get(1).getId());
    }

    @Test
    public void testMapToListDto() {
        //Given
        List<TrelloList> trelloList = new ArrayList<>();
        trelloList.add(new TrelloList("1", "Card", false));
        trelloList.add(new TrelloList("2", "Card2", false));

        List<TrelloListDto> trelloListDto = new ArrayList<>();
        trelloListDto.add(new TrelloListDto("1", "Card", false));

        //When
        List<TrelloList> mappedList = trelloMapper.mapToList(trelloListDto);
        List<TrelloListDto> theList = trelloMapper.mapToListDto(trelloList);

        //Then
        assertEquals("Card", mappedList.get(0).getName());
        assertEquals("Card", theList.get(0).getName());
        assertEquals(2, trelloMapper.mapToListDto(trelloList).size());
        assertEquals("2", trelloMapper.mapToListDto(trelloList).get(1).getId());
        assertEquals("Card2", trelloMapper.mapToListDto(trelloList).get(1).getName());
    }

    @Test
    public void testMapToCardDto() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("exampleCard", "Card", "1", "11");
        TrelloCard trelloCard = new TrelloCard("exampleCard", "Card", "1", "11");

        //When
        TrelloCardDto dtoCard = trelloMapper.mapToCardDto(trelloCard);

        //Then
        assertEquals("exampleCard", dtoCard.getName());
        assertEquals("Card", dtoCard.getDescription());
        assertEquals("11", dtoCard.getListId());
    }
}
