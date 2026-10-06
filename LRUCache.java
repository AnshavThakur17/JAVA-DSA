import java.util.HashMap;

class LRUCache {

    class Node {
        int key, value;
        Node prev, next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    HashMap<Integer, Node> map = new HashMap<>();
    Node head = new Node(0, 0);
    Node tail = new Node(0, 0);
    int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (!map.containsKey(key))
            return -1;

        Node node = map.get(key);

        node.prev.next = node.next;
        node.next.prev = node.prev;

        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;

        return node.value;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.value = value;

            node.prev.next = node.next;
            node.next.prev = node.prev;

            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;

            return;
        }

        Node node = new Node(key, value);
        map.put(key, node);

        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;

        if (map.size() > capacity) {
            Node lru = tail.prev;

            lru.prev.next = tail;
            tail.prev = lru.prev;

            map.remove(lru.key);
        }
    }
}