package com.math.alstudymathjava.system.controller;


import com.math.alstudymathjava.system.service.MathQuestionKpRelationService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/math/questionKpRelation")
public class MathQuestionKpRelationController {

    @Resource
    private MathQuestionKpRelationService mathQuestionKpRelationService;

    /**
     * 批量绑定题目与知识点关联
     * 路径参数 questionId，请求体传知识点ID列表
     */
    @PostMapping("/bind/{questionId}")
    public String batchBind(@PathVariable("questionId") Long questionId,
                            @RequestBody List<Long> kpIds) {
        try {
            int rows = mathQuestionKpRelationService.batchBindRelations(questionId, kpIds);
            return rows >= 0 ? "success" : "fail";
        } catch (RuntimeException e) {
            // 返回错误信息给前端
            return e.getMessage();
        }
    }

    /**
     * 根据题目ID解除该题目的所有知识点关联
     */
    @DeleteMapping("/unbindByQuestion/{questionId}")
    public String unbindByQuestion(@PathVariable("questionId") Long questionId) {
        int rows = mathQuestionKpRelationService.unbindRelationsByQuestionId(questionId);
        return rows >= 0 ? "success" : "fail";
    }

    /**
     * 根据知识点ID解除该知识点与所有题目的关联
     */
    @DeleteMapping("/unbindByKp/{kpId}")
    public String unbindByKp(@PathVariable("kpId") Long kpId) {
        int rows = mathQuestionKpRelationService.unbindRelationsByKpId(kpId);
        return rows >= 0 ? "success" : "fail";
    }

    /**
     * 根据题目ID查询关联的知识点ID列表
     */
    @GetMapping("/kpIds/{questionId}")
    public List<Long> getKpIdsByQuestionId(@PathVariable("questionId") Long questionId) {
        return mathQuestionKpRelationService.getKpIdsByQuestionId(questionId);
    }

    /**
     * 根据知识点ID查询关联的题目数量
     */
    @GetMapping("/count/{kpId}")
    public int countQuestionsByKpId(@PathVariable("kpId") Long kpId) {
        return mathQuestionKpRelationService.countQuestionsByKpId(kpId);
    }
}
