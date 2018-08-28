package com.crud.tasks.trello.client;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TaskMapper;
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
public class mappersTestSuite {

    @Autowired
    private TrelloMapper trelloMapper;

    @Autowired
    private TaskMapper taskMapper;

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

    @Test
    public void testMapToTask() {
        //Given
        TaskDto taskDto = new TaskDto(1L, "title", "content");
        //When
        Task task = taskMapper.mapToTask(taskDto);
        //Then
        Assert.assertEquals(1, task.getId(), 0);
        Assert.assertEquals("title", task.getTitle());
        Assert.assertEquals("content", task.getContent());
    }

    @Test
    public void testMapToTaskDto() {
        //Given
        Task task = new Task(1L, "title", "content");
        //When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);
        //Then
        Assert.assertEquals(1, taskDto.getId(), 0);
        Assert.assertEquals("title", taskDto.getTitle());
        Assert.assertEquals("content", taskDto.getContent());
    }

    @Test
    public void testMapToTaskDtoList() {
        //Given
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task());
        tasks.add(new Task());
        tasks.add(new Task(1L, "title", "content"));
        //When
        List<TaskDto> taskDtos = taskMapper.mapToTaskDtoList(tasks);
        //Then
        Assert.assertNotNull(taskDtos);
        Assert.assertEquals(3, taskDtos.size());
        Assert.assertEquals("title", taskDtos.get(2).getTitle());
    }

}