package listNode;

/**
 * @author chrisxu
 * @create 2021-06-27 15:48
 * Ctrl + Alt + L：格式化代码
 * ctrl + Alt + T：代码块包围
 * ctrl + Y：删除行
 * ctrl + D：复制行
 * alt+上/下：移动光标到上/下方法
 * ctrl+shift+/：注释多行
 */
public class Trie {
    private Trie[] children;
    private boolean isEnd;

    public Trie() {
        children = new Trie[26];
        isEnd = false;
    }

    public void insert(String word) {
        Trie node = this;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            int index = ch - 'a';
            if (node.children[index] == null) {
                //子节点不存在。创建一个新的子节点，记录在children 数组的对应位置上
                node.children[index] = new Trie();
            }
            node = node.children[index];//沿着指针移动到子节点，继续处理下一个字符。
        }
        //直到处理字符串的最后一个字符，然后将当前节点标记为字符串的结尾
        node.isEnd = true;
    }

    public boolean search(String word) {
        Trie node = searchPrefix(word);
        //说明字典树中存在该前缀,且前缀末尾对应节点的isEnd 为真
        return node != null && node.isEnd;
    }

    public boolean startsWith(String prefix) {
        //说明字典树中存在该前缀
        return searchPrefix(prefix) != null;
    }

    private Trie searchPrefix(String prefix) {
        Trie node = this;
        for (int i = 0; i < prefix.length(); i++) {
            char ch = prefix.charAt(i);
            int index = ch - 'a';
            if (node.children[index] == null) {
                //子节点不存在。说明字典树中不包含该前缀，返回空指针
                return null;
            }
            //子节点存在。沿着指针移动到子节点，继续搜索下一个字符
            node = node.children[index];
        }
        return node;
    }
}
