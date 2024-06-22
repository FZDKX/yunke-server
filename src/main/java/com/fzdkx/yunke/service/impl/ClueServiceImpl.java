package com.fzdkx.yunke.service.impl;

import com.alibaba.excel.EasyExcel;
import com.fzdkx.yunke.bean.dao.*;
import com.fzdkx.yunke.bean.vo.ClueOperateVO;
import com.fzdkx.yunke.bean.vo.ClueVO;
import com.fzdkx.yunke.bean.vo.DicVO;
import com.fzdkx.yunke.common.Result;
import com.fzdkx.yunke.config.listener.ClueDataListener;
import com.fzdkx.yunke.mapper.TClueMapper;
import com.fzdkx.yunke.mapper.TClueRemarkMapper;
import com.fzdkx.yunke.service.ClueService;
import com.fzdkx.yunke.service.DicService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * @author 发着呆看星
 * @create 2024/6/20
 */
@Service
public class ClueServiceImpl implements ClueService {

    @Resource
    private TClueMapper tClueMapper;

    @Resource
    private TClueRemarkMapper tClueRemarkMapper;

    @Resource
    private DicService dicService;

    @Override
    public Result<PageInfo<ClueVO>> cluePage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ClueVO> clueVOList = tClueMapper.selectList();
        PageInfo<ClueVO> pageInfo = new PageInfo<>(clueVOList);
        return Result.success(pageInfo);
    }

    @Override
    public Result<String> importExcel(InputStream file) {
        // 获取当前用户对象
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        // 读取excel，写入数据库
        EasyExcel.read(file, TClue.class, new ClueDataListener(tClueMapper, loginUser)).sheet().doRead();
        return Result.success();
    }

    @Override
    public Result<ClueOperateVO> getClueOperateVO(Integer id) {
        DicVO clueDic = dicService.getClueDic();
        ClueVO clueVO = tClueMapper.selectClueVO(id);
        // 设置当前活动
        TActivity activity = new TActivity();
        activity.setId(clueVO.getActivityId());
        activity.setName(clueVO.getActivityName());
        clueDic.getActivityList().add(activity);
        // 设置当前意向产品
        TProduct tProduct = new TProduct();
        tProduct.setId(clueVO.getIntentionProduct());
        tProduct.setName(clueVO.getIntentionProductStr());
        clueDic.getIntentionProductList().add(tProduct);
        // 返回对象
        ClueOperateVO clueOperateVO = new ClueOperateVO();
        clueOperateVO.setDicVO(clueDic);
        clueOperateVO.setTClue(clueVO);
        return Result.success(clueOperateVO);
    }

    @Override
    public void saceClue(TClue tClue) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        tClue.setCreateTime(new Date());
        tClue.setCreateBy(((LoginUser) authentication.getPrincipal()).getTUser().getId());
        tClueMapper.insert(tClue);
    }

    @Override
    public void editClue(TClue tClue) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        tClue.setEditTime(new Date());
        tClue.setEditBy(((LoginUser) authentication.getPrincipal()).getTUser().getId());
        tClueMapper.updateByPrimaryKeySelective(tClue);
    }

    @Override
    @Transactional
    public void remove(Integer id) {
        // 删除相关备注
        tClueRemarkMapper.deleteByClueId(id);
        // 删除线索
        tClueMapper.deleteByPrimaryKey(id);
    }


}
