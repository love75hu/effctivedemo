/*
 * Copyright (c) Jack魏 2023 - 2023, All Rights Reserved.
 */

package cn.mediinfo.grus.shujuzx.config;


import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.HeaderParameter;
import org.springdoc.core.customizers.GlobalOpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * SpringDoc 配置类
 */
@Configuration
public class OpenApiConfig {
    /**
     * link https://springdoc.org/index.html#how-can-i-set-a-global-header
     *
     */
    @Bean
    public GlobalOpenApiCustomizer  globalOpenApiCustomizer()
    {
        return openApi -> openApi.getPaths().values().stream().flatMap(pathItem -> pathItem.readOperations().stream())
                .forEach(operation -> operation
                        //添加全局认证
                        .addSecurityItem(new io.swagger.v3.oas.models.security.SecurityRequirement().addList("bearer"))
//                        .addParametersItem(new HeaderParameter().name("weiZhiLB").description("位置类别（1:门诊医生；2:住院医生; 3:住院护士）").schema(new StringSchema()))
//                        .addParametersItem(new HeaderParameter().name("weiZhiCXBS").description("位置查询标识(用户id/科室id/病区id)").schema(new StringSchema()))
//                        .addParametersItem(new HeaderParameter().name("chaXunLX").description("查询类型（1:要求时间、2:申请时间、3:安排时间）").schema(new StringSchema()))
//                        .addParametersItem(new HeaderParameter().name("kaiShiSj").description("开始时间").schema(new StringSchema().type("date")))
//                        .addParametersItem(new HeaderParameter().name("jieShuSJ").description("结束时间").schema(new StringSchema().type("date")))
//                        .addParametersItem(new HeaderParameter().name("shouShuZT").description("手术状态(多个用\",\"分隔)").schema(new StringSchema()))
//                        .addParametersItem(new HeaderParameter().name("WeiZhiID").description("位置ID").schema(new StringSchema()).required(true))
//                        .addParametersItem(new HeaderParameter().name("WeiZhiMC").description("位置名称").schema(new StringSchema()))
//                        .addParametersItem(new HeaderParameter().name("KeShiID").description("科室ID").schema(new StringSchema()).required(true))
//                        .addParametersItem(new HeaderParameter().name("KeShiMC").description("科室名称").schema(new StringSchema()))
//                        .addParametersItem(new HeaderParameter().name("BingQuID").description("病区ID").schema(new StringSchema()).required(true))
//                        .addParametersItem(new HeaderParameter().name("BingQuMC").description("病区名称").schema(new StringSchema()))
//                        .addParametersItem(new HeaderParameter().name("JiGouID").description("机构ID").schema(new StringSchema()).required(true))
//                        .addParametersItem(new HeaderParameter().name("JiGouMC").description("机构名称").schema(new StringSchema()))
//                        .addParametersItem(new HeaderParameter().name("GongNengID").description("功能ID").schema(new StringSchema()))
//                        .addParametersItem(new HeaderParameter().name("ShuRuMLX").description("输入码类型").schema(new StringSchema()))
//                        .addParametersItem(new HeaderParameter().name("CaiDanID").description("菜单ID").schema(new StringSchema()))
                );
    }
}
