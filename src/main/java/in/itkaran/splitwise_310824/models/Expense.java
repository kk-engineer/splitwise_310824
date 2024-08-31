package in.itkaran.splitwise_310824.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Expense extends BaseModel{
    private String description;
    private int amount;
    @ManyToOne
    private Group group;
    @ManyToOne
    private User createdBy;
    @OneToMany
    private List<UserExpense> userExpenses;
    @Enumerated(EnumType.ORDINAL)
    private ExpenseType expenseType;
}
