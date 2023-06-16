import commands.*;
import exchanger.Exchanger;
import executor.Executor;

public class Main {
    public static void main(String[] args) {
        Exchanger exchanger = new Exchanger();
        try {
            var commandManager = new CommandManager() {{
                addCommand(new Exit(exchanger));
                addCommand(new Test(exchanger));
                addCommand(new Help(this));
                addCommand(new Info(exchanger));
                addCommand(new Show(exchanger));
                addCommand(new Add(exchanger));
                addCommand(new Update(exchanger));
                addCommand(new RemoveById(exchanger));
                addCommand(new Clear(exchanger));
                addCommand(new ExecuteScript(this));
                addCommand(new InsertAtIndex(exchanger));
                addCommand(new Shuffle(exchanger));
                addCommand(new RemoveGreater(exchanger));
                addCommand(new SumOfStudentsCount(exchanger));
                addCommand(new MaxById(exchanger));
                addCommand(new CountByFormOfEducation(exchanger));
            }};

            Executor executor = new Executor(commandManager);
            executor.interactiveRun();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
// execute_script D:\Documents\ITMO\2_sem\programming\lab_6\lab_6\example-script.txt