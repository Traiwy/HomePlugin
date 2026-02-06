package traiwy.homePlugin.gui;

import java.util.ArrayDeque;
import java.util.Deque;

public class MenuHistory {
    private final Deque<Menu> history = new ArrayDeque<>();

    public void push(Menu menu) {
        history.push(menu);
    }

    public Menu pop() {
        return history.isEmpty() ? null : history.pop();
    }

    public Menu peek() {
        return history.peek();
    }

    public boolean isEmpty() {
        return history.isEmpty();
    }

    public void clear() {
        history.clear();
    }
}
