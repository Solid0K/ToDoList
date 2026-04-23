import java.time.LocalDateTime;

public class Task{
    public int id;
    public String description;
    public String status;
    public String createdAt;
    public String updateAt;

    public Task(int id,String description){
        this.id=id;
        this.description=description;
        this.status="Todo";
        this.createdAt=LocalDateTime.now().toString();
        this.updateAt=this.createdAt;
    }
}