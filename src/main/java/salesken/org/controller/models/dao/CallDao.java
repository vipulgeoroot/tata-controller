package salesken.org.controller.models.dao;

import lombok.Builder;
import lombok.Data;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import salesken.org.controller.constants.CallState;

import java.util.Date;
import java.util.UUID;

@Data
@Builder
@Setter
@Document(indexName = "test_cdr_v0")
// TODO: 15/06/22 replace the indexName as per the table name and also see the below field name matched the table column name
public class CallDao {
    @Id
    private Integer id;

    @Field(type = FieldType.Keyword, name = "sId")
    private String sId;

    @Field(type = FieldType.Keyword, name = "from_number")
    private String fromNumber;

    @Field(type = FieldType.Keyword, name = "to_number")
    private String toNumber;

    @Field(type = FieldType.Keyword, name = "leg_id")
    private String legId;

    @Field(type = FieldType.Keyword, name = "bridge_id")
    private String bridgeId;

    @Field(type = FieldType.Keyword, name = "extension")
    private String extension;

    @Field(type = FieldType.Keyword, name = "call_state")
    private CallState callState;

    @Field(type = FieldType.Date, name = "created_at")
    private Date createdAt;

    @Field(type = FieldType.Date, name = "updated_at")
    private Date updatedAt;

}
