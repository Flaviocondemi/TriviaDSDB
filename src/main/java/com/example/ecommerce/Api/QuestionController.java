package com.example.ecommerce.Api;

import com.example.ecommerce.DemoModel.Question;
import com.example.ecommerce.DemoModel.QuestionRepository;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    QuestionRepository questionRepository;

    Counter easyQuestionCount,
            mediumQuestionCount,
            hardQuestionCount;
    Counter questionAddedCount;
    Counter historyCount,
            sportsCount,
            geographyCount,
            artCount,
            politicsCount,
            animalsCount;

    public QuestionController(MeterRegistry registry){
        easyQuestionCount = Counter.builder("easy_question_counter")
                    .description("counter of all the time easy questions are used ")
                    .register(registry);
        mediumQuestionCount = Counter.builder("medium_question_counter")
                    .description("counter of all the time medium questions are used ")
                    .register(registry);
        hardQuestionCount = Counter.builder("hard_question_counter")
                    .description("counter of all the time hard questions are used ")
                    .register(registry);
        questionAddedCount = Counter.builder("question_added")
                    .description("Counting of all the question added from the users")
                    .register(registry);
        historyCount = Counter.builder("history_question_counter")
                .description("Counting of all the history questions ")
                .register(registry);
        sportsCount = Counter.builder("sport_question_counter")
                .description("Counting of all the sport questions ")
                .register(registry);
        geographyCount = Counter.builder("geography_question_counter")
                .description("Counting of all the geography questions ")
                .register(registry);
        artCount = Counter.builder("art_question_counter")
                .description("Counting of all the art questions")
                .register(registry);
        politicsCount = Counter.builder("politics_question_counter")
                .description("Counting of all the politics questions")
                .register(registry);
        animalsCount = Counter.builder("animals_question_counter")
                .description("Counting of all the animals questions")
                .register(registry);

    }

    @GetMapping(value= "/get", params = {"difficulty", "amount"})
    public @ResponseBody Page<Question> getQuestionByDifficulty(@RequestParam(required = false) String difficulty,
                                                              @RequestParam(required = false) int amount){


        switch(difficulty){
            case "easy":
                easyQuestionCount.increment();
                break;
            case "medium":
                mediumQuestionCount.increment();
                break;
            case "hard":
                hardQuestionCount.increment();
                break;
        }

        if(amount > 50){
            return questionRepository.findByDifficulty(difficulty,PageRequest.of(0, 50));
        }else {
            return questionRepository.findByDifficulty(difficulty, PageRequest.of(0, amount));
        }
    }

    @GetMapping(value= "/get", params = {"category", "amount"})
    public @ResponseBody Page<Question> getQuestionByCategory(@RequestParam(required = false) String category,
                                                            @RequestParam(required = false) int amount){

        switch(category){
            case "History":
                historyCount.increment();
                break;
            case "Sports":
                sportsCount.increment();
                break;
            case "Geography":
                geographyCount.increment();
                break;
            case "Art":
                artCount.increment();
                break;
            case "Politics":
                politicsCount.increment();
                break;
            case "Animals":
                animalsCount.increment();
                break;
        }
        if(amount > 50){
            return questionRepository.findByCategory(category,PageRequest.of(0, 50));
        }else {
            return questionRepository.findByCategory(category, PageRequest.of(0, amount));
        }
    }

    @GetMapping(value= "/get", params = {"type", "amount"})
    public @ResponseBody Page<Question> getQuestionByType(@RequestParam(required = false) String type,
                                                                       @RequestParam(required = false) int amount){

        if(amount > 50){
            return questionRepository.findByType(type,PageRequest.of(0, 50));
        }else {
            return questionRepository.findByType(type, PageRequest.of(0, amount));
        }
    }

    @GetMapping(value= "/get", params = {"type","category", "amount"})
    public @ResponseBody Page<Question> getQuestionByTypeAndCategory(@RequestParam(required = false) String type,
                                                    @RequestParam(required = false) String category,
                                                    @RequestParam(required = false) int amount){

        switch(category){
            case "History":
                historyCount.increment();
                break;
            case "Sports":
                sportsCount.increment();
                break;
            case "Geography":
                geographyCount.increment();
                break;
            case "Art":
                artCount.increment();
                break;
            case "Politics":
                politicsCount.increment();
                break;
            case "Animals":
                animalsCount.increment();
                break;
        }

        if(amount > 50){
            return questionRepository.findByTypeAndCategory(type, category, PageRequest.of(0, 50));
        }else {
            return questionRepository.findByTypeAndCategory(type, category, PageRequest.of(0, amount));
        }
    }

    @GetMapping(value= "/get", params = {"difficulty", "type", "amount"})
    public @ResponseBody Page<Question> getQuestionByTypeAndDifficulty(@RequestParam(required = false) String difficulty,
                                                    @RequestParam(required = false) String type,
                                                    @RequestParam(required = false) int amount){
        switch(difficulty){
            case "easy":
                easyQuestionCount.increment();
                break;
            case "medium":
                mediumQuestionCount.increment();
                break;
            case "hard":
                hardQuestionCount.increment();
                break;
        }

        if(amount > 50){
            return questionRepository.findByTypeAndDifficulty(difficulty, type, PageRequest.of(0, 50));
        }else {
            return questionRepository.findByTypeAndDifficulty(difficulty, type, PageRequest.of(0, amount));
        }
    }

    @GetMapping(value= "/get", params = {"difficulty","category", "amount"})
    public @ResponseBody Page<Question> getQuestionByCategoryAndDifficulty(@RequestParam(required = false) String difficulty,
                                                    @RequestParam(required = false) String category,
                                                    @RequestParam(required = false) int amount){
        switch(difficulty){
            case "easy":
                easyQuestionCount.increment();
                break;
            case "medium":
                mediumQuestionCount.increment();
                break;
            case "hard":
                hardQuestionCount.increment();
                break;
        }

        switch(category){
            case "History":
                historyCount.increment();
                break;
            case "Sports":
                sportsCount.increment();
                break;
            case "Geography":
                geographyCount.increment();
                break;
            case "Art":
                artCount.increment();
                break;
            case "Politics":
                politicsCount.increment();
                break;
            case "Animals":
                animalsCount.increment();
                break;
        }

        if(amount > 50){
            return questionRepository.findByDifficultyAndCategory(difficulty, category, PageRequest.of(0, 50));
        }else {
            return questionRepository.findByDifficultyAndCategory(difficulty, category, PageRequest.of(0, amount));
        }
    }

    @GetMapping(value= "/get", params = {"difficulty", "type","category", "amount"})
    public @ResponseBody Page<Question> getQuestion(@RequestParam(required = false) String difficulty,
                               @RequestParam(required = false) String type,
                               @RequestParam(required = false) String category,
                               @RequestParam(required = false) int amount){
        switch(difficulty){
            case "easy":
                easyQuestionCount.increment();
                break;
            case "medium":
                mediumQuestionCount.increment();
                break;
            case "hard":
                hardQuestionCount.increment();
                break;
        }

        switch(category){
            case "History":
                historyCount.increment();
                break;
            case "Sports":
                sportsCount.increment();
                break;
            case "Geography":
                geographyCount.increment();
                break;
            case "Art":
                artCount.increment();
                break;
            case "Politics":
                politicsCount.increment();
                break;
            case "Animals":
                animalsCount.increment();
                break;
        }

        if(amount > 50){
            return questionRepository.findByCategoryAndDifficultyAndType(difficulty, type, category, PageRequest.of(0, 50));
        }else {
            return questionRepository.findByCategoryAndDifficultyAndType(difficulty, type, category, PageRequest.of(0, amount));
        }
    }

    @GetMapping("/all")
    public @ResponseBody Iterable<Question> getAllQuestion(){
            return questionRepository.findAll();
    }

    @PostMapping(path = "/add")
    public @ResponseBody Question addQuestion(@RequestBody Question question){
        questionAddedCount.increment();
        return questionRepository.save(question);
    }

    @DeleteMapping("/delete")
    public @ResponseBody void deleteQuestion(@RequestBody Question question){
       questionRepository.delete(question);
    }


}
