package com.cluboat.springcloud.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClubBuildApplyDTO {
    private Integer buildApplyId;
    private Integer userId;
    private String buildApplyReason;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp buildApplyTime;
    private String status;
    private String adminClubName;
    private String feedback;
    private String userName;
}