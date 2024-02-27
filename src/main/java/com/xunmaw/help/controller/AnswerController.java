package com.xunmaw.help.controller;

import com.xunmaw.help.entity.Answer;
import com.xunmaw.help.entity.Users;
import com.xunmaw.help.service.AnswerService;
import com.xunmaw.help.service.CommentService;
import com.xunmaw.help.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 */
@Controller
@RequestMapping("answer")
public class AnswerController {

    @Autowired
    private AnswerService answerService;
    @Autowired
    private CommentService commentService;

    @RequestMapping("create.action")
    @ResponseBody
    public Message addAnswer(Integer ids,String content, HttpServletRequest request){
        Answer answer=new Answer();
        Users user = (Users)request.getSession().getAttribute("user");
        answer.setComment(commentService.findById(ids));
        answer.setAnswerTime(new Date());
        answer.setUser(user);
        answer.setContent(content);
        int i = answerService.addAnswer(answer);
        if(i>0){
            return Message.success();
        }else{
            return Message.fail();
        }
    }

    @RequestMapping("creates.action")
    @ResponseBody
    public Message addAnswers(Integer id,String content,Integer comment_id,HttpServletRequest request){
        Answer answer=new Answer();
        Users user = (Users)request.getSession().getAttribute("user");
        answer.setComment(commentService.findById(comment_id));
        answer.setAnswer(answerService.findById(id));
        answer.setAnswerTime(new Date());
        answer.setUser(user);
        answer.setContent(content);
        int i = answerService.addAnswers(answer);
        if(i>0){
            return Message.success();
        }else{
            return Message.fail();
        }
    }


    @RequestMapping("findByCommentId.action")
    @ResponseBody
    public Message findByCommentId(Integer comment_id){
        List<Answer> answers = answerService.findByCommentId(comment_id);
        System.out.println(answers);
        if(answers!=null){
            return Message.success().add("answer",answers);
        }else{
            return Message.fail();
        }
    }


    @RequestMapping("findById.action")
    @ResponseBody
    public Message findById(Integer id){
        Answer answer = answerService.findById(id);
        System.out.println(answer);
        if(answer!=null){
            return Message.success().add("answer",answer);
        }else{
            return Message.fail();
        }
    }

}
