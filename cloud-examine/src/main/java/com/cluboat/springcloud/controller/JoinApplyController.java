package com.cluboat.springcloud.controller;

import com.cluboat.springcloud.entities.CommonResult;
import com.cluboat.springcloud.entity.apply.JoinApplyEntity;
import com.cluboat.springcloud.service.JoinApplyService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/joinapply")
public class JoinApplyController {

    @Resource
    private JoinApplyService joinApplyService;
    @GetMapping
    public CommonResult getJoinApplyById() {
        List<JoinApplyEntity> list = joinApplyService.lambdaQuery().eq(JoinApplyEntity::getJoinApplyIsPass, 0).list();
        log.info("****插入结果：{payment}");
        if (list != null) {
            return new CommonResult(200, "查询成功", list);
        } else {
            return new CommonResult(400, "无记录");
        }
    }

    @Resource
    private UserClubService userClubService;
    @PostMapping
    public CommonResult updateById(@RequestBody String json) {
        JSONObject jsonObject = new JSONObject(json);
        int id = jsonObject.getInt("id");
        int state = jsonObject.getInt("state");
        String feedback =jsonObject.optString("feedback");
        JoinApplyEntity joinApply = joinApplyService.getById(id);
        UserClubEntity userClubEntity=new UserClubEntity();
        joinApply.setJoinApplyIsPass(state);
        joinApply.setFeedback(feedback);

        boolean isSuccess = joinApplyService.updateById(joinApply);
        if(state==1)
        {
            userClubEntity.setUserId(id);
            userClubEntity.setClubId(joinApply.getJoinClubId());
            userClubService.save(userClubEntity);
        }
        if (isSuccess) {
            return new CommonResult(200, "修改成功");
        }
        else {
            return new CommonResult(400, "修改失败");
        }

    }
}
