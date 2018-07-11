Implementation of MinHeap(PriorityQueue) in java:

PriorityQueue is minHeap, which is binary tree(complete binary tree)
Complete binary tree has two features:
    1. filled as complete as possible, except last layer and last layer filled as left as possible
    2. all chidren nodes < parent nodes
Complete binary tree is Implemented using array
    left child Index = parent Index * 2+1
    right child Index = parent Index * 2 +2
    parent Index = (childIndex-1)/2
Insert Node: add to the last layer left possible node and level up
Delete Node: delete root node and move last node to root and level down

class MinHeap{
    private int capacity = 10;
    private int size = 0;
    int[] items = new int[capacity];
    
    private int getLeftChildIdnex(int parentIndex){return parentIndex*2+1;}
    private int getRightChildIndex(int parentIndex){return parentIndex*2+2;}
    private int getParentIndex(int childIndex){return (childIndex-1)/2;}

    private boolean hasLeftChild(int parentIndex){return getLeftChildIndex(parentIndex)<=size;}
    private boolean hasRightChild(int parentIndex){return getRightChildIndex(parentIndex)<=size;}
    private boolean hasParent(int childIndex){return getParentIndex(childIndex)>=0;}

    private int getLeftChildValue(int index){return items[index];}
    private int getRightChildValue(int index){return items[index];}
    private int getParentValue(int index){return items[index];}

    private void swap(int i, int j, int[] nums){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private void ensureExtraSpace(){  //this is the implementation of ArrayList
        if(size == capacity){
            items = Arrays.copy(items, capacity*2);
            capacity = capacity*2;
        }
    }

    public int peek(){
        if(size==0) throw new IllegalStateException();
        return items[0];
    }

    public int poll(){
        int res = items[0];
        items[0] = items[size-1];
        size--;
        heapifyDown();
        return res;
    }

    public void add(int value){
        ensureExtraSpace();
        items[size] = value;
        size++;
        heapifyUp(); 
    }

    public void heapifyDown(){
        int index = 0;
        while(hasLeftChild(index)){
            int smallerChildIndex = getLeftChildIndex(index);
            if(hasRightChild(index)&&getRightChildValue(index)<=getLeftChildValue(index)){
                smallerChildIndex = getRightChildIndex(index); 
            }
            if(items[index]<items[smallerChildIndex]){
                break;
            }else{
                swap(index,smallerChildIndex,items); 
            }
            index = smallerChildIndex;
        }
    }

    public void heapifyUp(){
        int index = size-1;
        while(hasParent(index)&&getParentValue(index)>items[index]){
            swap(index,getParentIndex(index),items);
            index = getParentIndex(index);
        }
    }

}
