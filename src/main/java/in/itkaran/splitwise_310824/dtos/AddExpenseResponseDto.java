package in.itkaran.splitwise_310824.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AddExpenseResponseDto {
    private String paidByUserName;
    private String description;
    private int amount;
    private String groupName;

    public String toString() {
        return "Paid by: " + paidByUserName + "\n" +
                "Amount: " + amount + "\n" +
                "Description: " + description + "\n" +
                "Group: " + groupName;
    }
}
