import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String args[]){
        TaskManager tm=new TaskManager();
        List<Task> tasks=tm.loadTasks();
        if(args.length==0){
            System.out.println("No command is provided");
        }
        String command=args[0];
        switch(command){
            case "add":{
                int id=tm.getId(tasks);
                String des=args[1];
                Task task=new Task(id,des);
                tasks.add(task);
                tm.saveTasks(tasks);
                System.out.println("Task has been added");
                break;
            }
            case "list":{
                System.out.println("Tasks loaded: " + tasks.size());
                for(Task t:tasks){
                    System.out.println(t.id + "|" + t.description + "|" + t.status + "|" + t.createdAt + "|" + t.updateAt);
                }
                break;
            }
            case "delete":{
                int deleteId=Integer.parseInt(args[1]);
                tasks.removeIf(t->t.id==deleteId);
                tm.saveTasks(tasks);
                System.out.println("Task deleted");
                break;
            }
            case "mark-done":{
                String id=args[1];
                updateTask(tasks,id,"Done",tm);
                break;
            }
            case "mark-in-progress":{
                String id=args[1];
                updateTask(tasks,id,"In-Progress",tm);
                break;
            }
            case "update":{
                String updateId=args[1];
                String newDes=args[2];
                for(Task t:tasks){
                    if(t.id==Integer.parseInt(updateId)){
                        t.description=newDes;
                        t.updateAt=LocalDateTime.now().toString();
                    }
                }
                tm.saveTasks(tasks);
                System.out.println("Updation done");
                break;
            }
            default:{
                System.out.println("Unknown Command");
            }
        }
    }

    public static void updateTask(List<Task> tasks,String id,String status,TaskManager manager){
        for(Task t: tasks){
            if(t.id==Integer.parseInt(id)){
                t.status=status;
                t.updateAt=LocalDateTime.now().toString();
            }
        }
        manager.saveTasks(tasks);
        System.out.println("Status has been updated");
    }
}
