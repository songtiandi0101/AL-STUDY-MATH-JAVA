package com.math.alstudymathjava.system.service.impl;


import com.math.alstudymathjava.system.entity.math.MathQuestionOption;
import com.math.alstudymathjava.system.mapper.MathQuestionOptionMapper;
import com.math.alstudymathjava.system.service.MathQuestionOptionService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

/**
 * 选择题选项业务实现
 */
@Service
public class MathQuestionOptionServiceImpl implements MathQuestionOptionService {

    @Resource
    private MathQuestionOptionMapper optionMapper;

    /**
     * 新增单个选项
     */
    @Override
    public int addOption(MathQuestionOption option) {
        if (option == null || option.getQuestionId() == null) {
            throw new IllegalArgumentException("选项信息或题目ID不能为空");
        }
        return optionMapper.insert(option);
    }

    /**
     * 批量新增选项（通常配合题目新增时使用）
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addBatchOptions(List<MathQuestionOption> options) {
        if (options == null || options.isEmpty()) {
            return 0;
        }
        return optionMapper.batchInsert(options);
    }

    /**
     * 根据题目ID查询该题的所有选项（按 sortOrder 排序）
     */
    @Override
    public List<MathQuestionOption> getOptionsByQuestionId(Long questionId) {
        if (questionId == null) {
            return Collections.emptyList();
        }
        return optionMapper.selectByQuestionId(questionId);
    }

    /**
     * 更新单个选项信息
     */
    @Override
    public int updateOption(MathQuestionOption option) {
        if (option == null || option.getId() == null) {
            throw new IllegalArgumentException("选项ID不能为空");
        }
        return optionMapper.update(option);
    }

    /**
     * 根据题目ID物理删除该题下的所有选项（通常在更新或删除题目时，先删后插）
     */
    @Override
    public int deleteOptionsByQuestionId(Long questionId) {
        if (questionId == null) {
            return 0;
        }
        return optionMapper.deleteByQuestionId(questionId);
    }

    /**
     * 根据选项ID删除单个选项
     */
    @Override
    public int deleteOptionById(Long id) {
        if (id == null) {
            return 0;
        }
        return optionMapper.deleteById(id);
    }
}
