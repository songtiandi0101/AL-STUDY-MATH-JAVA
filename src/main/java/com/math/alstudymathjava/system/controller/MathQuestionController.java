package com.math.alstudymathjava.system.controller;


import com.math.alstudymathjava.system.entity.math.MathQuestion;
import com.math.alstudymathjava.system.entity.user.PageResult;
import com.math.alstudymathjava.system.service.MathQuestionService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questions/question")
public class MathQuestionController {

    @Resource
    private MathQuestionService mathQuestionService;

    /**
     * 新增题目（包含选项和知识点关联）
     */
    @PostMapping("/add")
    public String add(@RequestBody MathQuestion mathQuestion) {
        try {
            int rows = mathQuestionService.addQuestion(mathQuestion);
            return rows > 0 ? "success" : "fail";
        } catch (RuntimeException e) {
            // 返回错误信息给前端
            return e.getMessage();
        }
    }

    /**
     * 根据ID查询题目详情（包含选项和知识点详细信息）
     */
    @GetMapping("/get/{id}")
    public MathQuestion getById(@PathVariable("id") Long id) {
        return mathQuestionService.getQuestionById(id);
    }

    /**
     * 更新题目（包含选项和知识点关联的覆盖更新）
     */
    @PutMapping("/update")
    public String update(@RequestBody MathQuestion mathQuestion) {
        try {
            int rows = mathQuestionService.updateQuestion(mathQuestion);
            return rows > 0 ? "success" : "fail";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    /**
     * 物理删除题目（级联删除选项和知识点关联）
     */
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        int rows = mathQuestionService.deleteQuestion(id);
        return rows > 0 ? "success" : "fail";
    }

    /**
     * 批量物理删除题目
     */
    @DeleteMapping("/deleteBatch")
    public String deleteBatch(@RequestBody List<Long> ids) {
        try {
            int rows = mathQuestionService.deleteBatchQuestions(ids);
            return rows >= 0 ? "success" : "fail";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    /**
     * 分页查询题目列表（支持按年级、题型、难度、关键字模糊搜索等）
     */
    @GetMapping("/list")
    public PageResult<MathQuestion> list(MathQuestion query) {
        return mathQuestionService.getQuestionPageList(query);
    }

    /**
     * 修改题目发布状态（上架/下架）
     */
    @PutMapping("/publish/{id}")
    public String updatePublishStatus(@PathVariable("id") Long id,
                                      @RequestParam("isPublished") Integer isPublished) {
        try {
            int rows = mathQuestionService.updatePublishStatus(id, isPublished);
            return rows > 0 ? "success" : "fail";
        } catch (RuntimeException e) {
            // 题目不存在时 Service 会抛异常，把提示返回前端
            return e.getMessage();
        }
    }
}