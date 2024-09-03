package in.itkaran.splitwise_310824.controllers;

import in.itkaran.splitwise_310824.dtos.AddExpenseRequestDto;
import in.itkaran.splitwise_310824.dtos.AddExpenseResponseDto;
import in.itkaran.splitwise_310824.models.Expense;
import in.itkaran.splitwise_310824.services.ExpenseService;
import org.springframework.stereotype.Controller;

@Controller
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    public AddExpenseResponseDto addExpense(AddExpenseRequestDto addExpenseRequestDto) {
        System.out.println("Adding Expense");
        AddExpenseResponseDto addExpenseResponseDto = new AddExpenseResponseDto();
        Expense expense = expenseService.addExpense(addExpenseRequestDto.getPaidByUserId(),
                addExpenseRequestDto.getOwedByUserIds(),
                addExpenseRequestDto.getAmount(),
                addExpenseRequestDto.getGroupId(),
                addExpenseRequestDto.getDescription());

        addExpenseResponseDto.setPaidByUserName(expense.getCreatedBy().getName());
        addExpenseResponseDto.setAmount(expense.getAmount());
        addExpenseResponseDto.setDescription(expense.getDescription());
        //addExpenseResponseDto.setGroupName(expense.getGroup().getId());
        return addExpenseResponseDto;
    }


}
