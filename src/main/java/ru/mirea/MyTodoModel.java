package ru.mirea;

import java.util.ArrayList;
import java.util.List;


public class MyTodoModel implements TodoModel {
    private List<TodoItem> items = new ArrayList<>();
    private int count = 1;

    public MyTodoModel() {
        add("ghju");
        add("oiuuy");
    }

    @Override
    public List<TodoItem> getItems() {
        return items;
    }

    @Override
    public void add(String text) {
        items.add(new TodoItem(count++ ,text));
    }

    @Override
    public void delete(int id) {
        for( int i = 0; i < items.size(); i++){
            if(items.get(i).getId() == id){
                items.remove(i);
            }
        }
    }
}
