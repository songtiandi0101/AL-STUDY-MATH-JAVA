package com.math.alstudymathjava.system.service.impl;


import com.math.alstudymathjava.system.entity.math.MathKnowledgePoint;
import com.math.alstudymathjava.system.entity.math.MathQuestion;
import com.math.alstudymathjava.system.entity.math.MathQuestionOption;
import com.math.alstudymathjava.system.entity.user.PageResult;
import com.math.alstudymathjava.system.mapper.MathKnowledgePointMapper;
import com.math.alstudymathjava.system.mapper.MathQuestionKpRelationMapper;
import com.math.alstudymathjava.system.mapper.MathQuestionMapper;
import com.math.alstudymathjava.system.mapper.MathQuestionOptionMapper;
import com.math.alstudymathjava.system.service.MathQuestionService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

/**
 * 数学题目业务实现
 */
@Service
public class MathQuestionServiceImpl implements MathQuestionService {

    @Resource
    private MathQuestionMapper questionMapper;

    @Resource
    private MathQuestionOptionMapper optionMapper;

    @Resource
    private MathQuestionKpRelationMapper relationMapper;

    @Resource
    private MathKnowledgePointMapper knowledgePointMapper;

    /**
     * 新增题目（包含选项和知识点关联）
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addQuestion(MathQuestion mathQuestion) {
        if (mathQuestion == null) {
            throw new IllegalArgumentException("题目信息不能为空");
        }

        LocalDateTime now = LocalDateTime.now();
        mathQuestion.setCreateTime(now);
        mathQuestion.setUpdateTime(now);
        if (mathQuestion.getIsPublished() == null) {
            mathQuestion.setIsPublished(0);
        }

        // 1. 插入题目主表
        int rows = questionMapper.insert(mathQuestion);
        Long questionId = mathQuestion.getId();

        // 2. 插入选项
        saveOptions(questionId, mathQuestion.getOptions());

        // 3. 绑定知识点
        if (mathQuestion.getKpIds() != null && !mathQuestion.getKpIds().isEmpty()) {
            relationMapper.batchInsert(questionId, mathQuestion.getKpIds());
        }

        return rows;
    }

    /**
     * 根据ID查询题目详情（包含选项和知识点详细信息）
     */
    @Override
    public MathQuestion getQuestionById(Long id) {
        if (id == null) {
            return null;
        }

        MathQuestion question = questionMapper.selectById(id);
        if (question == null) {
            return null;
        }

        // 选项
        question.setOptions(optionMapper.selectByQuestionId(id));

        // 知识点ID列表
        question.setKpIds(relationMapper.selectKpIdsByQuestionId(id));

        // 知识点详情
        List<MathKnowledgePoint> kps = knowledgePointMapper.selectKpsByQuestionId(id);
        question.setKnowledgePoints(kps == null ? Collections.emptyList() : kps);

        return question;
    }

    /**
     * 更新题目（包含选项和知识点关联的覆盖更新）
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateQuestion(MathQuestion mathQuestion) {
        if (mathQuestion == null || mathQuestion.getId() == null) {
            throw new IllegalArgumentException("题目ID不能为空");
        }

        mathQuestion.setUpdateTime(LocalDateTime.now());

        // 1. 更新题目主表
        int rows = questionMapper.update(mathQuestion);
        Long questionId = mathQuestion.getId();

        // 2. 覆盖更新选项：先删后插
        optionMapper.deleteByQuestionId(questionId);
        saveOptions(questionId, mathQuestion.getOptions());

        // 3. 覆盖更新知识点关联：先删后插
        relationMapper.deleteByQuestionId(questionId);
        if (mathQuestion.getKpIds() != null && !mathQuestion.getKpIds().isEmpty()) {
            relationMapper.batchInsert(questionId, mathQuestion.getKpIds());
        }

        return rows;
    }

    /**
     * 物理删除题目（级联删除选项和知识点关联）
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteQuestion(Long id) {
        if (id == null) {
            return 0;
        }

        // 1. 删除选项
        optionMapper.deleteByQuestionId(id);

        // 2. 删除知识点关联
        relationMapper.deleteByQuestionId(id);

        // 3. 删除题目
        return questionMapper.deleteById(id);
    }

    /**
     * 批量物理删除题目
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteBatchQuestions(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return 0;
        }

        // 1. 批量删除选项、知识点关联
        for (Long id : ids) {
            optionMapper.deleteByQuestionId(id);
            relationMapper.deleteByQuestionId(id);
        }

        // 2. 批量删除题目
        return questionMapper.deleteBatchByIds(ids);
    }

    /**
     * 分页查询题目列表
     */
    @Override
    public PageResult<MathQuestion> getQuestionPageList(MathQuestion query) {
        Integer pageNum = query.getPageNum();
        Integer pageSize = query.getPageSize();
        // 计算起始下标
        int start = (pageNum - 1) * pageSize;
        query.setStart(start);

        PageResult<MathQuestion> pageResult = new PageResult<>();
        // 查询当前页数据
        List<MathQuestion> records = questionMapper.selectPage(query);
        // 查询总条数
        long total = questionMapper.countPage(query);

        pageResult.setRecords(records);
        pageResult.setTotal(total);
        return pageResult;
    }

    /**
     * 修改题目发布状态
     */
    @Override
    public int updatePublishStatus(Long id, Integer isPublished) {
        if (id == null) {
            return 0;
        }

        // 先查再改，避免全量更新把其它字段置空
        MathQuestion existing = questionMapper.selectById(id);
        if (existing == null) {
            throw new RuntimeException("题目不存在，ID=" + id);
        }

        existing.setIsPublished(isPublished);
        existing.setUpdateTime(LocalDateTime.now());
        return questionMapper.update(existing);
    }

    /**
     * 保存选项（内部辅助方法）
     */
    private void saveOptions(Long questionId, List<MathQuestionOption> options) {
        if (options == null || options.isEmpty()) {
            return;
        }
        for (MathQuestionOption option : options) {
            option.setQuestionId(questionId);
        }
        optionMapper.batchInsert(options);
    }
}
