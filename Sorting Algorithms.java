Sorting Algorithms
1. QuickSort:
The core function is partition(put <pivot to left, >pivot to right)
public class QuickSort{
    public void quickSort(int[] arr, int low, int high){
        int p = partition(arr,low,high);
        quickSort(arr,low,p-1);
        quickSort(arr,p+1,high);
    }

    public int partition(int[] arr, int low, int high){
        int i = low, j = high;
        int pivot = arr[low];
        while(i!=j){
            //find the first < pivot item from right
            while(arr[j] >= pivot && i < j){
                j--;
            }
            // find the first > pivot item from left
            while(arr[i] <= pivot && i < j){
                i++;
            }
            if(i < j){
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        arr[low] = arr[i];
        arr[i] = pivot;
        return i;
    }
}

O(NlogN)

2. Bucket Sort
O(N) But take a lot of space


3. Insertion Sort
//can use binary search to find the first element <= target and then insert
public void insertionSort(int[] arr){
    int len = arr.length;
    for(int i = 1; i < len; i++){
        int key = arr[i];
        int j = i-1;
        while(j>=0 && arr[j] > key){
            arr[j+1] = arr[j];
            j = j-1;
        }
        arr[j+1] = key;
    }
}




4. Selection Sort
//repeatly find the minimum element from unsorted part and put it at the beginning
public void selectionSort(int[] arr){
    int len = arr.length;
    for(int i = 0; i < len-1; i++){
        int min = i;
        //find the minimum
        for(int j = i+1; j < len; j++){
            if(arr[j] < arr[min]){
                min = j;
            }
        }
        //swap
        int temp = arr[min];
        arr[min] = arr[i];
        arr[i] = temp; 
    }
}

5. Merge Sort
private void mergeSort(int[] nums, int start, int end){
        if(start<end){
            int mid = start+(end-start)/2;
            mergeSort(nums,start,mid);
            mergeSort(nums,mid+1,end);
            merge(nums,mid,start,end);
        }
        
    }
    private void merge(int[] nums, int mid, int start, int end){
        int leftLen = mid-start+1;
        int rightLen = end-mid;
        int[] left = new int[leftLen];
        int[] right = new int[rightLen];
        for(int i = 0; i < leftLen; i++){
            left[i] = nums[i+start];
        }
        for(int i = 0; i < rightLen; i++){
            right[i] = nums[mid+i+1];
        }
        int i = 0, j = 0, k = start;
        while(i < leftLen && j < rightLen){
            if(left[i] <= right[j]){
                nums[k++] = right[j++];
            }else{
                nums[k++] = left[i++];
            }
        }
        while( i < leftLen){
            nums[k++] = left[i++];
        }
        while(j < rightLen){
            nums[k++] = right[j++];
        }
    }

6. Heap Sort
// Binary tree: For i layer, the layer at most has 2^i-1 nodes.    
//              For k height, the tree at most has 2^k-1 nodes.
// Full Binary tree: has 2^k-1 nodes
// Complete Binary Tree: full on all layer except root layer, root layer all nodes are as far left as possible

//Heap can be represent by complete binary tree, so that it can be stored in an array(0-based)
//In the array, given an index of a node,
// parent = floor((i-1)/2)
//left child = 2i+1
//right child = 2(i+1)

// Heap sort is basically maintain a max heap and get the root node and adjust to keep the max heap
//1. Max-Heapify: maintain the characteristic of a max heap
//2. Build-Max-Heap: change an array(input) to max heap
//3. Heap-Sort


public static maxHeapify(int[] arr, int len, int index){
    int max = index;
    int left = 2*index+1;
    int right = 2*(index+1);

    if(left < len && arr[left]>arr[max]){
        max = left;
    }
    if(right < len && arr[right]>arr[max]){
        max = right;
    }
    if(max!=index){
        int temp = arr[index];
        arr[index] = arr[max];
        arr[max] = temp;
        maxHeapify(arr,len,max);
    }
}

//bottom up to build the maxHeap
public static buildMaxHeap(int[] arr, int len){
    for(int i = len/2-1; i >=0; i--){
        maxHeapify(arr,len,i);
    }
}

public void heapSort(int[] arr){
    int len = arr.length;
    buildMaxHeap(arr,len);
    for(int i = len-1; i >=0; i--){
        int temp = arr[0];
        arr[0] = arr[i];
        arr[i] = temp;
        maxHeapify(arr,i,0);
    }
}

