package cn.mediinfo.grus.shujuzx.dto.fangansc;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@Schema(description = "方案输出项")
public class FangAnSCDTO {

    /**
     * 药品
     */
    private List<DrugField>  drugFields;

    /**
     * 检查
     */
    private List<InspectField> inspectFields;

    /**
     * 检验
     */
    private List<LabField> labFields;

    /**
     * 数据元
     */
    private List<QueryField> queryFields;
}
