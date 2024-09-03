package in.itkaran.splitwise_310824.commands;

import in.itkaran.splitwise_310824.controllers.ExpenseController;
import in.itkaran.splitwise_310824.dtos.AddExpenseRequestDto;
import in.itkaran.splitwise_310824.dtos.AddExpenseResponseDto;

import java.util.ArrayList;
import java.util.List;

public class CreateExpenseCommand implements Command {
    private ExpenseController expenseController;

    public CreateExpenseCommand(ExpenseController expenseController) {
        this.expenseController = expenseController;
    }

    @Override
    public boolean matches(String input) {
        /*
        e.g:  u1 Expense u2 u3 u4 iPay 1000 Equal Desc Last night dinner
         */
        List<String> words = List.of(input.split(" "));
        return words.get(1).equals(CommandKeyWords.CREATE_EXPENSE_COMMAND);
    }

    @Override
    public void execute(String input) {
        /* u1 Expense u2 u3 u4 1000 Desc Last night dinner */
        List<String> words = List.of(input.split(" "));
        System.out.println("Create Expense:" + words);
        Long paidUserId = Long.valueOf(words.get(0));
        List<Long> owedUserIds = new ArrayList<>();

        int i = 2;
        while(!words.get(i).equals("iPay")) {
            owedUserIds.add(Long.valueOf(words.get(i)));
            i++;
        }

        // skip iPay
        i++;

        int amount = Integer.parseInt(words.get(i));
        while(!words.get(i).equals("Desc")) i++;

        // Skip Desc
        i++;

        StringBuilder description = new StringBuilder();
        while(i<words.size()) {
            description.append(words.get(i)).append(" ");
            i++;
        }

        System.out.println("Paid By: " + paidUserId);
        System.out.println("Owed By: " + owedUserIds);
        System.out.println("Amount: " + amount);
        System.out.println("Description: " + description);

        AddExpenseRequestDto addExpenseRequestDto = new AddExpenseRequestDto();
        addExpenseRequestDto.setPaidByUserId(paidUserId);
        addExpenseRequestDto.setOwedByUserIds(owedUserIds);
        addExpenseRequestDto.setAmount(amount);
        addExpenseRequestDto.setDescription(description.toString());

        AddExpenseResponseDto addExpenseResponseDto = expenseController.addExpense(addExpenseRequestDto);
        System.out.println(addExpenseResponseDto);



    }
}


/*
e.g:
1 Expense 2 3 iPay 3000 Desc Last night dinner
2 Expense 1 3 4 iPay 4000 Desc BBQ party
3 Expense 1 iPay 2000 Desc Lunch
4 Expense 3 iPay 1000 Desc Movie
 */