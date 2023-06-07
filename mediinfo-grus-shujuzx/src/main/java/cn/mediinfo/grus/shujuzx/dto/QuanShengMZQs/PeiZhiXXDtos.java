package cn.mediinfo.grus.shujuzx.dto.QuanShengMZQs;

import java.util.*;

import lombok.Data;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;

@Data
public class PeiZhiXXDtos {

    /**
     * 隐私配置列表
     */
    @Schema(description = "隐私配置列表")
    private List<YinSiPZItem> yinSiGZList;

    /**
     * 功能模块列表（展示配置）
     */
    @Schema(description = "功能模块列表（展示配置）")
    private List<ZhanShiPZItem> gongNengMKList;
}
