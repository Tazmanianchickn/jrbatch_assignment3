package MaxBinHeap_A3;

public class MaxBinHeap implements Heap_Interface {
    private double[] array; //load this array
    private int size;
    private static final int arraySize = 10000; //Everything in the array will initially
    //be null. This is ok! Just build out
    //from array[1]

    private double delMaxValue;


    public MaxBinHeap() {
        initHeap();
    }

    /*
     * When insert if called we insert the element at the last position of the
     * array and then we need to get the parent node index so we can then run the swap
     * method if the passed value is greater than its parent.
     *
     */
    public void insert(double element) {
        array[size] = element;
        int currentIndex = size;
        heapifyUp(currentIndex);
        size++;
    }

    /*
     * To ensure the correct ordering of our heap tree we must take specific
     * precautions to ensure the max heap priority is maintained
     *
     * First we take that last index in the array and replace it with index 1
     * Once this is complete we then need to ensure the integreity of the heap
     * is still intact.  We will do this by comparing the child nodes and return the greater value.
     *
     * The best way to go about this would be to have a function that will take in
     * the current index find the child nodes, compare the child node values and then
     * return the index of the larger values
     */
    public void delMax() {
        delMaxValue = array[1];
        array[1] = array[size-1];
        array[size-1] = 0;
        int currentIndex = 1;
        // Checks to make sure there are nodes to compare it to
        heapifyDown(currentIndex);
        size--;
    }

    public double getMax() {
        if (size()==0) {
            return Double.NaN;
        } else { return array[1]; }
    }

    public void clear() {
        initHeap();
    }

    public int size() {
        return size-1;
    }

    /*
     * From the instructions, build() is our special case in which
     * out runtime should execute in O(N) instead of O(N*Log(N))
     * The reasoning is that because all leaf indices do not need
     * to be checked we can simply start at the last parent node index
     * and then work our way backwards up the tree.
     */
    public void build(double[] elements) {
        initHeap();
        copyArrayToInternal(elements);
        size = elements.length+1;
        int lastParentIndex = size()/2;
        for (int x=lastParentIndex; x>=1; x--) {
            heapifyDown(x);
        }
    }

    @Override
    public double[] getHeap() {
        return this.array;
    }

    public double[] sort(double[] elements) {
        build(elements);
        double[] returnArray = new double[elements.length];
        int currentInsertIndex = size()-1;
        for (int x=currentInsertIndex; x>-1; x--) {
            delMax();
            returnArray[x] = delMaxValue;
        }
        return returnArray;
    }

    // Sets initial tree parameters
    private void initHeap() {
        array = new double[arraySize];
        array[0] = Double.NaN;
        size = 1;
        delMaxValue = 0;
    }

    /* If i understand everything correctly we can use the equations:
     * Return Parent Node: Arr[(i-1)/2]
     * Returns the left child node: Arr[(2*1)+1)]
     * Returns the right child node Arr[(2*1)+2)]
     */

    /*
     * The methods below hasParent() && leftChild()/rightChild() is functions
     * that will allow me to make assumptions about the current state of the tree
     * in other various methods
     */
    private boolean hasParent(int passedIndex) {
        return passedIndex > 1;
    }

    private int leftChild(int passedIndex) {
        passedIndex*=2;
        if (passedIndex<size) {
            return passedIndex;
        } else { return -1;}

    }

    private int rightChild(int passedIndex) {
        passedIndex*=2;
        passedIndex+=1;
        if (passedIndex<size) {
            return passedIndex;
        } else { return -1;}

    }

    /*
     * getParentIndex() will be called in insert().  This function
     * will return an integer value representing the parent of the
     * new inserted element
     */
    private int getParentIndex(int passedIndex) {
        if (size == 2) { return 1; }
        else if (hasParent(passedIndex)) {
            if (passedIndex % 2 != 0) {
                passedIndex -= 1;
            } return (passedIndex/2);
        } else {return -1;}
    }

    /*
     * swapValues() will be called in insert() to move the newly
     * inserted element to the parent position if it is called
     *
     * *** Note, error check is not done in this function and will
     * rely on a check from insert()
     */
    private void swapValues(int parentIndex, int childIndex, double passedParent, double passedChild) {
        array[parentIndex] = passedChild;
        array[childIndex] = passedParent;
    }


    /*
     * Heapify()
     * In order to get heapify to run in time complexity O(N) we need to
     * only check parent nodes or non leaf index.  We can make this assumption
     * as the leaf index already have the heap property.
     *
     * Start by check the last non leaf-index which can be found by using
     * Check Node:
     * (n/2) -1
     * Then work our way up the tree in reverse order:
     */

    private void heapifyUp(int passedIndex) {
        int currentIndex = passedIndex;
        int parentIndex = getParentIndex(currentIndex);
        while ( parentIndex>0) {
            double parentValue = array[parentIndex];
            if (parentValue<array[currentIndex]) {
                swapValues(parentIndex, currentIndex, parentValue, array[currentIndex]);
                currentIndex = parentIndex;
                parentIndex = getParentIndex(parentIndex);
            } else { break; }
        }
    }

    private void heapifyDown(int passedIndex) {
        int currentIndex = passedIndex;
        while (getLargestChild(currentIndex)>0) {
            // gets the index
            int largestChild = getLargestChild(currentIndex);
            if (array[largestChild]> array[currentIndex]) {
                swapValues(currentIndex, largestChild, array[currentIndex], array[largestChild]);
                currentIndex = largestChild;
            } else { break; }
        }
    }


    /*
     * getLargestChild() takes in the current index when called, then makes calls to
     * get the children index values.
     * *** Note error checking if the current node has a child is done in getChild()
     * both functions will return a -1 int value if the current node doesn't have a
     * child that was asked for.  Which is why we run checks to ensure the index of the children
     * is greater than 0.
     *
     * Also we make the assumption that because this is a complete tree, meaning that values are
     * filled from left to right in sequential order if the current node does not have a left child,
     * it is then impossible to have a right child.
     *
     * This function will also follow the same convention of returning -1 if there are no child
     * nodes to compare
     */
    private int getLargestChild(int passedIndex) {
        int rightChild = rightChild(passedIndex);
        int leftChild = leftChild(passedIndex);
        if (leftChild>0) {
            if (rightChild>0) {
                if (array[leftChild]>array[rightChild]) {
                    return leftChild;
                } else { return rightChild; }
            } else {
                return leftChild;
            }
        } else { return -1; }
    }

    private void copyArrayToInternal(double[] passedArray) {
        for (int x=1; x<=passedArray.length; x++) {
            array[x] = passedArray[x-1];
        }
    }
}
