package com.fzdkx.yunke.controller;

import com.fzdkx.yunke.bean.vo.DicVO;
import com.fzdkx.yunke.common.Result;
import com.fzdkx.yunke.service.DicService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 发着呆看星
 * @create 2024/6/21
 */
@RestController
@RequestMapping("/api/dic")
public class DicController {

    @Resource
    private DicService dicService;

    @GetMapping("/clue")
    public Result<DicVO> getClueDic() {
        DicVO clueDic = dicService.getClueDic();
        return Result.success(clueDic);
    }

    @GetMapping("/clueRemark")
    public Result<DicVO> getClueRemarkDic() {
        DicVO clueDic = dicService.getClueRemarkDic();
        return Result.success(clueDic);
    }
}
