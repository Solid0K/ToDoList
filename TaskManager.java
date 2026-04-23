import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private static final String fileName="Tasks.json";
    
    public List<Task> loadTasks(){
        File file=new File(fileName);
        List<Task> tasks=new ArrayList<>();
        try{
            if(!file.exists()){
                file.createNewFile();
                return tasks;
            }
            BufferedReader br=new BufferedReader(new FileReader(file));
            String line;
            while((line=br.readLine())!=null){
                String[] parts=line.split("\\|");
                if(parts.length<5){
                    continue;
                }
                Task task=new Task(Integer.parseInt(parts[0]),parts[1]);
                task.status=parts[2];
                task.createdAt=parts[3];
                task.updateAt=parts[4];
                tasks.add(task);
            }
            br.close();
        }catch(Exception e){
            System.out.println("Yo something went wrong");
        }
        return tasks;
    }

    public void saveTasks(List<Task> tasks){
        try{
            BufferedWriter bw=new BufferedWriter(new FileWriter(fileName));
            for(Task t:tasks){
                bw.write(t.id + "|" + t.description + "|" + t.status + "|" + t.createdAt + "|" + t.updateAt);
                bw.newLine();
            }
            bw.close();
        }catch(Exception e){
            System.out.println("Cant able to save data due some internal reasons");
        }
    }

    public int getId(List<Task> tasks){
        int max=0;
        for(Task t:tasks){
            if(t.id>max){
                max=t.id;
            }
        }
        return max+1;
    }
}
