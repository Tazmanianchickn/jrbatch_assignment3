package MaxBinHeap_A3;

public interface Heap_Interface {
  /*
    Interface: The BHEAP will provide this collection of operations:

    insert
      in: a double (assume no duplicates will be inserted)
      return: void
    delMax
      in: nothing
      return: void
    getMax
      in: nothing
      return: a double
    size
      in: nothing
      return: integer 0 or greater
    clear
      in: nothing
      return: void
      this sets the heap to the state it has when first created... no elements
      in the heap array, size 0, next useable array slot is 1.
    build
      in: array of double that needs to be in the heap
      return: void
      (assume for a build that the bheap will start empty)
    sort
      in: array of double
      return: array of double
    getheap:
      in: nothing
      return: array of double (the internal array that holds the heap elements
      (this will be called by the grader to examine your data structure)
  */

    // ADT operations
    void insert(double element);
    void delMax();
    double getMax();
    void clear();
    int size();
    void build(double [] elements);
    double[] getHeap();
    double[] sort(double[] elements);
}