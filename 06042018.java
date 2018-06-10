146. LRU Cache
class LRUCache {
    class DLinkedNode{
        int val;
        DLinkedNode pre;
        DLinkedNode post;
        int key;
    }

    private void addNode(DLinkedNode node){
        node.pre = head;
        node.post = head.post;
        head.post.pre = node;
        head.post = node;
    }

    private void removeNode(DLinkedNode node){
        DLinkedNode pre = node.pre;
        DLinkedNode post = node.post;
        pre.post = post;
        post.pre = pre;
    }

    private void moveToHead(DLinkedNode node){
        this.removeNode(node);
        this.addNode(node);
    }

    private DLinkedNode popTail(){
        DLinkedNode node = tail.pre;
        this.removeNode(node);
        return node;
    }

    private HashMap<Integer,DLinkedNode> cache = new HashMap<>();
    private int count;
    private int capacity;
    private DLinkedNode head, tail;

    public LRUCache(int capacity) {
        this.count = 0;
        this.capacity = capacity;
        head = new DLinkedNode();
        head.pre = null;
        tail = new DLinkedNode();
        tail.post = null;
        tail.pre = head;
        head.post = tail;
    }
    
    public int get(int key) {
        DLinkedNode node = cache.get(key);
        if(node == null) return -1;
        this.moveToHead(node);
        return node.val;
    }
    
    public void put(int key, int value) {
        DLinkedNode node = cache.get(key);
        if(node == null){
            DLinkedNode newNode = new DLinkedNode();
            newNode.key = key;
            newNode.val = value;
            this.cache.put(key,newNode);
            this.addNode(newNode);
            count++;
            if(count > capacity){
                DLinkedNode tail = this.popTail();
                this.cache.remove(tail.key);
                count--;
            }
        }else{
            node.val = value;
            this.moveToHead(node);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */