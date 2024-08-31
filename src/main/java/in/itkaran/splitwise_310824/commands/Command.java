package in.itkaran.splitwise_310824.commands;

public interface Command {
    boolean matches(String input);
    void execute(String input);
}
