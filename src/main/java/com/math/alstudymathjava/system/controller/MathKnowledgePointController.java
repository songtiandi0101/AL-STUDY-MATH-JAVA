package com.math.alstudymathjava.system.controller;


import com.math.alstudymathjava.system.entity.math.MathKnowledgePoint;
import com.math.alstudymathjava.system.service.MathKnowledgePointService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/math/knowledgePoint")
public class MathKnowledgePointController {

    @Resource
    private MathKnowledgePointService mathKnowledgePointService;

    @PostMapping("/add")
    public String add(@RequestBody MathKnowledgePoint knowledgePoint) {
        try {
            int rows = mathKnowledgePointService.addKnowledgePoint(knowledgePoint);
            return rows > 0 ? "success" : "fail";
        } catch (RuntimeException e) {
            // 返回错误信息给前端
            return e.getMessage();
        }
    }

    @GetMapping("/get/{id}")
    public MathKnowledgePoint getById(@PathVariable("id") Long id) {
        return mathKnowledgePointService.getKnowledgePointById(id);
    }

    @PutMapping("/update")
    public String update(@RequestBody MathKnowledgePoint knowledgePoint) {
        int rows = mathKnowledgePointService.updateKnowledgePoint(knowledgePoint);
        return rows > 0 ? "success" : "fail";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        try {
            int rows = mathKnowledgePointService.deleteKnowledgePoint(id);
            return rows > 0 ? "success" : "fail";
        } catch (RuntimeException e) {
            // 子节点存在、或题目已关联时，把业务提示返回前端
            return e.getMessage();
        }
    }

    /**
     * 获取完整知识点树（顶级节点 + 嵌套 children）
     */
    @GetMapping("/tree")
    public List<MathKnowledgePoint> tree() {
        return mathKnowledgePointService.getKnowledgePointTree();
    }

    /**
     * 根据父级ID查询直接子节点（懒加载）
     */
    @GetMapping("/children/{parentId}")
    public List<MathKnowledgePoint> children(@PathVariable("parentId") Long parentId) {
        return mathKnowledgePointService.getChildrenByParentId(parentId);
    }

    /**
     * 根据题目ID查询关联的知识点列表
     */
    @GetMapping("/byQuestion/{questionId}")
    public List<MathKnowledgePoint> byQuestion(@PathVariable("questionId") Long questionId) {
        return mathKnowledgePointService.getKnowledgePointsByQuestionId(questionId);
    }

    /**
     * 批量绑定题目与知识点
     */
    @PostMapping("/bind/{questionId}")
    public String bind(@PathVariable("questionId") Long questionId,
                       @RequestBody List<Long> kpIds) {
        try {
            int rows = mathKnowledgePointService.bindQuestionKnowledgePoints(questionId, kpIds);
            return rows >= 0 ? "success" : "fail";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    /**
     * 解绑题目下的所有知识点
     */
    @DeleteMapping("/unbind/{questionId}")
    public String unbind(@PathVariable("questionId") Long questionId) {
        int rows = mathKnowledgePointService.unbindQuestionKnowledgePoints(questionId);
        return rows >= 0 ? "success" : "fail";
    }
}
