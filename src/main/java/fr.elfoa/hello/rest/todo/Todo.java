package fr.elfoa.hello.rest.todo;

import javax.persistence.Entity;
import java.util.Date;
import java.util.Objects;

/**
 * @author Pierre Colomb
 */
public class Todo {


    private final String name;

    private final Date dueDate;

    private boolean done = false;



    public Todo(String name, Date dueDate) {
        this.name = name;
        this.dueDate = dueDate;
    }

    public Todo(String name) {
        this(name,null);
    }



    public Todo() {
        this(null);
    }



    public String getName() {
        return name;
    }



    public Date getDueDate() {
        return dueDate;
    }



    public boolean isDone() {
        return done;
    }



    public void setDone(boolean done) {
        this.done = done;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Todo))
            return false;
        Todo todo = (Todo) o;
        return Objects.equals(name, todo.name);
    }



    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
