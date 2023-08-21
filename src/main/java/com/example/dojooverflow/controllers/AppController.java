package com.example.dojooverflow.controllers;

import com.example.dojooverflow.models.Answer;
import com.example.dojooverflow.models.Question;
import com.example.dojooverflow.models.Tag;
import com.example.dojooverflow.models.TagQuestion;
import com.example.dojooverflow.services.AnswerService;
import com.example.dojooverflow.services.QuestionService;
import com.example.dojooverflow.services.TagQuestionService;
import com.example.dojooverflow.services.TagService;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AppController {
    private final AnswerService answerService;
    private final QuestionService questionService;
    private final TagService tagService;
    private final TagQuestionService tagQuestionService;

    public AppController(AnswerService answerService, QuestionService questionService, TagService tagService, TagQuestionService tagQuestionService){
        this.answerService = answerService;
        this.questionService = questionService;
        this.tagService = tagService;
        this.tagQuestionService = tagQuestionService;
    }

    @GetMapping("/")
    public String homePage(Model model){
        List<Question> listaQuestions = questionService.getAllQuestions();
        model.addAttribute("questions", listaQuestions);
        return "index";
    }

    @GetMapping("/questions/new")
    public String renderNewQuestion(@ModelAttribute("questionTagObject") TagQuestion tagQuestionObject){
        return "newquestion";
    }

    @PostMapping("/questions/new")
    public String sendFormNewQuestion(@Valid @ModelAttribute("questionTagObject") TagQuestion questionTagObject, BindingResult result){
        if(result.hasErrors()){
            System.out.println(result);
            return "newquestion";
        }else{
            Question questionDeForm = questionTagObject.getQuestion();
            Tag tagDeForm = questionTagObject.getTag();

            System.out.println(questionDeForm);
            System.out.println(tagDeForm);

            questionService.createQuestion(questionDeForm);
            tagService.createTag(tagDeForm);
            tagQuestionService.saveTagQuestion(questionTagObject);


            return "redirect:/";
        }
    }

    @GetMapping("/questions/{id}")
    public String renderDetailQuestion(@PathVariable("id") Long id, Model viewModel, @ModelAttribute("answerForm") Answer answerForm){
        Question questionParaDetallar = questionService.getQuestionById(id);
        viewModel.addAttribute("questionDetallada", questionParaDetallar);
        List<Answer> listAnswers = answerService.getAnswerByQuestion(id);

        viewModel.addAttribute("listaDeAnswers", listAnswers);

        return "questiondetail";
    }

    @PostMapping("/questions/{id}")
    public String postAnswer(@PathVariable Long id,@Valid @ModelAttribute("answerForm") Answer answerForm, BindingResult result){

        if(result.hasErrors()){
            return  "questiondetail";
        }else{
            Question questionDetail = questionService.getQuestionById(id);

            Answer newAnswer = new Answer();
            newAnswer.setQuestions(questionDetail);
            newAnswer.setAnswer(answerForm.getAnswer());
            answerService.createAnswer(newAnswer);

            return "redirect:/questions/"+id;
        }
    }

}