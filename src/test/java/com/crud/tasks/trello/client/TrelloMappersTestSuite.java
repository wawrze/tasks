package com.crud.tasks.trello.client;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloMappersTestSuite {

    @Autowired
    private TrelloMapper trelloMapper;

    @Test
    public void testMapToBoards() {
        //Given
        TrelloListDto trelloListDto = new TrelloListDto("some list", "0", true);
        List<TrelloListDto> trelloListDtos = new ArrayList<>();
        trelloListDtos.add(trelloListDto);
        TrelloBoardDto boardDto = new TrelloBoardDto("1", "some board", trelloListDtos);
        List<TrelloBoardDto> boardDtos = new ArrayList<>();
        boardDtos.add(boardDto);
        //When
        List<TrelloBoard> resultList = trelloMapper.mapToBoards(boardDtos);
        //Then
        Assert.assertEquals(1, resultList.size());
    }

    @Test
    public void testMapToBoardsDto() {
        //Given
        TrelloList trelloList = new TrelloList("some list", "0", true);
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(trelloList);
        TrelloBoard board = new TrelloBoard("1", "some board", trelloLists);
        List<TrelloBoard> boards = new ArrayList<>();
        boards.add(board);
        //When
        List<TrelloBoardDto> resultList = trelloMapper.mapToBoardsDto(boards);
        //Then
        Assert.assertEquals(1, resultList.size());
    }

    @Test
    public void testMapToCard() {
        //Given
        TrelloCardDto cardDto = new TrelloCardDto("some card", "some description", "1", "1");
        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(cardDto);
        //Then
        Assert.assertEquals(cardDto.getName(), trelloCard.getName());
        Assert.assertEquals(cardDto.getDescription(), trelloCard.getDescription());
        Assert.assertEquals(cardDto.getPos(), trelloCard.getPos());
        Assert.assertEquals(cardDto.getListId(), trelloCard.getListId());
    }

    @Test
    public void testMapToCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard("some card", "some description", "1", "1");
        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);
        //Then
        Assert.assertEquals(trelloCard.getName(), trelloCardDto.getName());
        Assert.assertEquals(trelloCard.getDescription(), trelloCardDto.getDescription());
        Assert.assertEquals(trelloCard.getPos(), trelloCardDto.getPos());
        Assert.assertEquals(trelloCard.getListId(), trelloCardDto.getListId());
    }

}