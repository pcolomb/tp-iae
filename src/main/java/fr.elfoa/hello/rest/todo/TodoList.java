package fr.elfoa.hello.rest.todo;

import java.util.*;

/**
 * @author Pierre Colomb
 */
public class TodoList {


    private Map<Integer,Todo> todos = new HashMap<>();

    public TodoList add(Todo todo){

        todos.put(getLastId() + 1 ,todo);

        return this;
    }

    public Integer getLastId(){
        return todos.keySet()
                    .stream()
                    .max(Comparator.naturalOrder())
                    .orElse(0);
    }

    public TodoList add(Integer id,Todo todo){
        todos.put(id,todo);
        return this;
    }

    public TodoList done(Integer id){
        Todo todo = todos.get(id);

        if(todo != null){
            todo.setDone(true);
        }

        return this;
    }

    public TodoList unDone(Integer id){
        Todo todo = todos.get(id);

        if(todo != null){
            todo.setDone(false);
        }

        return this;
    }

    public Todo update(Integer id,Todo todo){
        return todos.replace(id,todo);
    }


    public Todo delete(Integer id){
        return todos.remove(id);
    }



    public Todo getbyId(Integer id){
        return todos.get(id);
    }

    public Collection<Todo> getAll(){
        return todos.values();
    }





}
