package com.math.alstudymathjava.system.controller;


import com.math.alstudymathjava.system.entity.math.MathQuestionOption;
import com.math.alstudymathjava.system.service.MathQuestionOptionService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/math/questionOption")
public class MathQuestionOptionController {

    @Resource
    private MathQuestionOptionService mathQuestionOptionService;

    /**
     * 新增单个选项
     */
    @PostMapping("/add")
    public String add(@RequestBody MathQuestionOption option) {
        try {
            int rows = mathQuestionOptionService.addOption(option);
            return rows > 0 ? "success" : "fail";
        } catch (RuntimeException e) {
            // 返回错误信息给前端
            return e.getMessage();
        }
    }

    /**
     * 批量新增选项（通常配合题目新增时使用）
     */
    @PostMapping("/addBatch")
    public String addBatch(@RequestBody List<MathQuestionOption> options) {
        try {
            int rows = mathQuestionOptionService.addBatchOptions(options);
            return rows > 0 ? "success" : "fail";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    /**
     * 根据题目ID查询该题的所有选项（按 sortOrder 排序）
     */
    @GetMapping("/list/{questionId}")
    public List<MathQuestionOption> list(@PathVariable("questionId") Long questionId) {
        return mathQuestionOptionService.getOptionsByQuestionId(questionId);
    }

    /**
     * 更新单个选项信息
     */
    @PutMapping("/update")
    public String update(@RequestBody MathQuestionOption option) {
        int rows = mathQuestionOptionService.updateOption(option);
        return rows > 0 ? "success" : "fail";
    }

    /**
     * 根据题目ID物理删除该题下的所有选项
     */
    @DeleteMapping("/deleteByQuestion/{questionId}")
    public String deleteByQuestion(@PathVariable("questionId") Long questionId) {
        int rows = mathQuestionOptionService.deleteOptionsByQuestionId(questionId);
        return rows >= 0 ? "success" : "fail";
    }

    /**
     * 根据选项ID删除单个选项
     */
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        int rows = mathQuestionOptionService.deleteOptionById(id);
        return rows > 0 ? "success" : "fail";
    }
}
