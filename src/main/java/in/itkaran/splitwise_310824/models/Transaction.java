package in.itkaran.splitwise_310824.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Transaction extends BaseModel{
    private Long fromUserId;
    private Long toUserId;
    int amount;
}
