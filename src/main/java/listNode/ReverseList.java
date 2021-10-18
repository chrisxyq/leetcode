package listNode;

import java.util.*;

/**
 * @author chrisxu
 * @create 2021-06-27 10:22
 * Ctrl + Alt + L：格式化代码
 * ctrl + Alt + T：代码块包围
 * ctrl + Y：删除行
 * ctrl + D：复制行
 * alt+上/下：移动光标到上/下方法
 * ctrl+shift+/：注释多行
 */
public class ReverseList {
    public ListNode reverseList(ListNode head) {
        if(head==null ||head.next==null){
            return head;
        }
        ListNode res = reverseList(head.next);
        head.next.next=head;
        head.next=null;
        return res;

    }
    public static void main(){
        ArrayList<Object> arrayList = new ArrayList<>();
        arrayList.isEmpty();
        arrayList.add(new Object());
        arrayList.get(0);
        arrayList.size();

        LinkedList<Object> linkedList = new LinkedList<>();//双向链表
        linkedList.isEmpty();
        linkedList.size();
        linkedList.contains(new Object());
        linkedList.add(new Object());
        linkedList.addFirst(new Object());
        linkedList.removeFirst();
        linkedList.removeLast();

        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.containsKey(new Object());
        hashMap.get(new Object());
        hashMap.put(new Object(),new Object());
        hashMap.remove(new Object());
        hashMap.putIfAbsent(new Object(),new Object());
        Set<Object> objects = hashMap.keySet();

        Queue<Object> queue = new LinkedList<>();
        queue.peek();//返回队头
        queue.poll();//删除并返回队头
        queue.offer(new Object());//将元素插入队尾
        queue.size();//队列元素个数

        Stack<Object> stack = new Stack<>();
        stack.size();
        stack.push(new Object());//将元素压入栈顶
        stack.pop();//删除并返回栈顶
        stack.peek();//返回栈顶


    }
}
