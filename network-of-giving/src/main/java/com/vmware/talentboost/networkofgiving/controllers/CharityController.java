package com.vmware.talentboost.networkofgiving.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.vmware.talentboost.networkofgiving.models.Charity;
import com.vmware.talentboost.networkofgiving.models.User;
import com.vmware.talentboost.networkofgiving.services.charity.ICharityService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(value = "api/v1/charities", produces = MediaType.APPLICATION_JSON_VALUE)
public class CharityController {
    private final ICharityService charityService;

    public CharityController(ICharityService charityService) {
        this.charityService = charityService;
    }

    @GetMapping("/{title}")
    public Charity getCharity(@PathVariable("title") String title) {
        return charityService.getCharity(title);
    }

    @GetMapping
    public List<Charity> getAllCharities() {
        return charityService.getAllCharities();
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void addCharity(@RequestParam(value = "imageFile", required = false) MultipartFile imageFile,
                           @RequestParam("body") String body) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Charity charity = objectMapper.readValue(body, Charity.class);
        if (imageFile != null) {
            charity.setImage(imageFile.getBytes());
        }

        charityService.addCharity(charity);
    }


    @PutMapping(path = "/{title}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCharity(@PathVariable("title") String title, @RequestBody @Valid Charity charity) {
        charityService.updateCharity(title, charity);
    }

    @PostMapping("/{title}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCharity(@PathVariable("title") String title,@RequestBody @Valid Charity charity) {
        charityService.deleteCharity(charity,title);
    }


    @GetMapping("/{title}/participants")
    public List<User> getParticipantsForCharity(@PathVariable("title") String title) {
        return charityService.getParticipantsForCharity(title);
    }

    @GetMapping("/{title}/donations")
    public List<User> getDonationsForCharity(@PathVariable("title") String title) {
        return charityService.getDonationsForCharity(title);
    }

    @GetMapping("/{title}/creator")
    public User getCreatorForCharity(@PathVariable("title") String title) {
        return charityService.getCreatorForCharity(title);
    }


    @PostMapping(path = "/donate/{user_id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void donateMoneyForCharity(@RequestBody @Valid Charity charity, @RequestParam(name = "money", required = true) double money, @PathVariable("user_id") int userId) {
        charityService.donateMoneyForCharity(charity, userId, money);
    }

    @PostMapping(path = "/participate/{user_id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void participateInCharity(@RequestBody @Valid Charity charity, @PathVariable("user_id") int userId) {
        charityService.participateInCharity(charity, userId);
    }

    @GetMapping("/filtered")
    public List<Charity> getFilteredCharitiesByTitle(@RequestParam(name = "title", required = true) String title){
        return charityService.getFilteredCharitiesByTitle(title);
    }


    @PostMapping(path = "/suggestion/{userId}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public double getSuggestionForDonation(@RequestBody @Valid Charity charity, @PathVariable("userId") int userId){
      return charityService.getSuggestionForDonation(charity,userId);
    }


    @ExceptionHandler(EmptyResultDataAccessException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void userNotFoundExceptionHandler() {
    }

    @ExceptionHandler(SQLException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void incorrectSQLExceptionHandler() {
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void illegalArgumentsHandler() {
    }

    @ExceptionHandler(InvalidFormatException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public void invalidFormatExceptionHandler() {
    }


}